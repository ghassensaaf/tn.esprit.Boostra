package tn.esprit.boostra.service;

import java.util.List;
import tn.esprit.boostra.entity.Question;

public interface IQuestionService {
  public Question addQuestion(Question question);
  public Question updateQuestion(Question question, Long id);
  public Question retrieveQuestion(Long id);
  public List<Question> retrieveAllQuestion();
  public void deleteQuestion(Long id);
  public void deleteAllQuestion();
  public void addandsignquestion(Question question, Long idQuiz);
}
