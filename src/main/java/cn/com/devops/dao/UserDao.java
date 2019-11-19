package cn.com.devops.dao;

import java.util.List;

import cn.com.devops.entity.User;

/**
 * 用户数据库接口
 */
public interface UserDao {
    User queryByUsernameAndPassword(User user);
    User queryByUsername(User user);
    void insert(User user);
    User queryById(int id);
    List<User> query();
    List<User> search(User user);
    void updatePwd(User user);
    void update(User user);
    User validTransPwd(User user);
    void delete(User user);
}
