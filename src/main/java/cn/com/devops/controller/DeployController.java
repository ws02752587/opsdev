package cn.com.devops.controller;

import cn.com.devops.base.ResponseData;
import cn.com.devops.entity.DB;
import cn.com.devops.entity.Deploy;
import cn.com.devops.entity.Env;
import cn.com.devops.entity.Pager;
import cn.com.devops.enums.DeployWebEnum;
import cn.com.devops.enums.EditionEnum;
import cn.com.devops.enums.DeployEnum;
import cn.com.devops.service.DeployOperationService;
import cn.com.devops.service.DeployService;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;
import java.util.Map;

@Controller
@RequestMapping("/deploy")
public class DeployController {
    @Autowired
    private DeployService deployService;

    @Autowired
    private DeployOperationService deployOperationService;

    @RequestMapping(value = "/insert.do", method = RequestMethod.POST)
    @ResponseBody
    public ResponseData insert(@RequestBody Deploy deploy){
        deployService.insert(deploy);
        return ResponseData.ok("id",deploy.getId());
    }

    @RequestMapping(value = "/update.do", method = RequestMethod.POST)
    @ResponseBody
    public ResponseData update(@RequestBody Deploy deploy){
        deployService.update(deploy);
        return ResponseData.ok();
    }

    @RequestMapping(value = "/delete.do", method = RequestMethod.POST)
    @ResponseBody
    public ResponseData delete(@RequestBody Deploy deploy){
        deployService.delete(deploy.getId());
        return ResponseData.ok();
    }

    @RequestMapping(value = "/queryPage.do", method = RequestMethod.POST)
    @ResponseBody
    public ResponseData queryPage(@RequestBody Deploy deploy){
        //这行是重点，表示从pageNum页开始，每页pageSize条数据
        PageHelper.startPage(deploy.getPageNum(), 
        		deploy.getPageSize());
        List<Deploy> list = deployService.query();
        PageInfo<Deploy> info = new PageInfo<Deploy>(list);
        return ResponseData.page(info);
    }

    @RequestMapping(value = "/searchPage.do", method = RequestMethod.POST)
    public ResponseData searchPage(@RequestBody Deploy deploy,
                                   @RequestBody Pager pager){
        //这行是重点，表示从pageNum页开始，每页pageSize条数据
        PageHelper.startPage(pager.getPageNum(), 
        		pager.getPageSize());
        List<Deploy> list = deployService.search(deploy);
        PageInfo<Deploy> info = new PageInfo<Deploy>(list);
        return ResponseData.page(info);
    }

    @RequestMapping(value = "/checkWebServer.do", method = RequestMethod.POST)
    @ResponseBody
    public ResponseData checkWebServer(@RequestBody Deploy deploy){
        DeployWebEnum deployEnumWeb = deployOperationService.checkWebService(deploy.getId());
        return ResponseData.ok("webStatus", deployEnumWeb.toString());
    }

    @RequestMapping(value = "/checkAppServer.do", method = RequestMethod.POST)
    @ResponseBody
    public ResponseData checkAppServer(@RequestBody Deploy deploy){
        DeployEnum deployEnum = deployOperationService.checkAppService(deploy.getId());
        return ResponseData.ok("appStatus", deployEnum.toString());
    }

    @RequestMapping(value = "/queryEsbUrlAndDataSource.do", method = RequestMethod.POST)
    @ResponseBody
    public ResponseData queryEsbUrlAndDataSource(@RequestBody Deploy deploy){
        Env env = deployOperationService.getEsb(deploy);
        DB db = deployOperationService.getDataSource(deploy);
        if(env == null || db == null){
        	return ResponseData.ok("env",new Env(),"db",new DB());
        }
        if(StringUtils.isEmpty(env.getAppIp()) &&
                StringUtils.isEmpty(db.getIp())){
        	return ResponseData.ok("env",new Env(),"db",new DB());
        }
        return ResponseData.ok("env",env,"db",db);
    }

    /**
     * app
     * 	1.只重启服务，用于刷新缓存
     *  2.打war包，并重启服务
     *  3.停止服务，进程
     *  
     * web
     * 	1.打tar包，不用重启服务
     * 	2.只重启服务
     *  3.停止服务，进程
     *  处理以上情况
     * @param id
     * @param status
     * @return
     */
    @RequestMapping(value = "/operationService.do", method = RequestMethod.POST)
    @ResponseBody
    public ResponseData operationService(@RequestBody Map<String,String> map){
        boolean flag = deployOperationService.operationService(map);
        if(!flag){
            return ResponseData.fail("操作服务器失败!");
        }
        return ResponseData.ok();
    }

    @RequestMapping(value = "/showAppLog.do", method = RequestMethod.POST)
    @ResponseBody
    public ResponseData showAppLog(@RequestBody int id){
        String logs = deployOperationService.showLog(id, EditionEnum.APP);
        return ResponseData.ok("logs", logs);
    }

    @RequestMapping(value = "/showWebLog.do", method = RequestMethod.POST)
    @ResponseBody
    public ResponseData showWebLog(@RequestBody int id){
        String logs = deployOperationService.showLog(id, EditionEnum.WEB);
        return ResponseData.ok("logs", logs);
    }

    @RequestMapping(value = "/updateEsbUrl.do", method = RequestMethod.POST)
    @ResponseBody
    public ResponseData updateEsbUrl(@RequestBody Deploy deploy){
        deployOperationService.updateEsbUrl(deploy);
        return ResponseData.ok();
    }

    @RequestMapping(value = "/updateDataSource.do", method = RequestMethod.POST)
    @ResponseBody
    public ResponseData updateDataSource(@RequestBody Deploy deploy){
        deployOperationService.updateDataSource(deploy);
        return ResponseData.ok();
    }

}
