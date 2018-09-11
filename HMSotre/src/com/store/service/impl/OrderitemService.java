package com.store.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.store.dao.OrderitemMapper;
import com.store.pojo.Orderitem;
import com.store.service.IOrderitemService;
@Service
public class OrderitemService implements IOrderitemService {

	@Autowired
	private OrderitemMapper om;

	@Override
	public int deleteByPrimaryKey(String itemid) {
		return om.deleteByPrimaryKey(itemid);
	}

	@Override
	public int insert(Orderitem record) {
		return om.insert(record);
	}

	@Override
	public Orderitem selectByPrimaryKey(String itemid) {
		return om.selectByPrimaryKey(itemid);
	}

	@Override
	public List<Orderitem> selectAll() {
		return om.selectAll();
	}

	@Override
	public int updateByPrimaryKey(Orderitem record) {
		return om.updateByPrimaryKey(record);
	}

	@Override
	public List<Orderitem> selectByOid(String oid) {
		return om.selectByOid(oid);
	}
	
	
}
