package cn.com.devops.enums;

public enum EditionInfoEnum {
    SHMBank("0"),SHCMBank("1"),MobileManager("2");
    private String status;
    private EditionInfoEnum(String status) {
        this.status = status;
    }
    public String toString(){
    	return status;
    }
}
