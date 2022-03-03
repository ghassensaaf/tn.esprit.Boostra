package tn.esprit.boostra.controller;

import java.util.List;

import javax.websocket.server.PathParam;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;


import tn.esprit.boostra.entity.Post;
import tn.esprit.boostra.entity.TypePs;
import tn.esprit.boostra.service.IPostService;


@RestController
public class PostRestController {

	@Autowired
	IPostService ps;
	
	@PostMapping("/post/addPost")
	public void addPost(@RequestBody Post post) {
		ps.addPost(post);
	}
	
	@PutMapping("/post/updatePost")
	public Post updatePost(@RequestParam("postId") long postId, @RequestBody Post  post) {
		post.setId(postId);
		return ps.updatePost(post);
	}
	
	@DeleteMapping("/post/deletePost/{postId}")
	public void deletePost(@PathVariable("postId") Long postId) {
		Post post=ps.getByIdPost(postId);
		ps.deletePost(post);
	}
	
	@GetMapping("/post/getall")
	public List<Post> getAll(){
		return ps.getAll();
	}
	
	@GetMapping("/post/getpost/{postId}")
	public Post getByIdPost(@PathVariable("postId") Long postId){
		return  ps.getByIdPost(postId);
	}
	@GetMapping("/post/getpostbytype1")
	public List<Post> Postsbytype1(@RequestParam("type") int type ) {
		if(type==1) 
			return ps.Postsbytype1(TypePs.Status);
		else if (type==2)
			return ps.Postsbytype1(TypePs.Picture);
		else 
			return ps.Postsbytype1(TypePs.Video);


	}
	
}

