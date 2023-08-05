


class node:
  value = 0
  neighbors = [] #change to dictionary with video number --> score
  visited = False
  def __init__(self, children, vals):
    self.neighbors = children
    self.value = vals
  
  def visit(self):
    self.visited = True

  def isVisited(self):
    return self.visited
  
  def getNeighbors(self):
    return self.neighbors

f = open('mootube.in', 'r')
info = f.readline().strip().split()
numVideos = int(info[0])
videos = []
for i in range(numVideos):
  videos.append(node([], 0))
numQuestions = int(info[1])
overallLst = []
for i in range(numVideos - 1):
  lst = f.readline().strip().split()
  for j in range(len(lst)):
    lst[j] = int(lst[j])
  overallLst.append(lst)
print(overallLst)
