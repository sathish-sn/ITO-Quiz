package com.itoquiz.service;

import java.util.HashMap;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.itoquiz.dao.QuizRepository;
import com.itoquiz.entity.Question;

@Service
public class QuizServiceImpl{

	private QuizRepository quizRepository;

	@Autowired
	public QuizServiceImpl(QuizRepository quizRepository) { // Injecting the repository in Service layer
		this.quizRepository = quizRepository;
	}
	
	
	
	/* ****************************************** CRUD Operations on Questions **********************************************************************/

	// Get all the questions in the data base
	public List<Question> getQuestions() {
		return quizRepository.findAll();
	}

	// Find the question by ID and display the question. "findBy" is the prefix and "Id" is the parameter you are using to search
	public Question findById(int id) {

		Optional<Question> result = quizRepository.findById(id);
		Question question = null;

		if (result.isPresent()) {
			question = result.get();
		}

		else {
			throw new RuntimeException("Did not find the Quesiton Id - " + id);
		}

		return question;
	}
	
	// Post as single question
	public Question saveQuestion(Question question) {
		return quizRepository.save(question);
	}
	
	//Post multiple questions
	public List<Question> saveQuestions(List<Question> question) {
		return quizRepository.saveAll(question);
	}

	// Update the question
	public Question updateQuestion(Question question) {
		Question existingProduct = quizRepository.findById(question.getQuestion_id()).orElse(null);
		existingProduct.setQuestion(question.getQuestion());
		existingProduct.setOption1(question.getOption1());
		existingProduct.setOption2(question.getOption2());
		existingProduct.setOption3(question.getOption3());
		existingProduct.setOption4(question.getOption3());
		existingProduct.setAnswer(question.getAnswer());
		return quizRepository.getById(question.getQuestion_id());
	}

	// Delete the question by passing the id
	public void deleteById(Question que) {
		quizRepository.delete(que);
	}
	
	public void deleteMultipleByIds(Iterable<Integer> questionIds) {
		quizRepository.deleteAllById(questionIds);
	}
	
	/* ************************************************ Validaiton  ******************************************************************************** */
	
	public HashMap<Integer, Integer> answerSheet(){
		
		HashMap<Integer, Integer> map = new HashMap<>();
		quizRepository.findAll().forEach(a -> map.put(a.getQuestion_id(), a.getAnswer()));// Retrieving Question_id and Answer from the DB to validate the result of the candidate
		return map;
		
	}

}
