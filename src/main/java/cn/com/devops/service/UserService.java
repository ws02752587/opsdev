package cn.com.devops.service;

import java.util.List;

import cn.com.devops.dao.UserDao;
import cn.com.devops.entity.OperationLog;
import cn.com.devops.entity.User;
import cn.com.devops.enums.OperationLogEnum;
import cn.com.devops.exception.AresException;
import cn.com.devops.utils.JwtUtils;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

@Service
public class UserService {
    @Autowired
    private UserDao userDao;

    @Autowired
    private OperationLogService operationLogService;

    /**
     * 登录
     * @param user
     * @return token
     */
    public String login(User user){
        User user1 = userDao.queryByUsernameAndPassword(user);
        if(StringUtils.isEmpty(user1)){
            throw new AresException("用户名或密码错误!");
        }
        long exptime = user1.getLoginExptime();
        String token = JwtUtils.createJWT(user1, exptime*24*60*60*1000);
        if(StringUtils.isEmpty(token)){
        	throw new AresException("token初次化失败!");
        }
        String name = user1.getName();
        OperationLog operationLog = new OperationLog();
        operationLog.setType(OperationLogEnum.LOGIN);
        operationLog.setName(name);
        operationLog.setRemark("欢迎"+name+"登录本系统");
        operationLogService.insert(operationLog);
        return token;
    }

    /**
     * 退出登录
     * @param user
     */
    public void logout(User user){
        OperationLog operationLog = new OperationLog();
        operationLog.setType(OperationLogEnum.LOGOUT);
        operationLog.setName(user.getName());
        operationLogService.insert(operationLog);
    }

    /**
     * 新增
     * @param user
     */
    public void insert(User user){
    	User user1 = userDao.queryByUsername(user);
    	user.setStatus("1");
    	if(user1 != null){
    		if("1".equals(user1.getStatus())){
    			throw new AresException("该用户已存在，请不要重复添加!");
    		}else{
    			user.setId(user1.getId());
    			userDao.update(user);
    		}
    	}else{
        	user.setRemark(null);
        	user.setAge(null);
            userDao.insert(user);
    	}
    }

    /**
     * 获取用户id
     * @param id
     * @return
     */
    public User queryById(int id){
        return userDao.queryById(id);
    }
    
    public List<User> query(){
        return userDao.query();
    }
    
    public void updatePwd(User user){
    	User user1 = userDao.queryByUsernameAndPassword(user);
    	if(StringUtils.isEmpty(user1)){
            throw new AresException("密码错误!");
        }
    	user.setId(user1.getId());
    	userDao.updatePwd(user);
    }
    
    public void update(User user){
    	User user1 = userDao.queryByUsername(user);
    	if(user1 != null){
    		if("1".equals(user1.getStatus())){
    			throw new AresException("该用户已存在，请不要重复添加!");
    		}else{
    			userDao.delete(user);
    			user.setStatus("1");
    			user.setId(user1.getId());
    			userDao.update(user);
    		}
    	}else{
    		userDao.update(user);
    	}
    }
    
    public void validTransPwd(User user){
    	User user1 = userDao.validTransPwd(user);
    	if(StringUtils.isEmpty(user1)){
            throw new AresException("验证密码错误!");
        }
    }
    
    public void delete(User user){
    	userDao.delete(user);
    }
    
    public List<User> search(User user){
        return userDao.search(user);
    }
}
