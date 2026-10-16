package com.invex.employees.domain.exception;

public class EmployeeNotFoundException extends RuntimeException {

	private static final long serialVersionUID = 4241890846680085950L;

	public EmployeeNotFoundException(Long id) {
		super("Employee not found with id: "+id);
	}



}
