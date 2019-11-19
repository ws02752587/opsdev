package cn.com.devops.controller;

import cn.com.devops.base.ResponseData;
import cn.com.devops.dao.ServerEditionDao;
import cn.com.devops.entity.Pager;
import cn.com.devops.entity.ServerEdition;
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
@RequestMapping("/serverEdition")
public class ServerEditionController {
    @Autowired
    private ServerEditionDao serverEditionDao;

    @RequestMapping(value = "/insert.do", method = RequestMethod.POST)
    @ResponseBody
    public ResponseData insert(@Validated @RequestBody ServerEdition serverEdition){
    	ServerEdition serverEdition1 = serverEditionDao.queryByIdVersion(serverEdition);
    	if(serverEdition1 != null){
    		throw new AresException("该版本编号已存在，请不要重复添加!");
    	}else{
    		serverEdition.setStatus("1");
        	serverEdition.setRemark("");
            serverEditionDao.insert(serverEdition);
    	}
        return ResponseData.ok();
    }

    @RequestMapping(value = "/update.do", method = RequestMethod.POST)
    @ResponseBody
    public ResponseData update(@RequestBody ServerEdition serverEdition){
        serverEditionDao.update(serverEdition);
        return ResponseData.ok();
    }

    @RequestMapping(value = "/delete.do", method = RequestMethod.POST)
    @ResponseBody
    public ResponseData delete(@RequestBody ServerEdition serverEdition){
        serverEditionDao.delete(serverEdition.getId());
        return ResponseData.ok();
    }

    @RequestMapping(value = "/queryPage.do", method = RequestMethod.POST)
    @ResponseBody
    public ResponseData queryPage(@RequestBody ServerEdition serverEdition){
        //这行是重点，表示从pageNum页开始，每页pageSize条数据
        PageHelper.startPage(serverEdition.getPageNum(), 
        		serverEdition.getPageSize());
        List<ServerEdition> list = serverEditionDao.query(serverEdition);
        PageInfo<ServerEdition> info = new PageInfo<ServerEdition>(list);
        return ResponseData.page(info);
    }

    @RequestMapping(value = "/searchPage.do", method = RequestMethod.POST)
    @ResponseBody
    public ResponseData searchPage(@RequestBody ServerEdition serverEdition,
                                   @RequestBody Pager pager){
        //这行是重点，表示从pageNum页开始，每页pageSize条数据
        PageHelper.startPage(pager.getPageNum(), pager.getPageSize());
        List<ServerEdition> list = serverEditionDao.search(serverEdition);
        PageInfo<ServerEdition> info = new PageInfo<ServerEdition>(list);
        return ResponseData.page(info);
    }

    @RequestMapping(value = "/query.do", method = RequestMethod.POST)
    @ResponseBody
    public ResponseData query(){
        List<ServerEdition> list = serverEditionDao.query(null);
        return ResponseData.ok(list);
    }
}
