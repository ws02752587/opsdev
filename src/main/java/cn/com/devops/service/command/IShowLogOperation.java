package cn.com.devops.service.command;

import cn.com.devops.entity.Deploy;

public interface IShowLogOperation {
    StringBuilder showLog(Deploy deploy, int... num);
}
