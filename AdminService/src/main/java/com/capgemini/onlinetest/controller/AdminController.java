package com.capgemini.onlinetest.controller;

import java.util.List;
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
import com.capgemini.onlinetest.exceptions.UserNotFoundException;
import com.capgemini.onlinetest.service.AdminService;






@RestController
@RequestMapping("/admin")
@CrossOrigin(origins="http://localhost:4200", maxAge=3600)
public class AdminController {
	
	
	@Autowired
	AdminService adser;

	/**
	 * add the user
	 * @param u
	 * @return
	 */
	@PostMapping("/addUser")
	public ResponseEntity<String> addUser(@RequestBody Userdata u) {
		Userdata e =adser.addUser(u);
		if (e == null) {
			throw new IdNotFoundException("Enter Valid Id");
		} else {
			return new ResponseEntity<String>("User created successfully", new HttpHeaders(), HttpStatus.OK);
		}
	}
	

	
	// Get all users
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
	
	// Delete User
	@DeleteMapping("/DeleteUser/{userId}")
	public ResponseEntity<String> deleteUser(@PathVariable("userId") int userId) {
		Userdata e = adser.deleteUser(userId);
		System.out.println("controller delete");
		if (e == null) {
			throw new IdNotFoundException("Delete Operation Unsuccessful,Provided Id does not exist");
		} else {
			return new ResponseEntity<String>("User deleted successfully", new HttpHeaders(), HttpStatus.OK);
		}
	}
	
	
	//To validate Login 
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
	public ResponseEntity<Questions> updateQu(@RequestBody Questions q){
		Questions q1=adser.updateQuestion(q);
		return ResponseEntity.ok().body(q1);
	}
	
	//delete question
	@DeleteMapping("/delq/{qid}")
	public ResponseEntity<Questions> deleteQ(@PathVariable(value="qid")int qid)
	{
		Questions q=adser.findOneQ(qid);
		if(q==null) {
			return ResponseEntity.notFound().build();
		}
		adser.deleteQ(q);
		return ResponseEntity.ok().build();
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
	public ResponseEntity<TestOnline> updateT(@RequestBody TestOnline t){
		TestOnline t1=adser.updateTest(t);
		return ResponseEntity.ok().body(t1);
	}
	
	//delete test
     @DeleteMapping("/delt/{testid}")
	public ResponseEntity<TestOnline> deleteT(@PathVariable(value="testid")int testid)

	{
		TestOnline t=adser.findOne(testid); 
		if(t==null) {
			return ResponseEntity.notFound().build();
		}
		adser.deleteT(t);
		return ResponseEntity.ok().build();
		
	}
     
	
	//add test to question to test
	@PutMapping("/test/{testId}/{questionId}")
	public Questions assignQuestion(@PathVariable(value="testId")int testId,
			@PathVariable(value="questionId")int questionId){
		
		return adser.assignTestQ(questionId, testId);
	}
	
	//assign test to user
	@PutMapping("/user/{userid}/{testId}")
	public Userdata assignTest(@PathVariable(value="userid")int userid,
			@PathVariable(value="testId")int testId) {
		return adser.assignTest(userid,testId);
	}
	

	@ExceptionHandler(IdNotFoundException.class)
	public ResponseEntity<String> userNotFound(IdNotFoundException e) {
		return new ResponseEntity<String>(e.getMessage(), HttpStatus.NOT_FOUND);
	}
	@ExceptionHandler(UserNotFoundException.class)
	public ResponseEntity<String> userNotFound(UserNotFoundException e){
		return new ResponseEntity<String>(e.getMessage(), HttpStatus.NOT_FOUND);
	}
}

