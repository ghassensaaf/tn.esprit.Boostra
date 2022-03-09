package tn.esprit.boostra.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;
import com.cloudmersive.client.AnalyticsApi;
import com.cloudmersive.client.invoker.ApiClient;
import com.cloudmersive.client.invoker.ApiException;
import com.cloudmersive.client.invoker.Configuration;
import com.cloudmersive.client.invoker.auth.ApiKeyAuth;
import com.cloudmersive.client.model.ProfanityAnalysisRequest;
import com.cloudmersive.client.model.ProfanityAnalysisResponse;
import com.twilio.Twilio;

import tn.esprit.boostra.entity.Article;
import tn.esprit.boostra.entity.Reclamation;
import tn.esprit.boostra.entity.TypeRec;
import tn.esprit.boostra.entity.User;
import tn.esprit.boostra.repository.ReclamationRepository;
import tn.esprit.boostra.repository.UserRepository;
import com.twilio.rest.api.v2010.account.Message;

@Service
public class ReclamationService implements IReclamationService {

	@Autowired
	UserRepository ur;
	@Autowired
	ReclamationRepository rr;

	@Override
	public Reclamation AddReclamation(Reclamation reclamation) {

		Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		String username;
		if (principal instanceof UserDetails) {
			username = ((UserDetails) principal).getUsername();
		} else {
			username = principal.toString();
		}
		User user = ur.findByUserName(username);
		reclamation.setUser(user);
		reclamation.setStatuR("Non traitée");
		if (profanityDetection(reclamation.getContent())){
			reclamation.setStatuC("Angry Client");
		} else {
			reclamation.setStatuC("Happy Client");
		}
		return rr.save(reclamation);
	}

	@Override
	public Reclamation SetReclamtionTotraiter(Reclamation reclamation ) {
		Reclamation rec = rr.findById(reclamation.getId()).orElse(reclamation);
		if ((reclamation.getStatuR().equals("traitée")) && (rec.getTypeReclamation() == TypeRec.technical)) {
			rec.setStatuR("traitée");
			final String ACCOUNT_SID = "AC72f94813ad8924d467e7351a86480e20";
			final String AUTH_TOKEN = "f54024ac50c4523ed43fba2820ba87da";
			Twilio.init(ACCOUNT_SID, AUTH_TOKEN);
			Message message = Message.creator(new com.twilio.type.PhoneNumber("+216" + rec.getUser().getNumber()),
					new com.twilio.type.PhoneNumber("+16815324886"),
					"Bonjour " + rec.getUser().getFirstName() + " " + rec.getUser().getLastName() + " "
							+ "Votre Reclmation N°" + rec.getId() + " " + " a été traitée Merci pour votre confiance !")
					.create();
		}
		return rr.save(rec);
	};

	public boolean profanityDetection(String text) {
		int score = 0;
		ApiClient defaultClient = Configuration.getDefaultApiClient();
		ApiKeyAuth Apikey = (ApiKeyAuth) defaultClient.getAuthentication("Apikey");
		Apikey.setApiKey("d4f49860-d6ad-49c6-90da-802e2bab8b50");
		AnalyticsApi apiInstance = new AnalyticsApi();
		ProfanityAnalysisRequest input = new ProfanityAnalysisRequest();
		try {
			input.setTextToAnalyze(text);
			ProfanityAnalysisResponse result = apiInstance.analyticsProfanity(input);
			System.out.println(result);
			return result.getProfanityScoreResult() > 0.2;
		} catch (ApiException e) {
			System.err.println("Exception when calling AnalyticsApi#analyticsSentiment");
			e.printStackTrace();
		}
		return false;
	}

	@Override
	public void UpdateSystemReclamation() {
		List<Reclamation> reclamations=rr.TraiterSystemReclamation(TypeRec.System);
		for (Reclamation reclamation : reclamations) {
			reclamation.setStatuR("Traitée");
			final String ACCOUNT_SID = "AC72f94813ad8924d467e7351a86480e20";
			final String AUTH_TOKEN = "f54024ac50c4523ed43fba2820ba87da";
			Twilio.init(ACCOUNT_SID, AUTH_TOKEN);
			Message message = Message.creator(new com.twilio.type.PhoneNumber("+216" + reclamation.getUser().getNumber()),
					new com.twilio.type.PhoneNumber("+16815324886"),
					"Bonjour " + reclamation.getUser().getFirstName() + " " + reclamation.getUser().getLastName() + " "
							+ "Votre Reclmation N°" + reclamation.getId() + " " + "a été traitée Merci pour votre confiance !")
					.create();
			}
		rr.saveAll(reclamations);	
	}

	@Override
	public void DeleteReclamation(Reclamation reclamation) {
		Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		String username;
		if (principal instanceof UserDetails) {
			   username = ((UserDetails)principal).getUsername();
			} else {
			   username = principal.toString();
			}
		User user=ur.findByUserName(username);
		
		if (reclamation.getUser()==user)
		{
		rr.delete(reclamation);
		}	
	}

	@Override
	public List<Reclamation> GetAllReclamation() {
		return (List<Reclamation>) rr.findAll();
	}
	
	public Reclamation GeReclamationById(Long ReclamationId) {
		return rr.findById(ReclamationId).orElse(null);
	}
	@Override
	public List<Reclamation> GetReclmationByUserid() {
		Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		String username;
		if (principal instanceof UserDetails) {
			username = ((UserDetails) principal).getUsername();
		} else {
			username = principal.toString();
		}
		User user = ur.findByUserName(username);
		return  rr.findByUser(user);
	}
	
	
	
	
}
