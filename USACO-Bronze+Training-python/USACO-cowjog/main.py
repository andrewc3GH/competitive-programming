'''
ID: cruzan1
LANG: PYTHON3
TASK: cowjog
'''

def inPut():
  f = open('cowjog.in', 'r')
  numCows = int(f.readline().strip())
  lstOfLsts = []
  for i in range(numCows):
    currentLst = []
    cowStats = f.readline().strip().split()
    currentLst.append(int(cowStats[0]))
    currentLst.append(int(cowStats[1]))
    lstOfLsts.append(currentLst)
  lstOfLsts[::-1]
  return numCows, lstOfLsts[::-1]

def calculate(numCows, lstOfLsts):
  currentLst = [0]
  counter = 0
  for i in range(1, numCows):
    beginningSpeedOfGroup = lstOfLsts[currentLst[0]][1]
    if lstOfLsts[i][1] > beginningSpeedOfGroup:
      currentLst.append(i)
    else:
      counter += 1
      currentLst = [i]
  if len(currentLst) > 0:
    counter += 1
  return counter

out = open('cowjog.out', 'w')
out.write(str(calculate(inPut()[0], inPut()[1])) + '\n')
out.close()