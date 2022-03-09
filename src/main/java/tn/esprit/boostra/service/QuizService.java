package tn.esprit.boostra.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

import tn.esprit.boostra.entity.NoteQuiz;
import tn.esprit.boostra.entity.Question;
import tn.esprit.boostra.entity.Quiz;
import tn.esprit.boostra.entity.User;
import tn.esprit.boostra.repository.QuizRepository;
import tn.esprit.boostra.repository.UserRepository;

@Service
public class QuizService implements IQuizService {
	@Autowired
	QuizRepository quizRepository;
	@Autowired
	NoteService noteService;
	@Autowired
	UserService us;
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
	public String PasserQuiz( List<String>answers,long id){
		int notes=0;
		boolean found=false;
		 Quiz quiz=retrieveQuiz(id);
		 List<Question>questions=quiz.getQuestions();
		 for(Question question:questions){
			 found=false;
			 for(String answer:answers){
				 if(question.getCorrectAnswer().compareTo(answer)==0 && found==false){
					 notes++;
					 found=true;
				 }
			 }
		 }
		 NoteQuiz notequiz=new NoteQuiz();
		 notequiz.setNote(notes);
		
		 notequiz.setQuiz(quiz);
		 String username="";
		 Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		 if(principal instanceof UserDetails){
			 username=((UserDetails)principal).getUsername();
		 }else{
			 username=principal.toString();
		 }
		 User user=us.findUserByUserName(username);
		 notequiz.setUser(user);
		 noteService.addNote(notequiz);
		 return "your Quiz result is"+notes;
		 
	}
	@Override
	public String  PassQuizEval( List<String> answers,Long id, Long eventid)
	{
		//every Correct Answer must be different than the other Correct Answer
		int notes=0;
		
		Quiz quiz=retrieveQuiz(id);
			//found=false;
			for(String answer:answers) {
			if(answer.compareTo("good")==0)
			{
				notes=5;
				//break;
			}
			else if (answer.compareTo("bad")==0)
			{
				notes=1;
			}
			else if(answer.compareTo("excellent")==0)
			{
				notes=10;
			}
			}
		
		NoteQuiz notequiz= new NoteQuiz();
		notequiz.setNote(notes);
		notequiz.setQuiz(quiz);
		notequiz.setEventId(eventid);
		String username="";
		Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();

		if (principal instanceof UserDetails) {
		   username = ((UserDetails)principal).getUsername();
		} else {
		  username = principal.toString();
		}
		User user=us.findUserByUserName(username);
		notequiz.setUser(user); // Retrieve the Connected User and add it here
		noteService.addNote(notequiz);
		return "you have evaluated this event with  "+ notes;
		
	}
	@Override
	public List<Quiz> findByTheme(String Theme) {
		return quizRepository.findByTheme(Theme);
	}
}
