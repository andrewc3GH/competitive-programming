'''
ID: cruzan1
LANG: PYTHON3
TASK: lostcow
'''

# go through each distance expression and apply them to x
# see if the new value is > y if y > x
# see if the new value is < y if y < x
# count how many expressions you have gone through (= distance)
# HW: USACO 2015 December Contest, Bronze

def inPut():
  f = open('lostcow.in', 'r')
  var = f.readline().strip().split()
  start = int(var[0])
  end = int(var[1])
  return [start, end]

def calculate(start, end):
  distanceTravelled = 0
  currentPosition = start
  addValue = 1
  while True:
    if currentPosition == end:
      break
    elif start < end and currentPosition > end:
      distanceTravelled -= (currentPosition - end)
      break
    elif start > end and currentPosition < end:
      distanceTravelled -= abs(currentPosition - end)
      break
    else:
      distanceTravelled += abs(currentPosition - (start + addValue))
      currentPosition = start + addValue
      addValue *= -2
  return distanceTravelled

out = open('lostcow.out', 'w')
out.write(str(calculate(inPut()[0], inPut()[1])) + '\n')
out.close()