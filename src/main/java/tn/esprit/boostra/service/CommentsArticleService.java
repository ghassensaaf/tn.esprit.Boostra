package tn.esprit.boostra.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

import tn.esprit.boostra.entity.Article;
import tn.esprit.boostra.entity.Comment;
import tn.esprit.boostra.entity.User;
import tn.esprit.boostra.repository.ArticleRepository;
import tn.esprit.boostra.repository.CommentsArticleRepository;
import tn.esprit.boostra.repository.UserRepository;
@Service
public class CommentsArticleService implements ICommentsArticleService {

	@Autowired
	CommentsArticleRepository Cr;
	@Autowired
	ArticleRepository Ar;
	@Autowired
	UserRepository Ur;
	
	@Override
	public Comment UpadateComment(Comment comment) {
		Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		String username;
		if (principal instanceof UserDetails) {
			   username = ((UserDetails)principal).getUsername();
			} else {
			   username = principal.toString();
			}
		User user=Ur.findByUserName(username);
		comment.setUser(user);
		return Cr.save(comment);
	}

	@Override
	public void DeleteComment(Comment comment) {
		Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		String username;
		if (principal instanceof UserDetails) {
			   username = ((UserDetails)principal).getUsername();
			} else {
			   username = principal.toString();
			}
		User user=Ur.findByUserName(username);
		
		if (comment.getUser()==user)
		{
		Cr.delete(comment);
		}	
		
	}

	@Override
	public List<Comment> GetAllComment(Long ArticleId) {
		
		 return (List<Comment>) Cr.findAll();
	}

	@Override
	public Comment GetComment(Long CommentId) {
		return Cr.findById(CommentId).orElse(null);
	}

	@Override
	public Comment ajouterComment(Comment comment, Long ArticleId) {
		Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		String username;
		if (principal instanceof UserDetails) {
			   username = ((UserDetails)principal).getUsername();
			} else {
			   username = principal.toString();
			}
		User user=Ur.findByUserName(username);
		comment.setUser(user);
	 Article article=Ar.findById(ArticleId).orElse(null);
	 comment.setArticle(article);
		return Cr.save(comment) ;
	}

}
