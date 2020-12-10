

f = open('diamond.in', 'r')
info = f.readline().strip().split()
numDiamonds = int(info[0])
difference = int(info[1])

diamondLst = []
for i in range(numDiamonds): 
  diamondLst.append(int(f.readline().strip()))
diamondLst.sort()

pairingLst = []

"""
for i in range(len(diamondLst)):
  numPairings = 1
  if i >= len(diamondLst) - difference:
    for j in range(i + 1, len(diamondLst)):
      if abs(diamondLst[i] - diamondLst[j]) <= difference:
        numPairings += 1
      else:
        break
  else:
    for j in range(i + 1, len(diamondLst)):
      if abs(diamondLst[i] - diamondLst[j]) <= difference:
        numPairings += 1
      else:
        break
  pairingLst.append(numPairings)
"""
leftIndex = 0
rightIndex = 0
while leftIndex < len(diamondLst):
  if rightIndex <= len(diamondLst):
    for j in range(rightIndex, len(diamondLst)):
      if abs(diamondLst[rightIndex] - diamondLst[leftIndex]) > difference:
        break
      rightIndex += 1
  pairingLst.append(rightIndex - leftIndex)
  leftIndex += 1

print(diamondLst)
print(pairingLst)

#interval lengths of intervals that start at each index
#sliding window - maintain a left and a right pointer. As the left pointer increases, the right pointer can only increase.

currentMax = 0
maxLst = []
for i in range(len(pairingLst) - 1, -1, -1):
  if pairingLst[i] > currentMax:
      currentMax = pairingLst[i]
  maxLst.append(currentMax)
print(maxLst)

newMax = 0
for i in range(len(maxLst)):
  firstIntervalLen = pairingLst[i]
  firstIntervalIndex = i
  firstIntervalMax = pairingLst[i] + i - 1
  secondIntervalLen = maxLst[len(maxLst) - firstIntervalMax - 1]
  if (firstIntervalLen + secondIntervalLen) > newMax:
    newMax = (firstIntervalLen + secondIntervalLen)

if newMax > numDiamonds:
  newMax = numDiamonds

out = open('diamond.out', 'w+')
out.write(str(newMax) + '\n')
out.close()