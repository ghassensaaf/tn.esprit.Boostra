package tn.esprit.boostra.service;

import java.util.List;

import tn.esprit.boostra.entity.Ad;

public interface IAdService {
	 public void addAd(Ad ad);
	 public List<Ad> getAllAd();
	 public Ad getAdById(Long idAd);
	 public Ad updateAd(Ad ad,Long id);
	 public void deleteAdById(Long id);
	 public void deleteAllAd();
     public List<Ad> AdResaux(String ReseauxSociaux);
     public void addAndAssignAd(Ad ad,Long id);
     public List<String> getListResaux();
}
