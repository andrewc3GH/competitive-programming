'''
ID: cruzan1
LANG: PYTHON3
TASK: revegetate
'''

def inPut():
  f = open('revegetate.in', 'r')
  var = f.readline().strip().split()
  numFields = int(var[0])
  numCows = int(var[1])
  favoriteDict = {}
  for i in range(numFields):
    favoriteDict[i + 1] = []
  for i in range(numCows):
    newVar = f.readline().strip().split()
    newVar[0] = int(newVar[0])
    newVar[1] = int(newVar[1])
    favoriteDict[newVar[0]] += [newVar[1]]
    favoriteDict[newVar[1]] += [newVar[0]] 
  return favoriteDict

def calculate(favoriteDict):
  plantingSpot = []
  for i in range(len(favoriteDict)):
    plantingSpot.append(0)
  for key in favoriteDict:
    availableOptions = [1, 2, 3, 4]
    for num in (favoriteDict[key]):
      if num < key and plantingSpot[num - 1] in availableOptions:
        availableOptions.remove(plantingSpot[num - 1])
    plantingSpot[key - 1] = availableOptions[0]
  return plantingSpot

out = open('revegetate.out', 'w')  
for i in range(len(calculate(inPut()))):
  out.write(str(calculate(inPut())[i]))
out.write('\n')
out.close()