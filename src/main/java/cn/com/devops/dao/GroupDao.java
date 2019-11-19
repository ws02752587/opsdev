package cn.com.devops.dao;

import cn.com.devops.entity.Group;

import java.util.List;

public interface GroupDao {
    void insert(Group group);
    void update(Group group);
    void delete(int id);
    List<Group> query();
    Group queryByName(Group group);
}
