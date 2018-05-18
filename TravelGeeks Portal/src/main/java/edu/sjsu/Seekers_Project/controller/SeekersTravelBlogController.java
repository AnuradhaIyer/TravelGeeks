package edu.sjsu.Seekers_Project.controller;

import java.util.ArrayList;
import java.util.List;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import edu.sjsu.Seekers_Project.request.LoginRequest;
import edu.sjsu.Seekers_Project.request.SignUpRequest;
import edu.sjsu.Seekers_Project.request.UploadRequest;
import edu.sjsu.Seekers_Project.response.PlaceDetailsResponse;
import edu.sjsu.Seekers_Project.response.GenericResponse;
import edu.sjsu.Seekers_Project.response.UserResponse;
import edu.sjsu.Seekers_Project.service.SeekersTravelBlogService;

@Controller
public class SeekersTravelBlogController {

	private static final Logger LOG = Logger.getLogger(SeekersTravelBlogController.class);

	@Autowired
	SeekersTravelBlogService apiService;

	@RequestMapping(value = "/", method = RequestMethod.GET)
	public String startPage(ModelMap model) {
		return "index";
	}

	@RequestMapping(value = "/login", method = RequestMethod.POST, produces = "application/json")
	@ResponseBody
	public ResponseEntity<UserResponse> login(@RequestBody LoginRequest request) {
		ResponseEntity<UserResponse> responseEntity = null;
		UserResponse response = new UserResponse();
		try {
			response = apiService.login(request.getUserName(), request.getPassword());
			response.setStatusCode(HttpStatus.OK.toString());
			responseEntity = new ResponseEntity<>(response, HttpStatus.OK);
		} catch (Exception e) {
			LOG.error(e.getMessage());
			// response.setMessage("Invalid User Name and Password");
			responseEntity = new ResponseEntity<>(response, HttpStatus.EXPECTATION_FAILED);
		}
		return responseEntity;
	}

	@RequestMapping(value = "/signup", method = RequestMethod.POST, produces = "application/json")
	@ResponseBody
	public ResponseEntity<UserResponse> signup(@RequestBody SignUpRequest request) {
		ResponseEntity<UserResponse> responseEntity = null;
		UserResponse response = new UserResponse();
		try {
			response = apiService.signUp(request);
			response.setStatusCode(HttpStatus.OK.toString());
			responseEntity = new ResponseEntity<UserResponse>(response, HttpStatus.OK);
		} catch (Exception e) {
			LOG.error(e.getMessage());
			// response.setMessage("User already Exists");
			responseEntity = new ResponseEntity<UserResponse>(response, HttpStatus.EXPECTATION_FAILED);
		}
		return responseEntity;
	}
	
	@RequestMapping(value = "/upload", method = RequestMethod.POST, produces = "application/json")
	@ResponseBody
	public ResponseEntity<GenericResponse> upload(
	//		 @RequestBody UploadRequest request
			@RequestParam(value = "placeFile", required = true) MultipartFile placeFile,
			@RequestParam(value = "fileName", required = true) String fileName,
			@RequestParam(value = "userName", required = true) String userName,
			@RequestParam(value = "placeName", required = true) String placeName,
			@RequestParam(value = "placeDescription", required = false) String placeDescription,
			@RequestParam(value = "monthVisited", required = true) String monthVisited
			) {

		UploadRequest request = new UploadRequest();
		request.setPlaceFile(placeFile);
		request.setFileName(fileName);
		request.setUserName(userName);
		request.setPlaceName(placeName);
		request.setPlaceDescription(placeDescription);
		request.setMonthVisited(monthVisited);
		
		ResponseEntity<GenericResponse> responseEntity = null;
		GenericResponse response = new GenericResponse();
		try {
			response = apiService.upload(request);
			response.setMessage("Upload Successfull!!");
			response.setStatusCode(HttpStatus.OK.toString());
			responseEntity = new ResponseEntity<GenericResponse>(response, HttpStatus.OK);
		} catch (Exception e) {
			LOG.error(e.getMessage());
			 response.setMessage("Upload: something went wrong");
			 responseEntity = new ResponseEntity<GenericResponse>(response, HttpStatus.EXPECTATION_FAILED);
		}
		return responseEntity;

	}

	@RequestMapping(value = "/myuploadsList", method = RequestMethod.GET, produces = "application/json")
	@ResponseBody
	public ResponseEntity<PlaceDetailsResponse> getPlaceDetails(
			@RequestParam(value = "userName", required = true) String userName) {
		ResponseEntity<PlaceDetailsResponse> responseEntity = null;
		PlaceDetailsResponse response = new PlaceDetailsResponse();
		try {
			System.out.println("Controller Usrname:" + userName);
			response = apiService.getPlaceDetails(userName);
			response.setStatusCode(HttpStatus.OK.toString());
			responseEntity = new ResponseEntity<PlaceDetailsResponse>(response, HttpStatus.OK);
		} catch (Exception e) {
			LOG.error(e.getMessage());
			System.out.println(e.getMessage());
			 response.setMessage("Business List: Something went wrong");
			responseEntity = new ResponseEntity<PlaceDetailsResponse>(response, HttpStatus.EXPECTATION_FAILED);
		}
		return responseEntity;
	}
	
	
	@RequestMapping(value = "/alluploadslist", method = RequestMethod.GET, produces = "application/json")
	@ResponseBody
	public ResponseEntity<PlaceDetailsResponse> getAllPlacesDetails(
			@RequestParam(value = "userName", required = true) String userName) {
		ResponseEntity<PlaceDetailsResponse> responseEntity = null;
		PlaceDetailsResponse response = new PlaceDetailsResponse();
		try {
			System.out.println("Controller Usrname:" + userName);
			response = apiService.getAllPlacesDetails(userName);
			response.setStatusCode(HttpStatus.OK.toString());
			responseEntity = new ResponseEntity<PlaceDetailsResponse>(response, HttpStatus.OK);
		} catch (Exception e) {
			LOG.error(e.getMessage());
			System.out.println(e.getMessage());
			 response.setMessage("Business List: Something went wrong");
			responseEntity = new ResponseEntity<PlaceDetailsResponse>(response, HttpStatus.EXPECTATION_FAILED);
		}
		return responseEntity;
	}
	
	@RequestMapping(value = "/interestedPlacesList", method = RequestMethod.GET, produces = "application/json")
	@ResponseBody
	public ResponseEntity<PlaceDetailsResponse> getInterestedPlaceDetails(
			@RequestParam(value = "userName", required = true) String userName) {
		ResponseEntity<PlaceDetailsResponse> responseEntity = null;
		PlaceDetailsResponse response = new PlaceDetailsResponse();
		try {
			System.out.println("Controller Usrname:" + userName);
			response = apiService.getInterestedPlaceDetails(userName);
			response.setStatusCode(HttpStatus.OK.toString());
			responseEntity = new ResponseEntity<PlaceDetailsResponse>(response, HttpStatus.OK);
		} catch (Exception e) {
			LOG.error(e.getMessage());
			System.out.println(e.getMessage());
			 response.setMessage("Business List: Something went wrong");
			responseEntity = new ResponseEntity<PlaceDetailsResponse>(response, HttpStatus.EXPECTATION_FAILED);
		}
		return responseEntity;
	}
	
}
