package com.store.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.store.dao.CategoryMapper;
import com.store.pojo.Category;
import com.store.pojo.PageModel;
import com.store.service.ICategoryService;

@Service
public class CategoryService implements ICategoryService {
	@Autowired
	private CategoryMapper cm;

	@Override
	public List<Category> getAllCats() {
		return cm.selectAll();
	}

	@Override
	public int deleteCategory(String cid) {
		return cm.deleteByPrimaryKey(cid);
	}

	@Override
	public int addCategory(Category category) {
		return cm.insert(category);
	}

	@Override
	public int deleteByName(String cname) {
		// TODO Auto-generated method stub
		return cm.deleteByName(cname);
	}

	@Override
	public int updateByPrimaryKey(Category record) {
		return cm.updateByPrimaryKey(record);
	}

	@Override
	public PageModel findAllCatsByPage(int currPage) {
		int totalRecords = cm.findTotalRecords();
		PageModel p = new PageModel(currPage, totalRecords, 10);
		// 2_关联集合 select * from product where cid =? limit ? ,?
		List list = cm.findAllCatsByPage(p.getStartIndex(), p.getPageSize());
		p.setList(list);
		// 3_关联url
		p.setUrl("findAllCatsByPage");
		return p;
	}
	
	
}
