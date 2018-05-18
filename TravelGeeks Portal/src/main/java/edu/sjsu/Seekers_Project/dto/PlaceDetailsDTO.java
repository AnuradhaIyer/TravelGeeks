package edu.sjsu.Seekers_Project.dto;

import java.util.Comparator;
import java.util.Date;
import java.util.List;

import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBAttribute;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBHashKey;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBMapperFieldModel.DynamoDBAttributeType;

import edu.sjsu.Seekers_Project.model.User;
import edu.sjsu.Seekers_Project.model.PlaceDetails.CategoryEnum;

import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBTable;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBTypeConverted;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBTypeConverter;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBTyped;

public class PlaceDetailsDTO implements Comparable{
	private static final long serialVersionUID = 1L;
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
	
	

	public PlaceDetailsDTO() {
	}

	public PlaceDetailsDTO(User user, String placeId, String fileName, Date createdDate, String monthVisited,
			String placeName, String placeDescription, CategoryEnum category, String thingsToDo, List<String> usersUninterested,
			List<String> usersInterested) {
		super();
		user.setPassword(""); //no password to display on front end
		this.user = user;
		this.placeId = placeId; 
		this.fileName = fileName;
		this.createdDate = createdDate;
		this.monthVisited = monthVisited;
		this.placeName = placeName;
		this.placeDescription = placeDescription;
		this.category = category;
		this.thingsToDo = thingsToDo;
		this.usersUninterested = usersUninterested;
		this.usersInterested = usersInterested;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	public String getPlaceId() {
		return placeId;
	}

	public void setPlaceId(String placeId) {
		this.placeId = placeId;
	}

	public String getFileName() {
		return fileName;
	}

	public void setFileName(String fileName) {
		this.fileName = fileName;
	}

	public Date getCreatedDate() {
		return createdDate;
	}

	public void setCreatedDate(Date createdDate) {
		this.createdDate = createdDate;
	}

	public String getMonthVisited() {
		return monthVisited;
	}

	public void setMonthVisited(String monthVisited) {
		this.monthVisited = monthVisited;
	}

	public String getPlaceName() {
		return placeName;
	}

	public void setPlaceName(String placeName) {
		this.placeName = placeName;
	}


	public String getThingsToDo() {
		return thingsToDo;
	}

	public void setThingsToDo(String thingsToDo) {
		this.thingsToDo = thingsToDo;
	}

	public CategoryEnum getCategory() {
		return category;
	}

	public void setCategory(CategoryEnum category) {
		this.category = category;
	}

	public String getPlaceDescription() {
		return placeDescription;
	}

	public void setPlaceDescription(String placeDescription) {
		this.placeDescription = placeDescription;
	}

	public List<String> getUsersUninterested() {
		return usersUninterested;
	}

	public void setUsersUninterested(List<String> usersUninterested) {
		this.usersUninterested = usersUninterested;
	}

	public List<String> getUsersInterested() {
		return usersInterested;
	}

	public void setUsersInterested(List<String> usersInterested) {
		this.usersInterested = usersInterested;
	}

	@Override
	public int compareTo(Object o) {
		// TODO Auto-generated method stub
		return 0;
	}
}
