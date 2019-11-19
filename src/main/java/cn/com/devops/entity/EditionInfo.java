package cn.com.devops.entity;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

public class EditionInfo extends Pager{
    //唯一id
    private int id;
    private List<EditionVersion> editionVersions;
    private List<ServerEdition> serverEditions;
    //版本名称:标准版/企业版/后管
    private String editionName;
    //版本类型 0:标准版 1:企业版 2:后管
    //应用名称:SHMbank/SHOMbank/MobileManager
    private String appName;
    //静态名称:iweb_SHMbank
    private String webName;
    //进程名称:mobileNewServer/mobileCMServer
    private String processName;
    //数据源:mobileNewB
    private String dataSource;
    //日志地址:/mobile/app/newlogs
    private String logAddress;
    //日志名称
    private String logName;
    //应用check地址
    private String appCheckUrl;
    //静态check地址
    private String webCheckUrl;
    //版本类型 0:标准版 1:企业版 2:后管
    private String type;
    //状态 0:已废弃 1:使用中 default 1
    private String status;
    //备注
    private String remark;
    //保存日期
    private String saveDate;
    //保存时间
    private String saveTime;

    public EditionInfo(){
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

    public String getEditionName() {
        return editionName;
    }

    public void setEditionName(String editionName) {
        this.editionName = editionName;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
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

    public List<EditionVersion> getEditionVersions() {
        return editionVersions;
    }

    public void setEditionVersions(List<EditionVersion> editionVersions) {
        this.editionVersions = editionVersions;
    }

    public List<ServerEdition> getServerEditions() {
        return serverEditions;
    }

    public void setServerEditions(List<ServerEdition> serverEditions) {
        this.serverEditions = serverEditions;
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

    public String getProcessName() {
        return processName;
    }

    public void setProcessName(String processName) {
        this.processName = processName;
    }

    public String getDataSource() {
        return dataSource;
    }

    public void setDataSource(String dataSource) {
        this.dataSource = dataSource;
    }

    public String getLogAddress() {
        return logAddress;
    }

    public void setLogAddress(String logAddress) {
        this.logAddress = logAddress;
    }

    public String getAppCheckUrl() {
        return appCheckUrl;
    }

    public void setAppCheckUrl(String appCheckUrl) {
        this.appCheckUrl = appCheckUrl;
    }

    public String getWebCheckUrl() {
        return webCheckUrl;
    }

    public void setWebCheckUrl(String webCheckUrl) {
        this.webCheckUrl = webCheckUrl;
    }

    public String getLogName() {
        return logName;
    }

    public void setLogName(String logName) {
        this.logName = logName;
    }
}
