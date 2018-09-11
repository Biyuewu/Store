package com.store.dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.store.pojo.User;

public interface UserMapper {
    /**
     * ����idɾ��
     */
    int deleteByPrimaryKey(String uid);

    /**
     * �����û�
     */
    int insert(User user);

    /**
     * ����id��ѯ
     */
    User selectByPrimaryKey(String uid);

    /**
     * ��ѯ����
     */
    List<User> selectAll();

    /**
     * ����id����
     */
    int updateByPrimaryKey(User record);

    /**
     * ���������ѯ�û�
     */
	User userActive(String code);

	/**
	 * �û���¼
	 */
	User userLogin(User user);

	User findUserByCode(@Param("code")String code);
}