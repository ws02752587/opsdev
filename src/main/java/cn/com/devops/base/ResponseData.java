package cn.com.devops.base;

import com.github.pagehelper.PageInfo;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class ResponseData {
    private String code;
    private String msg;
    private Object data;
    private String count;
    private ResponseData(){

    }
    public static ResponseData ok(String code, String msg, Object data){
        ResponseData responseData = new ResponseData();
        responseData.code = code;
        responseData.msg = msg;
        responseData.data = data;
        return responseData;
    }

    public static ResponseData ok(Object... values){
        Map<String ,Object> map = new HashMap<String, Object>();
        if(values != null && values.length > 0 &&
            values.length%2 == 0){
            for (int i = 0; i < values.length; i+=2) {
                int j = i+1;
                map.put(String.valueOf(values[i]), values[j]);
            }
        }

        return ResponseData.ok(App.SUCCESS, "交易成功!", map);
    }

    public static ResponseData ok(Object data){
        return ResponseData.ok(App.SUCCESS, "交易成功!", data);
    }

    public static ResponseData ok(){
        return ResponseData.ok(App.SUCCESS, "交易成功!", null);
    }

    public static ResponseData fail(String msg){
        return ResponseData.ok(App.ERROR, msg, null);
    }

    public static ResponseData fail(String code, String msg){
        return ResponseData.ok(code, msg, null);
    }

    public static ResponseData page(PageInfo<?> pageInfo){
    	List<?> list = pageInfo.getList();
    	String code = App.SUCCESS;
    	String msg = "";
    	if(list != null && list.size()> 0){
    		code = App.SUCCESS;
    		msg = "交易成功!";
    	}else{
    		code = App.ERROR;
    		msg = "没有数据哦!";
    	}
    	ResponseData response = ResponseData.ok(code, 
    			msg, pageInfo.getList());
    	response.count = String.valueOf(pageInfo.getTotal());
    	return response;
    }

    public String getCode() {
        return code;
    }


    public String getMsg() {
        return msg;
    }


    public Object getData() {
        return data;
    }
    
	public String getCount() {
		return count;
	}
    
}
