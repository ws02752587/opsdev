package cn.com.devops.dao;

import cn.com.devops.entity.EditionVersion;

import java.util.List;
import java.util.Map;

public interface EditionVersionDao {
    void insert(EditionVersion editionVersion);
    void update(EditionVersion editionVersion);
    void delete(int id);
    List<EditionVersion> query(EditionVersion editionVersion);
    List<EditionVersion> search(EditionVersion editionVersion);
    void updateRNMD5(Map<String, String> map);
    EditionVersion queryByVersionId(EditionVersion editionVersion);
}
