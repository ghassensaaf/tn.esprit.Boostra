package tn.esprit.boostra.service;

import java.util.List;

import tn.esprit.boostra.entity.Comment;



public interface ICommentPostService {
	
	Comment UpdateComment(Comment comment);
	void DeleteComment(Comment comment);
	List<Comment> GetAllComment(Long postId);
	Comment GetComment(Long CommentId) ;
	Comment AddComment(Comment comment,Long postId);

}
