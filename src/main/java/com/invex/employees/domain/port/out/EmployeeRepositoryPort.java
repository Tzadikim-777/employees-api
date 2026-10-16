package com.invex.employees.domain.port.out;

import java.util.List;
import java.util.Optional;

import com.invex.employees.domain.model.Employee;

public interface EmployeeRepositoryPort {
	
	List<Employee> findAll();
	
	Optional<Employee> findById(Long id);
	
	Employee save(Employee employee);
	
	List<Employee> saveAll(List<Employee> employees);
	
	void deleteById(Long id);
	
	List<Employee> searchByName(String name);
	

}
