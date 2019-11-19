package cn.com.devops.dao;

import cn.com.devops.entity.DB;

import java.util.List;

public interface DBDao {
    void insert(DB db);
    void update(DB db);
    void delete(int id);
    List<DB> query();
}
