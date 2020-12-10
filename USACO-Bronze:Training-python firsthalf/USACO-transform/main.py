# (0,0) (0,1) (0,2)
# (1,0) (1,1) (1,2)
# (2,0) (2,1) (2,2)
#90 degree rotation: (row, column) => (column, n - 1 - row)
#180 degree rotation: (row, column) => 2(90 degree rotations)
#270 degree rotation: (row, column) => 3(90 degree rotations)
#horizontal reflection: (row, column) => (row, n - 1 - column)

#Take in first grid and output what it should look like after performing one of the above functions
#Compare this to the end grid

'''
ID: an.cruz1
LANG: PYTHON3
TASK: transform
'''

def inPut():
  f = open('transform.in', 'r')
  N = f.readline().strip()
  firstBox = []
  secondBox = []
  for i in range(int(N)):
    row = []
    for char in f.readline().strip():
      row.append(char)
    firstBox.append(row)
  for i in range(int(N)):
    row = []
    for char in f.readline().strip():
      row.append(char)
    secondBox.append(row)
  return [firstBox, secondBox, int(N)]

def rotation(firstBox, n):
  rotatedBox = []
  for i in range(n):
    row = []
    for j in range(n):
      row.append("+")
    rotatedBox.append(row)
  for i in range(n):
    for j in range(n):
      rotatedBox[i][j] = firstBox[n - 1 - j][i]
  return rotatedBox

def reflection(firstBox, n):
  reflectedBox = []
  for i in range(n):
    row = []
    for j in range(n):
      row.append("+")
    reflectedBox.append(row)
  for i in range(n):
    for j in range(n):
      reflectedBox[i][j] = firstBox[i][n - 1 - j]
  return reflectedBox

firstBox = inPut()[0]
secondBox = inPut()[1]
n = inPut()[2]
answer = ""

if rotation(firstBox, n) == secondBox:
  answer = "1"
elif rotation(rotation(firstBox, n), n) == secondBox:
  answer = "2"
elif rotation(rotation(rotation(firstBox, n), n), n) == secondBox:
  answer = "3"
elif reflection(firstBox, n) == secondBox:
  answer = "4"
elif rotation(reflection(firstBox, n), n) == secondBox or rotation(rotation(reflection(firstBox, n), n), n) == secondBox or rotation(rotation(rotation(reflection(firstBox, n), n), n), n) == secondBox:
  answer = "5"
elif firstBox == secondBox:
  answer = "6"
else:
  answer = "7"

out = open('transform.out', 'w')
out.write(answer + '\n')
out.close()