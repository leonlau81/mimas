package com.vanseed.mimas.domain.config;

import com.alibaba.druid.pool.DruidDataSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.autoconfigure.orm.jpa.JpaProperties;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.orm.jpa.EntityManagerFactoryBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.orm.jpa.JpaTransactionManager;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.transaction.PlatformTransactionManager;

import javax.persistence.EntityManager;
import javax.sql.DataSource;
import java.util.Map;

/**
 * @author leon
 * @version 1.0.0
 * @date 18/6/14 下午4:11.
 */

@Configuration
@EnableJpaRepositories(
        entityManagerFactoryRef="acctEntityManagerFactory",
        transactionManagerRef="acctTransactionManager",
        basePackages= { "com.vanseed.mimas.domain.repository.acct" }) //设置Repository所在位置
public class AcctJPAConfig {

    @Autowired
    @Qualifier("acctDataSource")
    private DataSource acctDataSource;

    @Autowired
    private JpaProperties jpaProperties;

    @Bean(name = "acctEntityManager")
    public EntityManager acctEntityManager(EntityManagerFactoryBuilder builder) {
        return acctEntityManagerFactory(builder).getObject().createEntityManager();
    }

    @Bean(name = "acctEntityManagerFactory")
    public LocalContainerEntityManagerFactoryBean acctEntityManagerFactory (EntityManagerFactoryBuilder builder) {
        return builder
                .dataSource(acctDataSource)
                .properties(getVendorProperties(acctDataSource))
                .packages("com.vanseed.mimas.domain.model.acct") //设置实体类所在位置
                .persistenceUnit("primaryPersistenceUnit")
                .build();
    }

    private Map<String, String> getVendorProperties(DataSource dataSource) {
        return jpaProperties.getHibernateProperties(dataSource);
    }

    @Bean(name = "acctTransactionManager")
    @Qualifier("acctTxn")
    public PlatformTransactionManager acctTransactionManager(EntityManagerFactoryBuilder builder) {
        return new JpaTransactionManager(acctEntityManagerFactory(builder).getObject());
    }

}
