
f = open('sort.in', 'r')
numNums = int(f.readline().strip())
numLst = []
for i in range(numNums):
  numLst.append(int(f.readline().strip()))
oldIndex = {}
print(numLst)
for i in range(len(numLst)):
  if numLst[i] in oldIndex:
    pass
  else:
    oldIndex[numLst[i]] = i
print(oldIndex)
numLst.sort()
indexLst = []
for i in range(len(numLst)):
  indexLst.append(oldIndex[numLst[i]])
print(indexLst)
f.close()
maxCounter = 0
for i in range(len(numLst)):
  if indexLst[i] > i and indexLst[i] - i > maxCounter:
    maxCounter = indexLst[i] - i
print(numLst)
out = open('sort.out', 'w+')
out.write(str(maxCounter + 1) + '\n')
out.close()
