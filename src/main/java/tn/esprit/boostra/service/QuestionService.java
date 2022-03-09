package tn.esprit.boostra.service;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import tn.esprit.boostra.entity.Question;
import tn.esprit.boostra.entity.Quiz;
import tn.esprit.boostra.repository.QuestionRepository;
import tn.esprit.boostra.repository.QuizRepository;

@Service
public class QuestionService implements IQuestionService {

  @Autowired QuestionRepository questionRepository;
  @Autowired QuizRepository quizRepository;

  @Override
  public Question addQuestion(Question question) {
    return questionRepository.save(question);
  }
  @Override
  public Question updateQuestion(Question question, Long id) {
    question.setId(id);
    return questionRepository.save(question);
  }
  @Override
  public Question retrieveQuestion(Long id) {

    return questionRepository.findById(id).get();
  }
  @Override
  public List<Question> retrieveAllQuestion() {
    List<Question> liste = (List<Question>)questionRepository.findAll();
    return liste;
  }
  @Override
  public void deleteQuestion(Long id) {
    questionRepository.deleteById(id);
  }
  @Override
  public void deleteAllQuestion() {
    questionRepository.deleteAll();
  }

  @Override
  public void addandsignquestion(Question question, Long idQuiz) {
    Quiz quiz = quizRepository.findById(idQuiz).orElse(new Quiz());
    question.setQuiz(quiz);
    questionRepository.save(question);
  }
}
