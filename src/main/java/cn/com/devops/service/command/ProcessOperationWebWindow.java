package cn.com.devops.service.command;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;

import cn.com.devops.entity.Deploy;
import cn.com.devops.entity.Env;

public class ProcessOperationWebWindow extends ProcessOperationWeb {
	private Logger logger = LoggerFactory.getLogger(ProcessOperationWebWindow.class);
	
	@Value("${sh.url}")
    private String sh_url;
	
    @Override
    public boolean isProcess(Deploy deploy) {
    	Env env  = deploy.getEnv();
        if(env == null)return false;
        String url = env.getWebIp();
        logger.info("url:{}",url);
        return true;
    }

    @Override
    public boolean killProcess(Deploy deploy) {
        return false;
    }

    @Override
    public boolean resetProcess(Deploy deploy) {
    	Env env  = deploy.getEnv();
        if(env == null)return false;
        String url = env.getWebIp();
        logger.info("url:{}",url);
        return true;
    }
}
