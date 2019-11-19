package cn.com.devops.entity;

import javax.validation.constraints.NotNull;
import java.text.SimpleDateFormat;
import java.util.Date;

public class DB {
    //唯一id
    private int id;
    //数据库ip地址
    @NotNull(message = "数据库ip地址不能为空！")
    private String ip;
    //数据库端口号
    @NotNull(message = "数据库端口号不能为空！")
    private String port;
    //数据库用户名
    private String username;
    //数据库应用名称
    private String databaseName;
    //数据库服务名称
    private String serverName;
    //数据库别名
    private String alias;
    //是否直连或者链接数据源 0:直连 1:数据源
    private String isSource;
    //状态 0:已废弃 1:使用中 default 1
    private String status;
    //备注
    private String remark;
    //保存日期
    private String saveDate;
    //保存时间
    private String saveTime;
    public DB(){
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

    public String getIp() {
        return ip;
    }

    public void setIp(String ip) {
        this.ip = ip;
    }

    public String getPort() {
        return port;
    }

    public void setPort(String port) {
        this.port = port;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getDatabaseName() {
        return databaseName;
    }

    public void setDatabaseName(String databaseName) {
        this.databaseName = databaseName;
    }

    public String getServerName() {
        return serverName;
    }

    public void setServerName(String serverName) {
        this.serverName = serverName;
    }

    public String getAlias() {
        return alias;
    }

    public void setAlias(String alias) {
        this.alias = alias;
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

    public String getIsSource() {
        return isSource;
    }

    public void setIsSource(String isSource) {
        this.isSource = isSource;
    }
}
