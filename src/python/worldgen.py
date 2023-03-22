import numpy as np
import sys
from scipy.ndimage import zoom
from skimage.graph import route_through_array
from random import randint

np.set_printoptions(threshold=sys.maxsize)


def create_river_paths(shape, num_rivers=20, thickness=1):
    cost_map = np.ones(shape) * 1000
    river_map = np.zeros(shape, dtype=np.str_)
    river_map.fill('-')

    def add_river(x, y):
        for dx in range(-thickness, thickness + 1):
            for dy in range(-thickness, thickness + 1):
                if 0 <= x + dx < shape[0] and 0 <= y + dy < shape[1]:
                    river_map[x + dx, y + dy] = 'r'

    for _ in range(num_rivers):
        x1, y1 = randint(0, shape[0] - 1), randint(0, shape[1] - 1)
        x2, y2 = randint(0, shape[0] - 1), randint(0, shape[1] - 1)

        _, path = route_through_array(cost_map, (x1, y1), (x2, y2), fully_connected=True)

        if not isinstance(path, np.ndarray) or path.ndim == 0:
            continue

        for point in path:
            x, y = map(int, point)  # Cast the point to integers
            add_river(x, y)
            cost_map[x, y] += 10000  # Update the cost map to increase the cost for river paths

    return river_map


mountain = np.random.uniform(size=(4, 4))
mountain = zoom(mountain, 16)
mountain = mountain > 0.3
mountain = np.where(mountain, '-', '#')
mountain = np.array(mountain)

forest = np.random.uniform(size=(16, 16))
forest = zoom(forest, 4)
forest = forest > 0.5
forest = np.where(forest, '-', 'A')
forest = np.array(forest)

river_map = create_river_paths((64, 64), num_rivers=5, thickness=1)

mainMap = []

y = 0
for row in forest:
    x = 0
    newRow = []
    for obj in row:
        if river_map[y][x] == "r":
            newRow.append("r")
        elif obj == "A":
            if mountain[y][x] == "-":
                newRow.append("A")
            else:
                newRow.append(mountain[y][x])
        else:
            newRow.append(mountain[y][x])
        x += 1
    y += 1
    mainMap.append(newRow)

mainMap = mainMap[:64]

file = open(sys.argv[1], "w")
for line in mainMap:
    for s in line:
        file.write(s)
    file.write("\n")
print("Worldmap Generated")
file.close()