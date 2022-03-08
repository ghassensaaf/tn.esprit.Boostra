package tn.esprit.boostra.repository;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import tn.esprit.boostra.entity.Post;
import tn.esprit.boostra.entity.TypePs;

@Repository
public interface PostRepository extends CrudRepository<Post, Long> {

	@Query("select p from Post p where p.typePost =?1 ")
	List<Post> Postsbytype1(TypePs type);
	
	@Query("select count(p) from Post p group by p.geoip ")
	List<Object> PostsByLocation();
}
