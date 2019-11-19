package cn.com.devops.service.command;

import cn.com.devops.entity.*;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.util.FileCopyUtils;

import java.io.IOException;

public class ShowLogOperationAppLinux extends ShowLogOperationApp {
    @Value("${sh.url}")
    private String sh_url;

    @Override
    public StringBuilder showLog(Deploy deploy, int... num) {
        StringBuilder sb = new StringBuilder();
        try {
            if(num == null || num.length == 0){
                num[0] = 200;
            }
            Env env  = deploy.getEnv();
            if(env == null)return sb;
            EditionInfo editionInfo = deploy.getEditionInfo();
            String logsUrl = editionInfo.getLogAddress();
            String logName = editionInfo.getLogName();
            String url = env.getAppIp();
            Process process = Runtime.getRuntime().exec(sh_url+"showAppLog.sh",
                    new String[]{url, logsUrl , logName, String.valueOf(num[0])});
            process.waitFor();
            byte[] bytes = FileCopyUtils.copyToByteArray(process.getInputStream());
            return new StringBuilder(new String(bytes, "UTF-8"));
        } catch (IOException e) {
            e.printStackTrace();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        return sb;
    }
}
