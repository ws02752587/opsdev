package cn.com.devops.controller;

import cn.com.devops.base.ResponseData;
import cn.com.devops.entity.Svn;
import cn.com.devops.service.SvnService;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

@Controller
@RequestMapping("/svn")
public class SvnController {
    @Autowired
    private SvnService svnService;

    @RequestMapping(value = "/insert.do", method = RequestMethod.POST)
    @ResponseBody
    public ResponseData insert(@Validated @RequestBody Svn svn){
        svnService.insert(svn);
        return ResponseData.ok();
    }

    @RequestMapping(value = "/update.do", method = RequestMethod.POST)
    @ResponseBody
    public ResponseData update(@RequestBody Svn svn){
        svnService.update(svn);
        return ResponseData.ok();
    }

    @RequestMapping(value = "/queryPage.do", method = RequestMethod.POST)
    @ResponseBody
    public ResponseData queryPage(@RequestBody Svn svn){
        //这行是重点，表示从pageNum页开始，每页pageSize条数据
        PageHelper.startPage(svn.getPageNum(), svn.getPageSize());
        List<Svn> list = svnService.query(svn);
        PageInfo<Svn> info = new PageInfo<Svn>(list);
        return ResponseData.page(info);
    }

    @RequestMapping(value = "/queryFirst.do", method = RequestMethod.POST)
    @ResponseBody
    public ResponseData queryFirst(@RequestBody String type){
        Svn svn = svnService.queryFirst(type);
        return ResponseData.ok(svn);
    }
}
