package edu.sjsu.Seekers_Project.request;

import java.io.Serializable;

public class SignUpRequest implements Serializable {

	private static final long serialVersionUID = 1L;

	private String userId;
	private String name;
	private String userName;
	private String password;
	private String role;
	private String address;
	private String city;
	private String zipcode;
	private String mountains;
	private String beach;
	private String architecture;
	private String forest;
	public String getMountains() {
		return mountains;
	}

	public void setMountains(String mountains) {
		this.mountains = mountains;
	}

	public String getBeach() {
		return beach;
	}

	public void setBeach(String beach) {
		this.beach = beach;
	}

	public String getArchitecture() {
		return architecture;
	}

	public void setArchitecture(String architecture) {
		this.architecture = architecture;
	}

	public String getForest() {
		return forest;
	}

	public void setForest(String forest) {
		this.forest = forest;
	}

	


	public SignUpRequest() {
	}

	public SignUpRequest(String userId, String name, String userName, String password, String role, String address,
			String city, String zipcode, String mountains, String beach,String architecture, String forest  ) {
		super();
		this.userId = userId;
		this.name = name;
		this.userName = userName;
		this.password = password;
		this.role = role;
		this.address = address;
		this.city = city;
		this.zipcode = zipcode;
		this.mountains = mountains;
		this.beach = beach;
		this.architecture = architecture;
		this.forest = forest;
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

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
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
		return "SignUpRequest [userId=" + userId + ", name=" + name + ", userName=" + userName + ", password="
				+ password + ", role=" + role + ", address=" + address + ", city=" + city + ", zipcode=" + zipcode
				+ "]";
	}

}
