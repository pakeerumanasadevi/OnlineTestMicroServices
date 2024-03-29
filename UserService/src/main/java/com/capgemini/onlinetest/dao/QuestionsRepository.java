package com.capgemini.onlinetest.dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.capgemini.onlinetest.entity.Questions;



@Repository
public interface QuestionsRepository extends JpaRepository<Questions,Integer>{

	
	@Query("select q from TestOnline t,Questions q where q.test=t.testId and t.testId=:testId")
	public List<Questions> getUserQuestion(@Param("testId")int testId);
	
	
	
	@Modifying	
	@Query("update Questions q set q.choosenAnswer=:choosenAnswer WHERE q.questionId=:questionId")
	public void UpdateChoosenOption(@Param("questionId")int questionId,@Param("choosenAnswer")String choosenAnswer);

}