package cn.com.devops.service;

import cn.com.devops.dao.SvnDao;
import cn.com.devops.entity.Svn;
import cn.com.devops.exception.AresException;

import org.apache.commons.codec.binary.Base64;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class SvnService {
    @Autowired
    private SvnDao svnDao;

    public void insert(Svn svn){
    	Svn svn1 = svnDao.queryByNameAndUrl(svn);
    	String str = Base64.encodeBase64String(
    			svn.getSvnPassword().getBytes());
    	svn.setSvnPassword(str);
    	svn.setStatus("1");
    	if(svn1 != null){
    		if("1".equals(svn1.getStatus())){
    			throw new AresException("该svn记录已存在，请不要重复添加!");
    		}else{
    			svn.setId(svn1.getId());
    			svnDao.update(svn);
    		}
    	}else{
        	svn.setRemark("");
            svnDao.insert(svn);
    	}
    }
    public void update(Svn svn){
    	String str = Base64.encodeBase64String(
    			svn.getSvnPassword().getBytes());
    	svn.setSvnPassword(str);
    	Svn svn1 = svnDao.queryByNameAndUrl(svn);
    	if(svn1 != null){
    		if("1".equals(svn1.getStatus())){
    			throw new AresException("该svn记录已存在，无法修改!");
    		}else{
    			svnDao.delete(svn.getId());
    			svn.setStatus("1");
    			svn.setId(svn1.getId());
    			svnDao.update(svn);
    		}
    	}else{
    		svnDao.update(svn);
    	}
    }

    public List<Svn> query(Svn svn){
        return svnDao.query(svn);
    }

    public Svn queryFirst(String type){
        List<Svn> list = svnDao.queryByType(type);
        if(list != null && list.size() > 0){
            return list.get(0);
        }
        return null;
    }
}
