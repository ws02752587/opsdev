package cn.com.devops.entity;

import javax.validation.constraints.NotNull;
import java.text.SimpleDateFormat;
import java.util.Date;

public class Env extends Pager{
    //唯一id
    private int id;
    //环境名称
    @NotNull(message = "环境名称不能为空!")
    private String envName;
    //weblogic-ip地址
    @NotNull(message = "app-ip地址不能为空!")
    private String appIp;
    //apache-ip地址
    @NotNull(message = "web-ip地址不能为空!")
    private String webIp;
    //app端口
    @NotNull(message = "app端口不能为空!")
    private String appPort;
    //web端口
    @NotNull(message = "web端口不能为空!")
    private String webPort;
    //环境标识 0:手机银行 1:整合服务
    private String envMark;
    //状态 0:已废弃 1:使用中 default 1
    private String status;
    //备注
    private String remark;
    //保存日期
    private String saveDate;
    //保存时间
    private String saveTime;

    public Env(){
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

    public String getEnvName() {
        return envName;
    }

    public void setEnvName(String envName) {
        this.envName = envName;
    }

    public String getAppIp() {
        return appIp;
    }

    public void setAppIp(String appIp) {
        this.appIp = appIp;
    }

    public String getWebIp() {
        return webIp;
    }

    public void setWebIp(String webIp) {
        this.webIp = webIp;
    }

    public String getAppPort() {
        return appPort;
    }

    public void setAppPort(String appPort) {
        this.appPort = appPort;
    }

    public String getWebPort() {
        return webPort;
    }

    public void setWebPort(String webPort) {
        this.webPort = webPort;
    }

    public String getEnvMark() {
        return envMark;
    }

    public void setEnvMark(String envMark) {
        this.envMark = envMark;
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
}
