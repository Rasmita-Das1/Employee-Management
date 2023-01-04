package com.employee.management.service;

import java.util.List;

import com.employee.management.model.Role;

public interface RoleService {

	List<Role> findAllRoles();

	Role saveRole(Role role);

	Role updateRole(Role role);

	void deleteRoleById(int id);
	
}
