package edu.sjsu.Seekers_Project.response;

import java.util.Date;
import java.util.List;

import edu.sjsu.Seekers_Project.dto.PlaceDetailsDTO;
import edu.sjsu.Seekers_Project.model.User;
import edu.sjsu.Seekers_Project.model.PlaceDetails.CategoryEnum;

public class PlaceDetailsResponse extends GenericResponse {

	private static final long serialVersionUID = 1L;
	List<PlaceDetailsDTO> placeDetailsDTOList;

	public PlaceDetailsResponse() {
	}

	public List<PlaceDetailsDTO> getPlaceDetailsDTOList() {
		return placeDetailsDTOList;
	}

	public void setPlaceDetailsDTOList(List<PlaceDetailsDTO> placeDetailsDTOList) {
		this.placeDetailsDTOList = placeDetailsDTOList;
	}

}
