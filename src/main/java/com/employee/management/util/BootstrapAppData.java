package com.employee.management.util;

import java.util.ArrayList;
import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.event.EventListener;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import com.employee.management.dao.EmployeeRepository;
import com.employee.management.dao.UserRepository;
import com.employee.management.model.Employee;
import com.employee.management.model.Role;
import com.employee.management.model.User;

import lombok.RequiredArgsConstructor;

@Configuration
@RequiredArgsConstructor
public class BootstrapAppData {

	private final UserRepository userRepository;
	private final EmployeeRepository employeeRepository;

	@Autowired
	private BCryptPasswordEncoder passwordEncoder;

	//@Transactional
	@EventListener(ApplicationReadyEvent.class)
	public void insertUsers(ApplicationReadyEvent event) {
		List<Role> roles1 = new ArrayList<>();
		roles1.add(Role.builder().name("ROLE_USER").build());

		List<Role> roles2 = new ArrayList<>();
		roles2.add(Role.builder().name("ROLE_USER").build());
		roles2.add(Role.builder().name("ROLE_ADMIN").build());

		User user1 = User.builder().username("USER").password(passwordEncoder.encode("secret"))
				.roles(roles1).build();
		this.userRepository.save(user1);

		User user2 = User.builder().username("ADMIN").password(passwordEncoder.encode("success"))
				.roles(roles2).build();
		this.userRepository.save(user2);

		Employee rasmita = Employee.builder().firstName("Rasmita").lastName("Das").email("rasmitadas@gmail.com").build();
		this.employeeRepository.save(rasmita);

		Employee sambit = Employee.builder().firstName("Sambit").lastName("Sahoo").email("ssahoo@gmail.com")
				.build();
		this.employeeRepository.save(sambit);

		Employee subhalaxmi = Employee.builder().firstName("Subhalaxmi").lastName("Sahoo").email("subhalaxmi@gmail.com").build();
		this.employeeRepository.save(subhalaxmi);
	}

}
