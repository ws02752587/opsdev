#!/bin/bash

if [ $0 != '10.240.92.138' ]; then
    ssh mobile@$0
fi

cd /mobile/app/upload/$name/WEB_INF/classes
ip=`cat conf.properties | grep ESB_SERVER | awk -F= '{print $2}' | head -n 1`
port=`cat conf.properties | grep ESB_SERVER | awk -F= '{print $2}' | tail -n 1`
echo "$ip:$port"
if [ $0 != '10.240.92.138' ]; then
    exit
fi