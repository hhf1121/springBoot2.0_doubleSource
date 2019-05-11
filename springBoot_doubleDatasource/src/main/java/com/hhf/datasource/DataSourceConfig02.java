//package com.hhf.datasource;
//
//import javax.sql.DataSource;
//
//import org.apache.ibatis.session.SqlSessionFactory;
//import org.mybatis.spring.SqlSessionFactoryBean;
//import org.mybatis.spring.SqlSessionTemplate;
//import org.mybatis.spring.annotation.MapperScan;
//import org.springframework.beans.factory.annotation.Qualifier;
//import org.springframework.boot.context.properties.ConfigurationProperties;
//import org.springframework.boot.jdbc.DataSourceBuilder;
//import org.springframework.context.annotation.Bean;
//import org.springframework.context.annotation.Configuration;
//import org.springframework.jdbc.datasource.DataSourceTransactionManager;
//
//
///**
// * 数据源配置信息、构建sqlsession等信息：test02
// * @author Administrator
// *
// */
//@Configuration//注册到spring容器中
//@MapperScan(basePackages="com.hhf.test02",sqlSessionFactoryRef="test002SqlSessionFactory")
//public class DataSourceConfig02 {
//	
//	
//	/**
//	 * 配置test02数据库
//	 * @return
//	 */
//	@Bean(name="test002DataSource")
//	@ConfigurationProperties(prefix="spring.datasource.test02")
//	public DataSource testDataSource() {
//		return DataSourceBuilder.create().type(com.alibaba.druid.pool.DruidDataSource.class).build();
//	}
//	
//	
//	/**
//	 * 数据源
//	 * @param dataSource
//	 * @return
//	 * @throws Exception
//	 */
//	@Bean(name="test002SqlSessionFactory")
//	public SqlSessionFactory testSqlSessionFactory(@Qualifier("test002DataSource") DataSource dataSource) throws Exception {
//		SqlSessionFactoryBean bean=new SqlSessionFactoryBean();
//		bean.setDataSource(dataSource);
//		return bean.getObject();
//	}
//	
//	
//	/**
//	 * 事务管理
//	 * @param dataSource
//	 * @return
//	 */
//	@Bean(name="test002TransactionManager")
//	public DataSourceTransactionManager testTransactionManager(@Qualifier("test002DataSource") DataSource dataSource) {
//		return new DataSourceTransactionManager(dataSource);
//	}
//	
//	@Bean(name="test002SqlSessionTemplate")
//	public SqlSessionTemplate testSqlSessionTemplate(@Qualifier("test002SqlSessionFactory") SqlSessionFactory sqlSessionFactory) {
//		return new SqlSessionTemplate(sqlSessionFactory);
//	}
//	
//}
