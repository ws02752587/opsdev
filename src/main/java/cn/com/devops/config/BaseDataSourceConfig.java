package cn.com.devops.config;

import java.sql.SQLException;

import javax.sql.DataSource;

import org.apache.ibatis.session.SqlSessionFactory;
import org.mybatis.spring.SqlSessionFactoryBean;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.core.io.support.PathMatchingResourcePatternResolver;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;

import com.alibaba.druid.pool.DruidDataSource;

@Configuration
@MapperScan(basePackages={BaseDataSourceConfig.MAPPER_PACKAGE},
		sqlSessionFactoryRef="baseSqlSessionTemplate")
public class BaseDataSourceConfig {
	// mapper 的xml存放路径
    protected final static String MAPPER_XML_AREA = "classpath:mybatis/mysql/mapper/*.xml";
    // mapper.java 存放路径，被@MapperScan扫描的，注入 sqlsession的
    protected final static String MAPPER_PACKAGE = "cn.com.devops.dao";
	@Value("${spring.datasource.mysql.url}")
    private String url;
 
    @Value("${spring.datasource.mysql.username}")
    private String user;
 
    @Value("${spring.datasource.mysql.password}")
    private String password;
 
    @Value("${spring.datasource.mysql.driver-class-name}")
    private String driverClass;
    
    @Value("${spring.datasource.filters}")
    private String driverFilters;
    
    //数据库连接池的最小维持连接数
    @Value("${spring.datasource.dbcp2.min-idle}")
    private int minidle;
    
    //初始化提供的连接数
    @Value("${spring.datasource.dbcp2.initial-size}")
    private int initialSize;
    
    //最大的连接数
    @Value("${spring.datasource.dbcp2.max-total}")
    private int maxTotal;
    
    //等待连接获取的最大超时时间
    @Value("${spring.datasource.dbcp2.max-wait-millis}")
    private long maxWaitMillis;
    
    /**
     * 注入 datasource
     * @return
     */
    @Bean
    @ConfigurationProperties( prefix = "spring.datasource.mysql")
    @Primary
    public DataSource mysqlDataSource()  {
        // 使用druid 则这样注入 dataSource，不需要则直接 DataSourceBuilder.create().build()
        DruidDataSource dataSource = new DruidDataSource();
        dataSource.setDriverClassName(driverClass);
        dataSource.setUrl(url);
        dataSource.setUsername(user);
        dataSource.setPassword(password);
        dataSource.setInitialSize(initialSize);
        dataSource.setMinIdle(minidle);
        dataSource.setMaxActive(maxTotal);
        dataSource.setMaxWait(maxWaitMillis);
        try {
            // 如果想使用 Druid 的sql监控则，此处需要写 stat
            dataSource.setFilters(driverFilters);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return dataSource;
    }
    
    /**
     * 注入 事务，在 serviceImpl 的时候使用
     * @return
     */
    @Bean(name = "baseTransationManager")
    @Primary
    public DataSourceTransactionManager setTransactionManager(){
        // 传入 dataSource
        return new DataSourceTransactionManager( mysqlDataSource() );
    }
    
    /**
     * 注入 sqlSession
     * @return
     * @throws Exception
     */
    @Bean(name = "baseSqlSessionTemplate")
    @Primary
    public SqlSessionFactory setSqlSessionFactory() throws Exception {
        final SqlSessionFactoryBean bean = new SqlSessionFactoryBean();
        bean.setDataSource(mysqlDataSource());
        // 设置mapper.xml 扫描路径
        bean.setMapperLocations( new PathMatchingResourcePatternResolver().
        		getResources(BaseDataSourceConfig.MAPPER_XML_AREA) );
        return bean.getObject();
    }
}
