package com.store.pojo;

import java.util.Date;

import org.springframework.format.annotation.DateTimeFormat;

public class User {
    /**
     * 用户编号
     */
    private String uid;

    /**
     * 用户账户
     */
    private String username;

    /**
     *用户密码
     */
    private String password;

    /**
     * 用户昵称
     */
    private String name;

    /**
     *用户邮箱
     */
    private String email;

    /**
     *  用户电话
     */
    private String telephone;

    /**
     *用户生日
     */

    private Date birthday;

    /**
     *  用户性别
     */
    private String sex;

    /**
     * 用户状态   1:激活 0:未激活
     */
    private Integer state;

    /**
     * 激活码
     */
    private String code;

    
    public User() {
		// TODO Auto-generated constructor stub
	}
	public User(String uid, String username, String password, String name, String email, String telephone,
			Date birthday, String sex, int state, String code) {
		super();
		this.uid = uid;
		this.username = username;
		this.password = password;
		this.name = name;
		this.email = email;
		this.telephone = telephone;
		this.birthday = birthday;
		this.sex = sex;
		this.state = state;
		this.code = code;
	}
    
    
    @Override
	public String toString() {
		return "User [uid=" + uid + ", username=" + username + ", password=" + password + ", name=" + name + ", email="
				+ email + ", telephone=" + telephone + ", birthday=" + birthday + ", sex=" + sex + ", state=" + state
				+ ", code=" + code + "]";
	}

	/**
     * @return the value of user.uid
     */
    public String getUid() {
        return uid;
    }

    /**
     * @param uid the value for user.uid
     */
    public void setUid(String uid) {
        this.uid = uid == null ? null : uid.trim();
    }

    /**
     * @return the value of user.username
     */
    public String getUsername() {
        return username;
    }

    /**
     * @param username the value for user.username
     */
    public void setUsername(String username) {
        this.username = username == null ? null : username.trim();
    }

    /**
     * @return the value of user.password
     */
    public String getPassword() {
        return password;
    }

    /**
     * @param password the value for user.password
     */
    public void setPassword(String password) {
        this.password = password == null ? null : password.trim();
    }

    /**
     * @return the value of user.name
     */
    public String getName() {
        return name;
    }

    /**
     * @param name the value for user.name
     */
    public void setName(String name) {
        this.name = name == null ? null : name.trim();
    }

    /**
     * @return the value of user.email
     */
    public String getEmail() {
        return email;
    }

    /**
     * @param email the value for user.email
     */
    public void setEmail(String email) {
        this.email = email == null ? null : email.trim();
    }

    /**
     * @return the value of user.telephone
     */
    public String getTelephone() {
        return telephone;
    }

    /**
     * @param telephone the value for user.telephone
     */
    public void setTelephone(String telephone) {
        this.telephone = telephone == null ? null : telephone.trim();
    }

    /**
     * @return the value of user.birthday
     */
    public Date getBirthday() {
        return birthday;
    }

    /**
     * @param birthday the value for user.birthday
     */
    public void setBirthday(Date birthday) {
        this.birthday = birthday;
    }

    /**
     * @return the value of user.sex

     */
    public String getSex() {
        return sex;
    }

    /**
     * @param sex the value for user.sex
     */
    public void setSex(String sex) {
        this.sex = sex == null ? null : sex.trim();
    }

    /**
     * @return the value of user.state
     */
    public Integer getState() {
        return state;
    }

    /**
     * @param state the value for user.state
     */
    public void setState(Integer state) {
        this.state = state;
    }

    /**
     * @return the value of user.code
     */
    public String getCode() {
        return code;
    }

    /**
     * @param code the value for user.code
     */
    public void setCode(String code) {
        this.code = code == null ? null : code.trim();
    }
}