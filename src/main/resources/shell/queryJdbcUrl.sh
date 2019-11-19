#!/bin/bash

if [ $0 != '10.240.92.138' ]; then
    ssh mobile@$0
fi

name=$1
cd /mobile/app/upload/$name/WEB_INF/spring
jndistr=`cat spring-mybatis.xml | grep org.springframework.jndi.JndiObjectFactoryBean`
jndiName=`cat spring-mybatis.xml | grep jndiName | awk -F\<value\> {'print $2'} | awk -F\< {'print $1'}`
cd ../classes/META-INF
jdbc=`cat jdbc.properties | grep jdbc:informix-sqli: | awk -F// '{print $2}' | awk -F/ '{print $1}'`
cd /mobile/app/devops/python
if [ $? -ne 0 ]; then
    echo "fail"
else
    export CLASSPATH="/weblogic/weblogic1036_64/wlserver_10.3/server/lib/weblogic.jar"
    /JAVA/jdk1.6.0_38/bin/java weblogic.WLST queryJdbcUrl.py "$0" "$jndistr" "$jdbc" "$jndiName"
fi

if [ $0 != '10.240.92.138' ]; then
    exit
fi