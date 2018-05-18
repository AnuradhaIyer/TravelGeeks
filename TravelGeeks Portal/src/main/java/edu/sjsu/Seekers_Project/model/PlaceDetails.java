package edu.sjsu.Seekers_Project.model;

import java.util.Date;
import java.util.List;

import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBAttribute;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBHashKey;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBMapperFieldModel.DynamoDBAttributeType;

import edu.sjsu.Seekers_Project.model.User;

import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBTable;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBTypeConverted;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBTypeConverter;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBTyped;

@DynamoDBTable(tableName = "Seekers_PlaceDetails")
public class PlaceDetails {
	private User user;
	private String placeId;
	private String fileName;
	private Date createdDate;
	private String monthVisited;
	private String placeName;
	private String placeDescription;
	private CategoryEnum category;
	private String thingsToDo;
	private List<String> usersUninterested;
	private List<String> usersInterested;

	public static enum CategoryEnum {
		Architecture, Mountains, Beach, Forest
	};

	@DynamoDBHashKey(attributeName = "PlaceId")
	public String getPlaceId() {
		return placeId;
	}

	public void setPlaceId(String placeId) {
		this.placeId = placeId;
	}

	@DynamoDBTyped(DynamoDBAttributeType.M)
	@DynamoDBAttribute(attributeName = "userDetails")
	// @DynamoDBTypeConverted(converter = User.class)
	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	@DynamoDBAttribute(attributeName = "FileName")
	public String getFileName() {
		return fileName;
	}

	public void setFileName(String fileName) {
		this.fileName = fileName;
	}

	@DynamoDBAttribute(attributeName = "CreatedDate")
	public Date getCreatedDate() {
		return createdDate;
	}

	public void setCreatedDate(Date createdDate) {
		this.createdDate = createdDate;
	}

	@DynamoDBAttribute(attributeName = "MonthVisited")
	public String getMonthVisited() {
		return monthVisited;
	}

	public void setMonthVisited(String monthVisited) {
		this.monthVisited = monthVisited;
	}

	@DynamoDBAttribute(attributeName = "PlaceName")
	public String getPlaceName() {
		return placeName;
	}

	public void setPlaceName(String placeName) {
		this.placeName = placeName;
	}

	@DynamoDBAttribute(attributeName = "ThingsToDo")
	public String getThingsToDo() {
		return thingsToDo;
	}

	public void setThingsToDo(String thingsToDo) {
		this.thingsToDo = thingsToDo;
	}

	@DynamoDBAttribute(attributeName="Category")
	@DynamoDBTyped(DynamoDBAttributeType.S)
	public CategoryEnum getCategory() {
		return category;
	}

	public void setCategory(CategoryEnum category) {
		this.category = category;
	}

	@DynamoDBAttribute(attributeName = "PlaceDescription")
	public String getPlaceDescription() {
		return placeDescription;
	}

	public void setPlaceDescription(String placeDescription) {
		this.placeDescription = placeDescription;
	}

	@DynamoDBTyped(DynamoDBAttributeType.L)
	@DynamoDBAttribute(attributeName = "UsersUninterested")
	public List<String> getUsersUninterested() {
		return usersUninterested;
	}

	public void setUsersUninterested(List<String> usersUninterested) {
		this.usersUninterested = usersUninterested;
	}

	@DynamoDBTyped(DynamoDBAttributeType.L)
	@DynamoDBAttribute(attributeName = "UsersInterested")
	public List<String> getUsersInterested() {
		return usersInterested;
	}

	public void setUsersInterested(List<String> usersInterested) {
		this.usersInterested = usersInterested;
	}

}
