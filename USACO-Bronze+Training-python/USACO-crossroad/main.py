'''
ID: cruzan1
LANG: PYTHON3
TASK: crossroad
'''

#go through all the observations 
#create a dictionary with a key for each cow - fill it with default values
#change values in the dictionary based on the observations
#if current value in dictionary is a default value, change it to the correct value but don't add one to counter
#if current value in dictionary is equal to the correct value, don't do anything
#if current value in dictionary is different from the correct value, change it to the correct value and add one to counter

f = open('crossroad.in', 'r')
overallDictionary = {}
for i in range(1, 11):
  overallDictionary[i] = -1
numObservations = int(f.readline().strip())
totalCrosses = 0
for j in range(numObservations):
  cowStats = f.readline().strip().split()
  cowId = int(cowStats[0])
  cowLocation = int(cowStats[1])
  if overallDictionary[cowId] == -1:
    overallDictionary[cowId] = cowLocation
  elif overallDictionary[cowId] != cowLocation:
    overallDictionary[cowId] = cowLocation
    totalCrosses += 1

out = open('crossroad.out', 'w')
out.write(str(totalCrosses) + '\n')
out.close()