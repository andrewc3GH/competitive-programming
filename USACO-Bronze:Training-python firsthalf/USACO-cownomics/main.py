'''
ID: cruzan1
LANG: PYTHON3
TASK: cownomics
'''

#input as list of strings (2 lists - one for spotted and one for plain)
#create two sets of all the letters in a single row of the plain cows and spotted cows
#check if the corresponding row of the spotted cows contains any of these letters
#if so, do not add to counter
#if not, add one to counter
#use intersection for sets to compare the letters

def inPut():
  f = open('cownomics.in', 'r')
  cowStats = f.readline().strip().split()
  numRowsForEach = int(cowStats[0])
  numColumns = int(cowStats[1])
  spottedCowLst = []
  plainCowLst = []
  for i in range(2):
    for j in range(numRowsForEach):
      if i == 0:
        spottedCowLst.append(list(f.readline().strip()))
      else:
        plainCowLst.append(list(f.readline().strip()))
  return numRowsForEach, numColumns, spottedCowLst, plainCowLst

def calculate(numRowsForEach, numColumns, spottedCowLst, plainCowLst):
  counter = 0
  for i in range(numColumns):
    spottedSet = set()
    plainSet = set()
    newLst = set()
    for j in range(numRowsForEach):
      spottedSet.add(spottedCowLst[j][i])
      plainSet.add(plainCowLst[j][i])
    newLst = spottedSet.intersection(plainSet)
    if len(newLst) == 0:
      counter += 1  
  return counter

out = open('cownomics.out', 'w')
out.write(str(calculate(inPut()[0], inPut()[1], inPut()[2], inPut()[3])))
out.close()