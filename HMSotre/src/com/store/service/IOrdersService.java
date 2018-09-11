package com.store.service;

import java.util.List;

import com.store.pojo.Orders;
import com.store.pojo.PageModel;
import com.store.pojo.User;

public interface IOrdersService {

	int deleteByPrimaryKey(String oid);

    int insert(Orders record);

    Orders selectByPrimaryKey(String oid);

    List<Orders> selectAll();

    int updateByPrimaryKey(Orders record);

	PageModel findMyOrdersWithPage(User user, int curNum);

	void saveOrder(Orders order);

	PageModel findAllOrdersByPage(int num, int state);
	
}
