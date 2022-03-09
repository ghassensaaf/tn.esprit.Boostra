package tn.esprit.boostra.service;

import java.util.List;

import tn.esprit.boostra.entity.Partner;

public interface IPartnerService {
	
	Partner addPartnere(Partner partner);
	Partner updatePartner(Partner partner);
	void deletePartner(long partnerId);
	List<Partner> getAllPartner();
	Partner getById(long partnerId);
	void affecterPartnerToEvent ( long partnerId, long eventId );

}
