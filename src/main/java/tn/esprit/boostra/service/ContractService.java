package tn.esprit.boostra.service;

import java.io.BufferedInputStream;
import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.Reader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.nio.charset.Charset;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Properties;

import org.json.JSONException;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Service;

import lombok.extern.slf4j.Slf4j;
import nonapi.io.github.classgraph.json.JSONSerializer;
import tn.esprit.boostra.entity.Contract;
import tn.esprit.boostra.entity.Partner;
import tn.esprit.boostra.repository.ContractRepository;
import tn.esprit.boostra.repository.PartnerRepository;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.JavaMailSenderImpl;

@Slf4j
@Service
public class ContractService implements IContractService {

	@Autowired
	ContractRepository cr;

	@Autowired
	PartnerRepository pr;

	@Override
	public Contract addContract(Contract contract, long partnerId) throws JSONException, IOException {
		JSONObject json = readJsonFromUrl("https://v6.exchangerate-api.com/v6/49ab5ca8e26fd77d5f131fcd/latest/TND");
		double valEUR = json.getJSONObject("conversion_rates").getDouble("EUR");
		double valUSD = json.getJSONObject("conversion_rates").getDouble("USD");
		double valTRY = json.getJSONObject("conversion_rates").getDouble("TRY");
	
		
		if (contract.getCuerrency().toString().equals("EUR")) {
			contract.setPriceTND(contract.getPrice() / valEUR);
		} else if (contract.getCuerrency().toString().equals("USD")) {
			contract.setPriceTND(contract.getPrice() / valUSD);
		}else if (contract.getCuerrency().toString().equals("TRY")) {
				contract.setPriceTND(contract.getPrice() / valTRY);
		}else {
			contract.setPriceTND(contract.getPrice());
		}
		Partner partner = pr.findById(partnerId).orElseGet(null);
		contract.setPartner(partner);
		return cr.save(contract);
	
	}

	@Override
	public Contract updateContract(Contract contract, long partnerId) throws JSONException, IOException{
		
		JSONObject json = readJsonFromUrl("https://v6.exchangerate-api.com/v6/49ab5ca8e26fd77d5f131fcd/latest/TND");
		double valEUR = json.getJSONObject("conversion_rates").getDouble("EUR");
		double valUSD = json.getJSONObject("conversion_rates").getDouble("USD");
		double valTRY = json.getJSONObject("conversion_rates").getDouble("TRY");
	
		
		if (contract.getCuerrency().toString().equals("EUR")) {
			contract.setPriceTND(contract.getPrice() / valEUR);
		} else if (contract.getCuerrency().toString().equals("USD")) {
			contract.setPriceTND(contract.getPrice() / valUSD);
		}else if (contract.getCuerrency().toString().equals("TRY")) {
				contract.setPriceTND(contract.getPrice() / valTRY);
		}else {
			contract.setPriceTND(contract.getPrice());
		}
		Partner partner = pr.findById(partnerId).orElseGet(null);
		contract.setPartner(partner);
		return cr.save(contract);
	}

	@Override
	public void deleteContract(long contractId) {
		// TODO Auto-generated method stub
		cr.deleteById(contractId);
	}

	@Override
	public List<Contract> getAllContract() {
		// TODO Auto-generated method stub
		return (List<Contract>) cr.findAll();
	}

	@Override
	public Contract getById(long contractId) {
		// TODO Auto-generated method stub
		return cr.findById(contractId).orElse(null);
	}

	@Override
	public String renewingContrat() {
		String msg = "";
		Date currentDate = new Date();
		List<Contract> list = cr.listContarctExpiried(currentDate);
		Calendar c = Calendar.getInstance();
		for (Contract contract : list) {
			c.setTime(currentDate);
			if (contract.isRenewing()) {
				contract.setStartDate(new Date());
				if (contract.getTypeContract().toString().equals("Monthly")) {
					c.add(Calendar.MONTH, 1);
					contract.setEndDate(c.getTime());
					msg += "Le Contract N° " + contract.getId() + " est renouvlable  \n";
					msg += "New Start Date " + new Date() + "\n";
					msg += "New End Date " + c.getTime() + "\n";
					msg += "****************************************** \n";
					sendmail("info@boostra.tn",contract.getPartner().getEmail(),"Renouvellement  du contrat " , "Bonjour cher partenaire \nNous vous informons que votre contrat n° "+  contract.getId() + " à bien été renouvelé d'un mois. \nCordialement " );
				} else if (contract.getTypeContract().toString().equals("halfYearly")) {
					c.add(Calendar.MONTH, 6);
					contract.setEndDate(c.getTime());
					msg += "Le contract N° " + contract.getId() + " est renouvlable \n";
					msg += "New Start Date " + new Date() + "\n";
					msg += "New End Date " + c.getTime() + "\n";
					msg += "****************************************** \n";
					sendmail("info@boostra.tn",contract.getPartner().getEmail(),"Renouvellement  du contrat" , "Bonjour cher partenaire \nNous vous informons que votre contrat n° "+  contract.getId() + " à bien été renouvelé de 6 mois. \nCordialement");
				} else if (contract.getTypeContract().toString().equals("Yearly")) {
					c.add(Calendar.YEAR, 1);
					contract.setEndDate(c.getTime());
					msg += "Le contract N° " + contract.getId() + " est renouvlable  \n";
					msg += "New Start Date " + new Date() + "\n";
					msg += "New End Date " + c.getTime() + "\n";
					msg += "****************************************** \n";
					sendmail("info@boostra.tn",contract.getPartner().getEmail(),"Renouvellement  du contrat" , "Bonjour cher partenaire \nNous vous informons que votre contrat n° "+  contract.getId() + " à bien été renouvelé d'un an. \nCordialement");

				}
			} else {
				contract.setStatut(false);
				msg += "Le Contract N° " + contract.getId() + " est non renouvlable  \n";
				msg += "Contract est donc expiré \n";
				msg += "****************************************** \n";
				sendmail("info@boostra.tn",contract.getPartner().getEmail(),"Contract Expiré" , "Bonjour cher partenaire \n Nous vous informons que votre contrat n° " +  contract.getId() + " à ete expiré \n Veuillez nous contacter si vous souhaitez renouveler votre contrat" );

			}
			cr.save(contract);
		}
		msg+= "Aucun contrat non traité ";
		return msg;
	
	}

	
	
	private static String readAll(Reader rd) throws IOException {
	    StringBuilder sb = new StringBuilder();
	    int cp;
	    while ((cp = rd.read()) != -1) {
	      sb.append((char) cp);
	    }
	    return sb.toString();
	  }

	  public static JSONObject readJsonFromUrl(String url) throws IOException, JSONException {
	    java.io.InputStream is = new URL(url).openStream();
	    try {
	      BufferedReader rd = new BufferedReader(new InputStreamReader(is, Charset.forName("UTF-8")));
	      String jsonText = readAll(rd);
	      JSONObject json = new JSONObject(jsonText);
	      return json;
	    } finally {
	      is.close();
	    }
	  }
	  
	  
	  
	  @Bean
		public JavaMailSender getJavaMailSender() {
		    JavaMailSenderImpl mailSender = new JavaMailSenderImpl();
		    mailSender.setHost("mail.lancersent.tn");
		    mailSender.setPort(587);
		    
		    mailSender.setUsername("info@lancersent.tn");
		    mailSender.setPassword("Za3maettal3ou");
		    
		    Properties props = mailSender.getJavaMailProperties();
		    props.put("mail.transport.protocol", "smtp");
		    props.put("mail.smtp.auth", "true");
		    props.put("mail.smtp.starttls.enable", "true");
		    props.put("mail.debug", "true");
		    
		    return mailSender;
		}
	  
	  @Autowired
		private JavaMailSender emailSender;
	  void sendmail(String from,String to, String sujet, String text) {
		  
			SimpleMailMessage message = new SimpleMailMessage();
			message.setFrom(from);
	        message.setTo(to); 
	        message.setSubject(sujet); 
	        message.setText(text);
	        emailSender.send(message);
		}
}
