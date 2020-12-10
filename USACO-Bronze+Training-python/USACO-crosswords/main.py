'''
ID: cruzan1
LANG: PYTHON3
TASK: crosswords
'''

def inPut():
  f = open('crosswords.in', 'r')
  var = f.readline().strip().split()
  verticalRows = int(var[0])
  horizontalColumns = int(var[1])
  lstOfLsts = []
  for i in range(verticalRows):
    row = f.readline().strip()
    lstOfLsts.append(list(row))
  return verticalRows, horizontalColumns, lstOfLsts

def calculate(verticalRows, horizontalColumns, lstOfLsts):
  startingCoordinateLst = []
  horizontalCheckLst = []
  verticalCheckLst = []
  for i in range(1, len(lstOfLsts) + 1):
    for j in range(1, len(lstOfLsts[0]) + 1):
      currentCoordinates = [i, j]
      print("currentCoordinates: " + str(currentCoordinates))
      if currentCoordinates not in startingCoordinateLst and lstOfLsts[i - 1][j - 1] == ".":
        if currentCoordinates not in horizontalCheckLst and ((j - 1) < 1 or lstOfLsts[i - 1][j - 2] == "#"):
          goUpByIndex = 0
          while lstOfLsts[i - 1][j - 1 + goUpByIndex] == ".":
            goUpByIndex += 1
            if (j + goUpByIndex) > len(lstOfLsts[0]):
              break
          if goUpByIndex >= 3:
            startingCoordinateLst.append(currentCoordinates)
            for k in range(goUpByIndex):
              horizontalCheckLst.append([i, j + k])
              print("horizontally added " + str([i, j + k]))
        if currentCoordinates not in verticalCheckLst and currentCoordinates not in startingCoordinateLst and ((i - 1) < 1 or lstOfLsts[i - 2][j - 1] == "#"):
          goUpByIndex = 0
          while lstOfLsts[i - 1 + goUpByIndex][j - 1] == ".":
            goUpByIndex += 1
            if (i + goUpByIndex) > len(lstOfLsts):
              break
          if goUpByIndex >= 3:
            startingCoordinateLst.append(currentCoordinates)
            for l in range(goUpByIndex):
              verticalCheckLst.append([i + l, j])
              print("vertically added " + str([i + l, j]))
  return startingCoordinateLst

var = calculate(inPut()[0], inPut()[1], inPut()[2])
out = open('crosswords.out', 'w')
out.write(str(len(var)) + '\n')
for lst in var:
  out.write(str(lst[0]) + " " + str(lst[1]) + '\n')
out.close()