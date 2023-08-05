'''
ID: cruzan1
LANG: PYTHON3
TASK: race
'''

def inPut():
  f = open('race.in', 'r')
  var = f.readline().strip().split()
  numMeters = int(var[0])
  numTrials = int(var[1])
  overallLst = []
  for i in range(numTrials):
    overallLst.append(int(f.readline().strip()))
  return numMeters, numTrials, overallLst

def calculate(numMeters, numTrials, minSpeed):
  lhst = 0
  maxSpeed = 1
  time = 0
  rhst = 0
  while True:
    lhst += maxSpeed
    time += 1
    if lhst + rhst >= numMeters:
      return time
    if maxSpeed >= minSpeed:
      rhst += maxSpeed
      time += 1
      if lhst + rhst >= numMeters:
        return time
    maxSpeed += 1
  return time

var = inPut()
print(var[2])
input()
out = open('race.out', 'w')
for i in range(var[1]):
  out.write(str(calculate(var[0], var[1], var[2][i])) + '\n')
out.close()