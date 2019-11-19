package cn.com.devops.service.command;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;

import cn.com.devops.entity.Deploy;
import cn.com.devops.entity.EditionInfo;
import cn.com.devops.entity.Env;

public class ShowLogOperationAppWindow extends ShowLogOperationApp {
	private Logger logger = LoggerFactory.getLogger(ShowLogOperationAppWindow.class);
	@Value("${sh.url}")
    private String sh_url;
	
    @Override
    public StringBuilder showLog(Deploy deploy, int... num) {
    	StringBuilder sb = new StringBuilder();
    	if(num == null || num.length == 0){
            num[0] = 200;
        }
        Env env  = deploy.getEnv();
        if(env == null)return sb;
        EditionInfo editionInfo = deploy.getEditionInfo();
        String logsUrl = editionInfo.getLogAddress();
        String logName = editionInfo.getLogName();
        String url = env.getAppIp();
        logger.info("logsUrl:{} logName:{} url:{}",logsUrl,logName,url);
        sb.append("java.lang.NullPointerException");
        return sb;
    }
}
