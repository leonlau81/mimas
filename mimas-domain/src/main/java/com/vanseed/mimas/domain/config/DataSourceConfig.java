package com.vanseed.mimas.domain.config;

import com.alibaba.druid.pool.DruidDataSource;
import com.alibaba.druid.pool.xa.DruidXADataSource;
import com.github.pagehelper.PageHelper;
import com.vanseed.mimas.domain.helper.DataSourceProperties;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.autoconfigure.jdbc.DataSourceBuilder;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.jta.atomikos.AtomikosDataSourceBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.context.annotation.PropertySource;
import org.springframework.core.env.Environment;

import javax.sql.DataSource;
import java.util.Properties;

/**
 * @author leon
 * @version 1.0.0
 * @date 18/6/14 下午4:11.
 */

@Configuration
@PropertySource(value="classpath:domain.properties")
public class DataSourceConfig {

//    @Bean(name = "acctDataSource")
//    @Qualifier("acctDataSource")
//    public DataSource acctDataSource() {
//        DruidDataSource druidDataSource = new DruidDataSource();
//        return druidDataSource;
//    }

    @Bean
    @Qualifier("userDSProp")
    @ConfigurationProperties(prefix="user.datasource")
    public DataSourceProperties userDSProp( ) {
        DataSourceProperties prop = new DataSourceProperties();
        return prop;
    }

    @Primary
    @Bean(name = "userDataSource")
    @Qualifier("userDataSource")
    public DataSource userDataSource(@Qualifier("userDSProp") DataSourceProperties dsprop ) {
        DruidXADataSource dataSource = new DruidXADataSource();
        BeanUtils.copyProperties(dsprop, dataSource);
        AtomikosDataSourceBean xaDataSource = new AtomikosDataSourceBean();
        xaDataSource.setXaDataSource(dataSource);
        xaDataSource.setUniqueResourceName("userDataSource");
        return xaDataSource;
    }

    @Bean
    @Qualifier("acctDruidSource")
    @ConfigurationProperties(prefix="acct.datasource")
    public DataSourceProperties acctDSProp( ) {
        DataSourceProperties prop = new DataSourceProperties();
        return prop;
    }


    @Bean(name = "acctDataSource")
    @Qualifier("acctDataSource")
    public DataSource acctDataSource(@Qualifier("acctDSProp") DataSourceProperties dsprop) {
        DruidXADataSource dataSource = new DruidXADataSource();
        BeanUtils.copyProperties(dsprop, dataSource);
        AtomikosDataSourceBean xaDataSource = new AtomikosDataSourceBean();
        xaDataSource.setXaDataSource(dataSource);
        xaDataSource.setUniqueResourceName("acctDataSource");
        return xaDataSource;
    }

    @Bean
    public PageHelper pageHelper(){
        PageHelper pageHelper = new PageHelper();
        Properties properties = new Properties();
        properties.setProperty("offsetAsPageNum","true");
        properties.setProperty("rowBoundsWithCount","true");
        properties.setProperty("reasonable","true");
        properties.setProperty("dialect","mysql");    //配置mysql数据库的方言
        pageHelper.setProperties(properties);
        return pageHelper;
    }


}
