#from itertools import combinations

f = open('cownomics.in', 'r')
info = f.readline().strip().split()
numCowsHalf = int(info[0])
numRows = int(info[1])
spottedCows = []
plainCows = []
for i in range(2 * numCowsHalf):
  if i + 1 > numCowsHalf:
    plainCows.append(f.readline().strip())
  else:
    spottedCows.append(f.readline().strip())

"""randomLst = []
for i in range(numRows):
  randomLst.append(i)
combLst = list(combinations(randomLst, 3))"""

numComb = 0
for i in range(numRows - 2):
  spottedSet = set()
  for spottedCow in spottedCows:
    spottedSet.add((spottedCow[i]))
  breakTime = True
  for plainCow in plainCows:
    if (plainCow[i]) in spottedSet:
      breakTime = False
      break
  if breakTime == False:
    pass
  else:
    numComb += int((numRows - i - 1) * (numRows - i - 2)/2)
    continue
  for j in range(i + 1, numRows - 1):
    spottedSet = set()
    for spottedCow in spottedCows:
      spottedSet.add((spottedCow[i], spottedCow[j]))
    breakTime = True
    for plainCow in plainCows:
      if (plainCow[i], plainCow[j]) in spottedSet:
        breakTime = False
        break
    if breakTime == False:
      pass
    else:
      numComb += numRows - j - 1
      continue
    for k in range(j + 1, numRows):
      """plainSet = set()
      for plainCow in plainCows:
        plainSet.add((plainCow[int(comb[0])], plainCow[int(comb[1])], plainCow[int(comb[2])]))"""
      spottedSet = set()
      for spottedCow in spottedCows:
        spottedSet.add((spottedCow[i], spottedCow[j], spottedCow[k]))
      breakTime = True
      for plainCow in plainCows:
        if (plainCow[i], plainCow[j], plainCow[k]) in spottedSet:
          breakTime = False
      if breakTime == True:
        numComb += 1
  

out = open('cownomics.out', 'w+')
out.write(str(numComb) + '\n')
out.close()
