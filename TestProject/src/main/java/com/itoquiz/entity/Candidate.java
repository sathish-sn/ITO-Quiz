package com.itoquiz.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "candidate")
public class Candidate  {
	
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "candidate_id")
	private int candidateId;
	
	@Column(name = "name")
	private String name;
	
	@Column(name = "email_id", unique = true)
	private String emailId;
	
	@Column(name = "is_started")
	private boolean isStarted;
	
	@Column(name = "is_submit")
	private boolean isSubmit;
	
	
	
	public Candidate() {
		super();

	}
	
	

	public Candidate(String name, String emailId) {
		super();
		this.name = name;
		this.emailId = emailId;
	}



	public Candidate(int candidateId, String name, String emilId, boolean isStarted, boolean isSubmit) {
		super();
		this.candidateId = candidateId;
		this.name = name;
		this.emailId = emilId;
		this.isStarted = isStarted;
		this.isSubmit = isSubmit;
	}
	

	public int getCandidateId() {
		return candidateId;
	}

	public void setCandidateId(int candidateId) {
		this.candidateId = candidateId;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getEmailId() {
		return emailId;
	}

	public void setEmailId(String emilId) {
		this.emailId = emilId;
	}

	public boolean isStarted() {
		return isStarted;
	}

	public void setStarted(boolean isStarted) {
		this.isStarted = isStarted;
	}

	public boolean isSubmit() {
		return isSubmit;
	}

	public void setSubmit(boolean isSubmit) {
		this.isSubmit = isSubmit;
	}

	@Override
	public String toString() {
		return "Candidate [candidateId=" + candidateId + ", name=" + name + ", emilId=" + emailId + ", isStarted="
				+ isStarted + ", isSubmit=" + isSubmit + "]";
	}
	

}
