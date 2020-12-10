from itertools import combinations
'''
Store cows in a lst
[[1, 1], [3, 6], [10, 10], [5, 19]]

highestcow = [10, 10]
difference = 5

X

 X

 X
  X

   X
X

   X

       X
       
Brute Force:
find all 3-point combinations of the 16 important points
temporarily remove them from the list and check the new area
find the minimum area
'''
f = open('reduce.in', 'r')
numCows = int(f.readline().strip())
cowLst = []
for i in range(numCows):
  cowInfo = f.readline().strip().split()
  cowLst.append([int(cowInfo[0]), int(cowInfo[1])])

if numCows <= 16:
  newNew = cowLst.copy()
else:
  newNew = []
  counter = 0
  while counter < 4:
    greatestLeft = 100000000
    coorLeft = []
    greatestRight = -100000000
    coorRight = []
    greatestDown = 100000000
    coorDown = []
    greatestUp = -100000000
    coorUp = []
    for coordinate in (cowLst):
      if coordinate[0] < greatestLeft:
        greatestLeft = coordinate[0]
        coorLeft = coordinate
      if coordinate[0] > greatestRight:
        greatestRight = coordinate[0]
        coorRight = coordinate
      if coordinate[1] < greatestDown:
        greatestDown = coordinate[1]
        coorDown = coordinate
      if coordinate[1] > greatestUp:
        greatestUp = coordinate[1]
        coorUp = coordinate
    if coorLeft in cowLst:
      cowLst.remove(coorLeft)
      newNew.append(coorLeft)
    if coorRight in cowLst:
      cowLst.remove(coorRight)
      newNew.append(coorRight)
    if coorUp in cowLst:
      cowLst.remove(coorUp)
      newNew.append(coorUp)
    if coorDown in cowLst:
      cowLst.remove(coorDown)
      newNew.append(coorDown)
    counter += 1

newCowLst = combinations(newNew, 3)
minArea = 10000000000000000000
for combination in (newCowLst):
  tempCowLst = newNew.copy()
  for coordinate in combination:
    tempCowLst.remove(coordinate)
  greatestLeft = 100000000
  greatestRight = -100000000
  greatestDown = 100000000
  greatestUp = -100000000
  for coordinate in tempCowLst:
    if coordinate[0] < greatestLeft:
      greatestLeft = coordinate[0]
    if coordinate[0] > greatestRight:
      greatestRight = coordinate[0]
    if coordinate[1] < greatestDown:
      greatestDown = coordinate[1]
    if coordinate[1] > greatestUp:
      greatestUp = coordinate[1]
  newArea = (greatestRight - greatestLeft) * (greatestUp - greatestDown)
  if newArea < minArea:
    minArea = newArea

out = open('reduce.out', 'w+')
out.write(str(minArea) + '\n')
out.close()
