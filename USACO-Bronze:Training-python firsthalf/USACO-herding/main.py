'''
ID: cruzan1
LANG: PYTHON3
TASK: herding
'''

f = open('herding.in', 'r')
inPut = f.readline().strip().split()
if int(inPut[0]) + 2 == int(inPut[2]):
  minNum = 0
elif int(inPut[1]) + 2 == int(inPut[2]) or int(inPut[0]) + 2 == int(inPut[1]):
  minNum = 1
else:
  minNum = 2
if int(inPut[1]) - int(inPut[0]) > int(inPut[2]) - int(inPut[1]):
  maxNum = int(inPut[1]) - int(inPut[0]) - 1
else:
  maxNum = int(inPut[2]) - int(inPut[1]) - 1
out = open('herding.out', 'w')
out.write(str(minNum) + '\n')
out.write(str(maxNum) + '\n')
out.close()