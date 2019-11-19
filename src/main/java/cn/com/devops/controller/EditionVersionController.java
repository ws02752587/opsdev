package cn.com.devops.controller;

import cn.com.devops.base.ResponseData;
import cn.com.devops.dao.EditionVersionDao;
import cn.com.devops.entity.EditionVersion;
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
@RequestMapping("/editionVersion")
public class EditionVersionController {
    @Autowired
    private EditionVersionDao editionVersionDao;

    @RequestMapping(value = "/insert.do", method = RequestMethod.POST)
    @ResponseBody
    public ResponseData insert(@Validated @RequestBody EditionVersion editionVersion){
    	EditionVersion editionVersion1 = editionVersionDao.queryByVersionId(editionVersion);
    	if(editionVersion1 != null){
    		throw new AresException("该客户端版本号已存在，请不要重复添加!");
    	}else{
    		editionVersion.setStatus("1");
        	editionVersion.setRemark("");
    		editionVersionDao.insert(editionVersion);
    	}
        return ResponseData.ok();
    }

    @RequestMapping(value = "/update.do", method = RequestMethod.POST)
    @ResponseBody
    public ResponseData update(@RequestBody EditionVersion editionVersion){
    	EditionVersion editionVersion1 = editionVersionDao.queryByVersionId(editionVersion);
    	if(editionVersion1 != null){
    		throw new AresException("该客户端版本号已存在，无法修改!");
    	}else{
    		editionVersionDao.update(editionVersion);
    	}
        return ResponseData.ok();
    }

    @RequestMapping(value = "/delete.do", method = RequestMethod.POST)
    @ResponseBody
    public ResponseData delete(@RequestBody EditionVersion editionVersion){
        editionVersionDao.delete(editionVersion.getId());
        return ResponseData.ok();
    }

    @RequestMapping(value = "/queryPage.do", method = RequestMethod.POST)
    @ResponseBody
    public ResponseData queryPage(@RequestBody EditionVersion editionVersion){
        //这行是重点，表示从pageNum页开始，每页pageSize条数据
        PageHelper.startPage(editionVersion.getPageNum(), 
        		editionVersion.getPageSize());
        System.out.print(editionVersion.getEditionId());
        List<EditionVersion> list = editionVersionDao.query(editionVersion);
        PageInfo<EditionVersion> info = new PageInfo<EditionVersion>(list);
        return ResponseData.page(info);
    }

    @RequestMapping(value = "/searchPage.do", method = RequestMethod.POST)
    @ResponseBody
    public ResponseData searchPage(@RequestBody EditionVersion editionVersion,
                                   @RequestBody Pager pager){
        //这行是重点，表示从pageNum页开始，每页pageSize条数据
        PageHelper.startPage(pager.getPageNum(), pager.getPageSize());
        List<EditionVersion> list = editionVersionDao.search(editionVersion);
        PageInfo<EditionVersion> info = new PageInfo<EditionVersion>(list);
        return ResponseData.page(info);
    }

    @RequestMapping(value = "/query.do", method = RequestMethod.POST)
    @ResponseBody
    public ResponseData query(@RequestBody EditionVersion editionVersion){
        List<EditionVersion> list = editionVersionDao.query(editionVersion);
        return ResponseData.ok(list);
    }
}
