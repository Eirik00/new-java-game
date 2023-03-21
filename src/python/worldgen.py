import numpy as np
import sys
from scipy.ndimage import zoom
from noise import pnoise2
from skimage.graph import route_through_array
from random import randint

np.set_printoptions(threshold=sys.maxsize)

def create_biome_map(shape, scale=0.1):
    perlin_noise = np.zeros(shape)

    for i in range(shape[0]):
        for j in range(shape[1]):
            x = i * scale
            y = j * scale
            perlin_noise[i, j] = pnoise2(x, y)

    return perlin_noise

def create_river_paths(shape, num_rivers=20):
    cost_map = np.ones(shape) * 1000
    river_map = np.zeros(shape, dtype=np.str_)
    river_map.fill('-')

    for _ in range(num_rivers):
        x1, y1 = randint(0, shape[0] - 1), randint(0, shape[1] - 1)
        x2, y2 = randint(0, shape[0] - 1), randint(0, shape[1] - 1)

        _, path = route_through_array(cost_map, (x1, y1), (x2, y2), fully_connected=True)

        if not isinstance(path, np.ndarray) or path.ndim == 0:
            continue

        for point in path:
            x, y = map(int, point)  # Cast the point to integers
            river_map[x, y] = 'r'
            cost_map[x, y] += 10000  # Update the cost map to increase the cost for river paths

    return river_map

def create_combined_map(size, river_map, mountain_map, forest_map):
    combined_map = []

    for y in range(size):
        newRow = []
        for x in range(size):
            if river_map[y][x] == "r":
                newRow.append(river_map[y][x])
            elif forest_map[y][x] == "A":
                newRow.append(forest_map[y][x])
            else:
                newRow.append(mountain_map[y][x])
        combined_map.append(newRow)

    return combined_map

def save_map_to_file(filename, map_data):
    with open(filename, "w") as file:
        for line in map_data:
            for s in line:
                file.write(s)
            file.write("\n")
    print("Worldmap Generated")

if __name__ == "__main__":
    size = 64
    biome_map = create_biome_map((size, size), scale=0.1)
    mountain_map = np.where(biome_map > 0.2, '#', '-')
    forest_map = np.where((biome_map <= 0.2) & (biome_map > -0.1), 'A', '-')
    river_map = create_river_paths((size, size), num_rivers=5)
    save_map_to_file("rivermap.txt", river_map)
    combined_map = create_combined_map(size, river_map, mountain_map, forest_map)
    save_map_to_file(sys.argv[1], combined_map)