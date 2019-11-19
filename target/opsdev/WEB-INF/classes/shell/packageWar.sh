#!/bin/bash

path=$0
appName=$1
target_app_ip=$2

cd ${path}
if [ $? -ne 0 ]; then
    echo "fail"
else
    cd ${appName}
    if [ $? -ne 0 ]; then
        echo "fail"
    else
        cd target
        rm -rf *.war ${name}* classes
        cd ..
        mvn package
    fi
    if [ $? -ne 0 ]; then
        echo "fail"
    else
        cd target
        scp *.war ${target_app_ip}:/mobile/app/upload
        if [ $? -ne 0 ]; then
            echo "fail"
        else
            ssh mobile@${target_web_ip}
            jar xf *.war
            rm -rf *.war
            exit
            echo "success"
        fi
    fi
fi