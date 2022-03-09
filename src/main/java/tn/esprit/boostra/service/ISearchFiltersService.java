package tn.esprit.boostra.service;

import java.util.List;

import tn.esprit.boostra.entity.Event;
import tn.esprit.boostra.entity.SearchFilters;

public interface ISearchFiltersService {

	public SearchFilters addFilters(String searchCode, List<Event> events);
}
