package com.capgemini.onlinetest.dao;

import java.util.List;

import com.capgemini.onlinetest.entity.Userdata;



public interface UserDao {

	Userdata addUser(Userdata u);
	List<Userdata> getAllUsers();
	Userdata deleteUser(int userId);
	Userdata updateUser(Userdata u);
	String loginUser(Userdata u);
	Userdata getById(int uid);
	
}