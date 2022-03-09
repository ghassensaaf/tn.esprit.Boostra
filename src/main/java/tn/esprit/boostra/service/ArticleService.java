package tn.esprit.boostra.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;
import tn.esprit.boostra.entity.Article;
import tn.esprit.boostra.entity.BadWords;
import tn.esprit.boostra.entity.User;
import tn.esprit.boostra.repository.ArticleRepository;
import tn.esprit.boostra.repository.BadWordsRepository;
import tn.esprit.boostra.repository.UserRepository;

import com.cloudmersive.client.invoker.ApiClient;
import com.cloudmersive.client.invoker.ApiException;
import com.cloudmersive.client.invoker.Configuration;
import com.cloudmersive.client.invoker.auth.*;
import com.cloudmersive.client.model.ProfanityAnalysisRequest;
import com.cloudmersive.client.model.ProfanityAnalysisResponse;
import com.cloudmersive.client.AnalyticsApi;
import com.cloudmersive.client.ConvertDocumentApi;

@Service
public class ArticleService implements IArticleService{

	@Autowired
	ArticleRepository ar;
	@Autowired
	UserRepository ur;
	@Autowired
	BadWordsRepository bdr;
	@Override
	public Article ajouterArticle(Article article) {
		
		Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		String username;
		if (principal instanceof UserDetails) {
			   username = ((UserDetails)principal).getUsername();
			} else {
			   username = principal.toString();
			}
		User user=ur.findByUserName(username);
		article.setUser(user);
		String newContent = RemoveBadWords(article.getContent());
		article.setContent(newContent);
		return ar.save(article);
	}

	@Override
	public Article UpadateArticle(Article article) {
		Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		String username;
		if (principal instanceof UserDetails) {
			   username = ((UserDetails)principal).getUsername();
			} else {
			   username = principal.toString();
			}
		User user=ur.findByUserName(username);
		article.setUser(user);
		String newContent = RemoveBadWords(article.getContent());
		article.setContent(newContent);
			
		return ar.save(article)	;
		}

	@Override
	public void DeleteArticle(Article article) {
		Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		String username;
		if (principal instanceof UserDetails) {
			   username = ((UserDetails)principal).getUsername();
			} else {
			   username = principal.toString();
			}
		User user=ur.findByUserName(username);
		
		if (article.getUser()==user)
		{
		ar.delete(article);
		}	
	}

	@Override
	public List<Article> GetAllArticle() {
		// TODO Auto-generated method stub
		return (List<Article>) ar.findAll();
	}

	@Override
	public Article GetArticle(Long ArticleId) {
		// TODO Auto-generated method stub
		return ar.findById(ArticleId).orElse(null);
	}
	
	 public String	RemoveBadWords(String text)
	 {
		List<BadWords> badwords =(List<BadWords>) bdr.findAll();
	
		for (BadWords badWord : badwords) {
			text=text.replaceAll(badWord.getWord(),badWord.getNbrstars());
		}
		return text;
	 }

	@Override
	public List<Article> MostlikedArticle() {
		// TODO Auto-generated method stub
		return (List<Article>) ar.MostlikedArticle().subList(0,3);
	}
	
	
	
	 
	
}
