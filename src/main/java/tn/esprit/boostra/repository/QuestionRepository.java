package tn.esprit.boostra.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import tn.esprit.boostra.entity.Question;

@Repository
public interface QuestionRepository extends CrudRepository<Question, Long> {}
