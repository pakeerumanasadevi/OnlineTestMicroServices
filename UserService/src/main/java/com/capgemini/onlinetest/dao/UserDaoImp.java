package com.capgemini.onlinetest.dao;


import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import org.springframework.stereotype.Repository;

import com.capgemini.onlinetest.entity.Userdata;

@Repository
public class UserDaoImp implements UserDao {

	@PersistenceContext	
	 EntityManager em;
	
	
	@Override
	public String loginUser(Userdata u) {
		String type = null;
		String flag = null;
	Query q=em.createQuery("select m.userType from Userdata m where m.username=?1 and m.userPassword=?2");
	String a=u.getUsername();
	String b=u.getUserPassword();
	q.setParameter(1,a);
	q.setParameter(2,b);
	try
	{
		type=(String) q.getSingleResult();
		if(type.equalsIgnoreCase("admin") && type!=null) {
			 flag="admin";
		 }
		 else if(!type.equalsIgnoreCase("admin") && type!=null)
			 flag="student";
		return flag;
	}catch(javax.persistence.NoResultException e)
    {
        e.printStackTrace();
    }
	return "no";
	}
}