package com.store.service;

import java.util.List;

import com.store.pojo.Orderitem;

public interface IOrderitemService {

	int deleteByPrimaryKey(String itemid);

    int insert(Orderitem record);

    Orderitem selectByPrimaryKey(String itemid);

    List<Orderitem> selectByOid(String oid);
    
    List<Orderitem> selectAll();
    
    int updateByPrimaryKey(Orderitem record);
}
