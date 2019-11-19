package cn.com.devops.entity;

import javax.validation.constraints.NotNull;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Map;

public class Branch extends Pager{
    //唯一id
    private int id;
    //上线版本
    private String beonlineDate;
    private int beonlineId;
    private BeonlineEdition beonlineEdition;
    //小组
    private String groupName;
    //生产分支id
    private int proBranchId;
    private Branch proBranch;
    private String newBranchName;
    private Map<String,String> mergeStatus;
    private String editionInfo;
    //分支名称
    @NotNull(message = "分支名称不能为空！")
    private String branchName;
    //工件号
    @NotNull(message = "工件号不能为空！")
    private String branchVersion;
    //svn地址
    @NotNull(message = "svn地址不能为空！")
    private String svnAddress;
    private String relnum;
    private String beonlineData;
    //需求编号
    private String demandId;
    //需求中文描述
    private String demandComment;
    //生产分支标识 0:不是 1:是
    private String proFlag;
    //错误信息
    private String errorMsg;

    //状态 0:已废弃 1:使用中 2:分支下载失败 3:分支下载中 default 3
    /**
     * 0:已废弃
     * 1:使用中
     * 2:分支下载失败
     * 3:分支下载中
     * 4:更新失败
     */
    private String status;
    //备注
    private String remark;
    //保存日期
    private String saveDate;
    //保存时间
    private String saveTime;

    public Branch(){
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

    public int getProBranchId() {
        return proBranchId;
    }

    public void setProBranchId(int proBranchId) {
        this.proBranchId = proBranchId;
    }

    public String getBranchName() {
        return branchName;
    }

    public void setBranchName(String branchName) {
        this.branchName = branchName;
    }

    public String getBranchVersion() {
        return branchVersion;
    }

    public void setBranchVersion(String branchVersion) {
        this.branchVersion = branchVersion;
    }

    public String getSvnAddress() {
        return svnAddress;
    }

    public void setSvnAddress(String svnAddress) {
        this.svnAddress = svnAddress;
    }

    public String getDemandId() {
        return demandId;
    }

    public void setDemandId(String demandId) {
        this.demandId = demandId;
    }

    public String getDemandComment() {
        return demandComment;
    }

    public void setDemandComment(String demandComment) {
        this.demandComment = demandComment;
    }

    public String getProFlag() {
        return proFlag;
    }

    public void setProFlag(String proFlag) {
        this.proFlag = proFlag;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
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

    public String getErrorMsg() {
        return errorMsg;
    }

    public void setErrorMsg(String errorMsg) {
        this.errorMsg = errorMsg;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }

	public String getBeonlineDate() {
		return beonlineDate;
	}

	public void setBeonlineDate(String beonlineDate) {
		this.beonlineDate = beonlineDate;
	}

	public String getGroupName() {
		return groupName;
	}

	public void setGroupName(String groupName) {
		this.groupName = groupName;
	}

	public Branch getProBranch() {
		return proBranch;
	}

	public void setProBranch(Branch proBranch) {
		this.proBranch = proBranch;
	}

	public String getNewBranchName() {
		return newBranchName;
	}

	public void setNewBranchName(String newBranchName) {
		this.newBranchName = newBranchName;
	}

	public Map<String, String> getMergeStatus() {
		return mergeStatus;
	}

	public void setMergeStatus(Map<String, String> mergeStatus) {
		this.mergeStatus = mergeStatus;
	}

	public String getEditionInfo() {
		return editionInfo;
	}

	public void setEditionInfo(String editionInfo) {
		this.editionInfo = editionInfo;
	}

	public int getBeonlineId() {
		return beonlineId;
	}

	public void setBeonlineId(int beonlineId) {
		this.beonlineId = beonlineId;
	}

	public BeonlineEdition getBeonlineEdition() {
		return beonlineEdition;
	}

	public void setBeonlineEdition(BeonlineEdition beonlineEdition) {
		this.beonlineEdition = beonlineEdition;
	}

	public String getRelnum() {
		return relnum;
	}

	public void setRelnum(String relnum) {
		this.relnum = relnum;
	}

	public String getBeonlineData() {
		return beonlineData;
	}

	public void setBeonlineData(String beonlineData) {
		this.beonlineData = beonlineData;
	}
	
}
