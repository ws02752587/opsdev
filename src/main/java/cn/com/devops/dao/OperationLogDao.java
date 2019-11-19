package cn.com.devops.dao;

import cn.com.devops.entity.OperationLog;

import java.util.List;

public interface OperationLogDao {
    void insert(OperationLog operationLog);
    List<OperationLog> query();
    List<OperationLog> search(OperationLog operationLog);
}
