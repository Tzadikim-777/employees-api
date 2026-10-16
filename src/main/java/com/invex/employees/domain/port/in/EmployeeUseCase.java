package com.invex.employees.domain.port.in;

import java.util.List;

import com.invex.employees.domain.model.Employee;

public interface EmployeeUseCase {
	
	List<Employee> findAll();
	
	Employee findById(Long id);
	
	Employee create(Employee employee);
	
	List<Employee> createAll(List<Employee> employees);

	Employee update(Long id, Employee employee);
	
	void deleteById(Long id);
	
	List<Employee> searchByName(String name);

}
