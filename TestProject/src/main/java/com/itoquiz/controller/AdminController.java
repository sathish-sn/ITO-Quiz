package com.itoquiz.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.itoquiz.dao.CandidateRepository;
import com.itoquiz.dao.QuizRepository;
import com.itoquiz.entity.Candidate;
import com.itoquiz.entity.Question;
import com.itoquiz.service.CandidateServiceImpl;
import com.itoquiz.service.QuizServiceImpl;

@RestController
@RequestMapping("/admin")
public class AdminController {

	@Autowired
	private QuizServiceImpl quizServiceImpl;
	
	@Autowired
	private QuizRepository quizRepo;

	@Autowired
	private CandidateServiceImpl candidateServiceImpl;

	public AdminController(QuizServiceImpl quizServiceImpl, CandidateServiceImpl candidateServiceImpl) {
		this.quizServiceImpl = quizServiceImpl;
		this.candidateServiceImpl = candidateServiceImpl;
	}

	// ***************************************************** Cadidate operations
	// **************************************************************

	@GetMapping("/getAllCandidates")
	public List<Candidate> Candidates() {
		return CandidateServiceImpl.getCandidates();
	}

	@GetMapping("/getCandidateById/{id}")
	public ResponseEntity<Candidate> findCandidateById(@PathVariable int id) {
		return ResponseEntity.ok(this.candidateServiceImpl.findCandidateById(id));
	}

	// ***************************************************** CRUD operations on Quiz
	// questions *************************************************
	@GetMapping("/questions")
	public List<Question> getAllQuestions() {

		return quizServiceImpl.getQuestions();
	}

	@GetMapping("/findQuestionByID/{questionId}")
	public Question getQuestionById(@PathVariable int questionId) {

		Question question = quizRepo.findById(questionId)
				.orElseThrow(() -> new com.itoquiz.exception.ResourceNotFoundException("question", "Id", questionId));

		if (question == null) {
			throw new RuntimeException("Employee Id not found - " + questionId);
		}

		return question;
	}

	@PostMapping("/addQuestion")
	public Question addProduct(@RequestBody Question question) {

		quizServiceImpl.saveQuestion(question);

		return question;
	}

	@PostMapping("/addQuestions")
	public List<Question> addQuestions(@RequestBody List<Question> question) {

		quizServiceImpl.toString();

		return question;
	}

	@PutMapping("/updateQuestion")
	public String updateQuestion(@RequestBody Question question) {

		String updatedMessage = quizServiceImpl.updateQuestion(question);

		return updatedMessage;
	}

	@DeleteMapping("/deleteQuestion/{questionId}")
	public String deleteQuestion(@PathVariable Integer questionId) {

		quizServiceImpl.deleteById(questionId);

		return "Deleted the Question number " + questionId;

	}

	@RequestMapping(value = "/deleteMultipleQuestions", params = "questionIds", method = RequestMethod.DELETE)
	@ResponseBody
	public String deleteMultipleQuestions(@RequestParam List<Integer> questionIds) {

		String qustions = quizServiceImpl.deleteMultipleByIds(questionIds);
		return qustions;
	}

}
