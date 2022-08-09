package com.itoquiz.service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.itoquiz.controller.QuestionModel;
import com.itoquiz.dao.CandidateRepository;
import com.itoquiz.dao.QuizRepository;
import com.itoquiz.entity.Candidate;
import com.itoquiz.exception.ResourceNotFoundException;

@Service
public class CandidateServiceImpl {

	private QuizRepository quizRepository;
	private static CandidateRepository candidateRepository;

	@Autowired
	public CandidateServiceImpl(QuizRepository quizRepository, CandidateRepository candidateRepository) { // Injecting Repository in Service Layer
		this.quizRepository = quizRepository;
		this.candidateRepository = candidateRepository;
	}
	
	
	
	 // ***************************************** Methods related to Quiz ************************************************************************

	public List<QuestionModel> getQuestions() {
		
		
		
		List<QuestionModel> questions = new ArrayList<>();
		
		quizRepository.findAll().forEach(q -> questions.add(new QuestionModel(q))); // 
		
		return questions;
	}
	
	public List<Candidate> findCandidateById(int n) {
		
		return candidateRepository.findAll();	
	}
	
	
	
	// ***************************************** Methods related to Cadidate Registration and more *************************************************

	public static List<Candidate> getCandidates() {
		return candidateRepository.findAll();
	}

	public Candidate generateCanditateId(Candidate candidate) {
		
		String regexp = "(?:[a-z0-9A-Z!#$%&'?^_`{|}~-]+(?:\\.[a-z0-9A-Z!#$%&'*?^_`{|}~-]+)*|\"(?:[\\x01-\\x08\\x0b\\x0c\\x0e-\\x1f\\x21\\x23-\\x5b\\x5d-\\x7f]|\\\\[\\x01-\\x09\\x0b\\x0c\\x0e-\\x7f])*\")@(?:(?:[a-z0-9A-Z](?:[a-z0-9A-Z-]*[a-z0-9A-Z])?\\.)+[a-z0-9A-Z](?:[a-z0-9A-Z]*[a-z0-9A-Z])?|\\[(?:(?:25[0-5]|2[0-4][0-9]|[01]?[0-9][0-9]?)\\.){3}(?:25[0-5]|2[0-4][0-9]|[01]?[0-9][0-9]?|[a-z0-9-]*[a-z0-9A-Z]:(?:[\\x01-\\x08\\x0b\\x0c\\x0e-\\x1f\\x21-\\x5a\\x53-\\x7f]|\\\\[\\x01-\\x09\\x0b\\x0c\\x0e-\\x7f])+)\\])";

		Pattern pattern = Pattern.compile(regexp);

		Matcher matcher = pattern.matcher(candidate.getEmailId());

		if (!matcher.matches()) {
			
			throw new ResourceNotFoundException("Enter valid Email Id");
		}

		int count = 2;
		
		String checkIsUpper = candidate.getEmailId();
		
		char[] charArray = checkIsUpper.toCharArray();
		
		for (int i = 0; i < charArray.length; i++) {

			if (charArray[i] == '@' || charArray[i] == '.') {

				continue;

			} else if (Character.isUpperCase(charArray[i])) {
				
				count++;
			}
		}
		if (count == charArray.length)
			
			if (candidate.getEmailId().equalsIgnoreCase(candidate.getEmailId())) {

				throw new ResourceNotFoundException("Email Id is already exits");
			}
		
		return candidateRepository.save(candidate);
	}
	
	



	// ***************************************** Methods related to Cadidate Result ****************************************************************
	public HashMap<Integer, Integer> answerSheet(){
		
		HashMap<Integer, Integer> map = new HashMap<>();
		quizRepository.findAll().forEach(a->map.put(a.getQuestion_id(), a.getAnswer()));
		return map;
		
	}


}
