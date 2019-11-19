package cn.com.devops.controller;

import cn.com.devops.base.ResponseData;
import cn.com.devops.dao.GroupDao;
import cn.com.devops.entity.Group;
import cn.com.devops.entity.Pager;
import cn.com.devops.exception.AresException;

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
@RequestMapping("/group")
public class GroupController {
    @Autowired
    private GroupDao groupDao;

    @RequestMapping(value = "/insert.do", method = RequestMethod.POST)
    @ResponseBody
    public ResponseData insert(@Validated @RequestBody Group group){
    	Group group1 = groupDao.queryByName(group);
    	group.setStatus("1");
    	if(group1 != null){
    		if("1".equals(group1.getStatus())){
    			throw new AresException("该组已存在，请不要重复添加!");
    		}else{
    			group.setId(group1.getId());
    			groupDao.update(group);
    		}
    	}else{
    		group.setRemark("");
    		groupDao.insert(group);
    	}
        return ResponseData.ok();
    }

    @RequestMapping(value = "/update.do", method = RequestMethod.POST)
    @ResponseBody
    public ResponseData update(@RequestBody Group group){
    	Group group1 = groupDao.queryByName(group);
    	if(group1 != null){
    		if("1".equals(group1.getStatus())){
    			throw new AresException("该组已存在，无法修改!");
    		}else{
    			groupDao.delete(group.getId());
    			group.setStatus("1");
    			group.setId(group1.getId());
    			groupDao.update(group);
    		}
    	}else{
    		groupDao.update(group);
    	}
        return ResponseData.ok();
    }

    @RequestMapping(value = "/delete.do", method = RequestMethod.POST)
    @ResponseBody
    public ResponseData delete(@RequestBody Group group){
        groupDao.delete(group.getId());
        return ResponseData.ok();
    }

    @RequestMapping(value = "/queryPage.do", method = RequestMethod.POST)
    @ResponseBody
    public ResponseData queryPage(@RequestBody Pager pager){
        //这行是重点，表示从pageNum页开始，每页pageSize条数据
        PageHelper.startPage(pager.getPageNum(), pager.getPageSize());
        List<Group> list = groupDao.query();
        PageInfo<Group> info = new PageInfo<Group>(list);
        return ResponseData.page(info);
    }

    @RequestMapping(value = "/query.do", method = RequestMethod.POST)
    @ResponseBody
    public ResponseData query(){
        List<Group> list = groupDao.query();
        return ResponseData.ok(list);
    }
}
