package tn.esprit.boostra.repository;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import tn.esprit.boostra.entity.SearchFilters;

@Repository
public interface SearchFiltersRepository extends MongoRepository<SearchFilters, String>{
	
	public SearchFilters findBySearchCode(String SearchCode);
}
