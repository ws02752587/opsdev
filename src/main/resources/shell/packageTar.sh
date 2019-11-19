#!/bin/bash

path=$0
appName=$1
target_web_ip=$2

cd ${path}
if [ $? -ne 0 ]; then
    echo "fail"
else
    cd ${appName}
    if [ $? -ne 0 ]; then
        echo "fail"
    else
        cd target
        rm -rf *.tar
        cd ..
        mvn package
    fi
    if [ $? -ne 0 ]; then
        echo "fail"
    else
        cd target
        scp *.tar ${target_web_ip}:/mobile/www
        if [ $? -ne 0 ]; then
            echo "fail"
        else
            ssh mobile@${target_web_ip}
            cd /mobile/www
            tar -xf *.tar
            rm -rf *.tar
            exit
            echo "success"
        fi
    fi
fi