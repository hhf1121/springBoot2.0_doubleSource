package com.hhf;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;

import com.hhf.config.DBconfig1;
import com.hhf.config.DBconfig2;

/**
 * Springboot全局启动app
 * @author Administrator
 *
 */
//第二种启动方式：
//@EnableAutoConfiguration
//@ComponentScan("com.hhf.controller")//扫描此包下的所有controller、并启动
//第三种启动方式：(扫描的类，在同包或之下。)
@SpringBootApplication
@EnableConfigurationProperties(value= {DBconfig1.class,DBconfig2.class})//atomikos统一事务管理、读取配置
//@EnableAsync//开启异步调用
//@MapperScan(basePackages= {"com.hhf.test02.mapper","com.hhf.test01.mapper"})
public class App {

	public static void main(String[] args) {
		SpringApplication.run(App.class,args);
	}
	
}
