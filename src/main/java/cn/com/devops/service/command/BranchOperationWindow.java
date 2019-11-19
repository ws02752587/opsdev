package cn.com.devops.service.command;

import org.apache.commons.codec.binary.Base64;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;

import cn.com.devops.base.App;
import cn.com.devops.entity.Branch;
import cn.com.devops.entity.Deploy;
import cn.com.devops.entity.Svn;
import cn.com.devops.enums.DeployEnum;
import cn.com.devops.service.DeployService;
import cn.com.devops.service.SvnService;

public class BranchOperationWindow implements IBranchOperation{
	@Value("${sh.url}")
    private String sh_url;
	
	private Logger logger = LoggerFactory.getLogger(BranchOperationWindow.class);
	
	@Autowired
    private SvnService svnService;

    @Autowired
    private DeployService deployService;
    
    @Override
    public boolean downBranch(Deploy deploy) {
    	boolean flag = false;
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
        Deploy deploy1 = new Deploy();
        deploy1.setId(deploy.getId());
        if("success".contains(App.SH_SUCCESS)){
            flag = true;
            deploy1.setBranchStatus(DeployEnum.BRANCHSUCCESS.toString());
        }else{
            flag = false;
            deploy1.setErrorMsg("error");
            deploy1.setBranchStatus(DeployEnum.DOWNFAIL.toString());
        }
        deployService.update(deploy1);
    	logger.info("svnBaseAddress:{},url:{},name:{},svnName:{},svnPwd:{}",svn.getSvnBaseAddress(), url, name, svn.getSvnName(),svnpwd);
        return flag;
    }
    
    /**
     * 更新分支
     */
    @Override
    public boolean updateBranch(Deploy deploy) {
    	return false;
    }

    @Override
    public boolean clearBranch(Branch branch) {
    	if(branch == null){return false;}
        String url = branch.getSvnAddress();
        url = url.replace("/","-");
        url = url.trim();
        logger.info("cd /mobile/app/devops/branch");
        logger.info("cd url:{}", url);
//        Process process = Runtime.getRuntime().exec(sh_url+"clearBranch.sh",
//                new String[]{url});
//        process.waitFor();
//        byte[] bytes = FileCopyUtils.copyToByteArray(process.getInputStream());
        String str = "success";
        if(str.contains(App.SH_SUCCESS)){
            return true;
        }
        return true;
    }
}
