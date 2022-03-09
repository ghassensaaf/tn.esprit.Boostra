package tn.esprit.boostra.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import tn.esprit.boostra.entity.Event;
import tn.esprit.boostra.entity.Interest;
import tn.esprit.boostra.entity.SearchFilters;
import tn.esprit.boostra.repository.SearchFiltersRepository;

@Service
public class SearchFiltersService implements ISearchFiltersService {

	@Autowired
	SearchFiltersRepository sfr;
	
	@Override
	public SearchFilters addFilters(String searchCode, List<Event> events) {
		// TODO Auto-generated method stub
		
		double max = 0;
		double min  = Float.MAX_VALUE;
		List<Interest> interests = new ArrayList<Interest>() ;
		List<String> locations = new ArrayList<String>();
		
		for (Event event : events) {
			if (!interests.contains(event.getInterest())) {
				interests.add(event.getInterest());
			}
			if (!locations.contains(event.getLocation())) {
				locations.add(event.getLocation());
			}
			if(event.getPrice() > max) {
				max = event.getPrice();
			}
			if(event.getPrice() < min) {
				min = event.getPrice();
			}
			
		}
		SearchFilters filters = new SearchFilters(searchCode, "success", locations, min, max, interests);
		sfr.save(filters);
		return filters;
	}

}
