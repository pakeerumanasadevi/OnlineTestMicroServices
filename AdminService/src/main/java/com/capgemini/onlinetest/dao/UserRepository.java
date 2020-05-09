package com.capgemini.onlinetest.dao;

import org.springframework.data.jpa.repository.JpaRepository;

import org.springframework.stereotype.Repository;


import com.capgemini.onlinetest.entity.Userdata;
@Repository
public interface UserRepository extends JpaRepository<Userdata,Integer>{
	
}