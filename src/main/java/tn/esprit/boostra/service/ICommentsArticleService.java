package tn.esprit.boostra.service;

import java.util.List;
import tn.esprit.boostra.entity.Article;
import tn.esprit.boostra.entity.Comment;

public interface ICommentsArticleService {

  Comment UpadateComment(Comment comment);
  void DeleteComment(Comment comment);
  List<Comment> GetAllComment(Long ArticleId);
  Comment GetComment(Long CommentId);
  Comment ajouterComment(Comment comment, Long articleId);
  public List<Article> Mostreplied();
}
