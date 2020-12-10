'''
ID: cruzan1
LANG: PYTHON3
TASK: triangles
'''

f = open('triangles.in', 'r')
numPoints = int(f.readline().strip())
coorLst = []
for i in range(numPoints):
  coor = f.readline().strip().split()
  coorLst.append([int(coor[0]), int(coor[1])])

maxArea = 0
for i in range(len(coorLst)):
  currentY = coorLst[i][1]
  currentX = coorLst[i][0]
  sameYpairs = []
  for j in range(len(coorLst)):
    if i != j and coorLst[j][1] == currentY:
      sameYpairs.append(coorLst[j])
  for k in range(len(sameYpairs)):
    newerX = sameYpairs[k][0]
    sameXpairs = []
    for l in range(len(coorLst)):
      if sameYpairs[k] != coorLst[l] and i != l and (coorLst[l][0] == newerX or coorLst[l][0] == currentX):
        sameXpairs.append(coorLst[l])
    for m in range(len(sameXpairs)):
      base = newerX - currentX
      height = sameXpairs[m][1] - currentY
      area = base * height / 2
      if area > maxArea:
        maxArea = area
      elif (-1 * area) > maxArea:
        maxArea = (-1 * area)

out = open('triangles.out', 'w+')
out.write(str(int(maxArea * 2)) + '\n')
out.close()

