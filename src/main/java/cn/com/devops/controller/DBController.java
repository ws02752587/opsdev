package cn.com.devops.controller;

import cn.com.devops.base.ResponseData;
import cn.com.devops.dao.DBDao;
import cn.com.devops.entity.DB;
import cn.com.devops.entity.Pager;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import java.util.List;

@Controller
@RequestMapping("/db")
public class DBController {
    @Autowired
    private DBDao dbDao;

    @RequestMapping(value = "/insert.do", method = RequestMethod.POST)
    public ResponseData insert(@Validated @RequestBody DB db){
        dbDao.insert(db);
        return ResponseData.ok();
    }

    @RequestMapping(value = "/update.do", method = RequestMethod.POST)
    public ResponseData update(@RequestBody DB db){
        dbDao.update(db);
        return ResponseData.ok();
    }

    @RequestMapping(value = "/delete.do", method = RequestMethod.POST)
    public ResponseData delete(@RequestBody int id){
        dbDao.delete(id);
        return ResponseData.ok();
    }

    @RequestMapping(value = "/queryPage.do", method = RequestMethod.POST)
    public ResponseData queryPage(@RequestBody Pager pager){
        //这行是重点，表示从pageNum页开始，每页pageSize条数据
        PageHelper.startPage(pager.getPageNum(), pager.getPageSize());
        List<DB> list = dbDao.query();
        PageInfo<DB> info = new PageInfo<DB>(list);
        return ResponseData.page(info);
    }

    @RequestMapping(value = "/query.do", method = RequestMethod.POST)
    public ResponseData query(){
        List<DB> list = dbDao.query();
        return ResponseData.ok(list);
    }
}
