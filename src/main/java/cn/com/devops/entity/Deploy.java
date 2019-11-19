package cn.com.devops.entity;

import java.text.SimpleDateFormat;
import java.util.Date;

public class Deploy extends Pager{
    //唯一id
    private int id;
    //服务版本id
    private int editionId;
    private EditionInfo editionInfo;
    //分支id
    private int branchId;
    private Branch branch;
    //环境id
    private int envId;
    private Env env;
    private int esbId;
    private Env env2;
    private String esbName;
    //应用状态
    private String appStatus;
    //静态状态
    private String webStatus;
    private String branchStatus;
    private String appVersion;
    private String webVersion;
    //错误信息
    private String errorMsg;
    //状态 0:已废弃 1:使用中 default 1
    private String status;
    //备注
    private String remark;
    //保存日期
    private String saveDate;
    //保存时间
    private String saveTime;

    public Deploy(){
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        String str = sdf.format(new Date());
        String[] strs = str.split(" ");
        this.setSaveDate(strs[0]);
        this.setSaveTime(strs[1]);
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getBranchId() {
        return branchId;
    }

    public void setBranchId(int branchId) {
        this.branchId = branchId;
    }

    public int getEnvId() {
        return envId;
    }

    public void setEnvId(int envId) {
        this.envId = envId;
    }

    /**
     * 0:失败(无进程)
     * 1:活动(有进程并且check成功)
     * 2:准备就绪(有进程但check失败,weblogic未启动应用)
     * 3:应用报错(有进程但check失败,应用报错)
     * 4:打包中(正在打war包)
     * 5:打包失败(打war包失败)
     * 6:空闲
     */
    public String getAppStatus() {
        return appStatus;
    }

    public void setAppStatus(String appStatus) {
        this.appStatus = appStatus;
    }

    /**
     * 0:失败(无进程)
     * 1:活动(有进程并且check成功)
     * 2:准备就绪(有进程但check失败)
     * 4:打包中(正在打tar包)
     * 5:打包失败(打tar包失败)
     * 6:空闲
     */
    public String getWebStatus() {
        return webStatus;
    }

    public void setWebStatus(String webStatus) {
        this.webStatus = webStatus;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }

    public String getSaveDate() {
        return saveDate;
    }

    public void setSaveDate(String saveDate) {
        this.saveDate = saveDate;
    }

    public String getSaveTime() {
        return saveTime;
    }

    public void setSaveTime(String saveTime) {
        this.saveTime = saveTime;
    }

    public Branch getBranch() {
        return branch;
    }

    public void setBranch(Branch branch) {
        this.branch = branch;
    }

    public Env getEnv() {
        return env;
    }

    public void setEnv(Env env) {
        this.env = env;
    }

    public String getErrorMsg() {
        return errorMsg;
    }

    public void setErrorMsg(String errorMsg) {
        this.errorMsg = errorMsg;
    }

	public int getEditionId() {
		return editionId;
	}

	public void setEditionId(int editionId) {
		this.editionId = editionId;
	}

	public EditionInfo getEditionInfo() {
		return editionInfo;
	}

	public void setEditionInfo(EditionInfo editionInfo) {
		this.editionInfo = editionInfo;
	}

	public String getAppVersion() {
		return appVersion;
	}

	public void setAppVersion(String appVersion) {
		this.appVersion = appVersion;
	}

	public String getWebVersion() {
		return webVersion;
	}

	public void setWebVersion(String webVersion) {
		this.webVersion = webVersion;
	}

	public String getEsbName() {
		return esbName;
	}

	public void setEsbName(String esbName) {
		this.esbName = esbName;
	}

	public String getBranchStatus() {
		return branchStatus;
	}

	public void setBranchStatus(String branchStatus) {
		this.branchStatus = branchStatus;
	}

	public int getEsbId() {
		return esbId;
	}

	public void setEsbId(int esbId) {
		this.esbId = esbId;
	}

	public Env getEnv2() {
		return env2;
	}

	public void setEnv2(Env env2) {
		this.env2 = env2;
	}
	
}
