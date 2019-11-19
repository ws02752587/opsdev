package cn.com.devops.controller;

import cn.com.devops.base.ResponseData;
import cn.com.devops.dao.EditionInfoDao;
import cn.com.devops.entity.EditionInfo;
import cn.com.devops.exception.AresException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;

import java.util.List;

@Controller
@RequestMapping("/editionInfo")
public class EditionInfoController {
    @Autowired
    private EditionInfoDao editionInfoDao;

    @RequestMapping(value = "/query.do", method = RequestMethod.POST)
    @ResponseBody
    public ResponseData query(){
        List<EditionInfo> list = editionInfoDao.query(null);
        return ResponseData.ok(list);
    }
    
    @RequestMapping(value = "/queryPage.do", method = RequestMethod.POST)
    @ResponseBody
    public ResponseData queryPage(@RequestBody EditionInfo editionInfo){
        //这行是重点，表示从pageNum页开始，每页pageSize条数据
        PageHelper.startPage(editionInfo.getPageNum(), 
        		editionInfo.getPageSize());
        List<EditionInfo> list = editionInfoDao.query(editionInfo);
        PageInfo<EditionInfo> info = new PageInfo<EditionInfo>(list);
        return ResponseData.page(info);
    }
    
    @RequestMapping(value = "/insert.do", method = RequestMethod.POST)
    @ResponseBody
    public ResponseData insert(@RequestBody EditionInfo editionInfo){
    	EditionInfo editionInfo1 = editionInfoDao.queryByType(editionInfo);
    	if(editionInfo1 != null){
    		throw new AresException("该版本已存在，请不要重复添加!");
    	}
    	editionInfo.setStatus("1");
    	editionInfo.setRemark("");
    	editionInfoDao.insert(editionInfo);
        return ResponseData.ok();
    }
    
    @RequestMapping(value = "/update.do", method = RequestMethod.POST)
    @ResponseBody
    public ResponseData update(@RequestBody EditionInfo editionInfo){
    	editionInfoDao.update(editionInfo);
        return ResponseData.ok();
    }
}
