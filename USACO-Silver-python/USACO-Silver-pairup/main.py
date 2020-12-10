

f = open('pairup.in', 'r')
N = int(f.readline().strip())

cowDict = {}
cowLst = []
numCows = 0
for i in range(N):
  info = f.readline().strip().split()
  cowDict[int(info[1])] = int(info[0])
  cowLst.append(int(info[1]))
  numCows += int(info[0])

cowLst.sort()
print(cowLst)

totalMax = 0
counterLeft = 0
counterRight = len(cowLst) - 1
while counterLeft < counterRight:
  print("counterLeft: " + str(counterLeft))
  print("counterRight: " + str(counterRight))
  if cowLst[counterLeft] + cowLst[counterRight] > totalMax:
    totalMax = cowLst[counterLeft] + cowLst[counterRight]
  cowDict[cowLst[counterLeft]] -= 1
  counterLeft += 1
  adding = cowDict[cowLst[counterLeft - 1]]
  inLoop = False
  while adding > 0:
    inLoop = True
    if cowDict[cowLst[counterRight]] <= adding:
      adding -= cowDict[cowLst[counterRight]]
      counterRight -= 1
    else:
      cowDict[cowLst[counterRight]] -= adding
      break
  if inLoop == False:
    counterRight -= 1


out = open('pairup.out', 'w+')
out.write(str(totalMax) + '\n')
out.close()
  