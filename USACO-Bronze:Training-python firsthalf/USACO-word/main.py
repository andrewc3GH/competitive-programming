'''
ID: cruzan1
LANG: PYTHON3
TASK: word
'''

def inPut():
  f = open('word.in', 'r')
  var = f.readline().strip().split()
  numWords = int(var[0])
  charPerLine = int(var[1])
  sentence = f.readline().strip().split()
  return numWords, charPerLine, sentence

def calculate(numWords, charPerLine, sentence):
  newEssay = ""
  currentLength = 0
  for i in range(numWords):
    if len(sentence[i]) + currentLength <= charPerLine:
      if currentLength == 0:
        newEssay += sentence[i]
      else:
        newEssay += " " + sentence[i]
      currentLength += len(sentence[i])
    else:
      newEssay += '\n'
      currentLength = 0
      if len(sentence[i]) + currentLength <= charPerLine:
        if currentLength == 0:
          newEssay += sentence[i]
        else:
          newEssay += " " + sentence[i]
        currentLength += len(sentence[i])
  return newEssay

out = open('word.out', 'w')
out.write(calculate(inPut()[0], inPut()[1], inPut()[2]))
out.close()