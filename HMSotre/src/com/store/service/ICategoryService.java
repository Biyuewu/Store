package com.store.service;

import java.util.List;

import com.store.pojo.Category;
import com.store.pojo.PageModel;

public interface ICategoryService {
	List<Category> getAllCats();

	int deleteCategory(String cid);

	int addCategory(Category category);

	int deleteByName(String cname);

	int updateByPrimaryKey(Category record);

	PageModel findAllCatsByPage(int curNum);
	
	
}
