'''
ID: cruzan1
LANG: PYTHON3
TASK: cowtip
'''

""" flip function --> takes in row, column, and entireArray - flips the array based on the row and column values
check function --> takes in entireArray and returns whether the whole array is flipped or not
scan function --> takes in the entireArray and returns the row and column values that need to be flipped (starts scanning in the bottom right corner)
"""

#http://www.usaco.org/index.php?page=viewproblem2&cpid=689

def inPut():
  f = open('cowtip.in', 'r')
  N = int(f.readline().strip())
  entireArray = []
  for i in range(N):
    row = []
    var = f.readline().strip()
    var.split()
    for j in range(N):
      row.append(var[j])
    entireArray.append(row)
  return entireArray

def scan(entireArray):
  numFlips = 0
  for i in range(len(entireArray) - 1, -1, -1):
    for j in range(len(entireArray) - 1, -1, -1):
      if entireArray[i][j] == "1":
        entireArray = flip(entireArray, i, j)
        numFlips += 1
        if check(entireArray) == True:
          return numFlips  
  return numFlips 

def flip(entireArray, row, column):
  for i in range(row + 1):
    for j in range(column + 1):
      if entireArray[i][j] == "0":
        entireArray[i][j] = "1"
      else:
        entireArray[i][j] = "0"
  return entireArray

def check(entireArray):
  counter = 0
  for i in range(len(entireArray)):
    for j in range(len(entireArray)):
      if entireArray[i][j] == "0":
        counter += 1
  if counter == (len(entireArray)) * (len(entireArray)):
    return True
  return False

out = open('cowtip.out', 'w')
out.write(str(scan(inPut())) + '\n')
out.close()
