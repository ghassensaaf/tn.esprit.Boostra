package tn.esprit.boostra.service;

import java.util.List;

import tn.esprit.boostra.entity.Question;
import tn.esprit.boostra.entity.Quiz;
import tn.esprit.boostra.entity.User;

public interface IQuizService {

	public Quiz addQuiz(Quiz quiz);
	public Quiz updateQuiz(Quiz quiz, Long id);
	public Quiz retrieveQuiz(Long id);
	public List<Quiz> retrieveAllQuiz();
	public void deleteQuiz(Long id);
	public void deleteAllQuiz();
	public String PasserQuiz( List<String>answers,long id);
	public String  PassQuizEval( List<String> answers,Long id, Long eventid);
	public List<Quiz> findByTheme(String Theme);
	
	
}
