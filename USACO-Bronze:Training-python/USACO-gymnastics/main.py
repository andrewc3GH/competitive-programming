'''
ID: cruzan1
LANG: PYTHON3
TASK: gymnastics
'''

def inPut():
  f = open('gymnastics.in', 'r')
  var = f.readline().strip().split()
  practiceSessions = int(var[0])
  numCows = int(var[1])
  totalLst = []
  for i in range(practiceSessions):
    individualLst = []
    rowCows = f.readline().strip().split()
    for j in range(numCows):
      individualLst.append(int(rowCows[j]))
    totalLst.append(individualLst)
  return totalLst, numCows, practiceSessions

def calculate(totalLst, numCows, practiceSessions):
  count = 0
  for i in range (1, numCows + 1):
    for j in range (1, numCows + 1):
      if i != j:
        isConsistent = True
        for lst in totalLst:
          if lst.index(i) < lst.index(j):
            isConsistent = False
            break
        if isConsistent == True:
          count += 1
  return count

out = open('gymnastics.out', 'w')
out.write(str(calculate(inPut()[0], inPut()[1], inPut()[2])) + '\n')
out.close()