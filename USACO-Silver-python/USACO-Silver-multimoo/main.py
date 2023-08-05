

f = open('multimoo.in', 'r')
sideLen = int(f.readline().strip())
cowSet = set()
boardLst = []
for i in range(sideLen):
  currentLst = f.readline().strip().split()
  for j in range(len(currentLst)):
    currentLst[j] = int(currentLst[j])
    cowSet.add(currentLst[j])
  boardLst.append(currentLst)
print(boardLst)
print(cowSet)
cowGreatestPoints = {}
for element in cowSet:
  cowGreatestPoints[element] = 0
for i in range(sideLen):
  for j in range(sideLen):
    index = 0
    visited = set()
    queue = [[j, i]]
    while index < sideLen * sideLen and len(queue) > 0:
      currentPoint = queue[0]
      queue.remove(currentPoint)
      visited.add(tuple(currentPoint))
      for element in [[currentPoint[0] + 1, currentPoint[1]], [currentPoint[0] - 1, currentPoint[1]], [currentPoint[0], currentPoint[1] + 1], [currentPoint[0], currentPoint[1] - 1]]:
        if element[0] <= sideLen - 1 and element[0] >= 0 and element[1] <= sideLen - 1 and element[1] >= 0 and boardLst[element[0]][element[1]] == boardLst[currentPoint[0]][currentPoint[1]]:
          queue.append([element[0], element[1]])
          if boardLst[i][j] == 7:
            print(element)
      index += 1
    if boardLst[i][j] == 7:
      print(visited)
    if len(visited) > cowGreatestPoints[boardLst[i][j]]:
      cowGreatestPoints[boardLst[i][j]] = len(visited)
    print(cowGreatestPoints)
