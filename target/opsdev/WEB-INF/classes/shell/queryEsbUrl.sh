#!/bin/bash

if [ $0 != '10.240.92.138' ]; then
    ssh mobile@$0
fi

cd /mobile/app/upload/$name/WEB_INF/classes/META-INF
ip=`cat conf.properties | grep ESB_SERVER | grep -v \# | awk -F= '{print $2}' | sed 'N;s/\n/:/g'`
echo $ip
if [ $0 != '10.240.92.138' ]; then
    exit
fi