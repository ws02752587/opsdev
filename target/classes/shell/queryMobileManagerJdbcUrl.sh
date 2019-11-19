#!/bin/bash

if [ $0 != '10.240.92.138' ]; then
    ssh mobile@$0
fi

name=$1
cd /mobile/app/upload/$name/WEB_INF/classes/spring
jndistr=`cat applicationContext-ibatis.xml | grep dataSourceFrom | grep org.springframework.jndi.JndiObjectFactoryBean`
jdbc=`cat applicationContext-ibatis.xml | grep jdbc:informix-sqli: | head -n 1 | awk -F// '{print $2}' | awk -F/ '{print $1}'`
jndiName=`cat applicationContext-ibatis.xml | grep jndiName\"\>\<value | head -n 1 | awk -F\<value\> '{print $2}' | awk -F\< '{print $1}'`
cd /mobile/app/devops/python
if [ $? -ne 0 ]; then
    echo "fail"
else
    export CLASSPATH="/weblogic/weblogic1036_64/wlserver_10.3/server/lib/weblogic.jar"
    /JAVA/jdk1.6.0_38/bin/java weblogic.WLST queryMobileManagerJdbcUrl.py "$0" "$jndistr" "$jdbc" "$jndiName"
fi

if [ $0 != '10.240.92.138' ]; then
    exit
fi