#!/bin/bash

if [ $0 != '10.240.12.31' ]; then
    ssh mobile@$0
fi
result=`ps -ef | grep apache`
cd /mobile/app/devops/python
if [ $? -ne 0 ]; then
    echo "fail"
else
    python utils.py 'contains' "$result" 'start -f /mobile/mobileapache/mobile.conf'
fi
if [ $0 != '10.240.12.31' ];then
    exit
fi