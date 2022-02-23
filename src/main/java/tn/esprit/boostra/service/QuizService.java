package tn.esprit.boostra.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import tn.esprit.boostra.entity.Question;
import tn.esprit.boostra.entity.Quiz;
//import tn.esprit.boostra.repository.QuestionRepository;
import tn.esprit.boostra.repository.QuizRepository;

@Service
public class QuizService implements IQuizService {
	@Autowired
	QuizRepository quizRepository;
	@Override
	public Quiz addQuiz(Quiz quiz) {
		return quizRepository.save(quiz);
	}
	@Override
	public Quiz updateQuiz(Quiz quiz, Long id) {
		quiz.setId(id);
		  return quizRepository.save(quiz);	
	}
	@Override
	public Quiz retrieveQuiz(Long id) {
		
		return quizRepository.findById(id).get();
	}
	@Override
	public List<Quiz> retrieveAllQuiz() {
		List<Quiz> liste=(List<Quiz>) quizRepository.findAll();
		return liste;
	}
	@Override
	public void deleteQuiz(Long id) {
		quizRepository.deleteById(id);
		
	}
	@Override
	public void deleteAllQuiz() {
	quizRepository.deleteAll();
		
	}
}
