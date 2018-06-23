package com.vanseed.mimas.domain.config;

import java.util.Properties;

import org.apache.ibatis.plugin.Interceptor;
import org.apache.ibatis.session.SqlSessionFactory;
import org.mybatis.spring.SqlSessionFactoryBean;
import org.mybatis.spring.SqlSessionTemplate;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.github.pagehelper.PageHelper;
import org.springframework.core.io.support.PathMatchingResourcePatternResolver;
import org.springframework.core.io.support.ResourcePatternResolver;

import javax.sql.DataSource;

@Configuration
@MapperScan(basePackages = {"com.vanseed.mimas.domain.mybatis.user"}, sqlSessionTemplateRef = "userSqlSessionTemplate")
public class UserMyBatisConfig {

	@Autowired
	PageHelper pageHelper;

	/*
	 * 重新定义了SqlSessionFactory会导致使用PageHelper.startPage(1,1)无效。
	 * 需要重新配置pagehelper拦截器插件
	 */
	@Bean(name = "userSqlSessionFactory")
	public SqlSessionFactory userSqlSessionFactory(@Qualifier("userDataSource") DataSource dataSource) throws Exception {
		SqlSessionFactoryBean bean = new SqlSessionFactoryBean();

		//设置数据源
		bean.setDataSource(dataSource);
		//设置pagehelper拦截器插件
		Interceptor[] plugins =  new Interceptor[]{pageHelper};
		bean.setPlugins(plugins);
		//添加XML目录
		ResourcePatternResolver resolver = new PathMatchingResourcePatternResolver();
		try {
			bean.setMapperLocations(resolver.getResources("classpath:com/vanseed/mimas/domain/mybatis/user/*.xml"));
			return bean.getObject();
		} catch (Exception e) {
			e.printStackTrace();
			throw new RuntimeException(e);
		}
	}

	@Bean(name = "userSqlSessionTemplate")
	public SqlSessionTemplate userSqlSessionTemplate(@Qualifier("userSqlSessionFactory") SqlSessionFactory sqlSessionFactory) throws Exception {
		SqlSessionTemplate template = new SqlSessionTemplate(sqlSessionFactory); // 使用上面配置的Factory
		return template;
	}
}
