import numpy as np
import sys
np.set_printoptions(threshold=sys.maxsize)
from scipy.ndimage import zoom
mountain = np.random.uniform(size=(8,8))
mountain = zoom(mountain, 8)
mountain = mountain > 0.5
mountain = np.where(mountain, '-', '#')
mountain = np.array(mountain)
#mountain = np.array_str(mountain, max_line_width=500)

forrest = np.random.uniform(size=(32,32))
forrest = zoom(forrest, 2)
forrest = forrest > 0.5
forrest = np.where(forrest, '-', 'A')
forrest = np.array(forrest)
#forrest = np.array_str(forrest, max_line_width=500)

mainMap = []

y = 0
for row in forrest:
    x = 0
    newRow = []
    for obj in row:
        match obj:
            case "A":
                if mountain[y][x] == "-":
                    newRow.append("A")
                else:
                    newRow.append(mountain[y][x])
            case "-":
                newRow.append(mountain[y][x])
        x+=1
    y+=1
    mainMap.append(newRow)

mainMap = mainMap[:64]


file = open(sys.argv[1], "w")
for line in mainMap:
    for s in line:
        file.write(s)
    file.write("\n")
print("Worldmap Generated")
file.close()

