f = open('closing.in', 'r')
info = f.readline().strip().split()
numPaths = int(info[1])
numBarnClosures = int(info[0])
pathDict = {}
openLst = []
for i in range(numPaths):
    pathInfo = f.readline().strip().split()
    if int(pathInfo[0]) not in pathDict:
        pathDict[int(pathInfo[0])] = {int(pathInfo[1])}
    else:
        pathDict[int(pathInfo[0])].add(int(pathInfo[1]))
    if int(pathInfo[1]) not in pathDict:
        pathDict[int(pathInfo[1])] = {int(pathInfo[0])}
    else:
        pathDict[int(pathInfo[1])].add(int(pathInfo[0]))

closedLst = []
for i in range(numBarnClosures):
    openLst.append(False)
    closedLst.append(int(f.readline().strip()))
numClosed = len(openLst)
f.close()
closedLst.reverse()

rootLst = []
indexDict = {}
numIslands = 0

def add(barnOpened): #closing all barns and then adding them with add function
  global numIslands
  indexDict[barnOpened] = len(rootLst)
  rootLst.append(len(rootLst))
  numIslands += 1

def getRoot(index):
  if rootLst[index] == index:
    return index
  rootLst[index] = getRoot(rootLst[index])
  return rootLst[index]

def same(point1, point2):
  return getRoot(point1) == getRoot(point2)

def merge(point1, point2):
  global numIslands
  if same(point1, point2) == False:
      numIslands -= 1
      rootLst[getRoot(point1)] = getRoot(point2)
      
def connected():
  global numIslands
  return numIslands == 1

ansLst = []
for i in range(numClosed):
    add(closedLst[i])
    for barn in (pathDict[closedLst[i]]):
        if barn in indexDict:
            merge(indexDict[closedLst[i]], indexDict[barn])
    if connected() == True:
        ansLst.append("YES")
    else:
        ansLst.append("NO")

ansLst.reverse()
out = open('closing.out', 'w+')
for i in range(len(ansLst)):
  out.write(str(ansLst[i]) + '\n')
out.close()

