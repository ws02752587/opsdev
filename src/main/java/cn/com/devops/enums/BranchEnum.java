package cn.com.devops.enums;

public enum BranchEnum {
    BEONLE("0"),USE("1");
    private String status;
    private BranchEnum(String status) {
        this.status = status;
    }
    public String toString(){
    	return status;
    }
}
