package cn.com.devops.config;

import java.util.HashMap;
import java.util.Map;

import javax.sql.DataSource;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Primary;

import cn.com.devops.base.DynamicDataSource;
import cn.com.devops.enums.BaseTypeEnum;

public class DynamicDataSourceConfig {
	@Bean(name = "dataSource")
	@Primary
    public DataSource dataSource(DataSource mysqlDataSource,
    		DataSource informixDataSource) {
		System.out.println(mysqlDataSource);
        DynamicDataSource dynamicDataSource = new DynamicDataSource();
        //配置默认数据源
        dynamicDataSource.setDefaultTargetDataSource(mysqlDataSource);
        //配置多数据源
        Map<Object, Object> map = new HashMap<Object,Object>(16);
        map.put(BaseTypeEnum.INFORMIX.toString(), mysqlDataSource);
        dynamicDataSource.setTargetDataSources(map);
        return dynamicDataSource;
    }
	
	
}
