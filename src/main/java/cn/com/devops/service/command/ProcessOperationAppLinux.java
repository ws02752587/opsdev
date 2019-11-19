package cn.com.devops.service.command;

import cn.com.devops.base.App;
import cn.com.devops.entity.Branch;
import cn.com.devops.entity.Deploy;
import cn.com.devops.entity.EditionInfo;
import cn.com.devops.entity.Env;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.util.FileCopyUtils;

import java.io.IOException;

public class ProcessOperationAppLinux extends ProcessOperationApp {
    @Value("${sh.url}")
    private String sh_url;

    @Override
    public boolean isProcess(Deploy deploy) {
        try {
            Env env  = deploy.getEnv();
            if(env == null)return false;
            Branch branch = deploy.getBranch();
            if(branch == null)return false;
            String url = env.getAppIp();
            String name = deploy.getEditionInfo().getProcessName();
            Process process = Runtime.getRuntime().exec(sh_url+"isAppProcess.sh",
                    new String[]{url, name});
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
            Branch branch = deploy.getBranch();
            if(branch == null)return false;
            String url = env.getAppIp();
            String name = "";
            Process process = Runtime.getRuntime().exec(sh_url+"killAppProcess.sh",
                    new String[]{url, name});
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
            Branch branch = deploy.getBranch();
            if(branch == null)return false;
            String url = env.getAppIp();
            String name = deploy.getEditionInfo().getProcessName();
            Process process = Runtime.getRuntime().exec(sh_url+"resetAppProcess.sh",
                    new String[]{url, name});
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
    public boolean resetWeblogicService(Deploy deploy) {
        try {
            Env env  = deploy.getEnv();
            if(env == null)return false;
            Branch branch = deploy.getBranch();
            if(branch == null)return false;
            String url = env.getAppIp();
            EditionInfo editionInfo = deploy.getEditionInfo();
            String name = editionInfo.getAppName();
            String processName = editionInfo.getProcessName();
            Process process = Runtime.getRuntime().exec(sh_url+"resetWeblogicService.sh",
                    new String[]{url, processName, name});
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
	public boolean stopWeblogicService(Deploy deploy) {
		return false;
	}
}
