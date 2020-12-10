'''
ID: cruzan1
LANG: PYTHON3
TASK: combo
'''

f = open('combo.in', 'r')
numDials = int(f.readline().strip())
farmerLock = f.readline().strip().split()
masterLock = f.readline().strip().split()
f.close()

for i in range(3):
  farmerLock[i] = int(farmerLock[i])
  masterLock[i] = int(masterLock[i])
print(farmerLock)
print(masterLock)
if numDials < 5:
  counter = 2*(numDials*numDials*numDials)
else:
  counter = 250
totalDifference = 1
use = False
for i in range(3):
  adding = abs(farmerLock[i] - masterLock[i])
  if abs((farmerLock[i] - numDials) - masterLock[i]) < adding:
    adding = abs((farmerLock[i] - numDials) - masterLock[i])
  if abs((masterLock[i] - numDials) - farmerLock[i]) < adding:
    adding = abs((masterLock[i] - numDials) - farmerLock[i])
  something = False
  if adding == 4:
    newadding = 1
    something = True
  if adding == 3:
    newadding = 2
    something = True
  if adding == 2:
    newadding = 3
    something = True
  if adding == 1:
    newadding = 4
    something = True
  if adding == 0:
    newadding = 5
    something = True
  if something == True:
    totalDifference *= newadding
    print(newadding)
    use = True
if use == True:
  counter -= totalDifference
if counter < 0:
  counter = 1
out = open('combo.out', 'w+')
out.write(str(counter) + '\n')
out.close()


