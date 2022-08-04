package com.javashitang.part4.mapper;

import com.javashitang.part4.pojo.Book;
import org.apache.ibatis.annotations.Param;

import java.util.List;


public interface BookMapper {

    Book selectBookByIdAndName(@Param("id") Integer id, @Param("name") String name);

    List<Book> selectBookById(List<Integer> list);

    List<Book> selectBookByIdV2(List<Integer> list);

    int updateById(@Param("id") Integer id, @Param("name") String name);

    int deleteById(Integer id);
}
