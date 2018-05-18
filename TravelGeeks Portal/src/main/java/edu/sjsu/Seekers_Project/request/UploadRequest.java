package edu.sjsu.Seekers_Project.request;

import java.io.File;
import java.io.Serializable;

import org.springframework.web.multipart.MultipartFile;

public class UploadRequest implements Serializable {

	private static final long serialVersionUID = 1L;
	private MultipartFile placeFile;
	private String fileName;
	private String userName;
	private String placeName;
	private String placeDescription;
	private String monthVisited;

	public UploadRequest() {
	}

	public UploadRequest(MultipartFile placeFile, String fileName, String userName, String placeName,
			String placeDescription) {
		super();
		this.placeFile = placeFile;
		this.fileName = fileName;
		this.userName = userName;
		this.placeName = placeName;
		this.placeDescription = placeDescription;

	}

	public MultipartFile getplaceFile() {
		return placeFile;
	}

	public void setPlaceFile(MultipartFile placeFile) {
		this.placeFile = placeFile;
	}

	public String getFileName() {
		return fileName;
	}

	public void setFileName(String fileName) {
		this.fileName = fileName;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public String getPlaceName() {
		return placeName;
	}

	public void setPlaceName(String placeName) {
		this.placeName = placeName;
	}

	public String getPlaceDescription() {
		return placeDescription;
	}

	public void setPlaceDescription(String placeDescription) {
		this.placeDescription = placeDescription;
	}

	public String getMonthVisited() {
		return monthVisited;
	}

	public void setMonthVisited(String monthVisited) {
		this.monthVisited = monthVisited;
	}

	@Override
	public String toString() {
		return "SignUpRequest [userName=" + userName + ", placeName=" + placeName + ", placeDescription="
				+ placeDescription + "]";
	}

}
