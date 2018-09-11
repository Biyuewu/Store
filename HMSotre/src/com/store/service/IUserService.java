package com.store.service;

import com.store.pojo.User;


public interface IUserService {

	int userRegist(User user);

	boolean userActive(String code);

	User userLogin(User user);
	
	User findUserByCode(String code);
	
	int updateByPrimaryKey(User record);
}
