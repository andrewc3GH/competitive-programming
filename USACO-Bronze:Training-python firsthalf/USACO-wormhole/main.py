'''
ID: cruzan1
LANG: PYTHON3
TASK: wormhole
'''
from itertools import combinations
f = open('wormhole.in', 'r')
numWormholes = int(f.readline().strip())
wormHoleDict = {}
for i in range(numWormholes):
  info = f.readline().strip().split()
  wormHoleDict[i + 1] = ([int(info[0]), int(info[1])])

closeDict = {}
for wormhole in wormHoleDict:
  currentDistance = 10000000000000000000000000
  create = False
  for key in (wormHoleDict):
    # If wormhole and key have the same y value,
    # and key is not the same as wormhole
    # and the distance between key and wormhole is less than currentDistance
    # and key is to the right of wormhole
    if (wormHoleDict[wormhole][1] == wormHoleDict[key][1]) and key != wormhole and ((wormHoleDict[key])[0] - (wormHoleDict[wormhole])[0]) < currentDistance and (wormHoleDict[key])[0] > (wormHoleDict[wormhole])[0]:
      currentDistance = ((wormHoleDict[key])[0] - (wormHoleDict[wormhole])[0])
      currentKey = key
      create = True
  if create == True:
    closeDict[wormhole] = currentKey


newLst = []
for i in range(len(wormHoleDict)):
  newLst.append(i + 1)
comb = list(combinations(newLst, 2))
print(len(comb))

counter = []
lstOfGroups = []
def findPairs(group, i, usedHoles):
  counter.append("")
  if len(group) == numWormholes/2:
    lstOfGroups.append(group)
    return
  if i == len(comb):
    return
  findPairs(group.copy(), i + 1, usedHoles.copy())
  #for j in range(len(group)):
    #if comb[i][0] in group[j] or comb[i][1] in group[j]:
      #return
  oldLen = len(usedHoles)
  usedHoles.add(comb[i][0])
  usedHoles.add(comb[i][1])
  if len(usedHoles) - 2 != oldLen:
    return
  # go through the grouping, put all of the used wormholes in a set
  # add comb[i][0] and comb[i][1] to the set
  # if the size of the set did not increase by 2, then you can't use comb
  group.append(comb[i])
  findPairs(group.copy(), i + 1, usedHoles.copy())
findPairs([], 0, set())
print(len(lstOfGroups))
print(len(counter))
"""
counter = 0
for i in range(len(comb)):
  for j in range(len(comb)):
    if comb[i][0] not in comb[j] and comb[i][1] not in comb[j] and [comb[j], comb[i]] not in lstOfGroups and [comb[i], comb[j]] not in lstOfGroups:
      lstOfGroups.append([comb[i], comb[j]])
    counter += 1
"""

invertDict = {}
numPairings = 0
for key in wormHoleDict.copy():
  invertDict[str(wormHoleDict[key])] = key
for i in range(len(lstOfGroups)):
  pairingDict = {}
  for j in range(len(lstOfGroups[i])):
    pairingDict[lstOfGroups[i][j][0]] = lstOfGroups[i][j][1]
    pairingDict[lstOfGroups[i][j][1]] = lstOfGroups[i][j][0]
  for key in wormHoleDict:
    startPoint = key
    currentCoor = wormHoleDict[key]
    currentPoint = key
    breakTime = False
    while True:
      if currentPoint in closeDict:
        currentPoint = closeDict[currentPoint]
      else:
        break
      currentPoint = pairingDict[currentPoint]
      currentCoor = wormHoleDict[currentPoint]
      if currentPoint == startPoint:
        numPairings += 1
        breakTime = True
        break
    if breakTime == True:
      break
      
out = open('wormhole.out', 'w+')
out.write(str(numPairings) + '\n')  
out.close()


"""
closeDict = {}
for wormhole in wormHoleDict:
  currentDistance = 10000000000000000000000000
  create = False
  for key in (wormHoleDict):
    # If wormhole and key have the same y value,
    # and key is not the same as wormhole
    # and the distance between key and wormhole is less than currentDistance
    # and key is to the right of wormhole
    if (wormHoleDict[wormhole][1] == wormHoleDict[key][1]) and key != wormhole and ((wormHoleDict[key])[0] - (wormHoleDict[wormhole])[0]) < currentDistance and (wormHoleDict[key])[0] > (wormHoleDict[wormhole])[0]:
      currentDistance = ((wormHoleDict[key])[0] - (wormHoleDict[wormhole])[0])
      currentKey = key
      create = True
  if create == True:
    closeDict[wormhole] = currentKey

print(closeDict)

#go through every combination of pairings and simulate where the cow will go starting at point 1 (use a while loop)
#if you get back to where you started, add 1 to counter break out of the loop

yDict = {}
for key in (wormHoleDict):
  element = wormHoleDict[key]
  if element[1] in yDict:
    yDict[element[1]].append(element)
  else:
    yDict[element[1]] = [element]
for key in yDict:
  yDict[key] = sorted(yDict[key])
print(yDict)

for i in range(len(lstOfGroups)):
  for numHole in range(1, numWormholes + 1):
    currentPoint = []
    while currentPoint != yDict[wormHoleDict[numHole][1]][0]:
    if len(yDict[wormHoleDict[numHole][1]]) > 1:
      currentPoint = yDict[wormHoleDict[numHole][1]][1]


    

"""