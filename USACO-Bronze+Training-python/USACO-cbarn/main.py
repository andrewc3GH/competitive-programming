'''
ID: cruzan1
LANG: PYTHON3
TASK: cbarn
'''

f = open('cbarn.in', 'r')
numRooms = int(f.readline().strip())
roomLst = []
for i in range(numRooms):
  roomLst.append(int(f.readline().strip()))

minNum = 100000000000000000000000
for i in range(len(roomLst)):
  currentNum = 0
  newLst = []
  currentIndex = 0
  for j in range(i + 1, i + len(roomLst)):
    if j >= len(roomLst):
      currentIndex = j - len(roomLst)
    else:
      currentIndex = j    
    newLst.append(roomLst[currentIndex])
  counterNum = 0
  for k in range(counterNum, len(newLst)):
    currentNum += (newLst[k] * (counterNum + 1))
    counterNum += 1
  if currentNum < minNum:
    minNum = currentNum

out = open('cbarn.out', 'w+')
out.write(str(minNum) + '\n')
out.close()