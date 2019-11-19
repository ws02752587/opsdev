package cn.com.devops.dao;

import cn.com.devops.entity.EditionInfo;

import java.util.List;

public interface EditionInfoDao {
    List<EditionInfo> query(EditionInfo editionInfo);
    void insert(EditionInfo editionInfo);
    void update(EditionInfo editionInfo);
    EditionInfo queryByType(EditionInfo editionInfo);
}
