package com.hhf.test02.service;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.hhf.entity.Book;
import com.hhf.test02.mapper.BookMapperTest02;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
public class bookService02{

	@Resource
	private BookMapperTest02 bookMapper02;
	
	
	public List<Book> getList(String name) {
		log.info("02-----getList");
		return bookMapper02.getList(name);
	}

	public int upDateCountById(Integer id) {
		log.info("02-----upDateCountById");
		return bookMapper02.upDateCountById(id);
	}
	@Transactional(transactionManager="test002TransactionManager")//指定事务的数据源
	public int addBook(Book book) {
		log.info("02-----addBook");
		int addBook = bookMapper02.addBook(book);
		int i=1/book.getCount();
		return addBook;
	}

}
