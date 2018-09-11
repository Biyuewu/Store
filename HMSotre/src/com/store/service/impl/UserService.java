package com.store.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.store.dao.UserMapper;
import com.store.pojo.User;
import com.store.service.IUserService;

@Service
public class UserService implements IUserService {

	@Autowired
	private UserMapper um;

	@Override
	public int userRegist(User user) {
		return um.insert(user);
	}

	@Override
	public boolean userActive(String code) {
		User user=um.userActive(code);
		if(null!=user){
			//可以根据激活码查询到一个用户
			//修改用户的状态,清除激活码
			user.setState(1);
			user.setCode(null);
			//对数据库执行一次真实的更新操作  update user set state=1 , code=null where uid=?
			//update user set username=? , password=? ,name =? ,email=?, telephone =? ,birthday =? ,sex=? ,state=? ,code= ? where uid=?
			um.updateByPrimaryKey(user);
			return  true;
		}else{
			//不可以根据激活码查询到一个用户
			return false;
		}
	}

	@Override
	public User userLogin(User user) {
		User uu=um.userLogin(user);
		if(null==uu){
			throw new RuntimeException("密码有误!");
		}else if(uu.getState()==0){
			throw new RuntimeException("用户未激活!");
		}else{
			return uu;
		}
	}

	@Override
	public User findUserByCode(String code) {
		return um.findUserByCode(code);
	}

	@Override
	public int updateByPrimaryKey(User record) {
		return um.updateByPrimaryKey(record);
	}

	
}
