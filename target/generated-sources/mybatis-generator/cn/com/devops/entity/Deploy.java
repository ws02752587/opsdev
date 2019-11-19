package cn.com.devops.entity;

import java.io.Serializable;

public class Deploy implements Serializable {
    private Integer id;

    private Integer editionId;

    private Integer branchId;

    private Integer envId;

    private String appStatus;

    private String webStatus;

    private String errorMsg;

    private String appVersion;

    private String webVersion;

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

    public Integer getEditionId() {
        return editionId;
    }

    public void setEditionId(Integer editionId) {
        this.editionId = editionId;
    }

    public Integer getBranchId() {
        return branchId;
    }

    public void setBranchId(Integer branchId) {
        this.branchId = branchId;
    }

    public Integer getEnvId() {
        return envId;
    }

    public void setEnvId(Integer envId) {
        this.envId = envId;
    }

    public String getAppStatus() {
        return appStatus;
    }

    public void setAppStatus(String appStatus) {
        this.appStatus = appStatus == null ? null : appStatus.trim();
    }

    public String getWebStatus() {
        return webStatus;
    }

    public void setWebStatus(String webStatus) {
        this.webStatus = webStatus == null ? null : webStatus.trim();
    }

    public String getErrorMsg() {
        return errorMsg;
    }

    public void setErrorMsg(String errorMsg) {
        this.errorMsg = errorMsg == null ? null : errorMsg.trim();
    }

    public String getAppVersion() {
        return appVersion;
    }

    public void setAppVersion(String appVersion) {
        this.appVersion = appVersion == null ? null : appVersion.trim();
    }

    public String getWebVersion() {
        return webVersion;
    }

    public void setWebVersion(String webVersion) {
        this.webVersion = webVersion == null ? null : webVersion.trim();
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