package cn.com.devops.base;

import cn.com.devops.enums.BaseTypeEnum;

public class DataSourceContextHolder {
	private static ThreadLocal<String> contextHolder = new ThreadLocal<String>();
	public static String getDataBaseType(){
		return contextHolder.get();
	}
	
	public static void setDataBaseType(BaseTypeEnum baseTypeEnum){
		contextHolder.set(baseTypeEnum.toString());
	}
	
	public static void clearDataBaseType(){
		contextHolder.remove();
	}
}
