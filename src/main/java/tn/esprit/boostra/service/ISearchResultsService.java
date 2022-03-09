package tn.esprit.boostra.service;

import java.util.Date;

import tn.esprit.boostra.entity.Filters;
import tn.esprit.boostra.entity.SearchResults;
import tn.esprit.boostra.entity.User;

public interface ISearchResultsService {

	SearchResults addSearchResults(String Location, Date startDate, Date endDate, int nbrPart, User user);
	SearchResults filtredResult(Filters filters , String searchCode);
}
