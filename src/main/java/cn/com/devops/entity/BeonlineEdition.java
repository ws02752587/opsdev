package cn.com.devops.entity;

import javax.validation.constraints.NotNull;
import java.text.SimpleDateFormat;
import java.util.Date;

public class BeonlineEdition extends Pager{
    //唯一id
    private int id;
    //上线日期
    @NotNull(message = "上线日期不能为空！")
    private String beonlineDate;
    //版本标识
    private String editionMark;
    //状态 0:已废弃 1:使用中 default 1
    private String status;
    //备注
    private String remark;
    //保存日期
    private String saveDate;
    //保存时间
    private String saveTime;

    public BeonlineEdition(){
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

    public String getBeonlineDate() {
        return beonlineDate;
    }

    public void setBeonlineDate(String beonlineDate) {
        this.beonlineDate = beonlineDate;
    }

    public String getEditionMark() {
        return editionMark;
    }

    public void setEditionMark(String editionMark) {
        this.editionMark = editionMark;
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
