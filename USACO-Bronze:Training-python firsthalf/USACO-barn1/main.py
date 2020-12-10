"""Create an array, find (M - 1) largest gaps, and remove the end stall(s) of the array for as long as they don't contain cows
The gaps separate the minimum sections that should be blocked
Find the number of stalls that are blocked (total - (gap space + unblocked end stall(s)))"""

'''
ID: an.cruz1
LANG: PYTHON3
TASK: barn1
'''

def inPut():
  f = open('barn1.in', 'r')
  MSC = f.readline().strip().split()
  M = int(MSC[0])
  S = int(MSC[1])
  C = int(MSC[2])
  valueLst = []
  for i in range(C):
    valueLst.append(int(f.readline().strip()))
  valueLst.sort()
  return [M, S, C, valueLst]

def calculate(M, S, C, valueLst):
  gapLst = []
  for i in range(1, len(valueLst)):
    if valueLst[i] > (valueLst[i - 1] + 1):
      gapLst.append(valueLst[i] - valueLst[i - 1] - 1)
  gapLst.sort()
  gapLst.reverse()
  gapSpace = 0
  if len(gapLst) < (M - 1):
    for i in range(len(gapLst)):
      gapSpace += gapLst[i]
  else:
    for i in range(M - 1):
      gapSpace += gapLst[i]
  gapSpace += (valueLst[0] - 1)
  gapSpace += (S - (valueLst[len(valueLst) - 1] + 1))
  return gapSpace

def outPut(gapSpace, S):
  numBoarded = str((S - 1) - gapSpace)
  out = open('barn1.out', 'w')
  out.write(numBoarded + '\n')
  out.close()


M = inPut()[0]
S = inPut()[1]
C = inPut()[2]
valueLst = inPut()[3]
outPut(calculate(M, S, C, valueLst), S)