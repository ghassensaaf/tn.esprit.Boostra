package tn.esprit.boostra.service;

import java.util.List;

import tn.esprit.boostra.entity.Post;
import tn.esprit.boostra.entity.TypePs;

public interface IPostService {

	Post addPost(Post post);
	Post updatePost(Post post);
	void deletePost(Post post);
	List<Post> getAll();
	Post getByIdPost(long PostId);
	public List<Post> Postsbytype1(TypePs type);
	public List<Object> PostsByLocation();
	
}
