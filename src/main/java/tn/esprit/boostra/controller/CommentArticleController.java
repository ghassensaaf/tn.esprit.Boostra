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

import tn.esprit.boostra.entity.Article;
import tn.esprit.boostra.entity.Comment;
import tn.esprit.boostra.service.IArticleService;
import tn.esprit.boostra.service.ICommentsArticleService;

@RestController
public class CommentArticleController {

	@Autowired	
	IArticleService As;
	@Autowired	
	ICommentsArticleService Cs;
	
	@PostMapping("/ArticleComment/Add")
	public Comment AddArticleComment(@RequestBody Comment comment,@RequestParam("ArticleId") Long ArticleId)
	{
		return Cs.ajouterComment(comment,ArticleId);
	}
	
	@PutMapping("/ArticleComment/Update")
	public Comment UpadateArticleComment(@RequestParam("CommentId") Long CommentId,@RequestBody Comment comment,@RequestParam("ArticleId") Long ArticleId)
	{
		Article article=As.GetArticle(ArticleId);
		comment.setArticle(article);
		comment.setId(CommentId);
		return Cs.UpadateComment(comment);
	}
	@DeleteMapping("/ArticleComment/delete/{CommentId}")
	public void DeleteArticleComment(@PathVariable("CommentId") Long CommentId)
	{
		Comment comment=Cs.GetComment(CommentId);
		Cs.DeleteComment(comment);
	}
	@GetMapping("/ArticleComment/getAllByArticle/{ArticleId}")
	public List<Comment> getAll(@PathVariable("ArticleId") Long ArticleId){
		return Cs.GetAllComment(ArticleId);
	}
	
	@GetMapping("/ArticleComment/GetComment/{CommentId}")
	public Comment GetArticleComment(@PathVariable("CommentId") Long CommentId){
		return  Cs.GetComment(CommentId);
	}
	
}
