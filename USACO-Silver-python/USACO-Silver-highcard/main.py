

f = open('highcard.in', 'r')
numCards = int(f.readline().strip())
cowDict = {}
for i in range(numCards * 2):
  cowDict[i + 1] = True
for i in range(numCards):
  cowDict[int(f.readline().strip())] = False

numNeeded = 0
numObtained = 0
numWins = 0
for i in range(numCards * 2):
  if cowDict[i + 1] == False:
    numNeeded += 1
  elif cowDict[i + 1] == True:
    if numNeeded == 0:
      numObtained += 1
    else:
      numNeeded -= 1
      numWins += 1

if numObtained > numNeeded:
  numWins += numObtained - numNeeded

out = open('highcard.out', 'w+')
out.write(str(numWins) + '\n')
out.close()
  
