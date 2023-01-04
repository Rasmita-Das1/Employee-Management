package com.employee.management.srvcimplement;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.stereotype.Service;

import com.employee.management.dao.EmployeeRepository;
import com.employee.management.model.Employee;
import com.employee.management.service.EmployeeService;

@Service
public class EmployeeServiceImpl implements EmployeeService {

	@Autowired
	EmployeeRepository empRepo;
	
	@Override
	public List<Employee> findAllEmployees() {
		return empRepo.findAll();
	}

	@Override
	public Employee findEmployeeById(long id) {
		// check whether record exists
		Optional<Employee> result = empRepo.findById(id);
		Employee employee = null;

		if (result.isPresent()) {
			employee = result.get();
		} else {
			//incase we didn't find the employee
			throw new IllegalArgumentException("Did not find employee with id - " + id);
		}
		
		return employee;
	}

	@Override
	public Employee saveEmployee(Employee employee) {
		return empRepo.save(employee);
	}

	@Override
	public Employee updateEmployee(Employee updatedEmployee) {
		// check whether record exists
		Optional<Employee> result = empRepo.findById(updatedEmployee.getEmp_id());
		Employee savedEmployee = null;

		if (result.isPresent()) {
			savedEmployee = result.get();
		} else {
			// we didn't find the employee
			throw new IllegalArgumentException("Did not find employee with id - " + updatedEmployee.getEmp_id());
		}
		savedEmployee.setFirstName(updatedEmployee.getFirstName());
		savedEmployee.setLastName(updatedEmployee.getLastName());
		savedEmployee.setEmail(updatedEmployee.getEmail());

		return empRepo.save(savedEmployee);
	}

	@Override
	public void deleteEmployeeById(long id) {
		empRepo.deleteById(id);
	}

	@Override
	public List<Employee> findEmployeesByFirstName(String firstName) {
		return empRepo.findAllByFirstName(firstName);
	}

	@Override
	public List<Employee> findEmployeesCustomSortedByFirstName(Direction direction) {
		return empRepo.findAll(Sort.by(direction, "firstName"));
	}

	@Override
	public Page<Employee> findEmployeesCustomPagedAndCustomSortedByFirstName(int pageNum, int recordsNum,
			Direction direction) {
		Pageable pageable = PageRequest.of(pageNum, recordsNum, direction, "firstName");
		return empRepo.findAll(pageable);
	}

}
