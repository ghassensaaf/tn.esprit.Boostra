package tn.esprit.boostra.controller;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import tn.esprit.boostra.entity.Question;
import tn.esprit.boostra.service.IQuestionService;

@RestController
public class QuestionController {
  @Autowired IQuestionService quesService;

  @PostMapping("/addQuestion")
  public Question addQuestion(@RequestBody Question question) {
    return quesService.addQuestion(question);
  }
  @PutMapping("/updateQuestion/{id}")
  public Question updateQuestion(@RequestBody Question question,
                                 @RequestParam("id") Long id) {
    return quesService.updateQuestion(question, id);
  }
  @GetMapping("/getQuestion/{id}")
  public Question retrieveQuestion(@PathVariable("id") Long id) {
    return quesService.retrieveQuestion(id);
  }
  @GetMapping("/getallQuestion")
  public List<Question> retrieveAllQuestion() {
    return quesService.retrieveAllQuestion();
  }
  @PostMapping("/signaddQuestion/{idQuiz}")
  public void addandsignquestion(@RequestBody Question question,
                                 @RequestParam("id") Long idQuiz) {
    quesService.addandsignquestion(question, idQuiz);
  }
}
