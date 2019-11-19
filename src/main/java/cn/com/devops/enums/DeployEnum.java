package cn.com.devops.enums;
/**
 * 应用状态
 * 0:失败(无进程)
 * 1:活动(有进程并且check成功)
 * 2:准备就绪(有进程但check失败,weblogic未启动应用)
 * 3:应用报错(有进程但check成功,整合报错，检查日志是否出现错误)
 * 4:打包中(正在打war包)
 * 5:打包失败(打war包失败)
 * 6:空闲
 */

/**
 * 静态状态
 * 0:失败(无进程)
 * 1:活动(有进程并且check成功)
 * 2:准备就绪(有进程但check失败)
 * 4:打包中(正在打tar包)
 * 5:打包失败(打tar包失败)
 * 6:空闲
 */
/**
 * 分支下载失败,DOWNFAIL
 * 分支下载中,DOWNING
 * 分支更新失败,UPDATEFAIL
 * 分支使用中,BRANCHSUCCESS
 */
public enum DeployEnum {
    FAIL("0"),SUCCESS("1"),READY("2"),ERROR("3"),PACKAGE("4"),PACKAGE_FAIL("5"),
    FREE("6"),
    DOWNING("7"),DOWNFAIL("8"),UPDATEFAIL("9"),BRANCHSUCCESS("10");
    
    private String status;
    private DeployEnum(String status) {
        this.status = status;
    }
    public String toString(){
    	return status;
    }
    public static DeployEnum changeEnum(String val){
    	DeployEnum[] deployEnums = DeployEnum.values();
    	DeployEnum result = null;
    	for(DeployEnum e : deployEnums){
    		if(e.toString().equals(val)){
    			result = e;
    			break;
    		}
    	}
    	return result;
    }
}
