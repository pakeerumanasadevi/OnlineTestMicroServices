package com.capgemini.onlinetest.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;


import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@Entity
@Table(name="questions")
public class Questions {
	@Id
	private int questionId;
	@Column(length=100)
	private String questionTitle;
	@Column(length=30)
	private String optionOne;
	@Column(length=30)
	private String optionTwo;
	@Column(length=30)
	private String optionThree;
	@Column(length=30)
	private String optionFour;
	@Column(length=30)
	private String rightAnswer;
	@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
	@ManyToOne
	@JoinColumn(name="testId")
	private TestOnline test;
	@Column(length=30)
	private int questionMarks;
	@Column(length=30)
	private String choosenAnswer;
	@Column(length=30)
	private int marksScored;
	public int getQuestionId() {
		return questionId;
	}
	public void setQuestionId(int questionId) {
		this.questionId = questionId;
	}
	public String getQuestionTitle() {
		return questionTitle;
	}
	public void setQuestionTitle(String questionTitle) {
		this.questionTitle = questionTitle;
	}
	public String getOptionOne() {
		return optionOne;
	}
	public void setOptionOne(String optionOne) {
		this.optionOne = optionOne;
	}
	public String getOptionTwo() {
		return optionTwo;
	}
	public void setOptionTwo(String optionTwo) {
		this.optionTwo = optionTwo;
	}
	public String getOptionThree() {
		return optionThree;
	}
	public void setOptionThree(String optionThree) {
		this.optionThree = optionThree;
	}
	public String getOptionFour() {
		return optionFour;
	}
	public void setOptionFour(String optionFour) {
		this.optionFour = optionFour;
	}
	public String getRightAnswer() {
		return rightAnswer;
	}
	public void setRightAnswer(String rightAnswer) {
		this.rightAnswer = rightAnswer;
	}
	//@JsonIgnore
	public TestOnline getTest() {
		return test;
	}
	public void setTest(TestOnline test) {
		this.test = test;
	}
	public int getQuestionMarks() {
		return questionMarks;
	}
	public void setQuestionMarks(int questionMarks) {
		this.questionMarks = questionMarks;
	}
	public String getChoosenAnswer() {
		return choosenAnswer;
	}
	public void setChoosenAnswer(String choosenAnswer) {
		this.choosenAnswer = choosenAnswer;
	}
	public int getMarksScored() {
		return marksScored;
	}
	public void setMarksScored(int marksScored) {
		this.marksScored = marksScored;
	}
	public Questions(int questionId, String questionTitle, String optionOne, String optionTwo, String optionThree,
			String optionFour, String rightAnswer, TestOnline test, int questionMarks, String choosenAnswer,
			int marksScored) {
		super();
		this.questionId = questionId;
		this.questionTitle = questionTitle;
		this.optionOne = optionOne;
		this.optionTwo = optionTwo;
		this.optionThree = optionThree;
		this.optionFour = optionFour;
		this.rightAnswer = rightAnswer;
		this.test = test;
		this.questionMarks = questionMarks;
		this.choosenAnswer = choosenAnswer;
		this.marksScored = marksScored;
	}
	public Questions() {}
	

	
}