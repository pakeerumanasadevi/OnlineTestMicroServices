package com.capgemini.onlinetest.controller;

import java.sql.SQLIntegrityConstraintViolationException;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.capgemini.onlinetest.entity.Questions;
import com.capgemini.onlinetest.entity.TestOnline;
import com.capgemini.onlinetest.entity.Userdata;
import com.capgemini.onlinetest.exceptions.IdNotFoundException;

import com.capgemini.onlinetest.service.AdminService;


@RestController
@RequestMapping("/admin")
@CrossOrigin(origins="http://localhost:4200", maxAge=3600)
public class AdminController {
	
	
	@Autowired
	AdminService adser;

	
	//User Sign up
	@PostMapping("/addUser")
	public ResponseEntity<String> addUser(@RequestBody Userdata u) {
		Userdata e =adser.addUser(u);
		if (e == null) {
			throw new IdNotFoundException("Enter Valid Id");
		} else {
			return new ResponseEntity<String>("User created successfully", new HttpHeaders(), HttpStatus.OK);
		}
	}
	

	//Display the list of users
	@GetMapping("/GetAllUsers")
	public ResponseEntity<List<Userdata>> getAllUsers() {
		List<Userdata> userlist = adser.getAllUsers();
		return new ResponseEntity<List<Userdata>>(userlist, new HttpHeaders(), HttpStatus.OK);
	}
	
	
	//Update User
	@PutMapping("/UpdateUser")
	public ResponseEntity<String> updateUser(@RequestBody Userdata u) {
		Userdata e = adser.updateUser(u);
		if (e == null) {
			throw new IdNotFoundException("Update Operation Unsuccessful,Provided Id does not exist");
		} else {
			return new ResponseEntity<String>("User updated successfully", new HttpHeaders(), HttpStatus.OK);
		}
	}
	
	
	//Delete User
	@DeleteMapping("/DeleteUser/{userId}")
	public ResponseEntity<String> deleteUser(@PathVariable("userId") int userId) {
		Userdata e = adser.deleteUser(userId);
		if (e == null) {
			throw new IdNotFoundException("Delete Operation Unsuccessful,Provided Id does not exist");
		} else {
			return new ResponseEntity<String>("User deleted successfully", new HttpHeaders(), HttpStatus.OK);
		}
	}
	
	
	//Login 
	@PutMapping("/Loginuser")
	public String loginUser(@RequestBody Userdata u)
	{
		 return adser.loginUser(u);
	}
	
	
	//add questions
	@PostMapping("/question")
	public Questions addQ(@RequestBody Questions q) {
		return adser.save(q);
	}
	
	
	//get all questions
	@GetMapping("/getq")
	public List<Questions> findQ(){
		return adser.getAllQ();
	}
	
	
	//update question
	@PutMapping("/uptques")
	public ResponseEntity<String> updateQu(@RequestBody Questions q){
		Questions q1=adser.updateQuestion(q);
		if (q1 == null) {
			throw new IdNotFoundException("Update Operation Unsuccessful,Provided Id does not exist");
		} else {
			return new ResponseEntity<String>("Question updated successfully", new HttpHeaders(), HttpStatus.OK);
		}
	}
	
	
	//delete question
	@DeleteMapping("/delq/{qid}")
	public ResponseEntity<String> deleteQ(@PathVariable(value="qid")int qid)
	{
		Questions q=adser.findOneQ(qid);
		if (q == null) {
			throw new IdNotFoundException("Delete Operation Unsuccessful,Provided Id does not exist");
		} else {
			adser.deleteQ(q);
			return new ResponseEntity<String>("Question deleted successfully", new HttpHeaders(), HttpStatus.OK);
		}
	}
	
	
	//add test
	@PostMapping("/test")
	public TestOnline addT(@RequestBody TestOnline t) {
		return adser.save(t);
	}
	
	
	//get all test
	@GetMapping("/gett")
	public List<TestOnline> findT() {
		return adser.getAll();
	}
	
	
	//update test
	@PutMapping("/upttest")
	public ResponseEntity<String> updateT(@RequestBody TestOnline t){
		TestOnline t1=adser.updateTest(t);
		if (t1 == null) {
			throw new IdNotFoundException("Update Operation Unsuccessful,Provided Id does not exist");
		} else {
			return new ResponseEntity<String>("Test updated successfully", new HttpHeaders(), HttpStatus.OK);
		}
	}
	
	
	//delete test
     @DeleteMapping("/delt/{testid}")
	public ResponseEntity<String> deleteT(@PathVariable(value="testid")int testid)
	{
		TestOnline t=adser.findOne(testid); 
    	Optional<TestOnline> t1=adser.findById(testid); 
		if(!t1.isPresent()){
			throw new IdNotFoundException("Delete Operation Unsuccessful");
		} else {
			adser.deleteT(t);
			return new ResponseEntity<String>("Test deleted successfully", new HttpHeaders(), HttpStatus.OK);
		}
		
	}
     
	
	//add test to question to test
	@PutMapping("/test/{testId}/{questionId}")
	public ResponseEntity<String> assignQuestion(@PathVariable(value="testId")int testId,
			@PathVariable(value="questionId")int questionId){
		Optional<TestOnline> t=adser.findById(testId); 
		if (!t.isPresent()) {
			throw new IdNotFoundException("Provided Id does not exist");
		}
		
		else {
			adser.assignTestQ(questionId, testId);
			return new ResponseEntity<String>("Assigned successfully", new HttpHeaders(), HttpStatus.OK);
		}
	}
	
	
	//assign test to user
	@PutMapping("/user/{userid}/{testId}")
	public ResponseEntity<String> assignTest(@PathVariable(value="userid")int userid,
			@PathVariable(value="testId")int testId) {
		Optional<TestOnline> t=adser.findById(testId); 
		if (!t.isPresent()) {
			throw new IdNotFoundException("Provided Id does not exist");
		}
		else {
			adser.assignTest(userid,testId);
			return new ResponseEntity<String>("Assigned successfully", new HttpHeaders(), HttpStatus.OK);
		}
	}
	

	@ExceptionHandler(IdNotFoundException.class)
	public ResponseEntity<String> IdException(IdNotFoundException e) {
		return new ResponseEntity<String>(e.getMessage(), HttpStatus.NOT_FOUND);
	}
	
	
}