#!/bin/bash

if [ $0 != '10.240.92.138' ]; then
    ssh mobile@$0
fi

cd /mobile/app/devops/python
if [ $? -ne 0 ]; then
    echo "fail"
else
    python updateEsbUrl.py $1 $2 $3
fi


if [ $0 != '10.240.92.138' ]; then
    exit
fi