package cn.com.devops.service.command;

import cn.com.devops.base.App;
import cn.com.devops.entity.*;
import cn.com.devops.enums.EditionInfoEnum;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.util.FileCopyUtils;
import org.springframework.util.StringUtils;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

public class SystemFileOperationLinux extends SystemFileOperation {
    @Value("${sh.url}")
    private String sh_url;

    @Autowired
    private ProcessOperationApp processOperationApp;

    @Override
    public DB queryJdbcUrl(Deploy deploy) {
        DB db = new DB();
        try {
            Env env  = deploy.getEnv();
            if(env == null)return db;
            Branch branch = deploy.getBranch();
            if(branch == null)return db;
            String type = "";
            String url = env.getAppIp();
            Process process = null;
            if(EditionInfoEnum.MobileManager.equals(type)){
                process = Runtime.getRuntime().exec(sh_url+"queryMobileManagerJdbcUrl.sh",
                        new String[]{url});
            }else{
                ServerEdition serverEdition = null;
                if(serverEdition == null)return db;
                String name = serverEdition.getEditionInfo().getAppName();
                process = Runtime.getRuntime().exec(sh_url+"queryJdbcUrl.sh",
                        new String[]{url, name});
            }
            process.waitFor();
            byte[] bytes = FileCopyUtils.copyToByteArray(process.getInputStream());
            String str = new String(bytes, "UTF-8");
            if(StringUtils.isEmpty(str)){
                return db;
            }
            String[] strs = str.split(":");
            db.setIsSource(strs[0]);
            db.setIp(strs[1]);
            db.setPort(strs[2]);
        } catch (IOException e) {
            e.printStackTrace();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        return db;
    }

    @Override
    public Map<String, String> queryMD5(String url) {
        Map<String, String> map = new HashMap<String, String>();
        try {
            Process process = Runtime.getRuntime().exec(sh_url+"queryMD5.sh",
                        new String[]{url});
            process.waitFor();
            byte[] bytes = FileCopyUtils.copyToByteArray(process.getInputStream());
            String str = new String(bytes, "UTF-8");
            if(StringUtils.isEmpty(str)){
                return map;
            }
            String[] strs = str.split(":");
            map.put(strs[0], strs[1]);
            map.put(strs[2], strs[3]);
        } catch (IOException e) {
            e.printStackTrace();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        return map;
    }

    @Override
    public Env queryEsbUrl(Deploy deploy) {
        Env resultEnv = new Env();
        try {
            Env env  = deploy.getEnv();
            if(env == null)return resultEnv;
            Branch branch = deploy.getBranch();
            if(branch == null)return resultEnv;
            String type = deploy.getEditionInfo().getType();
            String url = env.getAppIp();
            Process process = null;
            if(EditionInfoEnum.MobileManager.equals(type)){
                process = Runtime.getRuntime().exec(sh_url+"queryMobileManagerEsbUrl.sh",
                        new String[]{url});
            }else{
                process = Runtime.getRuntime().exec(sh_url+"queryEsbUrl.sh",
                        new String[]{url});
            }
            process.waitFor();
            byte[] bytes = FileCopyUtils.copyToByteArray(process.getInputStream());
            String str = new String(bytes, "UTF-8");
            if(StringUtils.isEmpty(str)){
                return resultEnv;
            }
            String[] strs = str.split(":");
            resultEnv.setAppIp(strs[0]);
            resultEnv.setAppPort(strs[1]);
        } catch (IOException e) {
            e.printStackTrace();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        return resultEnv;
    }

    @Override
    public boolean updateJdbcUrl(Deploy deploy, DB db) {
        return true;
    }

    @Override
    public boolean updateEsbUrl(Deploy deploy) {
        try {
            Env env  = deploy.getEnv();
            if(env == null)return false;
            Branch branch = deploy.getBranch();
            if(branch == null)return false;
            String type = "";
            String url = env.getAppIp();
            Process process = null;
            if(EditionInfoEnum.MobileManager.equals(type)){
                process = Runtime.getRuntime().exec(sh_url+"updateMobileManagerEsbUrl.sh",
                        new String[]{url});
            }else{
                process = Runtime.getRuntime().exec(sh_url+"updateEsbUrl.sh",
                        new String[]{url});
            }
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
    public boolean updateDataSource(Deploy deploy) {
        try {
            Env env  = deploy.getEnv();
            if(env == null)return false;
            Branch branch = deploy.getBranch();
            if(branch == null)return false;
            String type = "";
            String url = env.getAppIp();
            Process process = null;
            if(EditionInfoEnum.MobileManager.equals(type)){
                process = Runtime.getRuntime().exec(sh_url+"updateMobileManagerDataSource.sh",
                        new String[]{url});
            }else{
                process = Runtime.getRuntime().exec(sh_url+"updateDataSource.sh",
                        new String[]{url});
            }
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
