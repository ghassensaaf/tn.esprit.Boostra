package tn.esprit.boostra.repository;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import tn.esprit.boostra.entity.Tag;


@Repository
public interface TagRepository extends CrudRepository<Tag, Long>{

	@Query("select count(t) , t.tag from Tag t group by t.tag order by count(t) DESC")
	List<Object> TrendingTags();
	
	Tag findTopByTag(String tag);
	

	
}
