package cn.com.devops.service;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import cn.com.devops.dao.EnvDao;
import cn.com.devops.entity.*;
import cn.com.devops.enums.BranchEnum;
import cn.com.devops.enums.DeployWebEnum;
import cn.com.devops.enums.EditionEnum;
import cn.com.devops.enums.DeployEnum;
import cn.com.devops.enums.EditionInfoEnum;
import cn.com.devops.exception.AresException;
import cn.com.devops.service.command.*;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

/**
 * 部署操作类
 * 检查服务器状态
 * 启动服务器
 * 停止服务器
 * 修改整合地址
 * 修改数据源
 */
@Service
public class DeployOperationService {
	private Logger logger = LoggerFactory.getLogger(DeployOperationService.class);
    @Autowired
    private SystemFileOperation systemFileOperation;

    @Autowired
    private ProcessOperationApp processOperationApp;

    @Autowired
    private ProcessOperationWeb processOperationWeb;

    @Autowired
    private ShowLogOperationApp showLogOperationApp;

    @Autowired
    private ShowLogOperationWeb showLogOperationWeb;

    @Autowired
    private DeployService deployService;

    @Autowired
    private HttpService httpService;
    
    @Autowired
    private EnvDao envDao;

    private static final Map<String , Long> paramap = new HashMap<String ,Long>();
    
    private void updateDeploy(int id, DeployEnum appStatus){
    	Deploy deploy = new Deploy();
    	deploy.setId(id);
    	deploy.setAppStatus(appStatus.toString());
    	deployService.update(deploy);
    }
    public DeployEnum checkAppService(int id){
        Deploy deploy = deployService.queryById(id);
        if(deploy == null)return DeployEnum.FAIL;
        Branch branch = deploy.getBranch();
        if(branch == null)return DeployEnum.FAIL;
        Env env = deploy.getEnv();
        if(env == null)return DeployEnum.FAIL;
        if(BranchEnum.BEONLE.toString().equals(branch.getStatus())){
        	updateDeploy(id,DeployEnum.FREE);
            return DeployEnum.FREE;
        }
        String branchStatus = deploy.getBranchStatus();
        if(!DeployEnum.BRANCHSUCCESS.toString().equals(branchStatus)){
        	return DeployEnum.changeEnum(branchStatus);
        }
        String appStatus = deploy.getAppStatus();
        if(DeployEnum.READY.toString().equals(appStatus) ||
        		DeployEnum.ERROR.toString().equals(appStatus)){
        	if(!processOperationApp.isProcess(deploy)){
        		updateDeploy(id,DeployEnum.FAIL);
        		return DeployEnum.FAIL;
        	}else{
        		EditionInfo editionInfo = deploy.getEditionInfo();
                String url = editionInfo.getAppCheckUrl();
                String ip = env.getAppIp();
                url = url.replace("{}", ip);
                logger.info("get url:{}",url);
                String result = httpService.doGet(url);
                if(StringUtils.isEmpty(result)){
                	updateDeploy(id,DeployEnum.READY);
                	return DeployEnum.READY;
                }else{
                	updateDeploy(id,DeployEnum.SUCCESS);
                    StringBuilder sb = showLogOperationApp.showLog(deploy, 500);
                    if(!StringUtils.isEmpty(sb)){
                        if(sb.toString().contains("java.lang.NullPointerException") ||
                                sb.toString().contains("系统忙")){
                        	updateDeploy(id,DeployEnum.ERROR);
                            return DeployEnum.ERROR;
                        }
                    }
                }
        	}
        }else{
        	return DeployEnum.changeEnum(appStatus);
        }
        return DeployEnum.SUCCESS;
    }
    private void updateWebDeploy(int id, DeployWebEnum webStatus){
    	Deploy deploy = new Deploy();
    	deploy.setId(id);
    	deploy.setWebStatus(webStatus.toString());
    	deployService.update(deploy);
    }
    public DeployWebEnum checkWebService(int id){
        Deploy deploy = deployService.queryById(id);
        if(deploy == null) return DeployWebEnum.FAIL;
        Branch branch = deploy.getBranch();
        if(branch == null)return DeployWebEnum.FAIL;
        Env env = deploy.getEnv();
        if(env == null)return DeployWebEnum.FAIL;
        if(EditionInfoEnum.MobileManager.toString().equals(
        		deploy.getEditionInfo().getType())){
        	return null;
        }
        if(BranchEnum.BEONLE.toString().equals(branch.getStatus())){
        	updateWebDeploy(id,DeployWebEnum.FREE);
            return DeployWebEnum.FREE;
        }
        String branchStatus = deploy.getBranchStatus();
        if(!DeployEnum.BRANCHSUCCESS.toString().equals(branchStatus)){
        	return DeployWebEnum.changeEnum(branchStatus);
        }
        String webStatus = deploy.getWebStatus();
        if(DeployWebEnum.READY.toString().equals(webStatus)){
        	if(!processOperationWeb.isProcess(deploy)){
        		updateWebDeploy(id,DeployWebEnum.FAIL);
        		return DeployWebEnum.FAIL;
        	}else{
        		updateWebDeploy(id,DeployWebEnum.SUCCESS);
        		EditionInfo editionInfo = deploy.getEditionInfo();
                String url = editionInfo.getWebCheckUrl();
                String ip = env.getWebIp();
                url = url.replace("{}", ip);
                logger.info("get url:{}",url);
                String result = httpService.doGet(url);
                if(StringUtils.isEmpty(result)){
                	updateWebDeploy(id,DeployWebEnum.READY);
                    return DeployWebEnum.READY;
                }
        	}
        }else{
        	return DeployWebEnum.changeEnum(webStatus);
        }
        
        return DeployWebEnum.SUCCESS;
    }

    @Autowired
    private AsyncService asyncService;
    
    /**
     * app
     * 	1.只重启服务，用于刷新缓存
     *  2.打war包，并重启服务
     *  3.停止服务，进程
     *  
     * web
     * 	1.打tar包，不用重启服务
     * 	2.只重启服务
     *  3.停止服务，进程
     *  处理以上情况
     * @param map
     * @return
     */
    public boolean operationService(Map<String,String> map){
    	String id = map.get("id");
    	Date d = new Date();
    	if(paramap.containsKey(id)){
    		Long time = paramap.get(id);
    		logger.info("time:{}", d.getTime() - time);
    		if(d.getTime() - time > 600000){
    			Deploy deploy = deployService.queryById(Integer.valueOf(id));
    			asyncService.operationAppService(map,deploy);
    			asyncService.operationWebService(map,deploy);
    		}else{
    			throw new AresException("5分钟内不能重复发版！");
    		}
    	}else{
    		paramap.put(id, d.getTime());
    	}
    	return true;
    }
    
    public String showLog(int id, EditionEnum flag){
        Deploy deploy = deployService.queryById(id);
        StringBuilder sb = null;
        if(EditionEnum.WEB.equals(flag)){
            sb = showLogOperationWeb.showLog(deploy, 1000);
        }else {
            sb = showLogOperationApp.showLog(deploy, 1000);
        }
        if(!StringUtils.isEmpty(sb)){
            return sb.toString();
        }
        return null;
    }

    public boolean updateEsbUrl(Deploy deploy){
        return systemFileOperation.updateEsbUrl(deploy);
    }

    public boolean updateDataSource(Deploy deploy){
        return systemFileOperation.updateDataSource(deploy);
    }
    public DB getDataSource(Deploy deploy){
        return systemFileOperation.queryJdbcUrl(deploy);
    }

    public Env getEsb(Deploy deploy){
        Env env = systemFileOperation.queryEsbUrl(deploy);
        env = envDao.queryByUrl(env);
        return env;
    }

}
