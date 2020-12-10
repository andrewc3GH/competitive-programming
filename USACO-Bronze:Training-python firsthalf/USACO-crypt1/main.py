'''
ID: cruzan1
LANG: PYTHON3
TASK: crypt1
'''

f = open('crypt1.in', 'r')
numNumbers = int(f.readline().strip())
numLst = f.readline().strip().split()
f.close()
for i in range(len(numLst)):
  numLst[i] = int(numLst[i])
print(numLst)

counter = 0
for i in range(len(numLst)):
  for j in range(len(numLst)):
    for k in range(len(numLst)):
      for l in range(len(numLst)):
        for m in range(len(numLst)):
          add = True
          breakTime = True
          topNum = str(numLst[i]) + str(numLst[j]) + str(numLst[k])
          bottomNum = str(numLst[l]) + str(numLst[m])
          topSum = numLst[m] * int(topNum)
          if len(str(topSum)) == 3:
            for number in str(topSum):
              if int(number) not in numLst:
                add = False
          else:
            add = False
          bottomSum = numLst[l] * int(topNum)
          if len(str(bottomSum)) == 3:
            for number in str(bottomSum):
              if int(number) not in numLst:
                add = False
          else:
            add = False
          totalSum = (bottomSum * 10) + topSum
          if len(str(totalSum)) == 4:
            for number in str(totalSum):
              if int(number) not in numLst:
                add = False
          else:
            add = False
          if add == True:
            counter += 1

out = open('crypt1.out', 'w+')
out.write(str(counter) + '\n')
out.close()