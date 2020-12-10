'''
ID: cruzan1
LANG: PYTHON3
TASK: marathon
'''

def inPut():
  f = open('marathon.in', 'r')
  numCheckpoints = int(f.readline().strip())
  overalLst = []
  for i in range(numCheckpoints):
    coordinateLst = []
    coordinatePair = f.readline().strip().split()
    coordinateLst.append(int(coordinatePair[0]))
    coordinateLst.append(int(coordinatePair[1]))
    overalLst.append(coordinateLst)
  return numCheckpoints, overalLst

def calculate(numCheckpoints, overalLst):
  if numCheckpoints == 3:
    return abs(overalLst[0][0] - overalLst[2][0]) + abs(overalLst[0][1] - overalLst[2][1])
  overallMax = 0
  currentMax = 0
  currentMaxMinusStarting = 0
  currentIndex = 0
  for i in range(1, numCheckpoints - 1):
    currentMax = abs(overalLst[i + 1][0] - overalLst[i - 1][0]) + abs(overalLst[i + 1][1] - overalLst[i - 1][1])
    currentMaxMinusStarting = abs(overalLst[i][0] - overalLst[i + 1][0]) + abs(overalLst[i][1] - overalLst[i + 1][1]) + abs(overalLst[i][0] - overalLst[i - 1][0]) + abs(overalLst[i][1] - overalLst[i - 1][1])
    currentMax = currentMaxMinusStarting - currentMax
    print("i: " + str(i))
    print("currentMax: " + str(currentMax))
    if currentMax > overallMax:
      overallMax = currentMax
      currentIndex = i
  print(currentIndex)
  tempLst = overalLst
  currentRemove = overalLst[currentIndex]
  tempLst.pop(currentIndex)
  counter = checkNewLst(tempLst)
  overalLst.insert(currentIndex, currentRemove)
  return counter

def checkNewLst(tempLst):
  counter = 0
  for j in range(len(tempLst) - 1):
    counter += abs(tempLst[j][0] - tempLst[j + 1][0]) + abs(tempLst[j][1] - tempLst[j + 1][1])
  return counter


out = open('marathon.out', 'w')
out.write(str(calculate(inPut()[0], inPut()[1])) + '\n')
out.close()
