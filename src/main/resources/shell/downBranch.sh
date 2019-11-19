#!/bin/bash
cd /mobile/app/devops/branch

baseSvnUrl=$0
path=$1
appName=$2
name=$3
pwd=$4

cd ${path}
if [ $? -ne 0 ]; then
    mkdir ${path}
    cd ${path}
    svn co ${baseSvnUrl}/${path}/${appName} --username ${name} --password ${pwd}
else
    cd ${appName}
    if [ $? -ne 0 ]; then
        svn co ${baseSvnUrl}/${path}/${appName} --username ${name} --password ${pwd}
    else
        svn update
    fi
fi
if [ $? -ne 0 ]; then
    echo "fail"
else
    echo "success"
fi