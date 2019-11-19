package cn.com.devops.entity;

import java.io.Serializable;

public class Branch implements Serializable {
    private Integer id;

    private String beonlineDate;

    private String groupName;

    private Integer proBranchId;

    private String branchName;

    private String branchVersion;

    private String svnAddress;

    private String demandId;

    private String demandComment;

    private String proFlag;

    private String errorMsg;

    private String status;

    private String remark;

    private String saveDate;

    private String saveTime;

    private static final long serialVersionUID = 1L;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getBeonlineDate() {
        return beonlineDate;
    }

    public void setBeonlineDate(String beonlineDate) {
        this.beonlineDate = beonlineDate == null ? null : beonlineDate.trim();
    }

    public String getGroupName() {
        return groupName;
    }

    public void setGroupName(String groupName) {
        this.groupName = groupName == null ? null : groupName.trim();
    }

    public Integer getProBranchId() {
        return proBranchId;
    }

    public void setProBranchId(Integer proBranchId) {
        this.proBranchId = proBranchId;
    }

    public String getBranchName() {
        return branchName;
    }

    public void setBranchName(String branchName) {
        this.branchName = branchName == null ? null : branchName.trim();
    }

    public String getBranchVersion() {
        return branchVersion;
    }

    public void setBranchVersion(String branchVersion) {
        this.branchVersion = branchVersion == null ? null : branchVersion.trim();
    }

    public String getSvnAddress() {
        return svnAddress;
    }

    public void setSvnAddress(String svnAddress) {
        this.svnAddress = svnAddress == null ? null : svnAddress.trim();
    }

    public String getDemandId() {
        return demandId;
    }

    public void setDemandId(String demandId) {
        this.demandId = demandId == null ? null : demandId.trim();
    }

    public String getDemandComment() {
        return demandComment;
    }

    public void setDemandComment(String demandComment) {
        this.demandComment = demandComment == null ? null : demandComment.trim();
    }

    public String getProFlag() {
        return proFlag;
    }

    public void setProFlag(String proFlag) {
        this.proFlag = proFlag == null ? null : proFlag.trim();
    }

    public String getErrorMsg() {
        return errorMsg;
    }

    public void setErrorMsg(String errorMsg) {
        this.errorMsg = errorMsg == null ? null : errorMsg.trim();
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status == null ? null : status.trim();
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark == null ? null : remark.trim();
    }

    public String getSaveDate() {
        return saveDate;
    }

    public void setSaveDate(String saveDate) {
        this.saveDate = saveDate == null ? null : saveDate.trim();
    }

    public String getSaveTime() {
        return saveTime;
    }

    public void setSaveTime(String saveTime) {
        this.saveTime = saveTime == null ? null : saveTime.trim();
    }
}