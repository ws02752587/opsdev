package cn.com.devops.controller;

import cn.com.devops.base.ResponseData;
import cn.com.devops.informix.RnUpdateConfigDao;
import cn.com.devops.service.command.SystemFileOperation;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.Map;

@Controller
@RequestMapping("/rn")
public class RNController {
    @Autowired
    private RnUpdateConfigDao rnUpdateConfigDao;

    @Autowired
    private SystemFileOperation systemFileOperation;

    @Value("${md5.url}")
    private String url;

    @RequestMapping(value = "/update.do", method = RequestMethod.POST)
    @ResponseBody
    public ResponseData insert(@RequestBody Map<String ,String> params){
        //{"iphone":"13","android":"22"}
    	String version = params.get("version");
        Map<String, String> map = systemFileOperation.queryMD5(url);
        if(map == null || !map.containsKey("iphone") ||
                !map.containsKey("android")){
            return ResponseData.fail("获取MD5值失败!");
        }
//        map.put("databaseIp", databaseIp);
        map.put("version", version);
        try{
        	rnUpdateConfigDao.updateByAndroid(map);
            rnUpdateConfigDao.updateByIphone(map);
        }catch(Exception e){
        	System.out.print(e.getMessage());
        }
        return ResponseData.ok();
    }

}
