package cn.com.devops.dao;

import cn.com.devops.entity.ServerEdition;

import java.util.List;

public interface ServerEditionDao {
    void insert(ServerEdition serverEdition);
    void update(ServerEdition serverEdition);
    void delete(int id);
    List<ServerEdition> query(ServerEdition serverEdition);
    List<ServerEdition> search(ServerEdition serverEdition);
    ServerEdition queryById(int id);
    ServerEdition queryByIdVersion(ServerEdition serverEdition);
}
