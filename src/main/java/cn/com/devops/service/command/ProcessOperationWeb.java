package cn.com.devops.service.command;

import cn.com.devops.entity.Deploy;

public abstract class ProcessOperationWeb implements IProcessOperation {
    /**
     * web没有基于weblogic的启动服务器方法
     * @param deploy
     * @return
     */
    public boolean resetWeblogicService(Deploy deploy){

        return false;
    }
    
    public boolean stopWeblogicService(Deploy deploy){
    	return false;
    }
}
