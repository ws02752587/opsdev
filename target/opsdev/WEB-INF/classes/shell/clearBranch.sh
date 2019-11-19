#!/bin/bash
cd /mobile/app/devops/branch

path=$0
appName=$1

cd ${path}
if [ $? -ne 0 ]; then
    echo "fail"
else
    rm -rf ${appName}
    if [ $? -ne 0 ]; then
        echo "fail"
    else
        echo "success"
    fi
fi