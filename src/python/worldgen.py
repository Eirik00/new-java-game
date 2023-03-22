import numpy as np
import sys
import os
from scipy.ndimage import zoom
from skimage.graph import route_through_array
from random import randint


sys.stdout = os.fdopen(sys.stdout.fileno(), "w", 1)

np.set_printoptions(threshold=sys.maxsize)


def create_river_paths(shape, num_rivers=5, thickness=1):
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

        if not isinstance(_, list):
            continue

        for point in _:
            x, y = map(int, point)  # Cast the point to integers
            add_river(x, y)
            cost_map[x, y] += 10000  # Update the cost map to increase the cost for river paths

    return river_map


mountain = np.random.uniform(size=(4, 4))
mountain = zoom(mountain, 16)
mountain = mountain > 0.3
mountain = np.where(mountain, '-', '#')
mountain = np.array(mountain)
print(5)
sys.stdout.flush()

forest = np.random.uniform(size=(16, 16))
forest = zoom(forest, 4)
forest = forest > 0.5
forest = np.where(forest, '-', 'A')
forest = np.array(forest)
print(10)
sys.stdout.flush()

river_map = create_river_paths((64, 64), num_rivers=5, thickness=1)
print(15)
sys.stdout.flush()

mainMap = []

progress = 15
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
    progress +=1
    print(progress)
    sys.stdout.flush()
    y += 1
    mainMap.append(newRow)

mainMap = mainMap[:64]

print(80)
sys.stdout.flush()
file = open(sys.argv[1], "w")
print(90)
sys.stdout.flush()
for line in mainMap:
    for s in line:
        file.write(s)
    file.write("\n")
print(99)
sys.stdout.flush()
file.close()
print(100)
sys.stdout.flush()
print("EOF")
sys.stdout.flush()