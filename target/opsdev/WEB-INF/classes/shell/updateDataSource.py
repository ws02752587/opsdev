import sys
import re

argvs = list(sys.argv)
result = ''
dataSourceName=argvs[1]
name=argvs[2]
if not dataSourceName or not name:
    print "fail"
    return
path="/mobile/app/upload/"+name+"/WEB_INF/spring/spring-mybatis.xml"
with open(path) as file:
    result = file.read()

result = re.sub('<bean\\s+.*?org.apache.commons.dbcp.BasicDataSource.*?</bean>', "", result,flags=re.S)
jndiStr = re.findall('<!--\\s*(<bean\\s+.*?org.springframework.jndi.JndiObjectFactoryBean.*?)-->', result, flags=re.S)

if not jndiStr:
    jndiStr = """
    <bean id="dataSource" class="org.springframework.jndi.JndiObjectFactoryBean"> 
            <property name="jndiName"><value>"""+dataSourceName+"""</value> </property> </bean>
    """
else:
    jndiStr = jndiStr[0]
result = re.sub('<!--\\s*<bean\\s+.*?org.springframework.jndi.JndiObjectFactoryBean.*?-->', jndiStr, result,flags=re.S)

with open(path, "w", encoding="UTF-8") as file:
    file.write(result)
print "success"