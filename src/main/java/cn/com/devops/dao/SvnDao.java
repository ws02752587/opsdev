package cn.com.devops.dao;

import cn.com.devops.entity.Svn;

import java.util.List;

public interface SvnDao {
    void insert(Svn svn);
    void update(Svn svn);
    List<Svn> query(Svn svn);
    List<Svn> queryByType(String type);
    Svn queryByNameAndUrl(Svn svn);
    void delete(int id);
}
