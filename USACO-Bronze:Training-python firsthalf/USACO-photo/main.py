'''
ID: cruzan1
LANG: PYTHON3
TASK: photo
'''

def inPut():
  f = open('photo.in', 'r')
  numAnswers = int(f.readline().strip())
  cowLst = f.readline().strip().split()
  for i in range(len(cowLst)):
    cowLst[i] = int(cowLst[i])
  return numAnswers, cowLst

def calculate(numAnswers, cowLst):
  for i in range(1, numAnswers + 1):
    currentLst = [i]
    for j in range(numAnswers - 1):
      currentLst.append(cowLst[j] - currentLst[j])
    for k in range(numAnswers):
      if (k + 1) not in currentLst:
        break
      if k == numAnswers - 1:
        return currentLst

out = open('photo.out', 'w')
var = calculate(inPut()[0], inPut()[1])
out.write(str(var[0]))
for i in range (1, len(var[0:])):
  out.write(" " + str(var[i]))
out.write('\n')
out.close()