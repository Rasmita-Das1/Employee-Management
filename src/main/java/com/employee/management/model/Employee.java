package com.employee.management.model;

import javax.persistence.*;
import lombok.*;

@Entity
@Table(name="employee")
@NoArgsConstructor
@AllArgsConstructor
@Getter @Setter
@ToString
public class Employee {

	private long emp_id;
	
	private String firstName;
	
	private String lastName;
	
	private String email;

}
