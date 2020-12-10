'''
ID: cruzan1
LANG: PYTHON3
TASK: whereami
'''

#Misinterpreted the problem - At first, I thought the problem wanted you to find the unique strings and find the minimum length of these strings, but then I read over the problem again, and it said to instead find the smallest value of K for a string of length K that can be found with any consecutive string of length K in the total string.
#Switched code so now it processes the largest pair of same strings and returns the length of one of the strings plus 1 - There is still a bunch of useless code in the program that are finding the unique strings.

def inPut():
  f = open('whereami.in', 'r')
  numHouses = f.readline().strip()
  houseLetterLst = list(f.readline().strip())
  return houseLetterLst

def calculate(houseLetterLst):
  currentMin = len(houseLetterLst)
  longestLength = 0
  currentLength = 1
  allSet = set()
  for m in range(len(houseLetterLst)): #all letters in the lst
    allSet.add(houseLetterLst[m])
  for currentLength in range(1, len(houseLetterLst)): #changes the length of currentString
    for i in range(len(houseLetterLst) - currentLength + 1): #starts at every letter
      originalStr = ""
      for k in range(i, currentLength + i): #creating currentString
        if i + currentLength > len(houseLetterLst):
          break
        else:
          originalStr += houseLetterLst[k]
      for j in range(len(houseLetterLst) - currentLength + 1):
        counter = 0
        if i == j:
          pass
        else:
          compareStr = ""
          for l in range(j, currentLength + j):
            if j + currentLength > len(houseLetterLst):
              break
            else:
              compareStr += houseLetterLst[l]
          if originalStr == compareStr:
            counter = 1
            break
      if counter != 0:
        if len(originalStr) > longestLength:
          longestLength = len(originalStr)
    currentMin = longestLength + 1
  return currentMin

out = open('whereami.out', 'w')
out.write(str(calculate(inPut())) + '\n')
out.close()