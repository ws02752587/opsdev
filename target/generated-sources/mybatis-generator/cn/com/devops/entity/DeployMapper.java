package cn.com.devops.entity;

import cn.com.devops.entity.Deploy;

public interface DeployMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(Deploy record);

    int insertSelective(Deploy record);

    Deploy selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(Deploy record);

    int updateByPrimaryKey(Deploy record);
}