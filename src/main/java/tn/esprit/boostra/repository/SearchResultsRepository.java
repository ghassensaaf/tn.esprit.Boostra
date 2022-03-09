package tn.esprit.boostra.repository;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import tn.esprit.boostra.entity.SearchResults;
@Repository
public interface SearchResultsRepository extends MongoRepository<SearchResults, String>{

	public SearchResults findBySearchCode(String searchCode);
}
