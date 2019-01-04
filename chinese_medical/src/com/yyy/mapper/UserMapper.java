package com.yyy.mapper;

import org.apache.ibatis.annotations.Param;

import com.yyy.model.User;

public interface UserMapper {
	
	public int register(User user);
	
	public User searchById(int uid);
	
	public int update(User user);
	
	public User login(@Param(value="uaccountnum")int uaccountnum,@Param(value="upassword")String upassword);

}
