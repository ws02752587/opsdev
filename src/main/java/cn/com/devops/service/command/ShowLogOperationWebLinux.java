package cn.com.devops.service.command;

import cn.com.devops.entity.Deploy;
import cn.com.devops.entity.Env;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.util.FileCopyUtils;

import java.io.IOException;

public class ShowLogOperationWebLinux extends ShowLogOperationWeb {
    @Value("${sh.url}")
    private String sh_url;

    @Override
    public StringBuilder showLog(Deploy deploy, int... num) {
        StringBuilder sb = new StringBuilder();
        try {
            if(num == null || num.length == 0){
                return sb;
            }
            Env env  = deploy.getEnv();
            if(env == null)return sb;
            String url = env.getWebIp();
            Process process = Runtime.getRuntime().exec(sh_url+"showWebLog.sh",
                    new String[]{url, String.valueOf(num[0])});
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
