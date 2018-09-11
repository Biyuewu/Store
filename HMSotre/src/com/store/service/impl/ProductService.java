package com.store.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.store.dao.ProductMapper;
import com.store.pojo.PageModel;
import com.store.pojo.Product;
import com.store.service.IProductService;

@Service
public class ProductService implements IProductService {

	@Autowired
	private ProductMapper pm;

	@Override
	public List<Product> findHots() {
		return pm.fingHot();
	}

	@Override
	public List<Product> findNews() {
		return pm.findNews();
	}

	@Override
	public Product findProductByPid(String pid) {
		return pm.selectByPrimaryKey(pid);
	}

	@Override
	public PageModel findProductsByCidWithPage(String cid, int curNum) {
		// 1_创建PageModel对象 目的:计算分页参数
		// 统计当前分类下商品个数 select count(*) from product where cid=?
		int totalRecords = pm.findTotalRecords(cid);
		PageModel p = new PageModel(curNum, totalRecords, 12);
		// 2_关联集合 select * from product where cid =? limit ? ,?
		List list = pm.findProductsByCidWithPage(cid, p.getStartIndex(), p.getPageSize());
		p.setList(list);
		// 3_关联url
		p.setUrl("findProductsByCidWithPage?cid=" + cid);
		return p;
	}

	@Override
	public PageModel findAllProductsByPageUp(int currPage) {
		int totalRecords = pm.findAllTotalRecordsUp();
		PageModel p = new PageModel(currPage, totalRecords, 10);
		// 2_关联集合 select * from product where cid =? limit ? ,?
		List list = pm.findAllProductsByPageUp(p.getStartIndex(), p.getPageSize());
		p.setList(list);
		// 3_关联url
		p.setUrl("findAllProductsByPageUp");
		return p;
	}

	@Override
	public int updateByPrimaryKey(Product product) {
		return pm.updateByPrimaryKey(product);
	}

	@Override
	public PageModel findAllProductsByPageDown(int currPage) {
		int totalRecords = pm.findAllTotalRecordsDown();
		PageModel p = new PageModel(currPage, totalRecords, 10);
		// 2_关联集合 select * from product where cid =? limit ? ,?
		List list = pm.findAllProductsByPageDown(p.getStartIndex(), p.getPageSize());
		p.setList(list);
		// 3_关联url
		p.setUrl("findAllProductsByPageDown");
		return p;
	}

	@Override
	public int addProduct(Product product) {
		return pm.insert(product);
	}

	@Override
	public PageModel findProductLikeWithPage(String pname,int curNum) {
		
		int totalRecords = pm.findTotalRecordsByPname(pname);
		PageModel p = new PageModel(curNum, totalRecords, 10);
		// 2_关联集合 select * from product where cid =? limit ? ,?
		List list = pm.findProductsBypnameWithPage(pname, p.getStartIndex(), p.getPageSize());
		p.setList(list);
		// 3_关联url
		p.setUrl("findProductLikeWithPage?pname="+pname);
		return p;
	}

	
	
}
