package com.hhf.controller;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.hhf.entity.Book;
import com.hhf.test01.service.bookService01;
import com.hhf.test02.service.bookService02;
import com.hhf.utils.ResultUtils;

/**
 * userController
 * 集成mybatis/多数据源
 * 分布式事务解决方案
 * 原因：多个不同服务、操作多数据源
 * @author Administrator
 *
 */

@RestController
@RequestMapping("book")
public class BookController {

	@Autowired
	private bookService01 bookService01;
	
	@Autowired
	private bookService02 bookService02;
	
	
	
	@RequestMapping(value="/addBook01",method=RequestMethod.POST)
	public Map<String,Object> addBook01(@RequestBody Book book){
		return ResultUtils.getSuccessResult(bookService01.addBook(book));
	} 
	
	
	@RequestMapping(value="/addBook02",method=RequestMethod.POST)
	public Map<String,Object> addBook02(@RequestBody Book book){
		return ResultUtils.getSuccessResult(bookService02.addBook(book));
	} 
	
	
	/**
	 * 同时操作两个数据源
	 * @param book
	 * @return
	 */
	@RequestMapping(value="/addBooks",method=RequestMethod.POST)
	public Map<String,Object> addBooks(@RequestBody Book book){
		int result = bookService01.addBooks(book);
		if(result!=2) {
			return ResultUtils.getFailResult("保存失败...");
		}
		return ResultUtils.getSuccessResult(result);
	} 
	
	
	/**
	 * 同时操作两个数据源:Atomikos
	 * @param book
	 * @return
	 */
	@RequestMapping(value="/addBooksByAtomikos",method=RequestMethod.POST)
	public Map<String,Object> addBooksByAtomikos(@RequestBody Book book){
		int result = bookService01.addBooksByAtomikos(book);
		if(result!=2) {
			return ResultUtils.getFailResult("保存失败...");
		}
		return ResultUtils.getSuccessResult(result);
	} 
	
	
}
