package cn.com.devops.config;

import cn.com.devops.service.command.*;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.context.WebApplicationContext;

@Configuration
public class DevopsConfig {
	
	@Autowired
	private WebApplicationContext web;
	
    @Bean
    @ConditionalOnMissingBean(IBranchOperation.class)
    public IBranchOperation branchOperation(){
        String os = System.getProperty("os.name");
        if(os.contains("Windows")) {
            return new BranchOperationWindow();
        }
        return new BranchOperationLinux();
    }

    @Bean
    @ConditionalOnMissingBean(PackageOperationApp.class)
    public PackageOperationApp packageOperationApp(){
        String os = System.getProperty("os.name");
        if(os.contains("Windows")) {
            return new PackageOperationAppWindow();
        }
        return new PackageOperationAppLinux();
    }

    @Bean
    @ConditionalOnMissingBean(PackageOperationWeb.class)
    public PackageOperationWeb packageOperationWeb(){
        String os = System.getProperty("os.name");
        if(os.contains("Windows")) {
            return new PackageOperationWebWindow();
        }
        return new PackageOperationWebLinux();
    }

    @Bean
    @ConditionalOnMissingBean(ProcessOperationApp.class)
    public ProcessOperationApp processOperationApp(){
        String os = System.getProperty("os.name");
        if(os.contains("Windows")) {
            return new ProcessOperationAppWindow();
        }
        return new ProcessOperationAppLinux();
    }

    @Bean
    @ConditionalOnMissingBean(ProcessOperationWeb.class)
    public ProcessOperationWeb processOperationWeb(){
        String os = System.getProperty("os.name");
        if(os.contains("Windows")) {
            return new ProcessOperationWebWindow();
        }
        return new ProcessOperationWebLinux();
    }

    @Bean
    @ConditionalOnMissingBean(ShowLogOperationApp.class)
    public ShowLogOperationApp showLogOperationApp(){
        String os = System.getProperty("os.name");
        if(os.contains("Windows")) {
            return new ShowLogOperationAppWindow();
        }
        return new ShowLogOperationAppLinux();
    }

    @Bean
    @ConditionalOnMissingBean(ShowLogOperationWeb.class)
    public ShowLogOperationWeb showLogOperationWeb(){
        String os = System.getProperty("os.name");
        if(os.contains("Windows")) {
            return new ShowLogOperationWebWindow();
        }
        return new ShowLogOperationWebLinux();
    }

    @Bean
    @ConditionalOnMissingBean(SystemFileOperation.class)
    public SystemFileOperation systemFileOperation(){
        String os = System.getProperty("os.name");
        if(os.contains("Windows")) {
            return new SystemFileOperationWindow();
        }
        return new SystemFileOperationLinux();
    }
}
