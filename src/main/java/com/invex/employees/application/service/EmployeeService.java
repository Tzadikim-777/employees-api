package com.invex.employees.application.service;

import java.util.List;
import java.util.Optional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.invex.employees.domain.exception.EmployeeNotFoundException;
import com.invex.employees.domain.model.Employee;
import com.invex.employees.domain.port.in.EmployeeUseCase;
import com.invex.employees.domain.port.out.EmployeeRepositoryPort;

import lombok.RequiredArgsConstructor;

@Service
@Transactional
@RequiredArgsConstructor
public class EmployeeService implements EmployeeUseCase {

	private static final Logger log = LoggerFactory.getLogger(EmployeeService.class);
	
	private final EmployeeRepositoryPort repository;

	@Override
	public List<Employee> findAll() {
		return repository.findAll();
	}

	@Override
	@Transactional(readOnly = true)
	public Employee findById(Long id) {
		return repository.findById(id).
				orElseThrow(
				() -> new EmployeeNotFoundException(id));
	}

	@Override
	public Employee create(Employee employee) {
		employee.setId(null);
		employee.setCreateDate(null);
		
		if (employee.getActive() == null) {
			employee.setActive(true);
		}
		
		return repository.save(employee);
	}

	@Override
	public List<Employee> createAll(List<Employee> employees) {
		if (employees ==null || employees.isEmpty()) {
			throw new IllegalArgumentException("Employee list cannot be empty");			
		}
		
		for (Employee employee : employees) {
			if (employee == null) {
				throw new IllegalArgumentException("Employee cannot be null");
			}
			
			employee.setId(null);
			employee.setCreateDate(null);
			
			if (employee.getActive() == null) {
				employee.setActive(true);
			}
		}
		
		return repository.saveAll(employees);
	}

	@Override
	public Employee update(Long id, Employee employee) {
		Employee employeeBd = findById(id);
		
		employeeBd.setName(employee.getName());
		employeeBd.setPaternalSurname(employee.getPaternalSurname());
		employeeBd.setMaternalSurname(employee.getMaternalSurname());
		employeeBd.setAge(employee.getAge());
		employeeBd.setSex(employee.getSex());
		employeeBd.setBirthdate(employee.getBirthdate());
		employeeBd.setPosition(employee.getPosition());
		
		if (employee.getActive()!=null) {
			employeeBd.setActive(employee.getActive());			
		}
		
		return repository.save(employeeBd);
	}

	@Override
	public void deleteById(Long id) {
		
		if (repository.findById(id).isEmpty()) {
			throw new EmployeeNotFoundException(id);			
		}
		
		repository.deleteById(id);
	}

	@Override
	@Transactional(readOnly = true)
	public List<Employee> searchByName(String name) {
		if (name == null || name.isBlank()) {
			throw new IllegalArgumentException("name cannot be empty");
		}
		return repository.searchByName(name.trim());		
	}


}
