package edu.sjsu.Seekers_Project.sqs;

import javax.annotation.PostConstruct;

import org.apache.log4j.Logger;
import org.springframework.stereotype.Service;

import com.amazonaws.auth.AWSCredentialsProvider;
import com.amazonaws.auth.ClasspathPropertiesFileCredentialsProvider;
import com.amazonaws.regions.Regions;
import com.amazonaws.services.sqs.AmazonSQS;
import com.amazonaws.services.sqs.AmazonSQSClientBuilder;
import com.amazonaws.services.sqs.model.SendMessageRequest;

import edu.sjsu.Seekers_Project.sqs.events.MailerEvent;
import edu.sjsu.Seekers_Project.utils.CommonUtils;

@Service
public class SQSEventProducer {
	
	private static final Logger LOG = Logger.getLogger(SQSEventProducer.class);
	
	AmazonSQS sqsClient;
	
	@PostConstruct
	private void init() {
		AWSCredentialsProvider awsCredentialsProvider = new ClasspathPropertiesFileCredentialsProvider(
				"config/application.properties");

		AmazonSQSClientBuilder clientBuilder = AmazonSQSClientBuilder.standard();
		clientBuilder.setRegion(Regions.US_EAST_1.getName());
		clientBuilder.setCredentials(awsCredentialsProvider);

		sqsClient = clientBuilder.build();
	}


	public void pushEvents(MailerEvent event) {
		LOG.info("Publishing events :" + event);
		try {
			String queueUrl1 = CommonUtils.FILE_PROPERTIES.get("queueUrl");
			System.out.println(queueUrl1);
			SendMessageRequest request = new SendMessageRequest().withQueueUrl(queueUrl1).withMessageBody(event.toJsonString());
			sqsClient.sendMessage(request);
		}catch(Exception e) {
			LOG.error(e);
		}
	}

}
