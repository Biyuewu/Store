package com.store.dao;

import java.util.List;

import com.store.pojo.Orderitem;

public interface OrderitemMapper {
    int deleteByPrimaryKey(String itemid);

    int insert(Orderitem record);

    Orderitem selectByPrimaryKey(String itemid);
 
    List<Orderitem> selectAll();

    int updateByPrimaryKey(Orderitem record);
	
    List<Orderitem> selectByOid(String oid);
	
}