package tn.esprit.boostra.controller;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import tn.esprit.boostra.entity.Comment;
import tn.esprit.boostra.entity.Post;
import tn.esprit.boostra.service.ICommentPostService;
import tn.esprit.boostra.service.IPostService;

@RestController
public class CommentPostRestController {

  @Autowired IPostService ps;
  @Autowired ICommentPostService cs;

  @PostMapping("/PostComment/Add")
  public Comment AddPostComment(@RequestBody Comment comment,
                                @RequestParam("PostId") Long PostId) {
    return cs.AddComment(comment, PostId);
  }

  @PutMapping("/PostComment/Add")

  public Comment updateComment(@RequestBody Comment comment,
                               @RequestParam("commentId") long commentId,
                               @RequestParam("postId") long postId) {

    Post post = ps.getByIdPost(postId);
    comment.setPost(post);
    comment.setId(commentId);
    return cs.UpdateComment(comment);
  }

  @DeleteMapping("/PostComment/deleteComment/{commentId}")
  public void deleteComment(@PathVariable("commentId") Long commentId) {
    Comment comment = cs.GetComment(commentId);
    cs.DeleteComment(comment);
  }

  @GetMapping("/PostComment/getAllByPost/{postId}")
  public List<Comment> getAll(@PathVariable("postId") Long postId) {
    return cs.GetAllComment(postId);
  }

  @GetMapping("/PostComment/{commentId}")
  public Comment getByIdComment(@PathVariable("commentId") Long commentId) {
    return cs.GetComment(commentId);
  }
}
