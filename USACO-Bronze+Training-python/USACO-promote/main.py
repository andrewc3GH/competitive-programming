'''
ID: cruzan1
LANG: PYTHON3
TASK: promote
'''


f = open('promote.in', 'r')
bronze = f.readline().strip().split()
silver = f.readline().strip().split()
gold = f.readline().strip().split()
platinum = f.readline().strip().split() 
goldPromote = int(platinum[1]) - int(platinum[0])
silverPromote = (int(platinum[1]) + int(gold[1])) - (int(platinum[0]) + int(gold[0]))
bronzePromote = (int(platinum[1]) + int(gold[1]) + int(silver[1])) - (int(platinum[0]) + int(gold[0]) + int(silver[0]))
out = open('promote.out', 'w')
out.write(str(bronzePromote) + '\n' + str(silverPromote) + '\n' + str(goldPromote) + '\n')
out.close()
