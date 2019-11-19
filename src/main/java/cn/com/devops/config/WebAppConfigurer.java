package cn.com.devops.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;

import cn.com.devops.filter.LoginFilter;
import cn.com.devops.service.UserService;

@Configuration
public class WebAppConfigurer extends WebMvcConfigurerAdapter{
	
	@Autowired
    private UserService userService;
	
	@Override
	public void addInterceptors(InterceptorRegistry interceptorRegistry){
		LoginFilter filter = new LoginFilter();
		filter.setUserService(userService);
		InterceptorRegistration interceptorRegistration = 
				interceptorRegistry.addInterceptor(filter);
		interceptorRegistration.addPathPatterns("/**");
		interceptorRegistration.excludePathPatterns("/user/login.do");
	}
}
