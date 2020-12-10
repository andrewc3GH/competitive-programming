'''
ID: cruzan1
LANG: PYTHON3
TASK: learning
'''

#need to only count if the potentially spotted cows are in the valid weight range

#input a list of all the weights and a dictionary with whether each cow is spotted or not (weight is the key, true = spotted, false = nonspotted)
#iterate through the list of all the weights and create intervels of spotted cows
#determine which intervals of spotted cows are in the valid weight range

def inPut():
  f = open('learning.in', 'r')
  cowStats = f.readline().strip().split()
  numCows = int(cowStats[0])
  startingWeight = int(cowStats[1])
  endWeight = int(cowStats[2])
  orderLst = []
  cowDict = {}
  for i in range(numCows):
    currentCow = f.readline().strip().split()
    if currentCow[0] == "S":
      cowDict[int(currentCow[1])] = True
    else:
      cowDict[int(currentCow[1])] = False
    orderLst.append(int(currentCow[1]))
  orderLst.sort()
  return startingWeight, endWeight, orderLst, cowDict


#add intervals for the beginning and end 
def buildIntervals(startingWeight, endWeight, orderLst, cowDict):
  spottedIntervals = []
  if cowDict[orderLst[0]] == True and orderLst[0] != 0:
    spottedIntervals.append([0, orderLst[0] - 1])
  if cowDict[orderLst[len(orderLst) - 1]] == True and endWeight >= orderLst[len(orderLst) - 1]:
    spottedIntervals.append([orderLst[len(orderLst) - 1], endWeight])
  for i in range(len(orderLst) - 1):
    currentInterval = []
    if cowDict[orderLst[i]] == True and cowDict[orderLst[i + 1]] == True:
      currentInterval.append(orderLst[i])
      currentInterval.append(orderLst[i + 1] - 1)
    elif cowDict[orderLst[i]] == True and cowDict[orderLst[i + 1]] == False:
      currentDivision = int((orderLst[i] + orderLst[i + 1]) / 2)
      currentInterval.append(orderLst[i])
      currentInterval.append(currentDivision)
    elif cowDict[orderLst[i]] == False and cowDict[orderLst[i + 1]] == True:
      currentDivision = int((orderLst[i] + orderLst[i + 1]) / 2)
      if (orderLst[i] + orderLst[i + 1]) % 2 != 0:
        currentInterval.append(currentDivision + 1)
      else:
        currentInterval.append(currentDivision)
      currentInterval.append(orderLst[i + 1] - 1)
    if len(currentInterval) > 0 and currentInterval[0] <= currentInterval[1] and currentInterval[0] <= endWeight and currentInterval[1] >= startingWeight:
      spottedIntervals.append(currentInterval)
  return spottedIntervals

def checkWeights(startingWeight, endWeight, spottedIntervals):
  totalSpotted = 0
  for i in range(len(spottedIntervals)):
    #if spottedIntervals[i][1] == math.inf:
     # if startingWeight <= spottedIntervals[i][0] <= endWeight:
      #  for k in range(int(endWeight - spottedIntervals[i][0]) + 1):
       #   totalSpotted += 1
    if spottedIntervals[i][1] <= endWeight and spottedIntervals[i][0] >= startingWeight:
      totalSpotted += int(spottedIntervals[i][1] - spottedIntervals[i][0]) + 1
    elif spottedIntervals[i][0] <= endWeight and spottedIntervals[i][1] >= startingWeight:
      for j in range(int(spottedIntervals[i][1] - spottedIntervals[i][0]) + 1):
        if startingWeight <= spottedIntervals[i][0] + j <= endWeight:
          totalSpotted += 1
        elif spottedIntervals[i][0] + j == endWeight:
          break
  return totalSpotted

var = inPut()
out = open('learning.out', 'w')
out.write(str(checkWeights(var[0], var[1], buildIntervals(var[0], var[1], var[2], var[3]))) + '\n')
out.close()

