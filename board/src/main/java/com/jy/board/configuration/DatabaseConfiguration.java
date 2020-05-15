package com.jy.board.configuration;

import javax.sql.DataSource;

import org.apache.ibatis.session.SqlSessionFactory;
import org.mybatis.spring.SqlSessionFactoryBean;
import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;

import com.zaxxer.hikari.HikariConfig;
import com.zaxxer.hikari.HikariDataSource;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Configuration
@PropertySource("classpath:/application.yml")
public class DatabaseConfiguration {
	
	@Autowired
	private ApplicationContext applicationContext;	// MyBatis를 위한 SqlSessionFactory 생성에 필요
	
	@Bean
	@ConfigurationProperties(prefix = "spring.datasource.hikari")
	public HikariConfig hikariConfig() {
		return new HikariConfig();
	}

	@Bean
	public DataSource dataSource() {
		DataSource dataSource = new HikariDataSource(hikariConfig());
		log.info("데이터 소스 생성 완료 : " + dataSource.toString());
		return dataSource;
	}
	
	/**
	 * 전달받은 DataSoure 로 부터 SqlSessionFactory를 생성하여 빈으로 등록 
	 * @param dataSource
	 * @return SqlSessionFactory
	 * @throws Exception 
	 */
	@Bean	
	public SqlSessionFactory sqlSessionFactory(DataSource dataSource) throws Exception {
		SqlSessionFactoryBean sessionFactoryBean = new SqlSessionFactoryBean();
		sessionFactoryBean.setDataSource(dataSource);	// Data Source 객체를 등록
		// Mapper 파일 위치 지정
		sessionFactoryBean.setMapperLocations(applicationContext.getResources("classpath:/mapper/**/sql-*.xml"));
		
		return sessionFactoryBean.getObject();
	}
	
	/**
	 * SqlSessionFactory를 전달받아, SqlSessionTemplate를 생성하여 빈으로 등록
	 * @param sessionFactory
	 * @return SqlSessionTemplate
	 */
	@Bean
	public SqlSessionTemplate sqlSessionTemplate(SqlSessionFactory sessionFactory) {
		return new SqlSessionTemplate(sessionFactory);
				
	}
	
	
}
