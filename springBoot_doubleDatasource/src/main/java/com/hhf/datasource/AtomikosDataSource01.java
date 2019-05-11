package com.hhf.datasource;

import java.sql.SQLException;

import javax.sql.DataSource;

import org.apache.ibatis.session.SqlSessionFactory;
import org.mybatis.spring.SqlSessionFactoryBean;
import org.mybatis.spring.SqlSessionTemplate;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.jta.atomikos.AtomikosDataSourceBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.hhf.config.DBconfig1;
import com.mysql.jdbc.jdbc2.optional.MysqlXADataSource;


@Configuration//注册到spring容器中
@MapperScan(basePackages="com.hhf.test01",sqlSessionTemplateRef="test01SqlSessionTemplate")
public class AtomikosDataSource01 {

	//配置数据源
	@Bean(name="test01DataSource")
	public DataSource testDataSource(DBconfig1 dbconfig) throws SQLException {
		MysqlXADataSource mysqlXADataSource=new MysqlXADataSource();
		mysqlXADataSource.setURL(dbconfig.getUrl());
		mysqlXADataSource.setPinGlobalTxToPhysicalConnection(true);
		mysqlXADataSource.setPassword(dbconfig.getPassword());
		mysqlXADataSource.setUser(dbconfig.getUsername());
		mysqlXADataSource.setPinGlobalTxToPhysicalConnection(true);
		
		AtomikosDataSourceBean xaDataSource=new AtomikosDataSourceBean();
		xaDataSource.setXaDataSource(mysqlXADataSource);
		xaDataSource.setUniqueResourceName("test01DataSource");
		xaDataSource.setMinPoolSize(dbconfig.getMinPoolSize());
		xaDataSource.setMaxPoolSize(dbconfig.getMaxPoolSize());
		xaDataSource.setMaxLifetime(dbconfig.getMaxLifetime());
		xaDataSource.setBorrowConnectionTimeout(dbconfig.getBorrowConnectionTimeout());
		xaDataSource.setLoginTimeout(dbconfig.getLoginTimeout());
		xaDataSource.setMaintenanceInterval(dbconfig.getMaintenanceInterval());
		xaDataSource.setMaxIdleTime(dbconfig.getMaxIdleTime());
		xaDataSource.setTestQuery(dbconfig.getTestQuery());
		return xaDataSource;
	}
	
	
	/**
	 * 不再使用事务管理、创建SqlSessionTemplate就可。
	 * @param dataSource
	 * @return
	 * @throws Exception 
	 */
	@Bean(name="test01SqlSessionFactory")
	public SqlSessionFactory testSqlSessionFactory(@Qualifier("test01DataSource") DataSource dataSource) throws Exception {
		SqlSessionFactoryBean bean=new SqlSessionFactoryBean();
		bean.setDataSource(dataSource);
		return bean.getObject();
	}
	
	@Bean(name="test01SqlSessionTemplate")
	public SqlSessionTemplate testSqlSessionTemplate(@Qualifier("test01SqlSessionFactory") SqlSessionFactory sqlSessionFactory) {
		return new SqlSessionTemplate(sqlSessionFactory);
	}
	
}
