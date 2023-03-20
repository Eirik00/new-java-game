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

def create_river_paths(shape, num_rivers=5):
    cost_map = np.ones(shape)
    river_map = np.zeros(shape, dtype=np.str)
    river_map.fill('-')

    for _ in range(num_rivers):
        x1, y1 = randint(0, shape[0] - 1), randint(0, shape[1] - 1)
        x2, y2 = randint(0, shape[0] - 1), randint(0, shape[1] - 1)

        _, path = route_through_array(cost_map, (x1, y1), (x2, y2), fully_connected=True)

        for point in path:
            river_map[point] = 'r'

    return river_map

# Biomes
biome_map = create_biome_map((64, 64), scale=0.1)

# Mountains
mountain = np.where(biome_map > 0.2, '#', '-')

# Forests
forest = np.where((biome_map <= 0.2) & (biome_map > -0.1), 'A', '-')

# Rivers
river = create_river_paths((64, 64), num_rivers=5)

# Combine maps
mainMap = []

for y in range(64):
    newRow = []
    for x in range(64):
        if river[y][x] == "r":
            newRow.append(river[y][x])
        elif forest[y][x] == "A":
            newRow.append(forest[y][x])
        else:
            newRow.append(mountain[y][x])
    mainMap.append(newRow)

file = open(sys.argv[1], "w")
for line in mainMap:
    for s in line:
        file.write(s)
    file.write("\n")
print("Worldmap Generated")
file.close()