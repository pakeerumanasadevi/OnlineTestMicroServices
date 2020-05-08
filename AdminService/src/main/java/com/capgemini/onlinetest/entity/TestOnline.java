package com.capgemini.onlinetest.entity;


import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonBackReference;


@Entity
@Table(name="test")
public class TestOnline {
	@Id
	//@GeneratedValue(strategy=GenerationType.AUTO)
	private int testId;
	@Column(length=50)
	private String testTitle;
	@JsonBackReference
	@OneToMany(mappedBy="test")
	private Set<Questions> testQuestions;
	private int testTotalMarks;
	private int testMarksScored;

	public int getTestId() {
		return testId;
	}
	public void setTestId(int testId) {
		this.testId = testId;
	}
	public String getTestTitle() {
		return testTitle;
	}
	public void setTestTitle(String testTitle) {
		this.testTitle = testTitle;
	}
	
	public Set<Questions> getTestQuestions() {
		return testQuestions;
	}
	public void setTestQuestions(Set<Questions> testQuestions) {
		this.testQuestions = testQuestions;
	}
	public int getTestTotalMarks() {
		return testTotalMarks;
	}
	public void setTestTotalMarks(int testTotalMarks) {
		this.testTotalMarks = testTotalMarks;
	}
	public int getTestMarksScored() {
		return testMarksScored;
	}
	public void setTestMarksScored(int testMarksScored) {
		this.testMarksScored = testMarksScored;
	}
	public TestOnline(int testId, String testTitle, Set<Questions> testQuestions, int testTotalMarks,
			int testMarksScored) {
		super();
		this.testId = testId;
		this.testTitle = testTitle;
		this.testQuestions = testQuestions;
		this.testTotalMarks = testTotalMarks;
		this.testMarksScored = testMarksScored;
	}
	public TestOnline() {
		
	}
	
	
}


