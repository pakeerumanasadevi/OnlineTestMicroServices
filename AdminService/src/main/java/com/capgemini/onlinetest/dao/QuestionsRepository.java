package com.capgemini.onlinetest.dao;



import org.springframework.data.jpa.repository.JpaRepository;

import org.springframework.stereotype.Repository;

import com.capgemini.onlinetest.entity.Questions;


@Repository
public interface QuestionsRepository extends JpaRepository<Questions,Integer>{



}