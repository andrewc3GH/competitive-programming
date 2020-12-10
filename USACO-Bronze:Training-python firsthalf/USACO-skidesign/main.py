'''
ID: cruzan1
LANG: PYTHON3
TASK: skidesign
'''

f = open('skidesign.in', 'r')
numHills = int(f.readline().strip())
hillLst = []
for i in range(numHills):
  hillLst.append(int(f.readline().strip()))
hillLst.sort()
print(hillLst)
oldLst = hillLst.copy()
minCost = 1000000000000000000000000000
average = (hillLst[0] + hillLst[len(hillLst) - 1])/2
for i in range(16):
  hillLst = oldLst.copy()
  bottomRequirement = int(average - i)
  topRequirement = int(average + (16 - i))
  decimal = False
  if bottomRequirement % 1 != 0:
    bottomRequirement -= 0.5
    topRequirement += 0.5
    decimal = True

  numBottom = []
  travelledBottom = 0
  numTop = []
  travelledTop = 0
  for i in range(len(hillLst)):
    if hillLst[i] < bottomRequirement:
      numBottom.append(i)
      travelledBottom += bottomRequirement - hillLst[i]
      hillLst[i] = int(bottomRequirement)
    elif hillLst[i] > topRequirement:
      numTop.append(i)
      travelledTop += hillLst[i] - topRequirement
      hillLst[i] = int(topRequirement)
  if decimal == False:
    if travelledTop > travelledBottom:
      for index in numTop:
        hillLst[index] += 1
    else:
      for index in numBottom:
        hillLst[index] -= 1

  totalCost = 0
  for i in range(len(hillLst)):
    totalCost += abs(hillLst[i] - oldLst[i]) * abs(hillLst[i] - oldLst[i])
  if totalCost < minCost:
    minCost = totalCost

out = open('skidesign.out', 'w+')
out.write(str(minCost) + '\n')
out.close()