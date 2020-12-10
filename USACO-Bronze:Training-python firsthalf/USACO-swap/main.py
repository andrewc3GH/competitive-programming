'''
ID: cruzan1
LANG: PYTHON3
TASK: swap
'''

f = open('swap.in', 'r')
info = f.readline().strip().split()
numCows = int(info[0])
numLoops = int(info[1])
aSwap = f.readline().strip().split()
bSwap = f.readline().strip().split()
aSwap[0] = int(aSwap[0])
aSwap[1] = int(aSwap[1])
bSwap[0] = int(bSwap[0])
bSwap[1] = int(bSwap[1])

cowLst = []
originalCowLst = []
for i in range(1, numCows + 1):
  cowLst.append(i)
for i in range(1, numCows + 1):
  originalCowLst.append(i)

numAswaps = int((aSwap[1] - aSwap[0])/2)
numBswaps = int((bSwap[1] - bSwap[0])/2)

numTimesToLoop = 0
counter = 0
while counter <= numLoops - 1:
  counter += 1
  for j in range(numAswaps + 1):
    smallSwap = cowLst[aSwap[0] + j - 1]
    cowLst[aSwap[0] + j - 1] = cowLst[aSwap[1] - j - 1]
    cowLst[aSwap[1] - j - 1] = smallSwap
  for j in range(numBswaps + 1):
    smallSwap = cowLst[bSwap[0] + j - 1]
    cowLst[bSwap[0] + j - 1] = cowLst[bSwap[1] - j - 1]
    cowLst[bSwap[1] - j - 1] = smallSwap
  if cowLst == originalCowLst:
    numTimestoLoop = counter
    if numTimestoLoop + counter <= numLoops:
      counter += int((numLoops - counter)/numTimestoLoop) * numTimestoLoop

out = open('swap.out', 'w+')
for i in range(len(cowLst)):
  out.write(str(cowLst[i]) + '\n')
out.close()
