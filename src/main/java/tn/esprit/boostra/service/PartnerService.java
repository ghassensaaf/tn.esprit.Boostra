package tn.esprit.boostra.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import tn.esprit.boostra.entity.Event;
import tn.esprit.boostra.entity.Partner;
import tn.esprit.boostra.repository.EventRepository;
import tn.esprit.boostra.repository.PartnerRepository;

@Service 
public class PartnerService implements IPartnerService {

	
	@Autowired 
	PartnerRepository pr;
	
	@Autowired
	EventRepository er;
	
	@Override
	public Partner addPartnere(Partner partner) {
		// TODO Auto-generated method stub
		return pr.save(partner);
	}

	@Override
	public Partner updatePartner(Partner partner) {
		// TODO Auto-generated method stub
		return pr.save(partner);
	}

	@Override
	public void deletePartner(long partnerId) {
		// TODO Auto-generated method stub
		pr.deleteById(partnerId);
	}

	@Override
	public List<Partner> getAllPartner() {
		// TODO Auto-generated method stub
		return (List<Partner>) pr.findAll();
	}

	@Override
	public Partner getById(long partnerId) {
		// TODO Auto-generated method stub
		return pr.findById(partnerId).orElse(null);
	}

	@Override
	public void affecterPartnerToEvent(long partnerId, long eventId) {
		Partner p = pr.findById(partnerId).get();
		Event e = er.findById(eventId).get();
		p.getEvents().add(e);
		pr.save(p);
	}

}
