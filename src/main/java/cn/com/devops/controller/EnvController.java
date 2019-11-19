package cn.com.devops.controller;

import cn.com.devops.base.ResponseData;
import cn.com.devops.dao.EnvDao;
import cn.com.devops.entity.Env;
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
@RequestMapping("/env")
public class EnvController {
    @Autowired
    private EnvDao envDao;

    @RequestMapping(value = "/insert.do", method = RequestMethod.POST)
    @ResponseBody
    public ResponseData insert(@Validated @RequestBody Env env){
    	Env env1 = envDao.queryByInfo(env);
    	if(env1 != null){
    		throw new AresException("该环境已存在，请不要重复添加!");
    	}else{
    		env.setStatus("1");
        	env.setRemark("");
            envDao.insert(env);
    	}
        return ResponseData.ok();
    }

    @RequestMapping(value = "/update.do", method = RequestMethod.POST)
    @ResponseBody
    public ResponseData update(@RequestBody Env env){
    	envDao.update(env);
        return ResponseData.ok();
    }

    @RequestMapping(value = "/delete.do", method = RequestMethod.POST)
    @ResponseBody
    public ResponseData delete(@RequestBody Env env){
        envDao.delete(env.getId());
        return ResponseData.ok();
    }

    @RequestMapping(value = "/queryPage.do", method = RequestMethod.POST)
    @ResponseBody
    public ResponseData queryPage(@RequestBody Env env){
        //这行是重点，表示从pageNum页开始，每页pageSize条数据
        PageHelper.startPage(env.getPageNum(), env.getPageSize());
        List<Env> list = envDao.query(env);
        PageInfo<Env> info = new PageInfo<Env>(list);
        return ResponseData.page(info);
    }

    @RequestMapping(value = "/searchPage.do", method = RequestMethod.POST)
    @ResponseBody
    public ResponseData searchPage(@RequestBody Env env, @RequestBody Pager pager){
        //这行是重点，表示从pageNum页开始，每页pageSize条数据
        PageHelper.startPage(pager.getPageNum(), pager.getPageSize());
        List<Env> list = envDao.search(env);
        PageInfo<Env> info = new PageInfo<Env>(list);
        return ResponseData.page(info);
    }

    @RequestMapping(value = "/query.do", method = RequestMethod.POST)
    @ResponseBody
    public ResponseData query(){
    	Env env = new Env();
    	env.setEnvMark("0");
        List<Env> list = envDao.query(env);
        return ResponseData.ok(list);
    }
    
    @RequestMapping(value = "/queryEsb.do", method = RequestMethod.POST)
    @ResponseBody
    public ResponseData queryEsb(){
    	Env env = new Env();
    	env.setEnvMark("1");
        List<Env> list = envDao.query(env);
        return ResponseData.ok(list);
    }
}
