package cn.com.devops.enums;

public enum EditionEnum {
    WEB_TAR("0"),APP("2"),
    APP_TAR("3"),WEB("1");
    private String status;
    private EditionEnum(String status) {
        this.status = status;
    }
    public String toString(){
    	return status;
    }
}
