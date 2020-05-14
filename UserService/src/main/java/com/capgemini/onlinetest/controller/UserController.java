package com.capgemini.onlinetest.controller;



import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.capgemini.onlinetest.entity.Questions;
import com.capgemini.onlinetest.entity.TestOnline;
import com.capgemini.onlinetest.entity.Userdata;
import com.capgemini.onlinetest.exceptions.IdNotFoundException;
import com.capgemini.onlinetest.exceptions.UserNotFoundException;
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
		TestOnline t=user.findOne(testId);
		if(t==null) {
			throw new IdNotFoundException("Enter Valid Id");
		}
		else {
		    return user.getUQuestions(testId);
		}
	}
	
	//updating the user chosen answers for questions
	@PutMapping("/choosenAns/{testid}")
	public void getChoosenAns(@RequestBody Questions[] q,@PathVariable(value="testid")int testid)
	{
		TestOnline t1=user.findOne(testid);
		if(t1==null) {
			throw new IdNotFoundException("Enter Valid Id");
		}
		else {
			user.updateChoosenAns(q, testid);
		}
			
	}

	
	//get results
	@GetMapping("/getResult/{testId}")
	public int getResults(@PathVariable(value="testId") int testId)
	{
		TestOnline t1=user.findOne(testId);
		if(t1==null) {
			throw new IdNotFoundException("Enter Valid Id");
		}
		else {
	         return user.calculateTotalTestMarks(testId);
		}
	}
	
	
	//user profile
	@GetMapping("/getProfile/{username}")
	public Userdata getProfile(@PathVariable(value="username") String username)
	{
		  Userdata u=user.userProfileSer(username);
		  if(u==null) {
				throw new UserNotFoundException("Invalid Username");
			}
		  else {
			  return u;
		  }
		  
	}
		
	//Displaying the result report of user
	@GetMapping("/getReport/{usertest}")
	public Userdata getProfile(@PathVariable(value="usertest") int usertest)
	{
		  return user.userProfileLatPageSer(usertest);
	}
	
	@ExceptionHandler(IdNotFoundException.class)
	public ResponseEntity<String> IdException(IdNotFoundException e) {
		return new ResponseEntity<String>(e.getMessage(), HttpStatus.NOT_FOUND);
	}
	@ExceptionHandler(UserNotFoundException.class)
	public ResponseEntity<String> NameException(UserNotFoundException e) {
		return new ResponseEntity<String>(e.getMessage(), HttpStatus.NOT_FOUND);
	}
	




}