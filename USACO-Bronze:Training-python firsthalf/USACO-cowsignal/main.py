'''
ID: cruzan1
LANG: PYTHON3
TASK: blocks
'''

#http://usaco.org/index.php?page=viewproblem2&cpid=665

""" inPut --> create list of lists for each row
calculate --> for each character in each list, create n times that character in a new list
calculate --> multiply each list vertically by n
outPut --> turn new list of lists into a string"""

def inPut():
  f = open('cowsignal.in', 'r')
  var = f.readline().strip().split()
  M = int(var[0])
  N = int(var[1])
  K = int(var[2])
  lstOfSignal = []
  for i in range(M):
    lstOfSignal.append(f.readline().strip())
  return [M, N, K, lstOfSignal]

def calculate(M, N, K, lstOfSignal):
  newLst = []
  for m in range(M):
    newStr = ""
    for char in lstOfSignal[m]:
      for k in range(K):
        newStr += char
    for k in range(K):
      newLst.append(newStr)  
  return newLst

def output(newLst):
  out = open('cowsignal.out', 'w')
  for sTr in newLst:
    out.write(sTr + '\n')
  out.close()

M = inPut()[0]
N = inPut()[1]
K = inPut()[2]
lstOfSignal = inPut()[3]
output(calculate(M, N, K, lstOfSignal))
