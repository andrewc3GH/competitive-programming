

f = open('moop.in', 'r')
numParticles = int(f.readline().strip())
particleLst = []
for i in range(numParticles):
  particleInfo = f.readline().strip().split()
  particleLst.append([int(particleInfo[0]), int(particleInfo[1])])
f.close()
particleLst.sort()


yLst = []
for i in range(len(particleLst)):
  yLst.append(particleLst[i][1])

minLst = []
maxLst = []
currentMin = 100000000000000000
currentMax = -100000000000000000
print(yLst)
for i in range(len(yLst)):
  if yLst[len(yLst) - i - 1] > currentMax:
    currentMax = yLst[len(yLst) - i - 1]
    maxLst.append(currentMax)
  else:
    maxLst.append(maxLst[i - 1])
  if yLst[i] < currentMin:
    currentMin = yLst[i]
    minLst.append(currentMin)
  else:
    minLst.append(minLst[i - 1])
print(particleLst)
print(maxLst)
print(minLst)
numComponents = 1
for i in range(len(particleLst) - 1):
  if minLst[i] > maxLst[len(particleLst) - i - 2]:
    numComponents += 1

out = open('moop.out', 'w+')
out.write(str(numComponents) + '\n')
out.close()
