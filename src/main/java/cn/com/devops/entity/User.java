package cn.com.devops.entity;

import cn.com.devops.base.App;

import org.springframework.util.DigestUtils;

import com.alibaba.druid.util.StringUtils;

import javax.validation.constraints.NotNull;

import java.text.SimpleDateFormat;
import java.util.Date;

public class User extends Pager implements Cloneable{
    //唯一id-自增
    private int id;
    //用户名
    @NotNull(message = "用户名不能为空！")
    private String username;
    //姓名
    @NotNull(message = "姓名不能为空！")
    private String name;
    //登录密码
    @NotNull(message = "登录密码不能为空！")
    private String password;
    private String newpassword;
    private String transpwd;
    //年龄 
    private String age;
    //性别 0:男 1:女 2:保密
    private String sex;
    //登录过期时间(单位:天)
    private int loginExptime=30;
    private String qq;
    //邮箱
    private String email;
    //状态 0:已废弃 1:使用中 default 1
    private String status;
    //备注
    private String remark;
    //保存日期
    private String saveDate;
    //保存时间
    private String saveTime;
    

    public User(){
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        String str = sdf.format(new Date());
        String[] strs = str.split(" ");
        this.saveDate = strs[0];
        this.saveTime = strs[1];
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPassword() {
    	if(StringUtils.isEmpty(password)){
    		return password;
    	}
        StringBuilder sb = new StringBuilder(App.MD5_DIGEST);
        return DigestUtils.appendMd5DigestAsHex(this.password.getBytes(),sb).toString();
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getAge() {
        return age;
    }

    public void setAge(String age) {
        this.age = age;
    }

    public String getSex() {
        return sex;
    }

    public void setSex(String sex) {
        this.sex = sex;
    }

    public String getQq() {
        return qq;
    }

    public void setQq(String qq) {
        this.qq = qq;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
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

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }

    public int getLoginExptime() {
        return loginExptime;
    }

    public void setLoginExptime(int loginExptime) {
        this.loginExptime = loginExptime;
    }
    
    public String getNewpassword() {
    	if(StringUtils.isEmpty(newpassword)){
    		return newpassword;
    	}
    	StringBuilder sb = new StringBuilder(App.MD5_DIGEST);
        return DigestUtils.appendMd5DigestAsHex(this.newpassword.getBytes(),sb).toString();
	}

	public void setNewpassword(String newpassword) {
		this.newpassword = newpassword;
	}

	public String getTranspwd() {
		if(StringUtils.isEmpty(transpwd)){
    		return transpwd;
    	}
    	StringBuilder sb = new StringBuilder(App.MD5_DIGEST);
        return DigestUtils.appendMd5DigestAsHex(this.transpwd.getBytes(),sb).toString();
	}

	public void setTranspwd(String transpwd) {
		this.transpwd = transpwd;
	}

	public String toString(){
    	return "{ username: "+ this.username+", name: "+this.name + "}";
    }
    
    public User clone(){
    	try {
			return (User)super.clone();
		} catch (CloneNotSupportedException e) {
			e.printStackTrace();
		}
		return null;
    }
}
