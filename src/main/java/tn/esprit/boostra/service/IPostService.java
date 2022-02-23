package tn.esprit.boostra.service;

import java.util.List;

import tn.esprit.boostra.entity.Post;

public interface IPostService {

	Post addPost(Post post);
	Post updatePost(Post post);
	void deletePost(Post post);
	List<Post> getAll();
	Post getByIdPost(long PostId);
	
}
