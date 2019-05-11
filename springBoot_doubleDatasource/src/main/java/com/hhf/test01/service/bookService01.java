package com.hhf.test01.service;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.hhf.entity.Book;
import com.hhf.test01.mapper.BookMapperTest01;
import com.hhf.test02.mapper.BookMapperTest02;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
public class bookService01{

	@Resource
	private BookMapperTest01 bookMapper01;
	
	@Resource
	private BookMapperTest02 bookMapper02;
	
	public List<Book> getList(String name) {
		log.info("01-----getList");
		return bookMapper01.getList(name);
	}

	public int upDateCountById(Integer id) {
		log.info("01-----upDateCountById");
		return bookMapper01.upDateCountById(id);
	}

	
	@Transactional(transactionManager="test001TransactionManager")//指定事务的数据源、否则报错
	public int addBook(Book book) {
		log.info("01-----addBook");
		int addBook = bookMapper01.addBook(book);
		int i=1/book.getCount();
		return addBook;
	}
	
	
	@Transactional(transactionManager="test002TransactionManager")//指定开启事务的数据源是test01，那么test02的数据操作就不会有事务
	public int addBooks(Book book) {
		//需要注掉app里的@EnableConfigurationProperties和atomikos的数据源和配置文件
		//传统的事务解决方案：springBoot+jta（Java Transaction API）+atomikos-->注册到同一个全局事务中    (但微服务项目下，无法解决)
		log.info("01-----addBooks----02");
		int addBook2 = bookMapper02.addBook(book);
		int addBook1 = bookMapper01.addBook(book);
		int i=1/book.getCountSize();
		return addBook1+addBook2;
	}
	
	
	@Transactional()//开启事务:此时的事务被atomikos统一管理
	public int addBooksByAtomikos(Book book) {
		//需要注掉DataSourceConfig01和DataSourceConfig02（此文件中往spring容器中注入了事务管理bean，和atomikos的统一管理相悖）
		log.info("01-----addBooksByAtomikos----02");
		int addBook2 = bookMapper02.addBook(book);
		int addBook1 = bookMapper01.addBook(book);
		int i=1/book.getCountSize();
		return addBook1+addBook2;
	}

}
