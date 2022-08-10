package com.itoquiz.service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.itoquiz.dao.QuizRepository;
import com.itoquiz.entity.Question;
import com.itoquiz.exception.ResourceNotFoundException;

@Service
public class QuizServiceImpl {

	@Autowired
	private QuizRepository quizRepository;
	/*
	 * ****************************************** CRUD Operations on Questions
	 **********************************************************************/

	// Get all the questions in the data base
	public List<Question> getQuestions() {
		return quizRepository.findAll();
	}

	// Find the question by ID and display the question. "findBy" is the prefix and
	// "Id" is the parameter you are using to search
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

	// Post multiple questions
	public List<Question> saveQuestions(List<Question> question) {
		return quizRepository.saveAll(question);
	}

	// Update the question
	public String updateQuestion(Question question) {
		Question existingProduct = quizRepository.findById(question.getQuestion_id())
				.orElseThrow(() -> new ResourceNotFoundException("question", "Id", question.getQuestion_id()));
		System.out.println("Question ->" + question.getQuestion());
		existingProduct.setQuestion(question.getQuestion());
		existingProduct.setOption1(question.getOption1());
		existingProduct.setOption2(question.getOption2());
		existingProduct.setOption3(question.getOption3());
		existingProduct.setOption4(question.getOption4());
		existingProduct.setAnswer(question.getAnswer());
		System.out.println("id = " + existingProduct.getQuestion_id());
		quizRepository.save(existingProduct);

		return "Updated question number " + existingProduct.getQuestion_id() + " successfully";
	}

	// Delete the question by passing the id
	public void deleteById(Integer questionId) {
		Question question = this.quizRepository.findById(questionId)
				.orElseThrow(() -> new ResourceNotFoundException("question", "Id", questionId));
		this.quizRepository.delete(question);
	}

	public String deleteMultipleByIds(List<Integer> questionIds) {
		List<Integer> notAvailable = new ArrayList<Integer>();
		List<Integer> available = new ArrayList<Integer>();

		for (Integer ids : questionIds) {
			Integer checkNull = null;
			System.out.println(quizRepository.findById(ids));
			try {
				checkNull = quizRepository.findById(ids).get().getQuestion_id();
			} catch (Exception e) {
				checkNull = null;
			}
			if (checkNull == null) {

				notAvailable.add(ids);

			} else {

				available.add(ids);

				quizRepository.deleteById(ids);
			}
		}
		return "Question Deleted = " + "" + available + "  Unavailable questions = " + "" + notAvailable;
	}

	/*
	 * ************************************************ Validaiton
	 * *****************************************************************************
	 * ***
	 */

	public HashMap<Integer, Integer> answerSheet() {

		HashMap<Integer, Integer> map = new HashMap<>();
		quizRepository.findAll().forEach(a -> map.put(a.getQuestion_id(), a.getAnswer()));// Retrieving Question_id and
																							// Answer from the DB to
																							// validate the result of
																							// the candidate
		return map;

	}

}
