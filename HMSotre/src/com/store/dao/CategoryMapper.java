package com.store.dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.store.pojo.Category;

public interface CategoryMapper {
    int deleteByPrimaryKey(@Param("cid")String cid);

    int insert(Category record);

    Category selectByPrimaryKey(String cid);

    List<Category> selectAll();

    int updateByPrimaryKey(Category record);

	int deleteByName(String cname);

	int findTotalRecords();

	List findAllCatsByPage(@Param("startIndex")int startIndex,@Param("pageSize") int pageSize);
}