#!/bin/bash

if [ $0 != '10.240.12.31' ]; then
    ssh mobile@$0
fi
cd /mobile/mobileapache
ps -ef | grep apache | grep -v grep
if [ $? -eq 0 ]; then
    ./stop.sh
fi
./start.sh
if [ $0 != '10.240.12.31' ];then
    exit
fi
echo "success"

