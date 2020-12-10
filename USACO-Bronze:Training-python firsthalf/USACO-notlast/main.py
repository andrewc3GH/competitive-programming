'''
ID: cruzan1
LANG: PYTHON3
TASK: notlast
'''

#http://www.usaco.org/index.php?page=viewproblem2&cpid=687

import math

def inPut():
  f = open('notlast.in', 'r')
  N = int(f.readline().strip())
  logDict = {}
  nameLst = []
  for i in range(N):
    var = f.readline().strip().split()
    var1 = var[0]
    var2 = int(var[1])
    if var1 in nameLst:
      pass
    else:  
      nameLst.append(var1)
    if var1 in logDict:
      logDict[var1] += var2
    else:
      logDict[var1] = var2
  return [nameLst, logDict, N]

def calculator1(nameLst, logDict):
  minCowLst = []
  numLst = []
  num = logDict[nameLst[0]]
  counter = 0
  for i in range(len(logDict)):
    numLst.append(logDict[nameLst[i]])
    if logDict[nameLst[i]] == num:
      counter += 1
  if counter == len(numLst):
    return ["Tie"]
  minNum = math.inf
  for j in range(len(numLst)):
    if numLst[j] <= minNum:
      minNum = numLst[j]
  for i in range(len(logDict)):
    if logDict[nameLst[i]] == minNum:
      minCowLst.append(nameLst[i])
  if len(nameLst) < 7:
    return minCowLst
  else:
    secondLastCowLst = []
    oldMinNum = minNum
    minNum = math.inf
    for i in range(len(logDict)):
      if logDict[nameLst[i]] <= minNum and logDict[nameLst[i]] > oldMinNum:
        minNum = logDict[nameLst[i]]
    for j in range(len(logDict)):
      if logDict[nameLst[j]] == minNum:
        secondLastCowLst.append(nameLst[j])
    return secondLastCowLst

def output(secondLastCowLst):
  out = open('notlast.out', 'w')
  if len(secondLastCowLst) > 1:
    out.write("Tie" + '\n')
  else:
    out.write(secondLastCowLst[0] + '\n')
  out.close()

var = inPut()
var1 = var[0]
var2 = var[1]
N = var[2]
if N == 0:
  output(["Tie"])
elif len(var2) == 1:
  output(var1)
else:
  output(calculator1(var1, var2))