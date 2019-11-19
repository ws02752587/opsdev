package cn.com.devops.controller;

import cn.com.devops.base.ResponseData;
import cn.com.devops.entity.Branch;
import cn.com.devops.entity.Pager;
import cn.com.devops.service.BranchService;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;
import java.util.Map;

@Controller
@RequestMapping("/branch")
public class BranchController {
    @Autowired
    private BranchService branchService;

    @RequestMapping(value = "/insert.do", method = RequestMethod.POST)
    @ResponseBody
    public ResponseData insert(@Validated @RequestBody Branch branch){
        branchService.insert(branch);
        return ResponseData.ok();
    }

    @RequestMapping(value = "/update.do", method = RequestMethod.POST)
    @ResponseBody
    public ResponseData update(@RequestBody Branch branch){
        branchService.update(branch);
        return ResponseData.ok();
    }

    @RequestMapping(value = "/delete.do", method = RequestMethod.POST)
    @ResponseBody
    public ResponseData delete(@RequestBody Branch branch ){
        branchService.delete(branch.getId());
        return ResponseData.ok();
    }

    @RequestMapping(value = "/queryPage.do", method = RequestMethod.POST)
    @ResponseBody
    public ResponseData queryPage(@RequestBody Branch branch){
        //这行是重点，表示从pageNum页开始，每页pageSize条数据
        PageHelper.startPage(branch.getPageNum(), 
        		branch.getPageSize());
        List<Branch> list = branchService.query(branch);
        PageInfo<Branch> info = new PageInfo<Branch>(list);
        return ResponseData.page(info);
    }

    @RequestMapping(value = "/searchPage.do", method = RequestMethod.POST)
    @ResponseBody
    public ResponseData searchPage(@RequestBody Branch branch,
                                   @RequestBody Pager pager){
        //这行是重点，表示从pageNum页开始，每页pageSize条数据
        PageHelper.startPage(pager.getPageNum(), pager.getPageSize());
        List<Branch> list = branchService.search(branch);
        PageInfo<Branch> info = new PageInfo<Branch>(list);
        return ResponseData.page(info);
    }

    @RequestMapping(value = "/query.do", method = RequestMethod.POST)
    @ResponseBody
    public ResponseData query(){
        List<Branch> list = branchService.query(null);
        return ResponseData.ok(list);
    }
    
    @RequestMapping(value = "/queryPro.do")
    @ResponseBody
    public ResponseData queryProBranch(@RequestParam Map<String, String> map){
    	Branch branch = new Branch();
    	branch.setEditionInfo(map.get("editionInfo"));
    	Branch branch1 = branchService.queryProBranch(branch);
        return ResponseData.ok(branch1);
    }
}
