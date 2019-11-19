package cn.com.devops.service.command;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;

import cn.com.devops.base.App;
import cn.com.devops.entity.Branch;
import cn.com.devops.entity.Deploy;
import cn.com.devops.entity.Env;
import cn.com.devops.enums.DeployEnum;
import cn.com.devops.service.DeployService;

public class PackageOperationAppWindow extends PackageOperationApp {
	private Logger logger = LoggerFactory.getLogger(PackageOperationAppWindow.class);
	@Autowired
    private DeployService deployService;
	
	@Value("${sh.url}")
    private String sh_url;
	
    @Override
    public boolean packge(Deploy deploy) {
    	boolean flag = false;
        Branch branch = deploy.getBranch();
        if(branch == null)return false;
        Env env = deploy.getEnv();
        if(env == null)return false;
        String url = branch.getSvnAddress();
        url = url.replace("/","-");
        String name = deploy.getEditionInfo().getAppName();
        Deploy deploy1 = new Deploy();
        deploy1.setId(deploy.getId());
        if("success".contains(App.SH_SUCCESS)){
            flag = true;
            deploy1.setAppStatus(DeployEnum.READY.toString());
        }else{
            flag = false;
            deploy1.setErrorMsg("error");
            deploy1.setAppStatus(DeployEnum.PACKAGE_FAIL.toString());
        }
        logger.info("sh_url:{}", sh_url);
        logger.info("url:{} name:{} envAppIp:{}",url,name,env.getAppIp());
        deployService.update(deploy1);
        return flag;
    }

    @Override
    public boolean tarx(Deploy deploy) {
        return false;
    }
}
