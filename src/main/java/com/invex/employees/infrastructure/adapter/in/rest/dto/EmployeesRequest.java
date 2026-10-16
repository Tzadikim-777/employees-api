package com.invex.employees.infrastructure.adapter.in.rest.dto;

import java.util.List;

import javax.validation.Valid;
import javax.validation.constraints.NotEmpty;

import lombok.Data;

@Data
public class EmployeesRequest {

	@NotEmpty(message = "Employee lista cannot be empty")	
	private List<@Valid EmployeeRequest> employees;
}
