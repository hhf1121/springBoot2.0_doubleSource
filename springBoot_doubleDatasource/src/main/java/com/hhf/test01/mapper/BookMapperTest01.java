package com.hhf.test01.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

import com.hhf.entity.Book;

@Mapper
public interface BookMapperTest01 {

	// 获取列表
	@Select("select * from book where name like concat('%',#{name},'%')")
	List<Book> getList(@Param("name") String name);


	//改变书籍数量
	@Update("update book set count=count-1 where id=#{id}")
	int upDateCountById(Integer id);

	//增加书籍
	@Insert("insert into book(name,author,count,countSize) values(#{name},#{author},#{count},#{count})")
	int addBook(Book book);
	
}
