package com.store.dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.store.pojo.Product;

public interface ProductMapper {
    /**
     *����idɾ��
     */
    int deleteByPrimaryKey(String pid);

    /**
     * ������Ʒ
     */
    int insert(Product record);

    /**
     * ����id��ѯ��Ʒ
     */
    Product selectByPrimaryKey(String pid);

    /**
     * ��ѯ������Ʒ
     */
    List<Product> selectAll();

    /**
     * ����id�޸���Ʒ��Ϣ
     */
    int updateByPrimaryKey(Product record);
    
    /**
     * ����������Ʒ
     */
    List<Product> fingHot();

    /**
     * ��ѯ�ܼ�¼
     * @param cid
     * @return ��Ʒ������
     */
	int findTotalRecords(String cid);
	/**
	 * ��ҳ��ѯ
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