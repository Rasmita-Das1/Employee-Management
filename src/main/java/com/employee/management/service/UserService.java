package com.employee.management.service;

import java.util.List;

import com.employee.management.model.User;

public interface UserService {
	
	List<User> findAllUsers();

	User saveUser(User user);

	User updateUser(User user);

	void deleteUserById(int id);

}
