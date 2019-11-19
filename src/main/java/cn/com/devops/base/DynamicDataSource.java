package cn.com.devops.base;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.jdbc.datasource.lookup.AbstractRoutingDataSource;

public class DynamicDataSource extends AbstractRoutingDataSource{
	private Logger logger = LoggerFactory.getLogger(DynamicDataSource.class);
	@Override
	protected Object determineCurrentLookupKey() {
		logger.info("当前数据源：{}", DataSourceContextHolder.getDataBaseType());
		return DataSourceContextHolder.getDataBaseType();
	}
	
}
