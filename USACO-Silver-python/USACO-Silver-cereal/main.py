

f = open('cereal.in', 'r')
info = f.readline().strip().split()
numCows = int(info[0])
numCereals = int(info[1])
cowLst = []
for i in range(numCows):
  cowInfo = f.readline().strip().split()
  cowLst.append([int(cowInfo[0]), int(cowInfo[1])])
f.close()

cerealLst = []
for i in range(numCereals):
  cerealLst.append(-1)
cowNum = 0
for cow in cowLst:
  if cerealLst[cow[0] - 1] > cerealLst[cow[1] - 1] and cerealLst[cow[0] - 1] != -1:
    cerealLst[cow[1] - 1] = cerealLst[cow[0] - 1]
  cerealLst[cow[0] - 1] = cowNum
  cowNum += 1
cerealLst.sort()
for i in range(len(cerealLst)):
  if cerealLst[0] == -1:
    cerealLst.pop(0)
  else:
    break

numCerealsUsed = len(cerealLst)
index = 0
out = open('cereal.out', 'w+')
for i in range(len(cerealLst)):
  for j in range(index, cerealLst[i] + 1):
    out.write(str(numCerealsUsed) + '\n')
  numCerealsUsed -= 1
  index = cerealLst[i] + 1
out.close()