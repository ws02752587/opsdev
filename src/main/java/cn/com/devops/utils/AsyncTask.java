package cn.com.devops.utils;

import java.util.Map;

import cn.com.devops.dao.DeployDao;
import cn.com.devops.entity.Branch;
import cn.com.devops.entity.Deploy;
import cn.com.devops.enums.DeployEnum;
import cn.com.devops.enums.DeployWebEnum;
import cn.com.devops.service.command.*;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;

@Component
public class AsyncTask {
    @Autowired
    private PackageOperationApp packageOperationApp;

    @Autowired
    private PackageOperationWeb packageOperationWeb;

    @Autowired
    private IBranchOperation branchOperation;

    @Autowired
    private ProcessOperationApp processOperationApp;

    @Autowired
    private ProcessOperationWeb processOperationWeb;

    @Autowired
    private DeployDao deployDao;
    
    @Async
    public void packageWar(Deploy deploy){
        if(packageOperationApp.packge(deploy)){
            processOperationApp.resetProcess(deploy);
        }
    }

    @Async
    public void packageTar(Deploy deploy){
        if(packageOperationWeb.packge(deploy)){
            processOperationWeb.resetProcess(deploy);
        }
    }

    @Async
    public void downBranch(Deploy deploy){
        if(branchOperation.downBranch(deploy)){
            packageWar(deploy);
            packageTar(deploy);
        }
    }
    
    @Async
    public void clearBranch(Branch branch){
    	branchOperation.clearBranch(branch);
    }
    
    @Async
    public void operationAppService(Map<String,String> map,
    		Deploy deploy){
    	String commandApp = map.get("commandApp");
    	if("2".equals(commandApp)){
    		processOperationApp.killProcess(deploy);
    	}else if("1".equals(commandApp)){
			processOperationApp.stopWeblogicService(deploy);
    	}else {
    		if("0".equals(commandApp)){
    			String warType = map.get("warType");
    			boolean pflag = false;
    			if(!StringUtils.isEmpty(warType)){
    				pflag = packageOperationApp.packge(deploy);
    				if(!pflag){
    					updateDeploy(deploy.getId(), DeployEnum.PACKAGE_FAIL);
    				}
    			}
        		if(pflag){
        			boolean flag = processOperationApp.resetProcess(deploy);
        			if(flag){
        				updateDeploy(deploy.getId(), DeployEnum.READY);
        			}else{
        				updateDeploy(deploy.getId(), DeployEnum.ERROR);
        			}
        		}
        	}
    	}
    }
    
    private void updateDeploy(int id, DeployEnum deployEnum){
    	Deploy deploy = new Deploy();
    	deploy.setId(id);
    	deploy.setAppStatus(deployEnum.toString());
    	deployDao.update(deploy);
    }
    
    private void updateDeployWeb(int id, DeployWebEnum deployWebEnum){
    	Deploy deploy = new Deploy();
    	deploy.setId(id);
    	deploy.setWebStatus(deployWebEnum.toString());
    	deployDao.update(deploy);
    }
    
    @Async
    public void operationWebService(Map<String,String> map,
    		Deploy deploy){
    	String commandWeb = map.get("commandWeb");
    	if("1".equals(commandWeb)){
    		processOperationWeb.killProcess(deploy);
    	}else if("0".equals(commandWeb)){
    		processOperationWeb.resetProcess(deploy);
    	}
    	String tarType = map.get("tarType");
    	boolean pflag = false;
    	if(!StringUtils.isEmpty(tarType)){
			pflag = packageOperationWeb.packge(deploy);
			if(!pflag){
				updateDeployWeb(deploy.getId(), DeployWebEnum.PACKAGE_FAIL);
			}else{
				updateDeployWeb(deploy.getId(), DeployWebEnum.READY);
			}
		}
    }
}
