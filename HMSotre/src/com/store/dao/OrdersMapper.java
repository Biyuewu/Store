package com.store.dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.store.pojo.Orders;
import com.store.pojo.User;

public interface OrdersMapper {
    int deleteByPrimaryKey(String oid);

    int insert(Orders record);

    Orders selectByPrimaryKey(String oid);

    List<Orders> selectAll();

    int updateByPrimaryKey(Orders record);
    
    List findMyOrdersWithPage( @Param("uid")String uid, @Param("startIndex")int startIndex, @Param("pageSize")int pageSize);

	int getTotalRecords(@Param("uid")String uid);

	List<Orders> findMyOrdersWithPageByState(@Param("state")int state,@Param("startIndex")int startIndex, @Param("pageSize")int pageSize);

	int findAllTotalRecordsByState(@Param("state")int state);


}