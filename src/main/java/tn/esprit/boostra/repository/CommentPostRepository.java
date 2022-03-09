package tn.esprit.boostra.repository;

import java.util.List;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import tn.esprit.boostra.entity.Comment;

@Repository
public interface CommentPostRepository extends CrudRepository<Comment, Long> {

  @Query("select c from Comment c where c.post IS NOT NULL")
  List<Comment> findAll();
}
