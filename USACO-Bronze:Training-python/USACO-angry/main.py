'''
ID: cruzan1
LANG: PYTHON3
TASK: angry
'''

def inPut():
  f = open('angry.in', 'r')
  numBales = int(f.readline().strip())
  baleLst = []
  for i in range(numBales):
    baleLst.append(int(f.readline().strip()))
  baleLst.sort()
  return baleLst

#fix parameters for for loop to fit going backwards

  """def calculate(baleLst):
  maxBalesHit = 0
  print(baleLst)
  for i in range(len(baleLst)):
    minStarting = baleLst[i]
    maxStarting = baleLst[i]
    blastRadius = 1
    balesHit = 0
    maxRange = blastRadius + maxStarting
    minRange = minStarting - blastRadius
    print("i: " + str(baleLst[i]))
    while True:
      for j in range(i - 1, -1, -1):
        if minRange < baleLst[j]:
    print() #NEW FOR LOOPS FOR BOTH SIDES OF BALELST[i]
      for k in range(i + 1, len(baleLst)):
        if maxRange > baleLst[j]:
    for j in range(i, -1):
      if minRange <= baleLst[j] <= maxRange:
        balesHit += 1
        if baleLst[j] >= maxRange:
          maxRange = baleLst[j] + blastRadius
        elif baleLst[j] <= minRange:
          minRange = baleLst[j] - blastRadius
    if balesHit > maxBalesHit:
      maxBalesHit = balesHit
  return maxBalesHit"""
maxCount = 0
baleLst = inPut()
for j in range(len(baleLst)):
  currPos = baleLst[j]
  count = 1
  blastRadius = 1
  continueLeft = True
  while continueLeft == True:
    continueLeft = False
    newPos = currPos
    for i in range(currPos - 1, currPos - blastRadius - 1, -1):
      if i in baleLst:
        count += 1
        continueLeft = True
        newPos = i
    currPos = newPos
    blastRadius += 1
  continueRight = True
  currPos = baleLst[j]
  blastRadius = 1
  while continueRight == True:
    continueRight = False
    newPos = currPos
    for i in range(currPos + 1, currPos + blastRadius + 1):
      if i in baleLst:
        count += 1
        continueRight = True
        newPos = i
    currPos = newPos
    blastRadius += 1
  if count > maxCount:
    maxCount = count

out = open('angry.out', 'w')
out.write(str(maxCount) + '\n')
out.close()