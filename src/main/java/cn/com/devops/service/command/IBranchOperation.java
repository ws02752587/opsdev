package cn.com.devops.service.command;

import cn.com.devops.entity.Branch;
import cn.com.devops.entity.Deploy;

public interface IBranchOperation {
    boolean downBranch(Deploy deploy);

    boolean updateBranch(Deploy deploy);

    boolean clearBranch(Branch branch);
}
