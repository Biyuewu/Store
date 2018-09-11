package com.store.dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.store.pojo.User;

public interface UserMapper {
    /**
     * 根据id删除
     */
    int deleteByPrimaryKey(String uid);

    /**
     * 插入用户
     */
    int insert(User user);

    /**
     * 根据id查询
     */
    User selectByPrimaryKey(String uid);

    /**
     * 查询所有
     */
    List<User> selectAll();

    /**
     * 根据id更改
     */
    int updateByPrimaryKey(User record);

    /**
     * 根激活码查询用户
     */
	User userActive(String code);

	/**
	 * 用户登录
	 */
	User userLogin(User user);

	User findUserByCode(@Param("code")String code);
}