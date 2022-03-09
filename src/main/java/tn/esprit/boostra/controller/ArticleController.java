package tn.esprit.boostra.controller;

import java.util.List;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import tn.esprit.boostra.entity.Article;
import tn.esprit.boostra.service.IArticleService;
@Slf4j
@RestController
public class ArticleController {

  @Autowired IArticleService As;

  @PostMapping("/Article/Add")
  public Article AddArticle(@RequestBody Article article) {

    return As.ajouterArticle(article);
  }

  @PutMapping("/Article/Update")
  public Article UpadateArticle(@RequestParam("ArticleId") Long ArticleId,
                                @RequestBody Article article) {
    article.setId(ArticleId);
    return As.UpadateArticle(article);
  }
  @DeleteMapping("/Article/delete/{ArticleId}")
  public void DeleteArticle(@PathVariable("ArticleId") Long ArticleId) {
    Article article = As.GetArticle(ArticleId);
    As.DeleteArticle(article);
  }

  @GetMapping("/Article/getAll")
  public List<Article> getAll() {
    return As.GetAllArticle();
  }

  @GetMapping("/Article/GetArticle/{ArticleId}")
  public Article GetArticle(@PathVariable("ArticleId") Long ArticleId) {
    return As.GetArticle(ArticleId);
  }

  @Scheduled(cron = "*/10 * * * * *")
  public void Mostliked() {
    String text = "\n Mostliked Article \n";
    List<Article> article = As.MostlikedArticle();
    for (Article ar : article) {
      text += "ArticleTitle : " + ar.getTitle() + "\n";
    }
    log.info(text);
    text = "";
  }
}
