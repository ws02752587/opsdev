package cn.com.devops.informix;

import java.util.Map;

/**
 * 用户数据库接口
 */
public interface RnUpdateConfigDao {
    void updateByIphone(Map<String,String> map);
    void updateByAndroid(Map<String,String> map);
}
