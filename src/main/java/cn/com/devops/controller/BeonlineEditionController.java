package cn.com.devops.controller;

import cn.com.devops.base.ResponseData;
import cn.com.devops.dao.BeonlineEditionDao;
import cn.com.devops.entity.BeonlineEdition;
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
@RequestMapping("/beonlineEdition")
public class BeonlineEditionController {
    @Autowired
    private BeonlineEditionDao beonlineEditionDao;

    @RequestMapping(value = "/insert.do", method = RequestMethod.POST)
    @ResponseBody
    public ResponseData insert(@Validated @RequestBody BeonlineEdition beonlineEdition){
    	BeonlineEdition beonlineEdition1 = beonlineEditionDao.queryByDate(beonlineEdition);
    	beonlineEdition.setStatus("1");
    	if(beonlineEdition1 != null){
    		if("1".equals(beonlineEdition1.getStatus())){
    			throw new AresException("该上线版本已存在，请不要重复添加!");
    		}else{
    			beonlineEdition.setId(beonlineEdition1.getId());
    			beonlineEditionDao.update(beonlineEdition);
    		}
    	}else{
        	beonlineEdition.setRemark("");
            beonlineEditionDao.insert(beonlineEdition);
    	}
        return ResponseData.ok();
    }

    @RequestMapping(value = "/update.do", method = RequestMethod.POST)
    @ResponseBody
    public ResponseData update(@RequestBody BeonlineEdition beonlineEdition){
    	BeonlineEdition beonlineEdition1 = beonlineEditionDao.queryByDate(beonlineEdition);
    	if(beonlineEdition1 != null){
    		if("1".equals(beonlineEdition1.getStatus())){
    			throw new AresException("该上线版本已存在，无法修改!");
    		}else{
    			beonlineEditionDao.delete(beonlineEdition);
    			beonlineEdition.setId(beonlineEdition1.getId());
    			beonlineEdition.setStatus("1");
    			beonlineEditionDao.update(beonlineEdition);
    		}
    	}else{
    		beonlineEditionDao.update(beonlineEdition);
    	}
        return ResponseData.ok();
    }

    @RequestMapping(value = "/delete.do", method = RequestMethod.POST)
    @ResponseBody
    public ResponseData delete(@RequestBody BeonlineEdition beonlineEdition){
        beonlineEditionDao.delete(beonlineEdition);
        return ResponseData.ok();
    }

    @RequestMapping(value = "/queryPage.do", method = RequestMethod.POST)
    @ResponseBody
    public ResponseData queryPage(@RequestBody BeonlineEdition beonlineEdition){
        //这行是重点，表示从pageNum页开始，每页pageSize条数据
        PageHelper.startPage(beonlineEdition.getPageNum(), 
        		beonlineEdition.getPageSize());
        List<BeonlineEdition> list = beonlineEditionDao.query(beonlineEdition);
        PageInfo<BeonlineEdition> info = new PageInfo<BeonlineEdition>(list);
        return ResponseData.page(info);
    }

    @RequestMapping(value = "/searchPage.do", method = RequestMethod.POST)
    @ResponseBody
    public ResponseData searchPage(@RequestBody BeonlineEdition beonlineEdition,
                                   @RequestBody Pager pager){
        //这行是重点，表示从pageNum页开始，每页pageSize条数据
        PageHelper.startPage(pager.getPageNum(), pager.getPageSize());
        List<BeonlineEdition> list = beonlineEditionDao.search(beonlineEdition);
        PageInfo<BeonlineEdition> info = new PageInfo<BeonlineEdition>(list);
        return ResponseData.page(info);
    }

    @RequestMapping(value = "/query.do", method = RequestMethod.POST)
    @ResponseBody
    public ResponseData query(){
        List<BeonlineEdition> list = beonlineEditionDao.query(null);
        return ResponseData.ok(list);
    }
}
