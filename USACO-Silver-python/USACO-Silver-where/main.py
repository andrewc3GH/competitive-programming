from itertools import combinations

f = open('where.in', 'r')
sideLen = int(f.readline().strip())
lstOfLsts = []
for i in range(sideLen):
  lstOfLsts.append(f.readline().strip())
randomLst = []
for i in range(sideLen):
  for j in range(sideLen):
    randomLst.append([i, j])
combLst = list(combinations(randomLst, 2))

pclLst = []
counter = 0
for combination in combLst:
  if combination[0][0] > combination[1][0] or combination[0][1] > combination[1][1] or (combination[1][0] - combination[0][0] + 1) * (combination[1][1] - combination[0][1] + 1) <= 2:                    
    pass
  else:
    letterDict = {}
    indexDict = {}
    for i in range(combination[0][0], combination[1][0] + 1):
      for j in range(combination[0][1], combination[1][1] + 1):
        if lstOfLsts[i][j] not in letterDict:
          letterDict[lstOfLsts[i][j]] = 1
          indexDict[lstOfLsts[i][j]] = [[i, j]]
        else:
          letterDict[lstOfLsts[i][j]] += 1
          indexDict[lstOfLsts[i][j]].append([i, j])
    if len(letterDict) == 2:
      dictForLetters = {}
      visited = set()
      for i in range(combination[0][0], combination[1][0] + 1):
        for j in range(combination[0][1], combination[1][1] + 1):
          if tuple([i, j]) not in visited and (len(visited) != (combination[1][0] - combination[0][0] + 1) * (combination[1][1] - combination[0][1] + 1)):
            counter += 1
            queue = [[i, j]] #To determine if pcl, use breadth first search and count the number of regions (times to implement search)
            while len(queue) > 0:
              currentNum = lstOfLsts[queue[0][0]][queue[0][1]]
              currentCoor = [queue[0][0], queue[0][1]]
              visited.add(tuple(queue[0]))
              queue.remove(queue[0])
              for neighbor in ([currentCoor[0] - 1, currentCoor[1]], [currentCoor[0] + 1, currentCoor[1]], [currentCoor[0], currentCoor[1] - 1], [currentCoor[0], currentCoor[1] + 1]):
                if neighbor[0] >= combination[0][0] and neighbor[0] <= combination[1][0] and neighbor[1] >= combination[0][1] and neighbor[1] <= combination[1][1] and lstOfLsts[neighbor[0]][neighbor[1]] == currentNum and tuple((neighbor[0], neighbor[1])) not in visited:
                  queue.append(neighbor)
            if currentNum in dictForLetters:
              dictForLetters[currentNum] += 1
            else:
              dictForLetters[currentNum] = 1
      weirdLst = []
      for key in dictForLetters:
        weirdLst.append(dictForLetters[key])
      if (weirdLst[0] == 1 and weirdLst[1] > 1) or (weirdLst[1] == 1 and weirdLst[0] > 1):
        pclLst.append(combination)
areaDict = {}
areaLst = []
for i in range(len(pclLst)):
  if ((int(pclLst[i][1][0]) - int(pclLst[i][0][0]) + 1) * (int(pclLst[i][1][1]) - int(pclLst[i][0][1]) + 1)) not in areaDict:
    areaDict[((int(pclLst[i][1][0]) - int(pclLst[i][0][0]) + 1) * (int(pclLst[i][1][1]) - int(pclLst[i][0][1]) + 1))] = [i]
  else:
    areaDict[((int(pclLst[i][1][0]) - int(pclLst[i][0][0]) + 1) * (int(pclLst[i][1][1]) - int(pclLst[i][0][1]) + 1))].append(i)
  areaLst.append((int(pclLst[i][1][0]) - int(pclLst[i][0][0]) + 1) * (int(pclLst[i][1][1]) - int(pclLst[i][0][1]) + 1))
areaLst.sort()
pclCoorLst = []
counter = 0
for i in range(len(areaLst) - 1, -1, -1):
  indexOfPcl = areaDict[areaLst[i]][0]
  areaDict[areaLst[i]].pop(0)
  x1 = pclLst[indexOfPcl][0][0]
  x2 = pclLst[indexOfPcl][1][0]
  y1 = pclLst[indexOfPcl][0][1]
  y2 = pclLst[indexOfPcl][1][1]
  if len(pclCoorLst) > 0:
    count = 0
    for i in range(len(pclCoorLst)):
      if x1 >= pclCoorLst[i][0] and x2 <= pclCoorLst[i][1] and y1 >= pclCoorLst[i][2] and y2 <= pclCoorLst[i][3]:
        break
      else:
        count += 1
    if count == len(pclCoorLst):
      counter += 1
      pclCoorLst.append([x1, x2, y1, y2])
  else:
    counter += 1
    pclCoorLst.append([x1, x2, y1, y2])
  
out = open('where.out', 'w+')
out.write(str(counter) + '\n')
out.close()
#to check if pcl is in another pcl, sort list from greatest area to smallest area and determine if a later pcl (smaller) is in an earlier pcl (larger)