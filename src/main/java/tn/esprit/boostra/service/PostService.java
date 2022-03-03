package tn.esprit.boostra.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

import tn.esprit.boostra.entity.Post;
import tn.esprit.boostra.entity.TypePs;
import tn.esprit.boostra.entity.User;
import tn.esprit.boostra.repository.PostRepository;
import tn.esprit.boostra.repository.UserRepository;

@Service
public class PostService implements IPostService {

	@Autowired
	PostRepository pr;
	
	@Autowired
	UserRepository ur;
	
	@Override
	public Post addPost(Post post) {
		// TODO Auto-generated method stub
		Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		String username;
		if (principal instanceof UserDetails) {
			   username = ((UserDetails)principal).getUsername();
			} else {
			   username = principal.toString();
			}
		User user=ur.findByUserName(username);
		post.setUser(user);
		return pr.save(post);
	}

	@Override
	public Post updatePost(Post post) {
		// TODO Auto-generated method stub
		Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		String username;
		if (principal instanceof UserDetails) {
			   username = ((UserDetails)principal).getUsername();
			} else {
			   username = principal.toString();
			}
		User user=ur.findByUserName(username);
		post.setUser(user);
		return pr.save(post);
	}

	@Override
	public void deletePost(Post post) {
		// TODO Auto-generated method stub
		Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		String username;
		if (principal instanceof UserDetails) {
			   username = ((UserDetails)principal).getUsername();
			} else {
			   username = principal.toString();
			}
		User user=ur.findByUserName(username);
	if(post.getUser()==user) 
		pr.delete(post);
	
	}

	@Override
	public List<Post> getAll() {
		// TODO Auto-generated method stub
		return (List<Post>) pr.findAll();
	}

	@Override
	public Post getByIdPost(long PostId) {
		// TODO Auto-generated method stub
		
		return pr.findById(PostId).orElse(null);
	}

	@Override
	public List<Post> Postsbytype1(TypePs type) {
		// TODO Auto-generated method stub
		return pr.Postsbytype1(type);
	}
	

}
