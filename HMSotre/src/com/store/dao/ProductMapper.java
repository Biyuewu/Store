package com.store.dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.store.pojo.Product;

public interface ProductMapper {
    /**
     *根据id删除
     */
    int deleteByPrimaryKey(String pid);

    /**
     * 插入商品
     */
    int insert(Product record);

    /**
     * 根据id查询商品
     */
    Product selectByPrimaryKey(String pid);

    /**
     * 查询所有商品
     */
    List<Product> selectAll();

    /**
     * 根据id修改商品信息
     */
    int updateByPrimaryKey(Product record);
    
    /**
     * 查找热门商品
     */
    List<Product> fingHot();

    /**
     * 查询总记录
     * @param cid
     * @return 商品总条数
     */
	int findTotalRecords(String cid);
	/**
	 * 分页查询
	 * @param cid
	 * @param startIndex
	 * @param pageSize
	 * @return
	 */
	List findProductsByCidWithPage(@Param("cid")String cid, @Param("startIndex")int startIndex, @Param("pageSize")int pageSize);

	List<Product> findNews();

	List findAllProductsByPageUp(@Param("startIndex")int startIndex,@Param("pageSize") int pageSize);

	int findAllTotalRecordsUp();

	int findAllTotalRecordsDown();
	
	List findAllProductsByPageDown(@Param("startIndex")int startIndex,@Param("pageSize") int pageSize);

	List<Product> findProductsBypnameWithPage(@Param("pname")String pname,  @Param("startIndex")int startIndex, @Param("pageSize")int pageSize);

	int findTotalRecordsByPname(String pname);
}