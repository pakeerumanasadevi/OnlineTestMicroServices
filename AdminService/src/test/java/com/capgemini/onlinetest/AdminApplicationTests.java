package com.capgemini.onlinetest;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.List;

import org.junit.Before;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.capgemini.onlinetest.dao.QuestionsRepository;
import com.capgemini.onlinetest.dao.TestRepository;
import com.capgemini.onlinetest.dao.UserDaoImpl;
import com.capgemini.onlinetest.dao.UserRepository;
import com.capgemini.onlinetest.entity.Questions;
import com.capgemini.onlinetest.entity.TestOnline;
import com.capgemini.onlinetest.entity.Userdata;
import com.capgemini.onlinetest.service.AdminService;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest
class AdminApplicationTests {

	@Autowired
	private AdminService ser;
	@MockBean
	private QuestionsRepository qrep;
	@MockBean
	private TestRepository trep;
	@Mock
	UserDaoImpl dao;
	@MockBean
	UserRepository urep;
	
	@Before
	public void init() {
		MockitoAnnotations.initMocks(this);
	}
	@Test
	public void TestsaveQuestions() {
		Questions q=new Questions(1,"why?","a","b","c","d","b",null,5,"b",5);
		when(qrep.save(q)).thenReturn(q);
		Questions result = ser.save(q);
		assertEquals(1, result.getQuestionId());
		assertEquals("why?", result.getQuestionTitle());
		assertEquals("a", result.getOptionOne());
	}
	@Test
	public void testGetAllQuestions(){
		List<Questions> QuestionsList = new ArrayList<Questions>();
		QuestionsList.add(new Questions(1,"why?","a","b","c","d","b",null,5,"b",5));
		QuestionsList.add(new Questions(2,"why?","a","b","c","d","c",null,5,"c",5));
		QuestionsList.add(new Questions(3,"why?","a","b","c","d","a",null,5,"a",5));
		when(qrep.findAll()).thenReturn(QuestionsList);
		
		List<Questions> result = ser.getAllQ();
		assertEquals(3, result.size());
	}
	@Test
	public void testDeleteQuestions(){
		Questions q=new Questions(1,"why?","a","b","c","d","b",null,5,"b",5);
		qrep.delete(q);
        verify(qrep,times(1)).delete(q);
     }
	@Test
	public void testFindOneQuestions() {
	    Questions q=new Questions(1,"why?","a","b","c","d","b",null,5,"b",5);
		when(qrep.getOne(1)).thenReturn(q);
		Questions result = ser.findOneQ(1);
		assertEquals(1, result.getQuestionId());
		assertEquals("why?", result.getQuestionTitle());
		assertEquals("a", result.getOptionOne());
	} 
	@Test
	public void testUpdateQuestions(){
		Questions qt=new Questions(2,"why?","a","b","c","d","b",null,5,"b",5);
		qrep.save(qt);
        verify(qrep,times(1)).save(qt);
	}
	@Test
	public void testassignTest() {
	    Questions q=new Questions(1,"why?","a","b","c","d","b",null,5,"b",5);
		when(qrep.save(q)).thenReturn(q);
		Questions result = ser.save(q);
		assertEquals(1, result.getQuestionId());
		assertEquals(null, result.getTest());
	}
	//TestOnline
	@Test
	public void testsaveTest() {
		TestOnline t=new TestOnline(1,"hello",null,100,20);
		when(trep.save(t)).thenReturn(t);
		TestOnline result = ser.save(t);
		assertEquals(1, result.getTestId());
		assertEquals("hello", result.getTestTitle());
	}
	@Test
	public void testgetAllTest(){
		List<TestOnline> TestList = new ArrayList<TestOnline>();
		TestList.add(new TestOnline(1,"hello",null,100,20));
		TestList.add(new TestOnline(2,"world",null,120,26));
		TestList.add(new TestOnline(5,"welcome",null,140,29));
		when(trep.findAll()).thenReturn(TestList);
		List<TestOnline> result = ser.getAll();
		assertEquals(3, result.size());
	}
	@Test
	public void testfindoneTest() {
		TestOnline t=new TestOnline(1,"hello",null,100,20);
			when(trep.getOne(1)).thenReturn(t);
			TestOnline result = ser.findOne(1);
			assertEquals(1, result.getTestId());
			assertEquals("hello", result.getTestTitle());
	} 
	@Test
	public void testdeleteTTest() {
		TestOnline t=new TestOnline(1,"hello",null,100,20);
		trep.delete(t);
        verify(trep,times(1)).delete(t);
		
	}
	@Test
	public void testUpdateTest(){
		TestOnline tt=new TestOnline(1,"hello",null,100,20);
		trep.save(tt);
        verify(trep,times(1)).save(tt);
	}
	//user
	@Test
	public void testaddUser() {
        Userdata e=new Userdata();
	    e.setUserid(10);
	    e.setUsername("abc");
	    e.setUserPassword("abcd");
	    e.setUserPhoneno(9898l);
	    e.setUserEmail("abc@gmail.com");
	    dao.addUser(e);
	    Mockito.verify(dao, Mockito.times(1)).addUser(e); 
    }
	@Test
	public void testgetAllUsers() {
		List<Userdata> userlist = new ArrayList<Userdata>();
		userlist.add(new Userdata(10,"abc",null,"abcd",98981,"abc@gmail.com","student"));
		userlist.add(new Userdata(11,"abcd",null,"abcde",9898145,"abcd@gmail.com","student"));
		userlist.add(new Userdata(12,"abce",null,"abcdef",9898154,"abce@gmail.com","student"));
		when(urep.findAll()).thenReturn(userlist);
		
		List<Userdata> result =urep.findAll();
		assertEquals(3, result.size());
	}
	@Test
	public void testupdateUser() {
		Userdata ud=new Userdata(10,"abcde",null,"abcdefd",989814567,"abcdef@gmail.com","student");
		urep.save(ud);
        verify(urep,times(1)).save(ud);
	}
	@Test
	public void testdeleteUser() {
		Userdata u=new Userdata(10,"abcde",null,"abcdefd",989814567,"abcdef@gmail.com","student");
		dao.deleteUser(10);
        verify(dao,times(1)).deleteUser(10);
	}

}
