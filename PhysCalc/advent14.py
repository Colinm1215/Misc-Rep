crs = open("bob.txt", "r")
bb = []
for row in crs:
    r = int(row)
    bb.append(r)
print len(bb)

ob = 0

dup = 0
gotFreq = []
dod = True
freqChange = 0
listChange = -1
while dod:
    listChange += 1
    print "list"
    for b in bb:
        freqChange += 1
        # print "freq"
        gotFreq.append(ob)
        ob += b
        if ob in gotFreq:
            dup = ob
            dod = False
            break

print listChange
print freqChange
print dup