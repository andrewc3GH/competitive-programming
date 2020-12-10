'''
ID: cruzan1
LANG: PYTHON3
TASK: traffic
'''

def inPut():
  f = open('traffic.in', 'r')
  numMiles = int(f.readline().strip())
  lstOfLsts = []
  for i in range(numMiles):
    var = f.readline().strip().split()
    lstOfLsts.append([var[0], int(var[1]), int(var[2])])
  return lstOfLsts

def endRange(lstOfLsts):
  endRange = [0, 1000]
  for i in range(len(lstOfLsts)):
    if lstOfLsts[i][0] == "off":
      endRange = [endRange[0] - lstOfLsts[i][2], endRange[1] - lstOfLsts[i][1]]
      if endRange[0] < 0:
        endRange[0] = 0
      if endRange[1] < 0:
        endRange[1] = 0
    elif lstOfLsts[i][0] == "none":
      if lstOfLsts[i][1] > endRange[0]:
        endRange[0] = lstOfLsts[i][1]
      if lstOfLsts[i][2] < endRange[1]:
        endRange[1] = lstOfLsts[i][2]
    elif lstOfLsts[i][0] == "on":
      endRange = [endRange[0] + lstOfLsts[i][1], endRange[1] + lstOfLsts[i][2]]
      if endRange[0] > 1000:
        endRange[0] = 1000
      if endRange[1] > 1000:
        endRange[1] = 1000
  return endRange

def startRange(lstOfLsts):
  lstOfLsts.reverse()
  startRange = [0, 1000]
  for i in range(len(lstOfLsts)):
    if lstOfLsts[i][0] == "on":
      startRange = [startRange[0] - lstOfLsts[i][2], startRange[1] - lstOfLsts[i][1]]
      if startRange[0] < 0:
        startRange[0] = 0
      if startRange[1] < 0:
        startRange[1] = 0
    elif lstOfLsts[i][0] == "none":
      if lstOfLsts[i][1] > startRange[0]:
        startRange[0] = lstOfLsts[i][1]
      if lstOfLsts[i][2] < startRange[1]:
        startRange[1] = lstOfLsts[i][2]
    elif lstOfLsts[i][0] == "off":
      startRange = [startRange[0] + lstOfLsts[i][1], startRange[1] + lstOfLsts[i][2]]
      if startRange[0] > 1000:
        startRange[0] = 1000
      if startRange[1] > 1000:
        startRange[1] = 1000
  return startRange

out = open('traffic.out', 'w')
var1 = startRange(inPut())
var2 = endRange(inPut())
out.write(str(var1[0]) + " " + str(var1[1]) + '\n')
out.write(str(var2[0]) + " " + str(var2[1]) + '\n')
out.close()