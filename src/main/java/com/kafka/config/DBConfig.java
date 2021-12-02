package com.kafka.config;

import org.apache.commons.dbcp2.BasicDataSource;
import org.mybatis.spring.SqlSessionFactoryBean;
import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;

import javax.sql.DataSource;

/**
 * @author : hoyao
 * @name : hoyao
 * <PRE>
 * </PRE>
 * @class : DBConfig
 * date : 2021-11-05
 * @History <PRE>
 * NO  Date         time          Author                                      Desc
 * --------------------------------------------------------------------------------------------------------------
 * 1   2021-11-05   오후 2:55     hoyao (hoyaof@lgupluspartners.co.kr)        최초작성
 * </PRE>
 */

@Configuration
public class DBConfig {

	@Bean
	public DataSource dataSource() {
		BasicDataSource dataSource = new BasicDataSource();
		dataSource.setDriverClassName("net.sf.log4jdbc.sql.jdbcapi.DriverSpy");
		dataSource.setUrl("jdbc:log4jdbc:mysql://127.0.0.1:3306/springTest?useSSL=false");
		dataSource.setUsername("root");
		dataSource.setPassword("autoset");

		return dataSource;
	}

	@Bean
	public SqlSessionFactoryBean sqlSessionFactoryMt (DataSource dataSource, ApplicationContext ctx) throws Exception {
		SqlSessionFactoryBean sessionFactory = new SqlSessionFactoryBean();
		sessionFactory.setDataSource(dataSource);
		sessionFactory.setConfigLocation(ctx.getResource("classpath:/mybatis-config.xml"));
		sessionFactory.setMapperLocations(ctx.getResources("classpath:mappers/**/*Mapper.xml"));
		//sessionFactory.setMapperLocations(ctx.getResources("classpath:/mybatis/mappers/*.xml"));

		return sessionFactory;
	}
	@Bean
	public SqlSessionTemplate sqlTemplate(SqlSessionFactoryBean sqlSession) throws Exception {
		return new SqlSessionTemplate(sqlSession.getObject());
	}

	@Bean
	public DataSourceTransactionManager transactionManagerDasMt (DataSource dataSource) throws Exception {
		return new DataSourceTransactionManager(dataSource);
	}
}