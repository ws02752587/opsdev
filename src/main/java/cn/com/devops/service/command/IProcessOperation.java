package cn.com.devops.service.command;

import cn.com.devops.entity.Deploy;

public interface IProcessOperation {
    boolean isProcess(Deploy deploy);
    boolean killProcess(Deploy deploy);
    boolean resetProcess(Deploy deploy);
    boolean resetWeblogicService(Deploy deploy);
    boolean stopWeblogicService(Deploy deploy);
}
