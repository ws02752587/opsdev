package cn.com.devops.service.command;

import cn.com.devops.base.App;
import cn.com.devops.entity.Branch;
import cn.com.devops.entity.Deploy;
import cn.com.devops.entity.Env;
import cn.com.devops.enums.DeployEnum;
import cn.com.devops.service.DeployService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.util.FileCopyUtils;

import java.io.IOException;

public class PackageOperationAppLinux extends PackageOperationApp {
    @Value("${sh.url}")
    private String sh_url;

    @Autowired
    private DeployService deployService;

    @Override
    public boolean packge(Deploy deploy) {
        boolean flag = false;
        Branch branch = deploy.getBranch();
        if(branch == null)return false;
        Env env = deploy.getEnv();
        if(env == null)return false;
        String url = branch.getSvnAddress();
        url = url.replace("/","-");
        String name = deploy.getEditionInfo().getAppName();
        try {
            Process process = Runtime.getRuntime().exec(sh_url+"packageWar.sh",
                    new String[]{url, name, env.getAppIp()});
            process.waitFor();
            byte[] bytes = FileCopyUtils.copyToByteArray(process.getInputStream());
            String str = new String(bytes, "UTF-8");
            Deploy deploy1 = new Deploy();
            deploy1.setId(deploy.getId());
            if(str.contains(App.SH_SUCCESS)){
                flag = true;
                deploy1.setAppStatus(DeployEnum.READY.toString());
            }else{
                flag = false;
                deploy1.setErrorMsg(str);
                deploy1.setAppStatus(DeployEnum.PACKAGE_FAIL.toString());
            }
            deployService.update(deploy1);
        } catch (IOException e) {
            e.printStackTrace();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        return flag;
    }

    @Override
    public boolean tarx(Deploy deploy) {
        return true;
    }
}
