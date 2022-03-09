package tn.esprit.boostra.repository;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import tn.esprit.boostra.entity.Article;
import tn.esprit.boostra.entity.Comment;

@Repository
public interface CommentsArticleRepository extends CrudRepository<Comment,Long> {
	
	@Query("select c from Comment c where c.article IS NOT NULL")
	List<Comment>findAll();
	
	@Query("select c.article from Comment c  group by c.article order by count(c) desc")
	List<Article>Mostreplied();
}
