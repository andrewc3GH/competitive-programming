

f = open('socdist.in', 'r')
info = f.readline().strip().split()
numCows = int(info[0])
numIntervals = int(info[1])
intervalLst = []
for i in range(numIntervals):
  interval = f.readline().strip().split()
  intervalLst.append([int(interval[0]), int(interval[1])])
intervalLst.sort()

"""emptySpace = 0
for i in range(len(intervalLst) - 1):
  emptySpace += intervalLst[i + 1][0] - intervalLst[i][1] - 1
  print(emptySpace)
print(emptySpace)"""

startingNum = intervalLst[0][0]
endingNum = intervalLst[-1][1]

def findTrue(d):
  oldNum = startingNum - d
  numUsed = 0
  currentNum = startingNum
  window = 0
  while window < len(intervalLst):
    if currentNum - d >= oldNum:
      if currentNum >= intervalLst[window][0] and currentNum <= intervalLst[window][1]:
        oldNum = currentNum
        numUsed += 1
        currentNum += d
      elif currentNum > intervalLst[window][1]:
        window += 1
      elif currentNum < intervalLst[window][0]:
        currentNum = intervalLst[window][0]
    else:
      currentNum += 1
  if numUsed >= numCows:
    return True
  else:
    return False

endTest = int((intervalLst[-1][1] - intervalLst[0][0] + 1)/(numCows - 1)) + 1
startTest = 1
while endTest - startTest > 1:
  midD = int((endTest + startTest)/2)
  if findTrue(midD) == True:
    startTest = midD
    oldD = midD
  else:
    endTest = midD

out = open('socdist.out', 'w+')
out.write(str(oldD) + '\n')
out.close()


