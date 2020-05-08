package com.capgemini.onlinetest.service;



import java.util.List;

import com.capgemini.onlinetest.entity.Questions;
import com.capgemini.onlinetest.entity.TestOnline;
import com.capgemini.onlinetest.entity.Userdata;

public interface UserService {

	String loginUser(Userdata u);//didnt write test case
	public TestOnline get(String name);//didnt write test case
	int calculateTotalTestMarks(int tid);
	TestOnline findOne(int testid);
	int calculateMarks(Questions q);
	void updateChoosenAns(Questions[] ans, int tid);
	public List<Questions> getUQuestions(int tid);
	Userdata userProfileSer(String name);
	Userdata userProfileLatPageSer(int tid);

}

