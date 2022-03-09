package tn.esprit.boostra.service;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;
import tn.esprit.boostra.entity.Article;
import tn.esprit.boostra.entity.BadWords;
import tn.esprit.boostra.entity.Comment;
import tn.esprit.boostra.entity.Reclamation;
import tn.esprit.boostra.entity.User;
import tn.esprit.boostra.repository.ArticleRepository;
import tn.esprit.boostra.repository.BadWordsRepository;
import tn.esprit.boostra.repository.CommentsArticleRepository;
import tn.esprit.boostra.repository.UserRepository;
@Service
public class CommentsArticleService implements ICommentsArticleService {

  @Autowired CommentsArticleRepository Cr;
  @Autowired ArticleRepository Ar;
  @Autowired UserRepository Ur;
  @Autowired BadWordsRepository bdr;

  @Override
  public Comment UpadateComment(Comment comment) {
    Comment com = Cr.findById(comment.getId()).orElse(comment);
    Object principal =
        SecurityContextHolder.getContext().getAuthentication().getPrincipal();
    String username;
    if (principal instanceof UserDetails) {
      username = ((UserDetails)principal).getUsername();
    } else {
      username = principal.toString();
    }
    User user = Ur.findByUserName(username);
    comment.setUser(user);
    String newContent = RemoveBadWords(comment.getContent());
    com.setContent(newContent);
    return Cr.save(com);
  }

  @Override
  public void DeleteComment(Comment comment) {
    Object principal =
        SecurityContextHolder.getContext().getAuthentication().getPrincipal();
    String username;
    if (principal instanceof UserDetails) {
      username = ((UserDetails)principal).getUsername();
    } else {
      username = principal.toString();
    }
    User user = Ur.findByUserName(username);

    if (comment.getUser() == user) {
      Cr.delete(comment);
    }
  }

  @Override
  public List<Comment> GetAllComment(Long ArticleId) {

    return (List<Comment>)Cr.findAll();
  }

  @Override
  public Comment GetComment(Long CommentId) {
    return Cr.findById(CommentId).orElse(null);
  }

  @Override
  public Comment ajouterComment(Comment comment, Long ArticleId) {
    Object principal =
        SecurityContextHolder.getContext().getAuthentication().getPrincipal();
    String username;
    if (principal instanceof UserDetails) {
      username = ((UserDetails)principal).getUsername();
    } else {
      username = principal.toString();
    }
    User user = Ur.findByUserName(username);
    comment.setUser(user);
    Article article = Ar.findById(ArticleId).orElse(null);
    comment.setArticle(article);
    String newContent = RemoveBadWords(comment.getContent());
    comment.setContent(newContent);
    return Cr.save(comment);
  }

  public String RemoveBadWords(String text) {
    List<BadWords> badwords = (List<BadWords>)bdr.findAll();

    for (BadWords badWord : badwords) {
      text = text.replaceAll(badWord.getWord(), badWord.getNbrstars());
    }
    return text;
  }

  @Override
  public List<Article> Mostreplied() {
    return Cr.Mostreplied();
  }
}
