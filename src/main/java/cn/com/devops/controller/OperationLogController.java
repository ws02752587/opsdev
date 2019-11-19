package cn.com.devops.controller;

import cn.com.devops.base.ResponseData;
import cn.com.devops.entity.OperationLog;
import cn.com.devops.entity.Pager;
import cn.com.devops.service.OperationLogService;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import java.util.List;

@Controller
@RequestMapping("/operationLog")
public class OperationLogController {
    @Autowired
    private OperationLogService operationLogService;

    @RequestMapping(value = "/queryPage.do", method = RequestMethod.POST)
    public ResponseData queryPage(@RequestBody Pager pager){
        //这行是重点，表示从pageNum页开始，每页pageSize条数据
        PageHelper.startPage(pager.getPageNum(), pager.getPageSize());
        List<OperationLog> list = operationLogService.query();
        PageInfo<OperationLog> info = new PageInfo<OperationLog>(list);
        return ResponseData.page(info);
    }

    @RequestMapping(value = "/searchPage.do", method = RequestMethod.POST)
    public ResponseData searchPage(@RequestBody OperationLog operationLog,
                                   @RequestBody Pager pager){
        //这行是重点，表示从pageNum页开始，每页pageSize条数据
        PageHelper.startPage(pager.getPageNum(), pager.getPageSize());
        List<OperationLog> list = operationLogService.search(operationLog);
        PageInfo<OperationLog> info = new PageInfo<OperationLog>(list);
        return ResponseData.page(info);
    }
}
