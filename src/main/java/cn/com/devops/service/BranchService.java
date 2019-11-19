package cn.com.devops.service;

import cn.com.devops.dao.BranchDao;
import cn.com.devops.dao.ServerEditionDao;
import cn.com.devops.dao.SvnDao;
import cn.com.devops.entity.Branch;
import cn.com.devops.exception.AresException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.alibaba.druid.util.StringUtils;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class BranchService {
    @Autowired
    private BranchDao branchDao;

    @Autowired
    private SvnDao svnDao;

    @Autowired
    private AsyncService asyncService;

    @Autowired
    private ServerEditionDao serverEditionDao;

    public void insert(Branch branch){
    	Branch branch1 = branchDao.queryByVersion(branch);
    	if(branch1 != null){
    		throw new AresException("该工件号已存在，请不要重复添加!");
    	}else{
    		if(StringUtils.isEmpty(branch.getRemark())){
        		branch.setRemark("");
        	}
    		branch.setStatus("1");
            branchDao.insert(branch);
    	}
    }

    public void update(Branch branch){
    	if(StringUtils.isEmpty(branch.getRemark())){
    		branch.setRemark("");
    	}
//    	if("0".equals(branch.getStatus())){
//    		branch.setSvnAddress(null);
//    		asyncService.clearBranch(branchDao.queryById(branch.getId()));
//    	}
    	if("1".equals(branch.getRemark()) && 
    			"0".equals(branch.getStatus())){
    		Branch branch2 = branchDao.queryById(branch.getId());
        	branch2.setRemark("");
        	branch.setBranchVersion(branch.getBranchVersion()+"_grey");
        	branchDao.update(branch);
        	branchDao.insert(branch2);
    	}else{
    		branchDao.update(branch);
    	}
    }

    public void delete(int id){
    	//asyncService.clearBranch(branchDao.queryById(id));
        branchDao.delete(id);
    }

    public List<Branch> query(Branch branch){
    	if(branch == null){
    		branch = new Branch();
    	}
    	if(StringUtils.isEmpty(branch.getStatus())){
    		branch.setStatus("1");
    	}
    	branch.setEditionInfo("0");
    	Branch branch1 = queryProBranch(branch);
        List<Branch> list = branchDao.query(branch);
        if(branch1 != null){
	        for(Branch b : list){
	        	b.setNewBranchName(branch1.getBranchName());
	        	String info = b.getEditionInfo();
	        	String proInfo = branch1.getEditionInfo();
	        	Map<String,String> map = new HashMap<String,String>();
        		if(!"0".equals(b.getStatus()) && 
	        			branch1.getId() != b.getProBranchId()){
	        		if(!StringUtils.isEmpty(proInfo) &&
	        				!StringUtils.isEmpty(info)){
	        			for(String s : info.split("|")){
	        				if(proInfo.contains(s)){
	        					map.put(s, "1");
	        				}else{
	        					map.put(s, "0");
	        				}
	        			}
	        			b.setMergeStatus(map);
	        		}
	        	}
	        }
	        
        }
        return list;
    }
    
    public Branch queryProBranch(Branch branch){
        List<Branch> list = branchDao.queryProBranch(branch);
        if(list != null && list.size()>0){
        	return list.get(0);
        }
        return null;
    }

    public List<Branch> search(Branch branch){
        List<Branch> list = branchDao.search(branch);
        return list;
    }
}
