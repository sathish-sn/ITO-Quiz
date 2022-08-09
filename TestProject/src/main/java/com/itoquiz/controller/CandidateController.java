package com.itoquiz.controller;

import java.util.List;
import java.util.stream.Collectors;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.itoquiz.dao.CandidateRepository;
import com.itoquiz.entity.Answer;
import com.itoquiz.entity.Candidate;
import com.itoquiz.entity.Question;
import com.itoquiz.exception.ResourceNotFoundException;
import com.itoquiz.service.AnswerServiceImpl;
import com.itoquiz.service.CandidateServiceImpl;
import com.itoquiz.service.QuizServiceImpl;

@RestController
@RequestMapping("/candidate")
public class CandidateController {

	private QuizServiceImpl quizServiceImpl;
	private CandidateServiceImpl candidateServiceImpl;
	private AnswerServiceImpl answerServiceImpl;
	private CandidateRepository candidateRepo;

	@Autowired
	public CandidateController(QuizServiceImpl quizServiceImpl, CandidateServiceImpl cadidateServiceImpl,
			AnswerServiceImpl answerServiceImpl, CandidateRepository candidateRepo) {
		this.quizServiceImpl = quizServiceImpl;
		this.candidateServiceImpl = cadidateServiceImpl;
		this.answerServiceImpl = answerServiceImpl;
		this.candidateRepo = candidateRepo;
	}

	// ********************************************** Candidate Registration
	// **************************************
	@PostMapping("/register")
	public ResponseEntity<?> generateCandicateId(@RequestBody Candidate candidate) {

		// final long count =
		// candidateServiceImpl.getCandidates().stream().filter(candidate1 ->
		// candidate1.getEmailId().equals(candidate.getEmailId())).count();

		return new ResponseEntity<Candidate>(candidateServiceImpl.generateCanditateId(candidate), HttpStatus.CREATED);
	}

	// ********************************************** Quiz functionality
	// ****************************************
	@GetMapping("/exam/{n}")
	public ResponseEntity<?> getAllQuestions(@PathVariable int n) {

//		if (candidateRepo.findById(n).get().isStarted()) // This will check for the Candidate started status with the
//															// specified ID
//			return new ResponseEntity<>(new ResponseModel("Assement in progress. "), HttpStatus.ALREADY_REPORTED);

		Candidate candidate = candidateRepo.findById(n).orElseThrow(() -> new ResourceNotFoundException("Candidate", "Id", n));
	//	orElseThrow(() -> new ResourceNotFoundException("Candidate", "Id", n));		
		candidate.setStarted(true);
		candidateRepo.save(candidate);// Saving the updated isStarted value in the DB

		return new ResponseEntity<List<QuestionModel>>(candidateServiceImpl.getQuestions(), HttpStatus.FOUND);
	}

	@PostMapping("/submitQuiz/")
	public ResponseEntity<?> postAnswers(@RequestParam("id") int id, @RequestBody List<Answer> answer) {

		if (candidateRepo.findById(id).get().isSubmit()) // This method will check for the Candidate Submit status with
															// the specified ID
			return new ResponseEntity<>(new ResponseModel("Assement finished."), HttpStatus.ACCEPTED);

		for (Answer a : answer) {
			a.setCandidateId(id);
		}

		answerServiceImpl.submitAnswers(answer);
		Candidate candidate = candidateRepo.findById(id).get();
		candidate.setSubmit(true);
		candidateRepo.save(candidate); // Changing Submit Value to "true".

		int rightAnswer = 0;
		int wrongAnswer = 0;

		for (Answer a : answer) {

			int key = candidateServiceImpl.answerSheet().get(a.getQuestion_id()).intValue();// Here we take the
																							// question_id and answer
																							// from "answer" and
																							// comparing it with the
																							// key,value pairs from the
																							// question table.
			if (key == a.getAnswer())
				rightAnswer++;
			else
				wrongAnswer++;
		}

		return new ResponseEntity<>(
				new ResponseModel("Sorry, you are not selected. Better luck next time. Right answers : " + rightAnswer
						+ " Wrong Answers : " + wrongAnswer),
				HttpStatus.ACCEPTED);

		// new ResponseEntity<>(answerServiceImpl.submitAnswers(answer),
		// HttpStatus.FOUND); Returns the submitted answers
	}

}
