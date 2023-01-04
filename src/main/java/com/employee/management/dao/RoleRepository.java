package com.employee.management.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.employee.management.model.Role;

@Repository
public interface RoleRepository extends JpaRepository<Role, Integer>{

}
