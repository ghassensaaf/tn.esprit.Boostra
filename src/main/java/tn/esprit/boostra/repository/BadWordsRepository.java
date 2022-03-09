package tn.esprit.boostra.repository;

import org.springframework.data.repository.CrudRepository;
import tn.esprit.boostra.entity.BadWords;

public interface BadWordsRepository extends CrudRepository<BadWords, Long> {}
