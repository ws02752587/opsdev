package cn.com.devops.service;

import cn.com.devops.dao.OperationLogDao;
import cn.com.devops.entity.OperationLog;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class OperationLogService {
    @Autowired
    private OperationLogDao operationLogDao;

    public void insert(OperationLog operationLog){
        operationLogDao.insert(operationLog);
    }

    public List<OperationLog> query(){

        return operationLogDao.query();
    }

    public List<OperationLog> search(OperationLog operationLog){

        return operationLogDao.search(operationLog);
    }
}
