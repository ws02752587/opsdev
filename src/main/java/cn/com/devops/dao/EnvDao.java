package cn.com.devops.dao;

import cn.com.devops.entity.Env;

import java.util.List;

public interface EnvDao {
    void insert(Env env);
    void update(Env env);
    void delete(int id);
    Env queryByUrl(Env env);
    List<Env> query(Env env);
    List<Env> search(Env env);
    Env queryById(int id);
    Env queryByInfo(Env env);
}
