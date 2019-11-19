package cn.com.devops.filter;

import cn.com.devops.annotation.PassToken;
import cn.com.devops.entity.User;
import cn.com.devops.exception.AresException;
import cn.com.devops.service.UserService;
import cn.com.devops.utils.JwtUtils;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.util.StringUtils;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import java.lang.reflect.Method;

/**
 * 创建一个拦截器实现HandlerInterceptor接口
 * 登录拦截器,实现Web安全通讯之JWT
 */
public class LoginFilter implements HandlerInterceptor {
	private Logger logger = LoggerFactory.getLogger(LoginFilter.class);
    
	private UserService userService;

    @Override
    public boolean preHandle(HttpServletRequest httpServletRequest,
                             HttpServletResponse httpServletResponse,
                             Object object) throws Exception {
        // 从 http 请求头中取
        String token = httpServletRequest.getHeader("access_token");
        // 如果不是映射到方法直接通过
        if(!(object instanceof HandlerMethod)) {
            return true;
        }
        HandlerMethod handlerMethod=(HandlerMethod)object;
        Method method=handlerMethod.getMethod();
        //检查是否有passtoken注释，有则跳过认证
        if (method.isAnnotationPresent(PassToken.class)) {
            PassToken passToken = method.getAnnotation(PassToken.class);
            if (passToken.required()) {
                return true;
            }
        }
        // 执行认证
        if (StringUtils.isEmpty(token)) {
        	logger.info("无token!");
            throw new AresException("1001", "请重新登录!");
        }
        // 获取 token 中的 userId
        Integer userId = JwtUtils.getUserIdByJwt(token);
        if(StringUtils.isEmpty(userId)){
            // 验证JWT失败
        	logger.info("验证JWT失败,userID: {}, token: {}!", 
        			userId, token);
            throw new AresException("1001", "请重新登录!");
        }
        User user = userService.queryById(userId);
        if(StringUtils.isEmpty(user)){
        	logger.info("用户不存在，请重新登录!");
            throw new AresException("1001", "用户不存在，请重新登录!");
        }
        httpServletRequest.setAttribute("User", user);
        logger.info("User: {}", user);
        return true;
    }

    @Override
    public void postHandle(HttpServletRequest httpServletRequest,
                           HttpServletResponse httpServletResponse,
                           Object o,
                           ModelAndView modelAndView) throws Exception {

    }

    @Override
    public void afterCompletion(HttpServletRequest httpServletRequest,
                                HttpServletResponse httpServletResponse,
                                Object o,
                                Exception e) throws Exception {

    }

	public void setUserService(UserService userService) {
		this.userService = userService;
	}
}
