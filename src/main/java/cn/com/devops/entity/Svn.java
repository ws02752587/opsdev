package cn.com.devops.entity;

import javax.validation.constraints.NotNull;
import java.text.SimpleDateFormat;
import java.util.Date;

public class Svn extends Pager{
    //唯一id
    private int id;
    //svn账号
    @NotNull(message = "svn账号不能为空！")
    private String svnName;
    //svn登录密码
    @NotNull(message = "svn登录密码不能为空！")
    private String svnPassword;
    //svn基地址
    @NotNull(message = "svn基地址不能为空！")
    private String svnBaseAddress;
    //snv类型 0:手机银行 1:移动营销
    private String svnType;
    //状态 0:已废弃 1:使用中 default 1
    private String status;
    //备注
    private String remark;
    //保存日期
    private String saveDate;
    //保存时间
    private String saveTime;
    public Svn(){
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

    public String getSvnName() {
        return svnName;
    }

    public void setSvnName(String svnName) {
        this.svnName = svnName;
    }

    public String getSvnPassword() {
        return svnPassword;
    }

    public void setSvnPassword(String svnPassword) {
        this.svnPassword = svnPassword;
    }

    public String getSvnBaseAddress() {
        return svnBaseAddress;
    }

    public void setSvnBaseAddress(String svnBaseAddress) {
        this.svnBaseAddress = svnBaseAddress;
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

    public String getSvnType() {
        return svnType;
    }

    public void setSvnType(String svnType) {
        this.svnType = svnType;
    }
}
