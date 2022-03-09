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

import tn.esprit.boostra.entity.NoteQuiz;
import tn.esprit.boostra.entity.Quiz;
import tn.esprit.boostra.entity.Quiz.TypeQu;
import tn.esprit.boostra.entity.User;
import tn.esprit.boostra.service.INoteService;
import tn.esprit.boostra.service.IQuizService;
import tn.esprit.boostra.service.IUserService;

@RestController
public class QuizzController {
	
	@Autowired
	IQuizService quizzService;
	@Autowired
	IUserService userservice;
	@Autowired
	INoteService noteService;

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
	@GetMapping("/passerquiz")
	public String PasserQuiz(@RequestParam("answers") List<String>answers,@RequestParam("id")long id){
		return quizzService.PasserQuiz(answers, id);
	}
	@GetMapping("/passquizEval")
	public String  PassQuizEval( @RequestParam List<String> answers,@RequestParam("quizid") Long id,@RequestParam("eventid") Long eventid){
		return  quizzService.PassQuizEval(answers, id, eventid);
	}
	@GetMapping("/Suggestion")
	public List<Quiz>  Suggestion(@RequestParam("username") String username)
	{
		String theme="";
		User user=userservice.findUserByUserName(username);
		List<NoteQuiz> liste= noteService.getTheLastPassedQuiz(user);
		for(NoteQuiz answer:liste) {
			if(answer.getQuiz().getTypeQuiz() ==TypeQu.Knowledge)
			{
				theme=answer.getQuiz().getTheme();
				break;
			}
		
		}
	
		return quizzService.findByTheme(theme);
	}
}
