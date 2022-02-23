package tn.esprit.boostra.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import tn.esprit.boostra.entity.Post;

@Repository
public interface PostRepository extends CrudRepository<Post, Long> {

}
