

f = open('bcount.in', 'r')
info = f.readline().strip().split()
numCows = int(info[0])
numQ = int(info[1])

cowLst = []
num1 = 0
num2 = 0
num3 = 0
for i in range(numCows):
  newNum = int(f.readline().strip())
  if newNum == 1:
    num1 += 1
  elif newNum == 2:
    num2 += 1
  elif newNum == 3:
    num3 += 1
  cowLst.append([num1, num2, num3])

rangeLst = []
for i in range(numQ):
  rangeInfo = f.readline().strip().split()
  rangeLst.append([int(rangeInfo[0]), int(rangeInfo[1])])
f.close()

out = open('bcount.out', 'w+')
for i in range(numQ):
  highInfo = cowLst[rangeLst[i][1] - 1][:]
  if int(rangeLst[i][0]) > 1:
    lowInfo = cowLst[rangeLst[i][0] - 2]
    for j in range(len(highInfo)):
      highInfo[j] -= lowInfo[j]
  out.write(str(highInfo[0]) + " " + str(highInfo[1]) + " " + str(highInfo[2]) + '\n')
out.close()
