#!/bin/bash

if [ $0 != '10.240.92.138' ];then
    ssh mobile@$0
fi
cd /gtp/files/mobile/rcv/skin/AdvImagePath
a=`cat md5a.txt`
i=`cat md5i.txt`
echo "android:$a:iphone:$i"
if [ $0 != '10.240.92.138' ];then
    exit
fi