package edu.sjsu.Seekers_Project.service.impl;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;
import java.util.List;
import java.util.UUID;

import javax.xml.bind.ValidationException;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.amazonaws.util.CollectionUtils;

import edu.sjsu.Seekers_Project.dao.PlaceDetailsDao;
import edu.sjsu.Seekers_Project.dao.UserDao;
import edu.sjsu.Seekers_Project.dto.PlaceDetailsDTO;
//import edu.sjsu.Seekers_Project.kinesis.events.MailerEvent;
import edu.sjsu.Seekers_Project.model.PlaceDetails;
import edu.sjsu.Seekers_Project.model.PlaceDetails.CategoryEnum;
import edu.sjsu.Seekers_Project.model.User;
import edu.sjsu.Seekers_Project.request.SignUpRequest;
import edu.sjsu.Seekers_Project.request.UploadRequest;
import edu.sjsu.Seekers_Project.response.PlaceDetailsResponse;
import edu.sjsu.Seekers_Project.response.GenericResponse;
import edu.sjsu.Seekers_Project.response.UserResponse;
import edu.sjsu.Seekers_Project.service.SeekersTravelBlogService;
import edu.sjsu.Seekers_Project.sqs.SQSEventProducer;
import edu.sjsu.Seekers_Project.sqs.events.EventType;
import edu.sjsu.Seekers_Project.sqs.events.MailerEvent;
import edu.sjsu.Seekers_Project.utils.CommonUtils;
import edu.sjsu.Seekers_Project.utils.RekognitionUtils;
import edu.sjsu.Seekers_Project.utils.S3Utils;

@Service
public class SeekersTravelBlogServiceImpl implements SeekersTravelBlogService {

	private static final Logger LOG = Logger.getLogger(SeekersTravelBlogServiceImpl.class);

	@Autowired
	UserDao userDao;

	@Autowired
	PlaceDetailsDao placeDetailsDao;

	@Autowired
	SQSEventProducer eventProducer;

	@Override
	public UserResponse login(String userName, String password) throws Exception {

		UserResponse response = new UserResponse();

		try {
			User user = userDao.getUserByNameAndPassowrd(userName, password);
			if (user == null) {
				throw new UsernameNotFoundException(userName);
			}
			response.setName(user.getName());
			response.setRole(user.getRole());
			response.setAddress(user.getAddress());
			response.setUserId(user.getUserId());
			response.setUserName(user.getUserName());
			response.setCity(user.getCity());
			response.setZipcode(user.getZipcode());
		
			return response;

		} catch (Exception e) {
			LOG.error(e);
		}
		return null;
	}

	@Override
	public UserResponse signUp(SignUpRequest request) throws ValidationException {

		try {
			User existinguser = userDao.getUserByNameAndPassowrd(request.getUserName(), request.getPassword());
			if (null != existinguser) {
				throw new ValidationException("User already exists");
			}
			String interest="";
			if(request.getArchitecture()!= null)
				interest=interest + "Architecture" + ";" ;
			if(request.getForest()!= null)
				interest=interest + "Forest" + ";" ;
			if(request.getBeach()!= null)
				interest=interest + "Beach" + ";" ;
			if(request.getMountains()!= null)
				interest=interest + "Mountains" + ";" ;
			
			User user = new User();
			user.setUserId(UUID.randomUUID().toString());
			user.setName(request.getName());
			user.setUserName(request.getUserName());
			user.setPassword(request.getPassword());
			user.setRole("Traveller");
			user.setAddress(request.getAddress());
			user.setCity(request.getCity());
			user.setZipcode(request.getZipcode());
			user.setTravelInterest(interest);
			//user.setTravelInterest("Mountains"+ ";"+ "Beach" + ";" + "Forest");
			System.out.println("******User******" + user.getTravelInterest());
			userDao.signUpUser(user);

			UserResponse response = new UserResponse();
			response.setUserId(user.getUserId());
			response.setName(user.getName());
			response.setUserName(user.getUserName());
			response.setRole(user.getRole());
			response.setAddress(user.getAddress());
			response.setCity(user.getCity());
			response.setZipcode(user.getZipcode());

			// send verify email
			MailerEvent event = new MailerEvent(user.getUserId(), "", "", EventType.VALIDATE_EMAIL);
			eventProducer.pushEvents(event);

			return response;
		} catch (Exception e) {
			LOG.error(e);
		}
		return null;
	}

	@Override
	public GenericResponse upload(UploadRequest request) throws ValidationException {
		try {
			User existinguser = userDao.getUserByUserName(request.getUserName());
			if (null == existinguser) {
				throw new ValidationException("User does not exist");
			}
			S3Utils s3Util = new S3Utils();
			int ret = s3Util.uploadKey(request.getUserName(), request.getplaceFile(), request.getFileName());

			if (ret == -1)
				throw new Exception("Unable to upload image to S3");

			RekognitionUtils rekognitionUtilsUtil = new RekognitionUtils();
			CategoryEnum cat = rekognitionUtilsUtil.getPlaceCategory(request.getUserName(), request.getFileName());
			System.out.println("cat: " + cat);
			GenericResponse response = new GenericResponse();
			PlaceDetails placeDetails = new PlaceDetails();
			placeDetails.setPlaceId(UUID.randomUUID().toString());
			placeDetails.setMonthVisited(request.getMonthVisited());
			placeDetails.setCategory(cat);
			placeDetails.setCreatedDate(new Date());
			placeDetails.setFileName(request.getFileName());
			placeDetails.setThingsToDo("test");
			placeDetails.setPlaceName(request.getPlaceName());
			placeDetails.setPlaceDescription(request.getPlaceDescription());
			placeDetails.setUser(existinguser);
			placeDetails.setUsersUninterested(new ArrayList<String>());
			placeDetails.setUsersInterested(new ArrayList<String>());

			placeDetailsDao.upload(placeDetails);
			
			//send notifications
			MailerEvent event = new MailerEvent(placeDetails.getPlaceId(), cat.toString(), placeDetails.getPlaceName(), EventType.NOTIFY_PICUPLOAD);
			eventProducer.pushEvents(event);
			
			return response;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;

	}

	@Override
	public PlaceDetailsResponse getPlaceDetails(String userName) throws Exception {

		PlaceDetailsResponse response = new PlaceDetailsResponse();
		List<PlaceDetailsDTO> placeDetailsDTOLists = new ArrayList<PlaceDetailsDTO>();
		List<PlaceDetails> placeDetailsLists = placeDetailsDao.getPlaceDetails(userName);

		String cloudFrontURL = CommonUtils.FILE_PROPERTIES.get("cloudFrontURL");

		if (!CollectionUtils.isNullOrEmpty(placeDetailsLists)) {
			for (PlaceDetails fd : placeDetailsLists) {
				placeDetailsDTOLists.add(new PlaceDetailsDTO(fd.getUser(), fd.getPlaceId(),
						cloudFrontURL + fd.getUser().getUserName() + "/" + fd.getFileName(), fd.getCreatedDate(),
						fd.getMonthVisited(), fd.getPlaceName(), fd.getPlaceDescription(), fd.getCategory(), fd.getThingsToDo(),
						fd.getUsersUninterested(), fd.getUsersInterested()));

			}
			Collections.sort(placeDetailsDTOLists, Collections.reverseOrder());
		}
		response.setPlaceDetailsDTOList(placeDetailsDTOLists);

		return response;
	}
	
	@Override
	public PlaceDetailsResponse getInterestedPlaceDetails(String userName) throws Exception {

		PlaceDetailsResponse response = new PlaceDetailsResponse();
		List<PlaceDetailsDTO> placeDetailsDTOLists = new ArrayList<PlaceDetailsDTO>();
		List<PlaceDetails> placeDetailsLists = placeDetailsDao.getInterestedPlaceDetails(userName);

		String cloudFrontURL = CommonUtils.FILE_PROPERTIES.get("cloudFrontURL");

		if (!CollectionUtils.isNullOrEmpty(placeDetailsLists)) {
			for (PlaceDetails fd : placeDetailsLists) {
				placeDetailsDTOLists.add(new PlaceDetailsDTO(fd.getUser(), fd.getPlaceId(),
						cloudFrontURL + fd.getUser().getUserName() + "/" + fd.getFileName(), fd.getCreatedDate(),
						fd.getMonthVisited(), fd.getPlaceName(), fd.getPlaceDescription(), fd.getCategory(), fd.getThingsToDo(),
						fd.getUsersUninterested(), fd.getUsersInterested()));

			}
			Collections.sort(placeDetailsDTOLists, Collections.reverseOrder());
		}
		response.setPlaceDetailsDTOList(placeDetailsDTOLists);

		return response;
	}
	
	@Override
	public PlaceDetailsResponse getAllPlacesDetails(String userName) throws Exception {

		PlaceDetailsResponse response = new PlaceDetailsResponse();
		List<PlaceDetailsDTO> allPlacesDetailsDTOList = new ArrayList<PlaceDetailsDTO>();
		List<PlaceDetails> allPlacesDetailsList = placeDetailsDao.getAllPlacesDetails(userName);

		String cloudFrontURL = CommonUtils.FILE_PROPERTIES.get("cloudFrontURL");

		if (!CollectionUtils.isNullOrEmpty(allPlacesDetailsList)) {
			for (PlaceDetails fd : allPlacesDetailsList) {
				allPlacesDetailsDTOList.add(new PlaceDetailsDTO(fd.getUser(), fd.getPlaceId(),
						cloudFrontURL + fd.getUser().getUserName() + "/" + fd.getFileName(), fd.getCreatedDate(),
						fd.getMonthVisited(), fd.getPlaceName(), fd.getPlaceDescription(), fd.getCategory(), fd.getThingsToDo(),
						fd.getUsersUninterested(), fd.getUsersInterested()));

			}
			Collections.sort(allPlacesDetailsDTOList, Collections.reverseOrder());
		}
		response.setPlaceDetailsDTOList(allPlacesDetailsDTOList);
		System.out.println(allPlacesDetailsDTOList.get(0));
		return response;
	}
}
