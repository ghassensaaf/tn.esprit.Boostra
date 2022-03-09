package tn.esprit.boostra.repository;

import java.util.List;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import tn.esprit.boostra.entity.Article;
import tn.esprit.boostra.entity.Comment;
@Repository
public interface ArticleRepository extends CrudRepository<Article, Long> {

  @Query("select a from Article a ORDER BY  a.likeCount DESC")
  List<Article> MostlikedArticle();
}
