package tn.esprit.boostra.service;

import java.util.List;
import tn.esprit.boostra.entity.Offer;

public interface IOfferService {

  Offer addOffer(Offer offer, long partnerId);
  Offer updateOffer(Offer offer, long partnerId);
  void deleteOffer(long offerId);
  List<Offer> getAllOffer();
  Offer getById(long offerId);
}
