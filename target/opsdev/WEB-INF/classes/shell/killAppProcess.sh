#!/bin/bash

if [ $0 != '10.240.92.138' ]; then
    ssh mobile@$0
fi
ps -ef | grep $1 | grep -v grep
if [ $? -eq 0 ]; then
    ps -ef | grep $1 | grep -v grep | awk '{print $2}' | xargs kill -9
fi
if [ $0 != '10.240.92.138' ];then
    exit
fi
echo "success"

