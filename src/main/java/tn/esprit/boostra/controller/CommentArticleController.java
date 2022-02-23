package tn.esprit.boostra.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
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
	
}
