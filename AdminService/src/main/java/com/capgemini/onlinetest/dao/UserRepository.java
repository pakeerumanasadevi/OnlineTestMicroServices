package com.capgemini.onlinetest.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.capgemini.onlinetest.entity.TestOnline;
import com.capgemini.onlinetest.entity.Userdata;
@Repository
public interface UserRepository extends JpaRepository<Userdata,Integer>{
	
	@Query("select u from Userdata u where u.username=:username")
	public Userdata getByName(@Param("username")String username);//remove

	@Query("select t from Userdata u,TestOnline t where u.usertest=t.testId and u.username=:username")
	public TestOnline get(@Param("username")String username);//remove
	
}
