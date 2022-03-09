package tn.esprit.boostra.service;

import java.security.Principal;
import java.util.List;
import tn.esprit.boostra.entity.Article;
import tn.esprit.boostra.entity.User;

public interface IArticleService {

  Article UpadateArticle(Article article);
  void DeleteArticle(Article article);
  List<Article> GetAllArticle();
  Article GetArticle(Long ArticleId);
  Article ajouterArticle(Article article);
  public String RemoveBadWords(String text);
  List<Article> MostlikedArticle();
}
