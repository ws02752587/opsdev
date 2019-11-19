#!/bin/bash

if [ $0 != '10.240.92.138' ]; then
    ssh mobile@$0
fi
result=`ps -ef | grep $1`
cd /mobile/app/devops/python
if [ $? -ne 0 ]; then
    echo "fail"
else
    python utils.py contains "$result" $1
fi
if [ $0 != '10.240.92.138' ];then
    exit
fi
