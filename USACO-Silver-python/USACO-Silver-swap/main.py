'''
ID: cruzan1
LANG: PYTHON3
TASK: swap
'''

f = open('swap.in', 'r')
info = f.readline().strip().split()
numCows = int(info[0])
numRoutines = int(info[1])
numLoops = int(info[2])
swapLst = []
for i in range(numRoutines):
  newInfo = f.readline().strip().split()
  swapLst.append([int(newInfo[0]), int(newInfo[1])])

cowLst = []
originalCowLst = []
for i in range(1, numCows + 1):
  cowLst.append(i)
for i in range(1, numCows + 1):
  originalCowLst.append(i)

differentCombos = []

numTimesToLoop = 0
counter = 0
while counter <= numLoops - 1:
  counter += 1
  for i in range(len(swapLst)):
    for j in range(int((swapLst[i][1] - swapLst[i][0] + 1)/2)):
      smallSwap = cowLst[swapLst[i][0] + j - 1]
      cowLst[swapLst[i][0] + j - 1] = cowLst[swapLst[i][1] - j - 1]
      cowLst[swapLst[i][1] - j - 1] = smallSwap
  if cowLst == originalCowLst or cowLst in differentCombos:
    numTimestoLoop = counter
    if numTimestoLoop + counter <= numLoops:
      counter += int((numLoops - counter)/numTimestoLoop) * numTimestoLoop
  differentCombos.append(cowLst)

out = open('swap.out', 'w+')
for i in range(len(cowLst)):
  out.write(str(cowLst[i]) + '\n')
out.close()
