package cn.com.devops.enums;

/**
 * 日志enum类
 */
public enum OperationLogEnum {
    LOGIN("0"),LOGOUT("1"),START_SERVER("2"),
    STOP_SERVER("3"),RESET_SERVER("4"),
    UPDATE_ESB_URL("5"),UPDATE_DATA_SOURCE("6");
    private String status;
    private OperationLogEnum(String status) {
        this.status = status;
    }
    public String toString(){
    	return status;
    }
}
