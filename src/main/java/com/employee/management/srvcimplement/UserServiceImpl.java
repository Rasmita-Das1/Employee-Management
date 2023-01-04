package com.employee.management.srvcimplement;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import com.employee.management.dao.UserRepository;
import com.employee.management.model.User;
import com.employee.management.service.UserService;

@Service
public class UserServiceImpl implements UserService {

	@Autowired
	private UserRepository userRepository;
	
	@Autowired
	private BCryptPasswordEncoder bcryptEncoder;

	
	@Override
	public List<User> findAllUsers() {
		return userRepository.findAll();
	}

	
	@Override
	public User saveUser(User user) {
		user.setPassword(bcryptEncoder.encode(user.getPassword()));
		return userRepository.save(user);
	}

	
	@Override
	public User updateUser(User updatedUser) {
		User savedUser = userRepository.findById(updatedUser.getId()).get();

		savedUser.setUsername(updatedUser.getUsername());
		savedUser.setPassword(bcryptEncoder.encode(updatedUser.getPassword()));
		savedUser.setRoles(updatedUser.getRoles());

		return userRepository.save(savedUser);
	}

	
	@Override
	public void deleteUserById(int id) {
		userRepository.deleteById(id);
	}

}
