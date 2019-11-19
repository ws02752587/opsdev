package cn.com.devops.dao;

import cn.com.devops.entity.BeonlineEdition;

import java.util.List;

public interface BeonlineEditionDao {
    void insert(BeonlineEdition beonlineEdition);
    void update(BeonlineEdition beonlineEdition);
    void delete(BeonlineEdition beonlineEdition);
    List<BeonlineEdition> query(BeonlineEdition beonlineEdition);
    BeonlineEdition queryByDate(BeonlineEdition beonlineEdition);
    List<BeonlineEdition> search(BeonlineEdition beonlineEdition);
}
