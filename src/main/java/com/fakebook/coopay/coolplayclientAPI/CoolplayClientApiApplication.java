package com.fakebook.coopay.coolplayclientAPI;

//import org.springframework.boot.SpringApplication;
import com.fakebook.coopay.coolplayclientAPI.apibeans.Payment;
import com.fakebook.coopay.coolplayclientAPI.apibeans.Recipient;
import com.fakebook.coopay.coolplayclientAPI.client.RestTempClient;
import com.fakebook.coopay.coolplayclientAPI.apibeans.User;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.client.RestTemplate;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

@SpringBootApplication
public class CoolplayClientApiApplication {

	private static final Logger logger = LoggerFactory.getLogger(CoolplayClientApiApplication.class);
	public static String token;
	public static List<Recipient> recipients = new ArrayList<Recipient>();
	public static Recipient recipient = new Recipient();
	public static List<Payment> payments = new ArrayList<Payment>();
	public static Payment payment = new Payment();


	public static RestTemplate restTemplate = new RestTemplate();
	private static RestTempClient restTempClient = new RestTempClient(restTemplate);
	private static User user= new User();

	public static void main(String[] args) {

		logger.info("Calling Login API...");
		token = restTempClient.login(user.getUsername(),user.getApiKey());
		logger.info(String.format("Getting token value %s", token));
/*
		logger.info("Calling recipients API...");
		recipients = restTempClient.getRecipientList(token);
		for(Recipient recipient : recipients){
			logger.info(String.format("Getting recipients list,name: %s", recipient.getName()));
		}

		logger.info("Calling ADD recipient API...");
		recipient=restTempClient.createRecipient(token, "Adding recipient test 1");
		logger.info(String.format("Recipient value added: %s", recipient.getName()));

		logger.info("Calling recipients API...");
		recipients = restTempClient.getRecipientList(token);
		for(Recipient recipient : recipients){
			logger.info(String.format("Getting recipients list,name: %s", recipient.getName()));
		}
*/
		logger.info("Calling payments API...");
		payments = restTempClient.getPaymentList(token);
		for(Payment payment : payments){
			logger.info(String.format("Getting payments list,name: %s",
					payment.getRecipientId()+"-"+payment.getAmount()+"-"+payment.getCurrency()));
		}

		logger.info("Calling ADD payment API...");
		BigDecimal bc = new BigDecimal(10.5);

		payment=restTempClient.createPayment(token,
				"af94f8a4-b211-419d-b8e6-5ede6b6d84d8",
				bc,
				"GBP");

		logger.info(String.format("payment value added: %s", recipient.getName()));

		logger.info("Calling payments API...");
		payments = restTempClient.getPaymentList(token);
		for(Payment payment : payments){
			logger.info(String.format("Getting payments list,name: %s",
					payment.getRecipientId()+"-"+payment.getAmount()+"-"+payment.getCurrency()));
		}


	}
}
