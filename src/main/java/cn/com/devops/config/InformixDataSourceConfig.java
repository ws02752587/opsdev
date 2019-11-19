package cn.com.devops.config;

import java.beans.PropertyVetoException;

import javax.sql.DataSource;

import org.apache.ibatis.session.SqlSessionFactory;
import org.mybatis.spring.SqlSessionFactoryBean;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.support.PathMatchingResourcePatternResolver;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;

import com.mchange.v2.c3p0.ComboPooledDataSource;

@Configuration
@MapperScan(basePackages={InformixDataSourceConfig.MAPPER_PACKAGE},
		sqlSessionFactoryRef="informixSqlSessionTemplate")
public class InformixDataSourceConfig {
	// mapper 的xml存放路径
    protected final static String MAPPER_XML_AREA = "classpath:mybatis/informix/mapper/*.xml";
    // mapper.java 存放路径，被@MapperScan扫描的，注入 sqlsession的
    protected final static String MAPPER_PACKAGE = "cn.com.devops.informix";
	@Value("${spring.datasource.informix.url}")
    private String url;
 
    @Value("${spring.datasource.informix.username}")
    private String user;
 
    @Value("${spring.datasource.informix.password}")
    private String password;
 
    @Value("${spring.datasource.informix.driver-class-name}")
    private String driverClass;
    
    /**
     * 注入 datasource
     * @return
     */
    @Bean
    @ConfigurationProperties( prefix = "spring.datasource.informix")
    public DataSource informixDataSource()  {
        // 使用druid 则这样注入 dataSource，不需要则直接 DataSourceBuilder.create().build()
        ComboPooledDataSource c3p0 = new ComboPooledDataSource();
        try {
			c3p0.setDriverClass(driverClass);
			c3p0.setJdbcUrl(url);
			c3p0.setUser(user);
			c3p0.setPassword(password);
			c3p0.setTestConnectionOnCheckin(true);
			c3p0.setIdleConnectionTestPeriod(100);
		} catch (PropertyVetoException e) {
			e.printStackTrace();
		}
        return c3p0;
    }
    
    /**
     * 注入 事务，在 serviceImpl 的时候使用
     * @return
     */
    @Bean(name = "informixTransationManager")
    public DataSourceTransactionManager setTransactionManager(){
        // 传入 dataSource
        return new DataSourceTransactionManager( informixDataSource() );
    }
    
    /**
     * 注入 sqlSession
     * @return
     * @throws Exception
     */
    @Bean(name = "informixSqlSessionTemplate")
    public SqlSessionFactory setSqlSessionFactory() throws Exception {
        final SqlSessionFactoryBean bean = new SqlSessionFactoryBean();
        bean.setDataSource(informixDataSource());
        // 设置mapper.xml 扫描路径
        bean.setMapperLocations( new PathMatchingResourcePatternResolver().
        		getResources(InformixDataSourceConfig.MAPPER_XML_AREA) );
        return bean.getObject();
    }
}
