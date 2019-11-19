#!/bin/bash

if [ $0 != '10.240.92.138' ]; then
    ssh mobile@$0
fi
cd $1
if [ $? -ne 0 ]; then
    echo "not found $1 dir"
else
    tail -n $3 $2
fi
if [ $0 != '10.240.92.138' ];then
    exit
fi
