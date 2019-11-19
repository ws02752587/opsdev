package cn.com.devops.service.command;

import cn.com.devops.base.App;
import cn.com.devops.entity.Branch;
import cn.com.devops.entity.Deploy;
import cn.com.devops.entity.Svn;
import cn.com.devops.enums.DeployEnum;
import cn.com.devops.service.DeployService;
import cn.com.devops.service.SvnService;

import org.apache.commons.codec.binary.Base64;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.util.FileCopyUtils;

import java.io.IOException;

public class BranchOperationLinux implements IBranchOperation{
    @Value("${sh.url}")
    private String sh_url;
    
	private Logger logger = LoggerFactory.getLogger(BranchOperationLinux.class);

    @Autowired
    private SvnService svnService;

    @Autowired
    private DeployService deployService;

    public boolean downBranch(Deploy deploy){
        boolean flag = false;
        try {
            if(deploy == null){return false;}
            Branch branch = deploy.getBranch();
            if(branch == null)return false;
            //0:手机银行 1:移动营销
            Svn svn = svnService.queryFirst("0");
            if(svn == null)return false;
            String url = branch.getSvnAddress();
            url = url.replace("/","-");
            String name = deploy.getEditionInfo().getAppName();
            String svnpwd = svn.getSvnPassword();
            svnpwd = new String(Base64.decodeBase64(svnpwd.getBytes()));
            Process process = Runtime.getRuntime().exec(sh_url+"downBranch.sh",
                    new String[]{svn.getSvnBaseAddress(), url, name, svn.getSvnName(),svnpwd});
            process.waitFor();
            byte[] bytes = FileCopyUtils.copyToByteArray(process.getInputStream());
            String str = new String(bytes, "UTF-8");

            Deploy deploy1 = new Deploy();
            deploy1.setId(deploy.getId());
            if(str.contains(App.SH_SUCCESS)){
                flag = true;
                deploy1.setBranchStatus(DeployEnum.BRANCHSUCCESS.toString());
            }else{
                flag = false;
                deploy1.setErrorMsg(str);
                deploy1.setBranchStatus(DeployEnum.DOWNFAIL.toString());
            }
            deployService.update(deploy1);
        } catch (IOException e) {
            e.printStackTrace();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        return flag;
    }

    public boolean updateBranch(Deploy deploy){
        boolean flag = false;
        try {
            if(deploy == null){return false;}
            Branch branch = deploy.getBranch();
            if(branch == null)return false;
            String url = branch.getSvnAddress();
            url = url.replace("/","-");
            String name = "";
            Process process = Runtime.getRuntime().exec(sh_url+"updateBranch.sh",
                    new String[]{url, name});
            process.waitFor();
            byte[] bytes = FileCopyUtils.copyToByteArray(process.getInputStream());
            String str = new String(bytes, "UTF-8");
            Deploy deploy1 = new Deploy();
            deploy1.setId(deploy.getId());
            if(str.contains(App.SH_SUCCESS)){
                flag = true;
                deploy1.setBranchStatus(DeployEnum.BRANCHSUCCESS.toString());
            }else{
                flag = false;
                deploy1.setErrorMsg(str);
                deploy1.setBranchStatus(DeployEnum.UPDATEFAIL.toString());
            }
            deployService.update(deploy1);
        } catch (IOException e) {
            e.printStackTrace();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        return flag;
    }

    public boolean clearBranch(Branch branch){
        try {
            if(branch == null){return false;}
            String url = branch.getSvnAddress();
            url = url.replace("/","-");
            url = url.trim();
            Process process = Runtime.getRuntime().exec(sh_url+"clearBranch.sh",
                    new String[]{url});
            process.waitFor();
            byte[] bytes = FileCopyUtils.copyToByteArray(process.getInputStream());
            String str = new String(bytes, "UTF-8");
            if(str.contains(App.SH_SUCCESS)){
            	logger.info("clearBranch success; url: {} ", url);
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
