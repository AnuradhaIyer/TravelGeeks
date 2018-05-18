package edu.sjsu.Seekers_Project.dao.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.amazonaws.services.dynamodbv2.AmazonDynamoDB;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBMapper;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBScanExpression;
import com.amazonaws.services.dynamodbv2.model.AttributeValue;

import edu.sjsu.Seekers_Project.dao.UserDao;
import edu.sjsu.Seekers_Project.model.User;

@Repository
public class UserDaoImpl implements UserDao {

	@Autowired
	DynamoDbClient dbClient;

	public User getUserByNameAndPassowrd(String userName, String password) {

		AmazonDynamoDB dynamoDB = dbClient.getDynamoDB();
		DynamoDBMapper mapper = new DynamoDBMapper(dynamoDB);

		Map<String, AttributeValue> userValues = new HashMap<String, AttributeValue>();
		userValues.put(":UserVal", new AttributeValue().withS(userName));
		userValues.put(":passwordVal", new AttributeValue().withS(password));

		DynamoDBScanExpression scanExpression = new DynamoDBScanExpression()
				.withFilterExpression("UserName = :UserVal and Password = :passwordVal")
				.withExpressionAttributeValues(userValues);

		List<User> scanResultUser = mapper.scan(User.class, scanExpression);
		if (scanResultUser.size() == 1)
			return scanResultUser.get(0);
		else
			return null;
	}
	
	@Override
	public User getUserById(String id) {

		AmazonDynamoDB dynamoDB = dbClient.getDynamoDB();
		DynamoDBMapper mapper = new DynamoDBMapper(dynamoDB);

		Map<String, AttributeValue> userValues = new HashMap<String, AttributeValue>();
		userValues.put(":userId", new AttributeValue().withS(id));

		DynamoDBScanExpression scanExpression = new DynamoDBScanExpression()
				.withFilterExpression("UserId = :userId")
				.withExpressionAttributeValues(userValues);

		List<User> scanResultUser = mapper.scan(User.class, scanExpression);
		if (scanResultUser.size() == 1)
			return scanResultUser.get(0);
		else
			return null;
	}

	@Override
	public void signUpUser(User newuser) {
		AmazonDynamoDB dynamoDB = dbClient.getDynamoDB();
		DynamoDBMapper mapper = new DynamoDBMapper(dynamoDB);
		mapper.save(newuser);
	}
	
	@Override
	public User getUserByUserName(String userName) throws Exception {

		AmazonDynamoDB dynamoDB = dbClient.getDynamoDB();
		DynamoDBMapper mapper = new DynamoDBMapper(dynamoDB);

		Map<String, AttributeValue> userValues = new HashMap<String, AttributeValue>();
		userValues.put(":UserVal", new AttributeValue().withS(userName));

		DynamoDBScanExpression scanExpression = new DynamoDBScanExpression().withFilterExpression("UserName = :UserVal")
				.withExpressionAttributeValues(userValues);

		List<User> scanResultUser = mapper.scan(User.class, scanExpression);

		if (scanResultUser.size() == 1)
			return scanResultUser.get(0);
		else
			return null;
	}
	
	@Override
	public List<User> getUserByTravelInterest(String travelInterest) throws Exception {

		AmazonDynamoDB dynamoDB = dbClient.getDynamoDB();
		DynamoDBMapper mapper = new DynamoDBMapper(dynamoDB);

		Map<String, AttributeValue> userValues = new HashMap<String, AttributeValue>();
		userValues.put(":TravelInterest", new AttributeValue().withS(travelInterest));

		DynamoDBScanExpression scanExpression = new DynamoDBScanExpression().withFilterExpression("contains(TravelInterest, :TravelInterest)")
				.withExpressionAttributeValues(userValues);

		List<User> scanResultUser = mapper.scan(User.class, scanExpression);
		return scanResultUser;
	}

}
