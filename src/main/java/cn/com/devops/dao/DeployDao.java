package cn.com.devops.dao;

import cn.com.devops.entity.Deploy;

import java.util.List;

public interface DeployDao {
    int insert(Deploy deploy);
    void update(Deploy deploy);
    void delete(int id);
    List<Deploy> query();
    List<Deploy> search(Deploy deploy);
    Deploy queryById(int id);
    Deploy queryByInfo(Deploy deploy);
}
