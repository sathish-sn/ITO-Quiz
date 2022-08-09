package com.itoquiz.entity;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "answer")
public class Answer implements Serializable {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "Id")
	private int id;

	@Column(name = "Candidate_id")
	private int candidateId;

	@Column(name = "Question_id")
	private int question_id;

	@Column(name = "Answer")
	private int answer;
	
	

	public Answer(int candidateId, int question_id, int answer) {
		super();
		this.candidateId = candidateId;
		this.question_id = question_id;
		this.answer = answer;
	}

	public Answer() {
		super();
	}


	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public int getCandidateId() {
		return candidateId;
	}

	public void setCandidateId(int candidateId) {
		this.candidateId = candidateId;
	}

	public int getQuestion_id() {
		return question_id;
	}

	public void setQuestion_id(int question_id) {
		this.question_id = question_id;
	}

	public int getAnswer() {
		return answer;
	}

	public void setAnswer(int answer) {
		this.answer = answer;
	}

	@Override
	public String toString() {
		return "Answer [id=" + id + ", candidateId=" + candidateId + ", question_id=" + question_id + ", answer="
				+ answer + "]";

	}

}
