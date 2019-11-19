package cn.com.devops.dao;

import cn.com.devops.entity.Branch;

import java.util.List;

public interface BranchDao {
    void insert(Branch branch);
    void update(Branch branch);
    void delete(int id);
    List<Branch> query(Branch branch);
    List<Branch> queryProBranch(Branch branch);
    List<Branch> search(Branch branch);
    Branch queryById(int id);
    Branch queryByVersion(Branch branch);
}
