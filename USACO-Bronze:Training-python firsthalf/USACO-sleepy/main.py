'''
ID: cruzan1
LANG: PYTHON3
TASK: sleepy
'''

f = open('sleepy.in', 'r')
numCows = int(f.readline().strip())
cowLst = []
allCows = f.readline().strip().split()
for i in range(numCows):
  cowLst.append(int(allCows[i]))
cowLst.reverse()
numMoves = 0
numInOrder = 0
for i in range(1, len(cowLst)):
  if cowLst[i] < cowLst[i - 1]:
    numMoves += 1
    numInOrder += 1
  else:
    numMoves += 1
    break
if numInOrder == numCows - 1:
  numMoves = numCows
numMoves = numCows - numMoves
out = open('sleepy.out', 'w')
out.write(str(numMoves) + '\n')
out.close()
  