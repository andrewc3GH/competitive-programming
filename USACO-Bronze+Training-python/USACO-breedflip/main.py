'''
ID: cruzan1
LANG: PYTHON3
TASK: breedflip
'''

f = open('breedflip.in', 'r')
numCows = int(f.readline().strip())
correctOrder = list(f.readline().strip())
incorrectOrder = list(f.readline().strip())
lstCheck = []
for i in range(numCows):
  if correctOrder[i] == incorrectOrder[i]:
    lstCheck.append(True)
  else:
    lstCheck.append(False)

counter = 0
for i in range(numCows):
  if i != 0 and lstCheck[i] == False and lstCheck[i - 1] == False:
    pass
  elif lstCheck[i] == False:
    counter += 1

out = open('breedflip.out', 'w+')
out.write(str(counter) + '\n')
out.close()