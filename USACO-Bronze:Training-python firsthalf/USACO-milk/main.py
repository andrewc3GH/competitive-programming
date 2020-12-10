'''
ID: an.cruz1
LANG: PYTHON3
TASK: milk
'''
#inPut - add duplicate keys' costs
def inPut():
  f = open('milk.in', 'r')
  lineOne = f.readline().strip()
  unitsNeeded = lineOne.split()[0]
  numFarmers = lineOne.split()[1]
  farmersDict = {}
  for i in range(int(numFarmers)):
    comboVar = f.readline().strip().split()
    firstVar = int(comboVar[0])
    secondVar = int(comboVar[1])
    if firstVar in farmersDict:
      farmersDict[firstVar] += secondVar
    else:
      farmersDict[firstVar] = secondVar
  return [unitsNeeded, numFarmers, farmersDict]

def calculate(unitsNeeded, numFarmers, farmersDict):
  costList = list(farmersDict.keys())
  costList.sort()

  unitsOwned = 0
  cost = 0
  counter = 0
  while unitsOwned < int(unitsNeeded):
    minCost = int(costList[counter])
    if farmersDict[minCost] <= (int(unitsNeeded) - unitsOwned):
      unitsOwned += farmersDict[minCost]
      cost += (farmersDict[minCost] * minCost)
    else:
      units = (int(unitsNeeded) - unitsOwned)
      unitsOwned += units
      cost += minCost * units
    counter += 1

  return cost

def output(cost):
  out = open('milk.out', 'w')
  strCost = str(cost)
  out.write(strCost + '\n')
  out.close()

unitsNeeded = inPut()[0]
numFarmers = inPut()[1]
farmersDict = inPut()[2]
if unitsNeeded == "0" or numFarmers == "0":
  output(0)
else:
  output(calculate(unitsNeeded, numFarmers, farmersDict))