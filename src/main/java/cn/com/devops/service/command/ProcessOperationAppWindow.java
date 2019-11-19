package cn.com.devops.service.command;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;

import cn.com.devops.entity.Branch;
import cn.com.devops.entity.Deploy;
import cn.com.devops.entity.Env;

public class ProcessOperationAppWindow extends ProcessOperationApp {
	@Value("${sh.url}")
    private String sh_url;
	
	private Logger logger = LoggerFactory.getLogger(ProcessOperationAppWindow.class);
    @Override
    public boolean isProcess(Deploy deploy) {
    	Env env  = deploy.getEnv();
        if(env == null)return false;
        Branch branch = deploy.getBranch();
        if(branch == null)return false;
        String url = env.getAppIp();
        String name = deploy.getEditionInfo().getProcessName();
        logger.info("url:{} name:{}",url,name);
        return true;
    }

    @Override
    public boolean killProcess(Deploy deploy) {
    	
        return false;
    }

    @Override
	public boolean resetProcess(Deploy deploy) {
		Env env = deploy.getEnv();
		if (env == null) return false;
		Branch branch = deploy.getBranch();
		if (branch == null) return false;
		String url = env.getAppIp();
		String name = deploy.getEditionInfo().getProcessName();
		logger.info("url:{}, name:{}",url,name);
		return true;
	}

    @Override
    public boolean resetWeblogicService(Deploy deploy) {
        return false;
    }

	@Override
	public boolean stopWeblogicService(Deploy deploy) {
		return false;
	}
}
