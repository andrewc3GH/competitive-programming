'''
ID: cruzan1
LANG: PYTHON3
TASK: circlecross
'''

def inPut():
  f = open('circlecross.in', 'r')
  strLst = f.readline().strip()
  return list(strLst)

def calculate(strLst):
  counter = 0
  alreadyIn = []
  for i in range(51):
    currentLetterDict = {}
    j = i + 1
    while True:
      if strLst[i] == strLst[j]:
        break
      if strLst[j] not in currentLetterDict:
        currentLetterDict[strLst[j]] = 1
      else:
        currentLetterDict[strLst[j]] += 1
      j += 1
      if j == len(strLst):
        j = 0
    for key in currentLetterDict:
      if currentLetterDict[key] == 1 and [strLst[i], key] not in alreadyIn and [key, strLst[i]] not in alreadyIn:
        counter += 1
        alreadyIn.append([strLst[i], key])
        alreadyIn.append([key, strLst[i]])
  return counter
      
out = open('circlecross.out', 'w')
out.write(str(calculate(inPut())) + '\n')
out.close()