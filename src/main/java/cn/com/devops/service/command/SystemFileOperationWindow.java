package cn.com.devops.service.command;

import cn.com.devops.entity.DB;
import cn.com.devops.entity.Deploy;
import cn.com.devops.entity.Env;

import java.util.HashMap;
import java.util.Map;

public class SystemFileOperationWindow extends SystemFileOperation {
    @Override
    public DB queryJdbcUrl(Deploy deploy) {
    	 DB db = new DB();
    	 db.setIp("10.240.91.152");
    	 db.setPort("9088");
    	 db.setIsSource("0");
        return db;
    }

    @Override
    public Map<String, String> queryMD5(String url) {
    	Map<String, String> map = new HashMap<String, String>();
    	map.put("android", "1");
    	map.put("iphone", "2");
        return map;
    }

    @Override
    public Env queryEsbUrl(Deploy deploy) {
    	Env resultEnv = new Env();
    	resultEnv.setAppIp("10.240.86.58");
        resultEnv.setAppPort("18005");
        return resultEnv;
    }

    @Override
    public boolean updateJdbcUrl(Deploy deploy, DB db) {
        return false;
    }

    @Override
    public boolean updateEsbUrl(Deploy deploy) {
        return false;
    }

    @Override
    public boolean updateDataSource(Deploy deploy) {
        return false;
    }
}
