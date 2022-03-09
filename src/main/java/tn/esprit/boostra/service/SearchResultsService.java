package tn.esprit.boostra.service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import tn.esprit.boostra.entity.Event;
import tn.esprit.boostra.entity.Filters;
import tn.esprit.boostra.entity.SearchResults;
import tn.esprit.boostra.entity.User;
import tn.esprit.boostra.repository.EventRepository;
import tn.esprit.boostra.repository.InterestRepository;
import tn.esprit.boostra.repository.SearchResultsRepository;

@Service
public class SearchResultsService implements ISearchResultsService {

	@Autowired
	EventRepository er;
	@Autowired
	InterestRepository ir;
	@Autowired
	SearchResultsRepository sr;
	@Autowired
	ISearchFiltersService sfs;
	@Override
	public SearchResults addSearchResults(String Location, Date startDate, Date endDate, int nbrPart, User user) {
		List<Event> results = er.searchEvents(startDate, endDate, Location, nbrPart);
//		List<Event> results = (List<Event>) er.findAll();
		SearchResults result = new SearchResults();
		result.setEvents(results);
		result.setCount(results.size());
		result.setDateTime(new Date());
		result.setStatus("success");
		result.setUserId(user.getId());
		result.setUserName(user.getUserName());
		sr.save(result);
		sfs.addFilters(result.getSearchCode(), results);
		return result;
	}
	@Override
	public SearchResults filtredResult(Filters filters, String searchCode) {
		SearchResults results = sr.findBySearchCode(searchCode);
		List<Event> events = new ArrayList<Event>();
		for (Event event : results.getEvents()) {
			if(filters.getInterests().size() == 0 && filters.getLocations().size() == 0 && event.getPrice()<=filters.getMaxPrice() && event.getPrice()>=filters.getMinPrice())
				events.add(event);
			else if(filters.getInterests().size() == 0 && event.getPrice()<=filters.getMaxPrice() && event.getPrice()>=filters.getMinPrice() && filters.getLocations().contains(event.getLocation()))
				events.add(event);
			else if(filters.getLocations().size() == 0 && event.getPrice()<=filters.getMaxPrice() && event.getPrice()>=filters.getMinPrice() && filters.getInterests().contains(event.getInterest().getId()))
				events.add(event);
			else if(event.getPrice()<=filters.getMaxPrice() && event.getPrice()>=filters.getMinPrice() && filters.getLocations().contains(event.getLocation()) && filters.getInterests().contains(event.getInterest().getId()))
				events.add(event);
		}
		SearchResults filtred = new SearchResults(searchCode, "success", results.getUserId(), results.getUserName(), new Date(), events.size(), events);
		return filtred;
	}

}
