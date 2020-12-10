'''
ID: cruzan1
LANG: PYTHON3
TASK: billboard
'''

def inPut():
  f = open('billboard.in', 'r')
  lawnmowerBoard = f.readline().strip().split()
  cowfeedBoard = f.readline().strip().split()
  cowfeedBoard = list(map(int, cowfeedBoard))
  lawnmowerBoard = list(map(int, lawnmowerBoard))
  return lawnmowerBoard, cowfeedBoard

def calculate(lawnmowerBoard, cowfeedBoard):
  area = (lawnmowerBoard[2] - lawnmowerBoard[0]) * (lawnmowerBoard[3] - lawnmowerBoard[1])
  lawnCorners = [(int(lawnmowerBoard[0]), int(lawnmowerBoard[1]))]
  lawnCorners.append((int(lawnmowerBoard[0]), int(lawnmowerBoard[3])))
  lawnCorners.append((int(lawnmowerBoard[2]), int(lawnmowerBoard[1])))
  lawnCorners.append((int(lawnmowerBoard[2]), int(lawnmowerBoard[3])))
  numCorners = 0
  for i in range(len(lawnCorners)):
    if cowfeedBoard[0] <= lawnCorners[i][0] and lawnCorners[i][0] <= cowfeedBoard[2] and cowfeedBoard[1] <= lawnCorners[i][1] and lawnCorners[i][1] <= cowfeedBoard[3]:
      numCorners += 1
  if numCorners == 1 or numCorners == 0:
    return area
  elif numCorners == 4:
    return 0
  elif numCorners == 2:
    intersectionCoor = []
    if cowfeedBoard[0] > lawnCorners[0][0]:
      intersectionCoor.append(cowfeedBoard[0])
    else:
      intersectionCoor.append(lawnCorners[0][0])
    if cowfeedBoard[1] > lawnCorners[0][1]:
      intersectionCoor.append(cowfeedBoard[1])
    else:
      intersectionCoor.append(lawnCorners[0][1])
    if cowfeedBoard[2] < lawnCorners[3][0]:
      intersectionCoor.append(cowfeedBoard[2])
    else:
      intersectionCoor.append(lawnCorners[3][0])
    if cowfeedBoard[3] < lawnCorners[3][1]:
      intersectionCoor.append(cowfeedBoard[3])
    else:
      intersectionCoor.append(lawnCorners[3][1])
    area -= (intersectionCoor[2] - intersectionCoor[0]) * (intersectionCoor[3] - intersectionCoor[1])
    return area

out = open('billboard.out', 'w')
out.write(str(calculate(inPut()[0], inPut()[1])))
out.close()