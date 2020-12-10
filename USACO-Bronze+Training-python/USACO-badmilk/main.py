'''
ID: cruzan1
LANG: PYTHON3
TASK: badmilk
'''

def inPut():
  f = open('badmilk.in', 'r')
  var = f.readline().strip().split()
  N = int(var[0])
  M = int(var[1])
  D = int(var[2])
  S = int(var[3])
  drinkingDict = {}
  for i in range(M):
    drinkingDict[i + 1] = []
  for i in range(D):
    varLst = []
    var = f.readline().strip().split()
    p = int(var[0])
    m = int(var[1])
    t = int(var[2])
    varLst.append(p)
    varLst.append(t)
    drinkingDict[m].append(varLst)
  sickDict = {}
  for i in range(S):
    var = f.readline().strip().split()
    p = int(var[0])
    t = int(var[1])
    sickDict[p] = t
  return drinkingDict, sickDict

def calculator(drinkingDict, sickDict):
  suspiciousMilks = set()
  for milkNum in drinkingDict:
    for i in range(len(drinkingDict[milkNum])):
      if drinkingDict[milkNum][i][0] in sickDict:
        if drinkingDict[milkNum][i][1] == sickDict[drinkingDict[milkNum][i][0]]:
          break
        if drinkingDict[milkNum][i][1] < sickDict[drinkingDict[milkNum][i][0]]:
          suspiciousMilks.add(milkNum)
  removeLst = []
  for milkNum in suspiciousMilks:
    sickLst = list(sickDict.keys())
    for lst in drinkingDict[milkNum]:
      if lst[0] in sickLst:
        sickLst.remove(lst[0])
    if len(sickLst) != 0:
      removeLst.append(milkNum)
  for num in removeLst:
    suspiciousMilks.remove(num)
  maxMilk = 0
  for milkNum in suspiciousMilks:
    counter = 0
    peopleCounted = []
    for i in range(len(drinkingDict[milkNum])):
      if drinkingDict[milkNum][i][0] in peopleCounted:
        pass
      else:
        counter += 1
        peopleCounted.append(drinkingDict[milkNum][i][0])
    if counter > maxMilk:
      maxMilk = counter
  return maxMilk

#check maximum length of values in drinking Dict for milks in suspicious milks to find max medicine needed

out = open('badmilk.out', 'w')
out.write(str(calculator(inPut()[0], inPut()[1])))
out.close()