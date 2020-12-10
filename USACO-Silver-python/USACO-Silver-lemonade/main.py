

f = open('lemonade.in', 'r')
numCows = int(f.readline().strip())
cowLst = f.readline().strip().split()
f.close()
for i in range(len(cowLst)):
  cowLst[i] = int(cowLst[i])
cowLst.sort()

counter = 0
for i in range(len(cowLst) - 1, -1, -1):
  if cowLst[i] < len(cowLst[(i + 1):]):
    counter = len(cowLst[(i + 1):])
    break
if counter == 0:
  counter = numCows
out = open('lemonade.out', 'w+')
out.write(str(counter) + '\n')
out.close()