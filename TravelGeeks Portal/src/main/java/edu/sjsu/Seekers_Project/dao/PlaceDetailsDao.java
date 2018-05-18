package edu.sjsu.Seekers_Project.dao;

import java.util.List;

import edu.sjsu.Seekers_Project.model.PlaceDetails;

public interface PlaceDetailsDao {

	void upload(PlaceDetails placeDetails) throws Exception;

	List<PlaceDetails> getPlaceDetails(String userName) throws Exception;

	PlaceDetails getplaceDetailsByPlaceId(String requestId);

	List<PlaceDetails> getAllPlacesDetails(String userName) throws Exception;

	List<PlaceDetails> getInterestedPlaceDetails(String userName) throws Exception;
}
