package edu.sjsu.Seekers_Project.response;

public class UserResponse extends GenericResponse {

	private static final long serialVersionUID = 1L;
	private String userId;
	private String name;
	private String userName;
	private String role;
	private String address;
	private String city;
	private String zipcode;
	private String travelInterest;

	public UserResponse() {
	}

	public String getTravelInterest() {
		return travelInterest;
	}

	public void setTravelInterest(String travelInterest) {
		this.travelInterest = travelInterest;
	}

	public UserResponse(String userId, String name, String userName, String role, String address, String city,
			String zipcode, String travelInterest) {
		super();
		this.userId = userId;
		this.name = name;
		this.userName = userName;
		this.role = role;
		this.address = address;
		this.city = city;
		this.zipcode = zipcode;
		this.travelInterest=travelInterest;
	}

	public String getUserId() {
		return userId;
	}

	public void setUserId(String userId) {
		this.userId = userId;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public String getRole() {
		return role;
	}

	public void setRole(String role) {
		this.role = role;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public String getCity() {
		return city;
	}

	public void setCity(String city) {
		this.city = city;
	}

	public String getZipcode() {
		return zipcode;
	}

	public void setZipcode(String zipcode) {
		this.zipcode = zipcode;
	}

	@Override
	public String toString() {
		return "UserResponse [userId=" + userId + ", name=" + name + ", userName=" + userName + ", role=" + role
				+ ", address=" + address + ", city=" + city + ", zipcode=" + zipcode + "]";
	}

}
