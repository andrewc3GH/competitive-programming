'''from random import*
ofil = open("lightson.in", 'w')
ofil.write('100 9999\n')
for i in range(100):
    for j in range(100):
        if i*j != 9801:
            ofil.write(str(i + 1) + ' ' + str(j + 1) + ' ' + str(i + 1 + int(j/99)) + ' ' + str((j + 2)%100) + '\n')
ofil.close()'''

f = open('lightson.in', 'r')
info = f.readline().strip().split()
roomLen = int(info[0])
numSwitches = int(info[1])

onOrOff = []

for i in range(roomLen):
  currentLst = []
  for j in range(roomLen):
    currentLst.append(False)
  onOrOff.append(currentLst)
onOrOff[0][0] = True

switchDict = {}
for i in range(numSwitches):
  switchInfo = f.readline().strip().split()
  if (int(switchInfo[0]), int(switchInfo[1])) not in switchDict:
     switchDict[int(switchInfo[0]), int(switchInfo[1])] = []
  switchDict[int(switchInfo[0]), int(switchInfo[1])].append([int(switchInfo[2]), int(switchInfo[3])])
f.close()
oldNumRooms = 1
counter = 1
kindOfTrueLst = []

while True:
  for key in switchDict:
    if onOrOff[key[0] - 1][key[1] - 1] == True:
      for element in switchDict[key]:
        xCorr = element[0]
        yCorr = element[1]
        if ((xCorr > 1 and onOrOff[xCorr - 2][yCorr - 1] == True) or (xCorr < roomLen and onOrOff[xCorr][yCorr - 1] == True) or (yCorr > 1 and onOrOff[xCorr - 1][yCorr - 2] == True) or (yCorr < roomLen and onOrOff[xCorr - 1][yCorr] == True)) and onOrOff[xCorr - 1][yCorr - 1] == False:
          counter += 1
          onOrOff[xCorr - 1][yCorr - 1] = True
        elif onOrOff[xCorr - 1][yCorr - 1] == False:
          counter += 1
          onOrOff[xCorr - 1][yCorr - 1] = "kindOfTrue" 
          kindOfTrueLst.append([xCorr, yCorr])
  for i in range(len(kindOfTrueLst)):
    xCorr = kindOfTrueLst[i][0]
    yCorr = kindOfTrueLst[i][1]
    if (xCorr > 1 and onOrOff[xCorr - 2][yCorr - 1] == True) or (xCorr < roomLen and onOrOff[xCorr][yCorr - 1] == True) or (yCorr > 1 and onOrOff[xCorr - 1][yCorr - 2] == True) or (yCorr < roomLen and onOrOff[xCorr - 1][yCorr] == True):
      onOrOff[xCorr - 1][yCorr - 1] = True
      #kindOfTrueLst.remove([xCorr, yCorr]) WHY IS IT CRASHING?
  if counter == oldNumRooms:
    break
  oldNumRooms = counter
print(onOrOff)
out = open('lightson.out', 'w+')
out.write(str(counter) + '\n')
out.close()

