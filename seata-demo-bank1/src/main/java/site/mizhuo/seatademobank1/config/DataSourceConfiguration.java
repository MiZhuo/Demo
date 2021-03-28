package site.mizhuo.seatademobank1.config;

import com.alibaba.druid.pool.DruidDataSource;
import io.seata.rm.datasource.DataSourceProxy;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;

import javax.sql.DataSource;

/**
 * @author mizhuo
 */
@Configuration
public class DataSourceConfiguration {

    private final ApplicationContext applicationContext;

    public DataSourceConfiguration(ApplicationContext applicationContext) {
        this.applicationContext = applicationContext;
    }

    @Bean
    @ConfigurationProperties(prefix = "spring.datasource.local-db")
    public DataSource dataSource(){
        return new DruidDataSource();
    }

    @Bean
    @Primary
    public DataSource dataSourceProxy(DataSource dataSource){
        DataSourceProxy dataSourceProxy = new DataSourceProxy(dataSource);
        return dataSourceProxy;
    }
}
