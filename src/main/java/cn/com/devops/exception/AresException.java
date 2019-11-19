package cn.com.devops.exception;

/**
 * 本项目异常处理类
 */
public class AresException extends RuntimeException {
	private static final long serialVersionUID = -8200311011356522046L;
	private String code;
    public AresException(){
        super();
    }

    public AresException(String msg){
        super(msg);
    }

    public AresException(String code, String msg){
        super(msg);
        this.code = code;
    }

    public String getCode() {
        return code;
    }

}
