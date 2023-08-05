'''
ID: cruzan1
LANG: PYTHON3
TASK: hps
'''

#http://www.usaco.org/index.php?page=viewproblem2&cpid=688

def inPut():
  f = open('hps.in', 'r')
  N = int(f.readline().strip())
  matchLst = []
  for i in range(N):
    varLst = []
    var = f.readline().strip().split()
    varLst.append(int(var[0]))
    varLst.append(int(var[1]))
    if varLst[0] == varLst[1]:
      pass
    else:  
      matchLst.append(varLst)
  return matchLst

def calculator(matchLst):
  positiveMatchup = 0
  negativeMatchup = 0
  for i in range(len(matchLst)): #if 1 --> 2 --> 3 --> 1
    if (matchLst[i])[0] == 1:
      if (matchLst[i])[1] == 2:
        negativeMatchup += 1
    if (matchLst[i])[0] == 2:
      if (matchLst[i])[1] == 3:
        negativeMatchup += 1
    if (matchLst[i])[0] == 3:
      if (matchLst[i])[1] == 1:
        negativeMatchup += 1
  for i in range(len(matchLst)): #if 1 <-- 2 <-- 3 <-- 1
    if (matchLst[i])[0] == 1:
      if (matchLst[i])[1] == 3:
        positiveMatchup += 1
    if (matchLst[i])[0] == 2:
      if (matchLst[i])[1] == 1:
        positiveMatchup += 1
    if (matchLst[i])[0] == 3:
      if (matchLst[i])[1] == 2:
        positiveMatchup += 1
  if positiveMatchup > negativeMatchup:
    return positiveMatchup
  else:
    return negativeMatchup

def output(cow1wins):
  out = open('hps.out', 'w')
  out.write(str(cow1wins) + '\n')

output(calculator(inPut()))