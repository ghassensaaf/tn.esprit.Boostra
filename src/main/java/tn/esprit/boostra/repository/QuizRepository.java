package tn.esprit.boostra.repository;

import java.util.List;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import tn.esprit.boostra.entity.Quiz;

@Repository
public interface QuizRepository extends CrudRepository<Quiz, Long> {
	List<Quiz> findByTheme(String theme);
}
