#!/bin/bash

if [ $0 != '10.240.12.31' ]; then
    ssh mobile@$0
fi
ps -ef | grep apache | grep -v grep
if [ $? -eq 0 ]; then
    cd /mobile/mobileapache
    ./stop.sh
fi
if [ $0 != '10.240.12.31' ];then
    exit
fi
echo "success"

