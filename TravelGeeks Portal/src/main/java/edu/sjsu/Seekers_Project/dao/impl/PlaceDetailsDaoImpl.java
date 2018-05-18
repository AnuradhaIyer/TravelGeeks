package edu.sjsu.Seekers_Project.dao.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.amazonaws.services.dynamodbv2.AmazonDynamoDB;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBMapper;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBScanExpression;
import com.amazonaws.services.dynamodbv2.document.DynamoDB;
import com.amazonaws.services.dynamodbv2.document.Table;
import com.amazonaws.services.dynamodbv2.document.UpdateItemOutcome;
import com.amazonaws.services.dynamodbv2.document.spec.UpdateItemSpec;
import com.amazonaws.services.dynamodbv2.document.utils.ValueMap;
import com.amazonaws.services.dynamodbv2.model.AttributeValue;
import com.amazonaws.services.dynamodbv2.model.ReturnValue;

import edu.sjsu.Seekers_Project.dao.PlaceDetailsDao;
import edu.sjsu.Seekers_Project.model.PlaceDetails;
import edu.sjsu.Seekers_Project.model.User;

@Repository
public class PlaceDetailsDaoImpl implements PlaceDetailsDao {

	@Autowired
	DynamoDbClient dbClient;

	@Autowired
	UserDaoImpl userdaoImpl;
	
	@Override
	public void upload(PlaceDetails placeDetails) throws Exception {
		AmazonDynamoDB dynamoDB = dbClient.getDynamoDB();
		DynamoDBMapper mapper = new DynamoDBMapper(dynamoDB);
		mapper.save(placeDetails);
	}

	public List<PlaceDetails> getPlaceDetails(String userName) throws Exception {
		AmazonDynamoDB dynamoDB = dbClient.getDynamoDB();
		System.out.println("My Uploaded place");
		DynamoDBMapper mapper = new DynamoDBMapper(dynamoDB);
		Map<String, AttributeValue> userValues = new HashMap<String, AttributeValue>();
		userValues.put(":UserVal", new AttributeValue().withS(userName));

		DynamoDBScanExpression scanExpression = new DynamoDBScanExpression()
				.withFilterExpression("userDetails.UserName = :UserVal").withExpressionAttributeValues(userValues);

		List<PlaceDetails> scanResultPlaceDetails = mapper.scan(PlaceDetails.class, scanExpression);

		System.out.println(scanResultPlaceDetails.size());

		return scanResultPlaceDetails;

	}
	
	public List<PlaceDetails> getInterestedPlaceDetails(String userName) throws Exception {
		User us = null;
		try {
			us=userdaoImpl.getUserByUserName(userName);
		}
		catch(Exception e)
		{
			System.out.println(e.getMessage());
		}
		
		AmazonDynamoDB dynamoDB = dbClient.getDynamoDB();
		System.out.println("Interested  Places");
		DynamoDBMapper mapper = new DynamoDBMapper(dynamoDB);
		
		Map<String, AttributeValue> userValues = new HashMap<String, AttributeValue>();
		userValues.put(":TravelInterest", new AttributeValue().withS(us.getTravelInterest()));

		DynamoDBScanExpression scanExpression = new DynamoDBScanExpression()
				.withFilterExpression("contains(:TravelInterest, Category)").withExpressionAttributeValues(userValues);
		List<PlaceDetails> scanResultInterestedPlaceDetails = mapper.scan(PlaceDetails.class, scanExpression);

		System.out.println(scanResultInterestedPlaceDetails.size());

		return scanResultInterestedPlaceDetails;

	}
	
	public List<PlaceDetails> getAllPlacesDetails(String userName) throws Exception {
		System.out.println("All places");
		AmazonDynamoDB dynamoDB = dbClient.getDynamoDB();
		
		DynamoDBMapper mapper = new DynamoDBMapper(dynamoDB);

		DynamoDBScanExpression scan1Expression = new DynamoDBScanExpression();

		List<PlaceDetails> scanResultAllPlacesDetails = mapper.scan(PlaceDetails.class, scan1Expression);
		
		System.out.println(scanResultAllPlacesDetails.size());

		return scanResultAllPlacesDetails;

	}

	@Override
	public PlaceDetails getplaceDetailsByPlaceId(String requestId) {
		AmazonDynamoDB dynamoDB = dbClient.getDynamoDB();
		DynamoDBMapper mapper = new DynamoDBMapper(dynamoDB);
		PlaceDetails placeDetails = mapper.load(PlaceDetails.class, requestId);
		return placeDetails;
	}

}
