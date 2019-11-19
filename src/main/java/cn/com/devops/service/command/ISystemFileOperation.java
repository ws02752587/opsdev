package cn.com.devops.service.command;

import cn.com.devops.entity.DB;
import cn.com.devops.entity.Deploy;
import cn.com.devops.entity.Env;

import java.util.Map;

public interface ISystemFileOperation {
    DB queryJdbcUrl(Deploy deploy);
    Map<String ,String> queryMD5(String url);
    Env queryEsbUrl(Deploy deploy);
    boolean updateJdbcUrl(Deploy deploy, DB db);
    boolean updateEsbUrl(Deploy deploy);
    boolean updateDataSource(Deploy deploy);
}
