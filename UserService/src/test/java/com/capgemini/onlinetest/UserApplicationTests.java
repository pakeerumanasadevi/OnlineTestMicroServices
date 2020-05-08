package com.capgemini.onlinetest;

import org.junit.Before;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.capgemini.onlinetest.dao.UserRepository;


@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest
class UserApplicationTests {

	@MockBean
	UserRepository urep;//didnt write test cases for this yet
	
	
	@Before
	public void init() {
		MockitoAnnotations.initMocks(this);
	}
	

}
