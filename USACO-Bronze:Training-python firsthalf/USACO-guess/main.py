'''
ID: cruzan1
LANG: PYTHON3
TASK: guess
'''

f = open('guess.in', 'r')
numAnimals = int(f.readline().strip())
lstOfSets = []
for i in range(numAnimals):
  newSet = set()
  info = f.readline().strip().split()
  for j in range(int(info[1])):
    newSet.add(info[j + 2])
  lstOfSets.append(newSet)

maxIntersection = 0
for i in range(len(lstOfSets)):
  for j in range(len(lstOfSets)):
    if len(lstOfSets[i].intersection(lstOfSets[j])) > maxIntersection and i != j:
      maxIntersection = len(lstOfSets[i].intersection(lstOfSets[j]))
maxIntersection += 1

out = open('guess.out', 'w+')
out.write(str(maxIntersection) + '\n')
out.close()