import sys

argvs = list(sys.argv)
type = argvs[1]

def contains():
    key = argvs[2]
    result = argvs[3]
    if key in result:
        print "success"
    else:
        print "fail"

if type == "contains":
    contains()