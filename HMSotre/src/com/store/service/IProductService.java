package com.store.service;

import java.util.List;

import com.store.pojo.PageModel;
import com.store.pojo.Product;


public interface IProductService {


	List<Product> findHots();

	List<Product> findNews();

	Product findProductByPid(String pid);

	PageModel findProductsByCidWithPage(String cid, int curNum);

	PageModel findAllProductsByPageUp(int currPage);

	int updateByPrimaryKey(Product product);

	PageModel findAllProductsByPageDown(int currPage);

	int addProduct(Product product);
	
	PageModel findProductLikeWithPage(String pname,int curNum);
}
