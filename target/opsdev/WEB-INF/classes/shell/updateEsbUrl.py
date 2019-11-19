import sys

argvs = list(sys.argv)
result = []
ip=argvs[1]
port=argvs[2]
name=argvs[3]
if not ip or not port:
    print "fail"
    return
path="/mobile/app/upload/"+name+"/WEB_INF/classes/META-INF/conf.properties"
with open(path) as file:
    result = file.readlines

for index in range(len(result)):
    line = result[index]
    line = line.strip()
    if not line.startswith("#") and "ESB_SERVER_IP" in line:
        result[index] = "ESB_SERVER_IP="+ip+"\n"
    elif not line.startswith("#") and "ESB_SERVER_PORT" in line:
        result[index] = "ESB_SERVER_PORT="+port+"\n"

with open(path,"w",encoding="UTF-8") as file:
  file.write("".join(result))

print "success"