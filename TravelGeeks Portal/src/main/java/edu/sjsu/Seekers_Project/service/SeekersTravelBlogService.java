package edu.sjsu.Seekers_Project.service;

import java.util.List;

import javax.xml.bind.ValidationException;

import edu.sjsu.Seekers_Project.model.PlaceDetails;
import edu.sjsu.Seekers_Project.request.SignUpRequest;
import edu.sjsu.Seekers_Project.request.UploadRequest;
import edu.sjsu.Seekers_Project.response.PlaceDetailsResponse;
import edu.sjsu.Seekers_Project.response.GenericResponse;
import edu.sjsu.Seekers_Project.response.UserResponse;

public interface SeekersTravelBlogService {
  
	UserResponse login(String userName, String password) throws Exception;
	
	UserResponse signUp(SignUpRequest request) throws ValidationException;
	
	GenericResponse upload(UploadRequest request) throws ValidationException;
	
	PlaceDetailsResponse getPlaceDetails(String userName) throws Exception;

	PlaceDetailsResponse getAllPlacesDetails(String userName) throws Exception;

	PlaceDetailsResponse getInterestedPlaceDetails(String userName) throws Exception;
}
