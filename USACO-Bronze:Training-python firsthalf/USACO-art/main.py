'''
ID: cruzan1
LANG: PYTHON3
TASK: art
'''

def inPut():
  f = open('art.in', 'r')
  numLen = int(f.readline().strip())
  lstOfStrs = []
  for i in range(numLen):
    row = f.readline().strip()
    newRow = []
    for j in range(len(row)):
      newRow.append(int(row[j]))
    lstOfStrs.append(newRow)
  return numLen, lstOfStrs

def createDimensions(numLen, lstOfStrs):
  colorSet = set()
  dimensionDict = {}
  for i in range(numLen):
    for j in range(numLen):
      if lstOfStrs[i][j] not in colorSet and lstOfStrs[i][j] != 0:
        colorSet.add(lstOfStrs[i][j])
        maxRow = 0
        minRow = numLen - 1
        maxCol = 0
        minCol = numLen - 1
        for k in range(numLen):
          for l in range(numLen):
            if lstOfStrs[k][l] == lstOfStrs[i][j]:
              if k > maxRow:
                maxRow = k
              if k < minRow:
                minRow = k
              if l > maxCol:
                maxCol = l
              if l < minCol:
                minCol = l
        dimensionDict[lstOfStrs[i][j]] = [maxCol, maxRow, minCol, minRow]
  return dimensionDict

def findOutliers(lstOfStrs, dimensionDict):
  counter = 0
  alreadyIn = set()
  for key in dimensionDict:
    keyOccurs = False
    for otherKey in dimensionDict:
      if key != otherKey:
        startCol = dimensionDict[otherKey][2]
        startRow = dimensionDict[otherKey][3]
        endCol = dimensionDict[otherKey][0]
        endRow = dimensionDict[otherKey][1]
        for i in range(startRow, endRow + 1):
          for j in range(startCol, endCol + 1):
            if lstOfStrs[i][j] == key:
              keyOccurs = True
    if keyOccurs == False and key not in alreadyIn:
      counter += 1
      alreadyIn.add(key)
  return counter

out = open('art.out', 'w')
out.write(str(findOutliers(inPut()[1], createDimensions(inPut()[0], inPut()[1]))) + '\n')
out.close()