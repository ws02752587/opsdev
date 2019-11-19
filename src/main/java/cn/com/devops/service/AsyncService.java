package cn.com.devops.service;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import cn.com.devops.entity.Branch;
import cn.com.devops.entity.Deploy;
import cn.com.devops.utils.AsyncTask;

@Service
public class AsyncService {
	@Autowired
    private AsyncTask asyncTask;
	
	public void downBranch(Deploy deploy){
    	asyncTask.downBranch(deploy);
    }
	
	public void clearBranch(Branch branch){
		asyncTask.clearBranch(branch);
    }
	
    public void operationAppService(Map<String,String> map,
    		Deploy deploy){
    	asyncTask.operationAppService(map,deploy);
    }
    
    public void operationWebService(Map<String,String> map,
    		Deploy deploy){
    	asyncTask.operationWebService(map, deploy);
    }
}
