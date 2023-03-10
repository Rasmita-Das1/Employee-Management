package com.employee.management.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import static org.springframework.http.HttpStatus.CREATED;
import static org.springframework.http.HttpStatus.NO_CONTENT;

import com.employee.management.model.User;
import com.employee.management.service.UserService;

import io.swagger.annotations.ApiOperation;

@RestController
@RequestMapping("/api/user")
public class UserController {
	
	@Autowired
	private UserService userService;

	@ApiOperation(value = "List all Users", notes = "This API allows you to list all users from the users table")
	@GetMapping
	public List<User> listAllUsers() {
		return userService.findAllUsers();
	}

	@ApiOperation(value = "Add Single User", notes = "This API allows you to add a single User to the users table")
	@PostMapping
	@ResponseStatus(CREATED)
	public User saveUser(@RequestBody User user) {
		return userService.saveUser(user);
	}

	@ApiOperation(value = "Update User", notes = "User is not fetched by their Id. Please specify correct User Id for updation")
	@PutMapping
	public User updateUser(@RequestBody User user) {
		return userService.updateUser(user);
	}

	@ApiOperation(value = "Delete User by Id", notes = "This API allows you to delete a User by Id from the users table")
	@DeleteMapping("/{userId}")
	@ResponseStatus(NO_CONTENT)
	public String deleteUserById(@PathVariable("userId") int id) {
		userService.deleteUserById(id);

		return "Deleted user with id : " + id;
	}

}
