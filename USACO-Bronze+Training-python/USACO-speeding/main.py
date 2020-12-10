'''
ID: cruzan1
LANG: PYTHON3
TASK: paint
'''

def inPut():
  f = open('speeding.in', 'r')
  var = f.readline().strip().split()
  n = int(var[0])
  m = int(var[1])
  firstLst = []
  for i in range(n):
    varLst = []
    var = f.readline().strip().split()
    varLst.append(int(var[0]))
    varLst.append(int(var[1]))
    firstLst.append(varLst)
  secondLst = []
  for i in range(m):
    varLst = []
    var = f.readline().strip().split()
    varLst.append(int(var[0]))
    varLst.append(int(var[1]))
    secondLst.append(varLst)
  return [firstLst, secondLst]

def calculator(firstLst, secondLst):
  maxOver = 0
  firstArray = []
  for i in range(len(firstLst)):
    for j in range(firstLst[i][0]):
      firstArray.append(firstLst[i][1])
  secondArray = []
  for i in range(len(secondLst)):
    for j in range(secondLst[i][0]):
      secondArray.append(secondLst[i][1])
  for i in range(len(firstArray)):
    if secondArray[i] > firstArray[i]:
      if secondArray[i] - firstArray[i] > maxOver:
        maxOver = secondArray[i] - firstArray[i]
      else:
        pass
    else:
      pass
  return maxOver

inPutLst = inPut()
out = open('speeding.out', 'w')
out.write(str(calculator(inPutLst[0], inPutLst[1])))
out.close()