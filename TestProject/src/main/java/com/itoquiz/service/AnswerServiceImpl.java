package com.itoquiz.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.itoquiz.dao.AnswerRepository;
import com.itoquiz.entity.Answer;

@Service
public class AnswerServiceImpl{
	
	private AnswerRepository answerRepository;
	
	@Autowired
	AnswerServiceImpl(AnswerRepository answerRepository){
		this.answerRepository = answerRepository;
	}
	
	public List<Answer> submitAnswers(List<Answer> answer) {
		return answerRepository.saveAll(answer);
	}

}
