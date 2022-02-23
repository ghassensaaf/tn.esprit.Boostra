package tn.esprit.boostra.controller;


import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import tn.esprit.boostra.entity.Quiz;

import tn.esprit.boostra.service.IQuizService;

@RestController
public class QuizzController {
	
	@Autowired
	IQuizService quizzService;

	@PostMapping("/addQuiz")
	public  Quiz addQuizz(@RequestBody Quiz quizz) {
		return quizzService.addQuiz(quizz);
	} 
	@PutMapping("/updatequiz/{id}")
	public Quiz updateQuiz(@RequestBody Quiz quiz,@RequestParam("id") Long id){
		return quizzService.updateQuiz(quiz, id);
	}
	@GetMapping("/getallquiz")
	public List<Quiz> retrieveAllQuiz() {
		return quizzService.retrieveAllQuiz();
	}
	@GetMapping("/getquiz/{id}")
	public Quiz retrieveQuiz(@RequestParam("id") Long id) {
		return quizzService.retrieveQuiz(id);
	}
	@DeleteMapping("/deletequiz/{id}")
	public void deleteQuiz( @RequestParam("id") Long id) {
		quizzService.deleteQuiz(id);
	}
	@DeleteMapping("/deleteallquiz")
	public void deleteAllQuiz() {
		quizzService.deleteAllQuiz();
	}
}
