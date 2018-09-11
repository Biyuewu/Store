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
			//���Ը��ݼ������ѯ��һ���û�
			//�޸��û���״̬,���������
			user.setState(1);
			user.setCode(null);
			//�����ݿ�ִ��һ����ʵ�ĸ��²���  update user set state=1 , code=null where uid=?
			//update user set username=? , password=? ,name =? ,email=?, telephone =? ,birthday =? ,sex=? ,state=? ,code= ? where uid=?
			um.updateByPrimaryKey(user);
			return  true;
		}else{
			//�����Ը��ݼ������ѯ��һ���û�
			return false;
		}
	}

	@Override
	public User userLogin(User user) {
		User uu=um.userLogin(user);
		if(null==uu){
			throw new RuntimeException("��������!");
		}else if(uu.getState()==0){
			throw new RuntimeException("�û�δ����!");
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
