package tn.esprit.boostra.service;

import java.util.List;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

import tn.esprit.boostra.entity.Comment;
import tn.esprit.boostra.entity.Post;
import tn.esprit.boostra.entity.User;
import tn.esprit.boostra.repository.CommentPostRepository;
import tn.esprit.boostra.repository.PostRepository;
import tn.esprit.boostra.repository.UserRepository;

@Service
public class CommentPostService implements ICommentPostService {

	@Autowired
	UserRepository ur;
	
	@Autowired
	PostRepository pr;
	
	@Autowired
	CommentPostRepository cr;

	@Override
	public Comment UpdateComment(Comment comment) {
		// TODO Auto-generated method stub
		Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		String username;
		if (principal instanceof UserDetails) {
			   username = ((UserDetails)principal).getUsername();
			} else {
			   username = principal.toString();
			}
		User user=ur.findByUserName(username);
		comment.setUser(user);
		return cr.save(comment);
		
	}

	@Override
	public void DeleteComment(Comment comment) {
		// TODO Auto-generated method stub
		
		Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		String username;
		if (principal instanceof UserDetails) {
			   username = ((UserDetails)principal).getUsername();
			} else {
			   username = principal.toString();
			}
		User user=ur.findByUserName(username);
		
		if (comment.getUser()==user)
		{
		cr.delete(comment);
		}	
		
	}

	@Override
	public List<Comment> GetAllComment(Long postId) {
		// TODO Auto-generated method stub
		return (List<Comment>) cr.findAll();
	}

	@Override
	public Comment GetComment(Long CommentId) {
		// TODO Auto-generated method stub
		return cr.findById(CommentId).orElse(null);
	}

	@Override
	public Comment AddComment(Comment comment, Long postId) {
		// TODO Auto-generated method stub
		Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		String username;
		if (principal instanceof UserDetails) {
			   username = ((UserDetails)principal).getUsername();
			} else {
			   username = principal.toString();
			}
		User user=ur.findByUserName(username);
		comment.setUser(user);
	 Post post=pr.findById(postId).orElse(null);
	 comment.setPost(post);
		return  cr.save(comment) ;
	
	}
	
	
	
	
}
