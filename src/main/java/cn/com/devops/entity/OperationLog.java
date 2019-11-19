package cn.com.devops.entity;

import cn.com.devops.enums.OperationLogEnum;

import java.text.SimpleDateFormat;
import java.util.Date;

public class OperationLog {
    //唯一id
    private int id;
    //类型
    /**
     * 0:登录
     * 1:退出
     * 2:启动服务
     * 3:停止服务
     * 4:重启服务
     * 5:修改整合url地址
     * 6:修改数据源
     */
    private String type;
    //姓名
    private String name;
    //app-ip地址
    private String appIp;
    //app端口
    private String appPort;
    //ip地址
    private String newAppIp;
    //app端口
    private String newAppPort;
    //web-ip地址
    private String webIp;
    //应用名称:SHMbank/SHOMbank/MobileManager
    private String appName;
    //静态名称:iweb_SHMbank
    private String webName;
    //应用版本编号
    private String appVersion;
    //静态版本编号
    private String webVersion;
    //数据源:mobileNewB
    private String dataSource;
    //数据源:mobileNewB
    private String newDataSource;
    //备注
    private String remark;
    //保存日期
    private String saveDate;
    //保存时间
    private String saveTime;

    public OperationLog(){
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

    /**
     * 0:登录
     * 1:退出
     * 2:启动服务
     * 3:停止服务
     * 4:重启服务
     * 5:修改整合url地址
     * 6:修改数据源
     */
    public String getType() {
        return type;
    }

    public void setType(OperationLogEnum type) {
        this.type = type.toString();
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAppIp() {
        return appIp;
    }

    public void setAppIp(String appIp) {
        this.appIp = appIp;
    }

    public String getAppPort() {
        return appPort;
    }

    public void setAppPort(String appPort) {
        this.appPort = appPort;
    }

    public String getNewAppIp() {
        return newAppIp;
    }

    public void setNewAppIp(String newAppIp) {
        this.newAppIp = newAppIp;
    }

    public String getNewAppPort() {
        return newAppPort;
    }

    public void setNewAppPort(String newAppPort) {
        this.newAppPort = newAppPort;
    }

    public String getWebIp() {
        return webIp;
    }

    public void setWebIp(String webIp) {
        this.webIp = webIp;
    }

    public String getAppName() {
        return appName;
    }

    public void setAppName(String appName) {
        this.appName = appName;
    }

    public String getWebName() {
        return webName;
    }

    public void setWebName(String webName) {
        this.webName = webName;
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

    public String getDataSource() {
        return dataSource;
    }

    public void setDataSource(String dataSource) {
        this.dataSource = dataSource;
    }

    public String getNewDataSource() {
        return newDataSource;
    }

    public void setNewDataSource(String newDataSource) {
        this.newDataSource = newDataSource;
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
