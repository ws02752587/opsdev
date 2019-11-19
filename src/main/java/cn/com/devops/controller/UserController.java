package cn.com.devops.controller;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import cn.com.devops.base.ResponseData;
import cn.com.devops.entity.User;
import cn.com.devops.exception.AresException;
import cn.com.devops.service.UserService;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;

@Controller
@RequestMapping("/user")
public class UserController {
    @Autowired
    private UserService userService;
    
    private Logger logger = LoggerFactory.getLogger(UserController.class);

    @RequestMapping(value = "/login.do", method = RequestMethod.POST)
    @ResponseBody
    public ResponseData login(@RequestBody User user){
        String token = userService.login(user);
        logger.info("登录成功,token: {}", token);
        return ResponseData.ok("access_token", token);
    }

    @RequestMapping(value = "/logout.do", method = RequestMethod.POST)
    @ResponseBody
    public ResponseData logout(HttpServletRequest httpServletRequest){
    	User user = (User)httpServletRequest.getAttribute("User");
        userService.logout(user);
        logger.info("{} ,退出成功", user.getName());
        return ResponseData.ok();
    }

    @RequestMapping(value = "/insert.do", method = RequestMethod.POST)
    @ResponseBody
    public ResponseData insert(@Validated @RequestBody User user){
        userService.insert(user);
        return ResponseData.ok();
    }
    
    @RequestMapping(value = "/delete.do", method = RequestMethod.POST)
    @ResponseBody
    public ResponseData delete(@RequestBody User user,
    		HttpServletRequest httpServletRequest){
    	User user1 = (User)httpServletRequest.getAttribute("User");
    	if(user1.getId() == user.getId()){
    		throw new AresException("不能删除自己!");
    	}
    	validTransPwd(user.clone(),httpServletRequest);
        userService.delete(user);
        return ResponseData.ok();
    }
    
    @RequestMapping(value = "/query.do", method = RequestMethod.POST)
    @ResponseBody
    public ResponseData query(@RequestBody User user){
    	//这行是重点，表示从pageNum页开始，每页pageSize条数据
        PageHelper.startPage(user.getPageNum(),
        		user.getPageSize());
        List<User> list = userService.search(user);
        PageInfo<User> info = new PageInfo<User>(list);
        return ResponseData.page(info);
    }
    
    @RequestMapping(value = "/queryUser.do", method = RequestMethod.POST)
    @ResponseBody
    public ResponseData queryUser(HttpServletRequest httpServletRequest){
    	User user = (User)httpServletRequest.getAttribute("User");
        return ResponseData.ok(user);
    }
    
    @RequestMapping(value = "/updatePwd.do", method = RequestMethod.POST)
    @ResponseBody
    public ResponseData updatePwd(@RequestBody User user,
    		HttpServletRequest httpServletRequest){
    	User user1 = (User)httpServletRequest.getAttribute("User");
    	user.setUsername(user1.getUsername());
    	userService.updatePwd(user);
        return ResponseData.ok();
    }
    
    @RequestMapping(value = "/updateUser.do", method = RequestMethod.POST)
    @ResponseBody
    public ResponseData update(@RequestBody User user){
    	userService.update(user);
        return ResponseData.ok();
    }
    
    @RequestMapping(value = "/validTransPwd.do", method = RequestMethod.POST)
    @ResponseBody
    public ResponseData validTransPwd(@RequestBody User user,
    		HttpServletRequest httpServletRequest){
    	User user1 = (User)httpServletRequest.getAttribute("User");
    	user.setId(user1.getId());
        userService.validTransPwd(user);
        return ResponseData.ok();
    }
}
