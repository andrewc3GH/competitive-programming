'''
ID: cruzan1
LANG: PYTHON3
TASK: paint
'''

def inPut():
  f = open('paint.in', 'r')
  var = f.readline().strip().split()
  a = int(var[0])
  b = int(var[1])
  var2 = f.readline().strip().split()
  c = int(var2[0])
  d = int(var2[1])
  return [a, b, c, d]

def calculate(a, b, c, d):
  abLst = []
  for i in range(a, b + 1):
    abLst.append(i)
  cdLst = []
  for j in range(c, d + 1):
    cdLst.append(j)
  totalLst = []
  counter = 0
  totalLst = abLst[:len(abLst) - 1]
  for i in cdLst[:len(cdLst) - 1]:
    if i in totalLst:
      pass
    else:
      totalLst.append(i)
  for i in range(len(totalLst)):
    counter += 1
  return counter

out = open('paint.out', 'w')
out.write(str(calculate(inPut()[0], inPut()[1], inPut()[2], inPut()[3])) + '\n')
out.close()