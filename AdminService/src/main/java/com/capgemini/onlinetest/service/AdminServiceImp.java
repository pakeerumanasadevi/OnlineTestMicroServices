package com.capgemini.onlinetest.service;

import java.util.List;
import java.util.Optional;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.capgemini.onlinetest.dao.QuestionsRepository;
import com.capgemini.onlinetest.dao.TestRepository;
import com.capgemini.onlinetest.dao.UserDaoImpl;
import com.capgemini.onlinetest.dao.UserRepository;
import com.capgemini.onlinetest.entity.Questions;
import com.capgemini.onlinetest.entity.TestOnline;
import com.capgemini.onlinetest.entity.Userdata;

@Service
@Transactional
public class AdminServiceImp implements AdminService{
	
	@Autowired
	UserDaoImpl dao;
	@Autowired
	TestRepository trep;
	@Autowired
	UserRepository urep;
	@Autowired
	QuestionsRepository qrep;
	

	@Override
	public Userdata addUser(Userdata u) {
	return dao.addUser(u);
	}

	@Override
	public List<Userdata> getAllUsers()
	{
	return dao.getAllUsers();
	}


	@Override
	public Userdata deleteUser(int userId)
	{
	return dao.deleteUser(userId);
	}

	@Override
	public Userdata updateUser(Userdata u) {
	return dao.updateUser(u);
	}

	@Override
	public String loginUser(Userdata u)
	{
	return dao.loginUser(u);
	}

	@Override
	public Userdata assignTest(int uid, int tid) {
		Userdata u=dao.getById(uid);
		if(u!=null) {
			TestOnline t=trep.getOne(tid);
			u.setUsertest(t);
		}
		return urep.save(u);
	}
	//
	
	@Override
	public TestOnline save(TestOnline t) {
		return trep.save(t);
	}

	@Override
	public TestOnline findOne(int testid) {
		return trep.getOne(testid);
	}
	
	@Override
	public void deleteT(TestOnline t) {
		trep.delete(t);
	}
	
	
	@Override
	public TestOnline updateTest(TestOnline t) {
		TestOnline tt=trep.getOne(t.getTestId());
		if(tt!=null) {
			tt.setTestTitle(t.getTestTitle());
			tt.setTestMarksScored(t.getTestMarksScored());
			tt.setTestTotalMarks(t.getTestTotalMarks());
		}
		return trep.save(tt);
		
		
		
	}
	@Override
	public List<TestOnline> getAll() {
		return trep.findAll();
	}
	//
	
	@Override
	public Questions save(Questions q) {
		return qrep.save(q);
	}

	@Override
	public Questions findOneQ(int qid) {
		return qrep.getOne(qid);
	}

	@Override
	public Optional<Questions> findQuestionById(int qid) {
		return qrep.findById(qid);
	}

	@Override
	public void deleteQ(Questions q) {
		qrep.delete(q);
	}

	@Override
	public List<Questions> getAllQ() {
		return qrep.findAll();
	}

	@Override
	public Questions updateQuestion(Questions q) {
		Questions qt=qrep.getOne(q.getQuestionId());
		if(qt!=null) {
			qt.setQuestionTitle(q.getQuestionTitle());
			qt.setOptionOne(q.getOptionOne());
			qt.setOptionTwo(q.getOptionTwo());
			qt.setOptionThree(q.getOptionThree());
			qt.setOptionFour(q.getOptionFour());
			qt.setRightAnswer(q.getRightAnswer());
			qt.setQuestionMarks(q.getQuestionMarks());
		}
		
		return qrep.save(qt);
		
	}

	@Override
	public Questions assignTestQ(int qid, int tid) {

		Questions q=qrep.getOne(qid);
		if(q!=null) {
			TestOnline t=trep.getOne(tid);
			q.setTest(t);
		}
		
		return qrep.save(q);
	}


}
