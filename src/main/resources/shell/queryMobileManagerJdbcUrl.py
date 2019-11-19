import sys
import re

try:
    argvs = list(sys.argv)
    jndistr = argvs[2]
    if "<!--" in jndistr:
        isjndi = 0
    else:
        isjndi = 1
    if isjndi == 0:
        jdbc = argvs[3]
        print isjndi + ":" + jdbc
    else:
        weblogicip = argvs[1]
        user = "weblogic"
        pwd = "weblogic123"
        admin_url = "t3://"+weblogicip+":11070"
        connect(user, pwd, admin_url)
        jndiName=argvs[4]
        cd('serverConfig:/JDBCSystemResources/'+jndiName+'/JDBCResource/'+jndiName+'/JDBCDriverParams/'+jndiName)
        result = re.findall("-sqli://(.*?):(.*?)/", get('Url'))[0]
        ip = result[0]
        port = result[1]
        print isjndi + ":" + ip + ":" + port

        disconnect()
        exit()
except Exception as e:
    disconnect()
