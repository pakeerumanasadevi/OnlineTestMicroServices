package com.capgemini.onlinetest.service;



import java.util.ArrayList;
import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.capgemini.onlinetest.dao.QuestionsRepository;
import com.capgemini.onlinetest.dao.TestRepository;

import com.capgemini.onlinetest.dao.UserRepository;
import com.capgemini.onlinetest.entity.Questions;
import com.capgemini.onlinetest.entity.TestOnline;
import com.capgemini.onlinetest.entity.Userdata;



@Service
@Transactional
public class UserServiceImp implements UserService
{

@Autowired
TestRepository trep;
@Autowired
UserRepository urep;
@Autowired
QuestionsRepository qrep;


@Override
public TestOnline get(String name) {
	return urep.get(name);
}


@Override
public TestOnline findOne(int testid) {
	return trep.getOne(testid);
}

@Override
public int calculateTotalTestMarks(int tid) {
	TestOnline t=trep.getOne(tid);
	Questions q1;
	List<Questions> uq;
	uq=qrep.getUserQuestion(tid);
	int total=0;
	int n=uq.size();
	for(int i=0;i<n;i++) {
		q1=uq.get(i);
		calculateMarks(q1);
		total+=q1.getMarksScored();
	}
	t.setTestMarksScored(total);
	return total;
	
}
@Override
public List<Questions> getUQuestions(int tid) {

	TestOnline t=trep.getOne(tid);
	List<Questions> uq=new ArrayList<>();
	if(t!=null) {
		uq=qrep.getUserQuestion(tid);
	}
	return uq;
}
@Override
public void updateChoosenAns(Questions[] ans,int tid) {
	TestOnline t=trep.getOne(tid);
	int n=ans.length;
	if(t!=null) {
		for(int i=0;i<n;i++) {
			int id=ans[i].getQuestionId();
			qrep.UpdateChoosenOption(id,ans[i].getChoosenAnswer());
		}
	}
	
}

@Override
public int calculateMarks(Questions q) {
	int score = 0;
	if(q.getChoosenAnswer().equalsIgnoreCase(q.getRightAnswer()))
	{
		score=score+2;
	}
	else {
		score=score+0;
	}
	q.setMarksScored(score);
	return score;
}

@Override
public Userdata userProfileSer(String name) {

	return urep.userProfile(name);
}

@Override
public Userdata userProfileLatPageSer(int tid) {

	return urep.userProfileLastPage(tid);
}





}