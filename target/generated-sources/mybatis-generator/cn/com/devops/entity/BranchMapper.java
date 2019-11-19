package cn.com.devops.entity;

import cn.com.devops.entity.Branch;

public interface BranchMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(Branch record);

    int insertSelective(Branch record);

    Branch selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(Branch record);

    int updateByPrimaryKey(Branch record);
}