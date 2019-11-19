package cn.com.devops.service.command;

import cn.com.devops.entity.Deploy;

public interface IPackageOperation {
    boolean packge(Deploy deploy);
    boolean tarx(Deploy deploy);
}
