'''
ID: cruzan1
LANG: PYTHON3
TASK: pails
'''

f = open('pails.in', 'r')
info = f.readline().strip().split()
f.close()
firstPail = int(info[0])
secondPail = int(info[1])
thirdPail = int(info[2])

if thirdPail % firstPail != 0:
  maxBuckets = int(thirdPail/firstPail) + 1
else:
  maxBuckets = int(thirdPail/firstPail)

if thirdPail % secondPail != 0:
  minBuckets = int(thirdPail/secondPail) + 1
else:
  minBuckets = int(thirdPail/secondPail)

closestValue = 0

for j in range(minBuckets, maxBuckets + 1):
  for i in range(j + 1):
    currentValue = (i * firstPail) + ((j - i) * secondPail)
    if currentValue <= thirdPail and thirdPail - currentValue < thirdPail - closestValue:
      closestValue = currentValue

out = open('pails.out', 'w+')
out.write(str(closestValue) + '\n')
out.close()
