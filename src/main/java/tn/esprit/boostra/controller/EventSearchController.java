package tn.esprit.boostra.controller;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import tn.esprit.boostra.entity.Filters;
import tn.esprit.boostra.entity.SearchFilters;
import tn.esprit.boostra.entity.SearchQuery;
import tn.esprit.boostra.entity.SearchResults;
import tn.esprit.boostra.entity.User;
import tn.esprit.boostra.repository.SearchFiltersRepository;
import tn.esprit.boostra.repository.UserRepository;
import tn.esprit.boostra.service.ISearchResultsService;

@RestController
public class EventSearchController {

	@Autowired
	ISearchResultsService ss;
	@Autowired
	UserRepository ur;
	@Autowired
	SearchFiltersRepository sfr;
	
	@PostMapping("/Search")
	public SearchResults search(@RequestBody SearchQuery search) {
		Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		String uname;
		if (principal instanceof UserDetails) {
			   uname = ((UserDetails)principal).getUsername();
		} else {
			   uname = principal.toString();
		}
		User user = ur.findByUserName(uname);
		
		return ss.addSearchResults(search.getLocation(), search.getStartDate(), search.getEndDate(), search.getNbrPart(), user);
		
	}
	@PostMapping("/Search/filters")
	public SearchFilters filters(@RequestParam("searchCode") String searchCode) {
		return sfr.findBySearchCode(searchCode);
	}
	@PostMapping("/Search/filtred")
	public SearchResults filtredResult(@RequestBody Filters filters, @RequestParam("searchCode") String searchCode) {
		return ss.filtredResult(filters, searchCode);
	}
}
