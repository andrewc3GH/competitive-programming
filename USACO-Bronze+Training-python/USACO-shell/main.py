'''
ID: cruzan1
LANG: PYTHON3
TASK: shell
'''

def inPut():
  f = open('shell.in', 'r')
  numSwaps = int(f.readline().strip())
  lstOfLsts = []
  guessLst = []
  for i in range(numSwaps):
    swap = f.readline().strip().split()
    currentSwap = [int(swap[0]), int(swap[1])]
    lstOfLsts.append(currentSwap)
    guessLst.append(int(swap[2]))
  return lstOfLsts, guessLst

def calculator(lstOfLsts, guessLst):
  currentOrder = []
  guessTotals = []
  for i in range(len(guessLst)):
    currentOrder.append(i + 1)
    guessTotals.append(0)
  for i in range(len(guessLst)):
    firstIndexSwap = lstOfLsts[i][0] - 1
    secondIndexSwap = lstOfLsts[i][1] - 1
    swapVar = currentOrder[firstIndexSwap]
    currentOrder[firstIndexSwap] = currentOrder[secondIndexSwap]
    currentOrder[secondIndexSwap] = swapVar
    guessTotals[currentOrder[guessLst[i] - 1] - 1] += 1
  maxNum = 0
  for num in guessTotals:
    if num > maxNum:
      maxNum = num
  return maxNum

inPut = inPut()
out = open('shell.out', 'w')
out.write(str(calculator(inPut[0], inPut[1])))
out.close()
