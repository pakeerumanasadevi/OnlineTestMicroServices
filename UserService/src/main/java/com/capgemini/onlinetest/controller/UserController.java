package com.capgemini.onlinetest.controller;



import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.capgemini.onlinetest.entity.Questions;
import com.capgemini.onlinetest.entity.TestOnline;
import com.capgemini.onlinetest.entity.Userdata;
import com.capgemini.onlinetest.service.UserService;


@RestController
@RequestMapping("/user")
@CrossOrigin(origins="http://localhost:4200", maxAge=3600)
public class UserController {
	
	@Autowired
	UserService user;


	//get test details of user
	@GetMapping("/username/{username}")
	public TestOnline getUserByName(@PathVariable(value="username") String username)
	{
		return user.get(username);
	}

	//get user questions
	@GetMapping("/userQuestions/{testId}")
	public List<Questions> getUserQuestions(@PathVariable(value="testId") int testId)
	{
		return user.getUQuestions(testId);
	}
	
	@PutMapping("/choosenAns/{testid}")
	public void getChoosenAns(@RequestBody Questions[] q,@PathVariable(value="testid")int testid)
	{
		TestOnline t1=user.findOne(testid);
		if(t1!=null) {
			user.updateChoosenAns(q, testid);
		}
			
	}

	
	//get results
	@GetMapping("/getResult/{testId}")
	public int getResults(@PathVariable(value="testId") int testId)
	{
	      return user.calculateTotalTestMarks(testId);
	}
	//user profile
	@GetMapping("/getProfile/{username}")
	public Userdata getProfile(@PathVariable(value="username") String username)
	{
		  return user.userProfileSer(username);
	}
		
	@GetMapping("/getReport/{usertest}")
	public Userdata getProfile(@PathVariable(value="usertest") int usertest)
	{
		  return user.userProfileLatPageSer(usertest);
	}
	




}