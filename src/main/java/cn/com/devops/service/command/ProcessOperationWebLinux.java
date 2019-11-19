package cn.com.devops.service.command;

import cn.com.devops.base.App;
import cn.com.devops.entity.Deploy;
import cn.com.devops.entity.Env;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.util.FileCopyUtils;

import java.io.IOException;

public class ProcessOperationWebLinux extends ProcessOperationWeb {
    @Value("${sh.url}")
    private String sh_url;

    @Override
    public boolean isProcess(Deploy deploy) {
        try {
            Env env  = deploy.getEnv();
            if(env == null)return false;
            String url = env.getWebIp();
            Process process = Runtime.getRuntime().exec(sh_url+"isWebProcess.sh",
                    new String[]{url});
            process.waitFor();
            byte[] bytes = FileCopyUtils.copyToByteArray(process.getInputStream());
            String str = new String(bytes, "UTF-8");
            if(str.contains(App.SH_SUCCESS)){
                return true;
            }
        } catch (IOException e) {
            e.printStackTrace();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        return false;
    }

    @Override
    public boolean killProcess(Deploy deploy) {
        try {
            Env env  = deploy.getEnv();
            if(env == null)return false;
            String url = env.getWebIp();
            Process process = Runtime.getRuntime().exec(sh_url+"killWebProcess.sh",
                    new String[]{url});
            process.waitFor();
            byte[] bytes = FileCopyUtils.copyToByteArray(process.getInputStream());
            String str = new String(bytes, "UTF-8");
            if(str.contains(App.SH_SUCCESS)){
                return true;
            }
        } catch (IOException e) {
            e.printStackTrace();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        return false;
    }

    @Override
    public boolean resetProcess(Deploy deploy) {
        try {
            Env env  = deploy.getEnv();
            if(env == null)return false;
            String url = env.getWebIp();
            Process process = Runtime.getRuntime().exec(sh_url+"resetWebProcess.sh",
                    new String[]{url});
            process.waitFor();
            byte[] bytes = FileCopyUtils.copyToByteArray(process.getInputStream());
            String str = new String(bytes, "UTF-8");
            if(str.contains(App.SH_SUCCESS)){
                return true;
            }
        } catch (IOException e) {
            e.printStackTrace();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        return false;
    }
}
