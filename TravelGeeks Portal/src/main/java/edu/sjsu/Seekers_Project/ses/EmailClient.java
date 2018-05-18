package edu.sjsu.Seekers_Project.ses;

import java.io.IOException;
import java.io.InputStream;
import java.math.BigDecimal;
import java.net.URLEncoder;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Properties;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.select.Elements;

import javax.annotation.PostConstruct;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import com.amazonaws.auth.AWSCredentialsProvider;
import com.amazonaws.auth.ClasspathPropertiesFileCredentialsProvider;
import com.amazonaws.regions.Regions;
import com.amazonaws.services.simpleemail.AmazonSimpleEmailService;
import com.amazonaws.services.simpleemail.AmazonSimpleEmailServiceClientBuilder;
import com.amazonaws.services.simpleemail.model.Body;
import com.amazonaws.services.simpleemail.model.Content;
import com.amazonaws.services.simpleemail.model.Destination;
import com.amazonaws.services.simpleemail.model.Message;
import com.amazonaws.services.simpleemail.model.SendEmailRequest;
import com.amazonaws.services.simpleemail.model.VerifyEmailAddressRequest;

import edu.sjsu.Seekers_Project.dao.PlaceDetailsDao;
import edu.sjsu.Seekers_Project.dao.UserDao;
import edu.sjsu.Seekers_Project.model.PlaceDetails;
import edu.sjsu.Seekers_Project.model.User;
import edu.sjsu.Seekers_Project.sqs.events.MailerEvent;

@Service
public class EmailClient {

	@Autowired
	UserDao userDao;

	@Autowired
	PlaceDetailsDao placeDetailsDao;

	private AmazonSimpleEmailService sesClient;

	Map<Integer, String> daysOfWeekMap = new HashMap<Integer, String>();
	private Map<String, String> templates = new HashMap<String, String>();
	private static final Logger LOG = Logger.getLogger(EmailClient.class);
	private static final String FROM = "seekerssjsu@gmail.com";
	private static final String UTF8 = "UTF-8";

	@PostConstruct
	private void init() {
		AWSCredentialsProvider awsCredentialsProvider = new ClasspathPropertiesFileCredentialsProvider(
				"config/application.properties");
		sesClient = AmazonSimpleEmailServiceClientBuilder.standard().withCredentials(awsCredentialsProvider)
				.withRegion(Regions.US_EAST_1).build();

		loadEmailTemplates();
	}

	private void loadEmailTemplates() {
		Properties properties = new Properties();
		InputStream input = this.getClass().getClassLoader()
				.getResourceAsStream("/config/notify-upload-template.properties");
		if (input != null) {
			try {
				properties.load(input);
			} catch (IOException e) {
				LOG.error("I/O exception", e);
			} catch (IllegalArgumentException e) {
				LOG.error("Malfunction properties format", e);
			}
			for (String key : properties.stringPropertyNames()) {
				String value = properties.getProperty(key);
				templates.put(key, value);
			}
		} else {
			LOG.error("I/O exception");
		}
	}

	public void sendEmails(MailerEvent event) throws Exception {
		try {
			switch (event.getEventType().toString()) {
			case "VALIDATE_EMAIL":
				sendValidationEmail(event);
				break;
			case "NOTIFY_PICUPLOAD":
				notifyPicUploadEmail(event);
				break;
			}
		} catch (Exception e) {
			LOG.error(e);
			throw e;
		}
	}

	private void notifyPicUploadEmail(MailerEvent event) throws Exception {
		LOG.info("Sending Pic Upload emails");
		try {
			List<User> users=userDao.getUserByTravelInterest(event.getCategory());

			Document ggl=Jsoup.connect("https://www.google.com/search?q=" + URLEncoder.encode(event.getplaceName(),"UTF-8")).get();
			Elements link=ggl.getElementsByTag("cite"); 
			System.out.println("Users" + users.size());
			//link.forEach(lnk->System.out.println(lnk.text()));
			LOG.info("website links");
			for (User user : users) {
				Body body = new Body().withHtml(new Content().withCharset(UTF8).withData(templates.get("text.body").concat("<br/>  " + link.get(0).text() + "<br/>  " +link.get(1).text() +" <br/> " +link.get(2).text())))
						.withText(new Content().withCharset(UTF8).withData(templates.get("text.body")));
				Message message = new Message();
				message.withBody(body).withSubject(new Content().withCharset(UTF8).withData(templates.get("subject")));
				Destination destination = new Destination().withToAddresses(user.getUserName());
				SendEmailRequest request = new SendEmailRequest().withDestination(destination).withMessage(message)
						.withSource(FROM);
				
				//Nidhi -- comment to prevent sending mails.
				sesClient.sendEmail(request);
				
				System.out.println("mail sent");
				LOG.info("mail sent");
			}
		} catch (Exception e) {
			e.printStackTrace();
			LOG.error(e);
		}
	}

	private void sendValidationEmail(MailerEvent event) {
		
		User user = userDao.getUserById(event.getId());
		VerifyEmailAddressRequest request = new VerifyEmailAddressRequest().withEmailAddress(user.getUserName());
		sesClient.verifyEmailAddress(request);
		
	}
}
