package edu.sjsu.Seekers_Project.dao;

import java.util.List;

import edu.sjsu.Seekers_Project.model.User;

public interface UserDao {

	User getUserByNameAndPassowrd(String userName, String password) throws Exception;

	void signUpUser(User user) throws Exception;

	User getUserById(String id);
	
	User getUserByUserName(String userName) throws Exception;

	List<User> getUserByTravelInterest(String role) throws Exception;
}
