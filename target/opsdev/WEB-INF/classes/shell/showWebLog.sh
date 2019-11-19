#!/bin/bash

if [ $0 != '10.240.12.31' ]; then
    ssh mobile@$0
fi
cd /mobile/mobileapache/logs
name=`ll -tr 12600*access* | tail -1 | awk '{print $9}'`
tail -n $1 $name
if [ $0 != '10.240.12.31' ];then
    exit
fi
