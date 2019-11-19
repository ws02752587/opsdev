#!/bin/bash
cd /mobile/app/devops/branch

path=$0

cd ${path}
if [ $? -ne 0 ]; then
    echo "fail"
else
    rm -rf *
    if [ $? -ne 0 ]; then
        echo "fail"
    else
        echo "success"
    fi
fi