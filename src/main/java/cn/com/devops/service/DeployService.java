package cn.com.devops.service;

import cn.com.devops.dao.DeployDao;
import cn.com.devops.entity.Deploy;
import cn.com.devops.exception.AresException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class DeployService {
    @Autowired
    private DeployDao deployDao;

    @Autowired
    private BranchService branchService;
    
    @Autowired
    private AsyncService asyncService;

    public void insert(Deploy deploy){
    	Deploy deploy1 = deployDao.queryByInfo(deploy);
    	if(deploy1 != null){
    		throw new AresException("该部署信息已存在，请不要重复添加!");
    	}
    	deploy.setStatus("1");
    	deploy.setAppStatus("4");
    	deploy.setWebStatus("4");
    	deploy.setAppVersion("1");
    	deploy.setWebVersion("1");
    	deploy.setRemark("");
    	deploy.setErrorMsg("");
    	deploy.setBranchStatus("1");
    	deployDao.insert(deploy);
//    	if(num > 0){
//    		asyncService.downBranch(deployDao.queryById(deploy.getId()));
//    	}
    }

    public void update(Deploy deploy){
        deployDao.update(deploy);
    }

    public void delete(int id){
        deployDao.delete(id);
    }

    public List<Deploy> query(){
        return deployDao.query();
    }

    public List<Deploy> search(Deploy deploy){
        return deployDao.search(deploy);
    }

    public Deploy queryById(int id){
        return deployDao.queryById(id);
    }
}
