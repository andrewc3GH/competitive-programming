'''
ID: cruzan1
LANG: PYTHON3
TASK: cowqueue
'''

# sort list of values
# call dictionary from sorted list

def inPut():
  f = open('cowqueue.in', 'r')
  N = int(f.readline().strip())
  valLst = []
  valDict = {}
  for i in range(N):
    var = f.readline().strip().split()
    var1 = int(var[0])
    if var1 not in valLst:
      valLst.append(var1)
    var2 = int(var[1])
    if var1 in valDict: 
      valDict[var1] += var2
    else:
      valDict[var1] = var2
  valLst.sort()
  return [valLst, valDict]

def calculate(valLst, valDict):
  currentTime = 0
  for i in range(len(valLst)):
    if i == 0:
      currentTime += valLst[i]
    if currentTime < valLst[i]:
      currentTime = valLst[i]
    currentTime += valDict[valLst[i]]
  return currentTime

out = open('cowqueue.out', 'w')
out.write(str(calculate(inPut()[0], inPut()[1])) + '\n')
out.close()