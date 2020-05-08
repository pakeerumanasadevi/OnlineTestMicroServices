package com.capgemini.onlinetest.service;

import java.util.List;
import java.util.Optional;

import com.capgemini.onlinetest.entity.Questions;
import com.capgemini.onlinetest.entity.TestOnline;
import com.capgemini.onlinetest.entity.Userdata;

public interface AdminService {

	//
	Userdata addUser(Userdata u);//
    List<Userdata> getAllUsers();//
    Userdata deleteUser(int userId);//
    Userdata updateUser(Userdata u);//
    String loginUser(Userdata u);//
    public Userdata assignTest(int uid,int tid);
    //
    public TestOnline save(TestOnline t);
	public TestOnline findOne(int testid);
	public void deleteT(TestOnline t);
	public List<TestOnline> getAll();
	public TestOnline updateTest(TestOnline t);
	//
	public Questions save(Questions q);
	public Questions findOneQ(int qid);
	public Optional<Questions> findQuestionById(int qid);
	public void deleteQ(Questions q);
	public List<Questions> getAllQ();
	public Questions updateQuestion(Questions q);
	public Questions assignTestQ(int qid,int tid);
}
