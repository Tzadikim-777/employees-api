package com.invex.employees.infrastructure.adapter.out.persistence;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Repository;

import com.invex.employees.domain.model.Employee;
import com.invex.employees.domain.port.out.EmployeeRepositoryPort;

import lombok.RequiredArgsConstructor;

@Repository
@RequiredArgsConstructor
public class EmployeePersistenceAdapter implements EmployeeRepositoryPort {

    private final EmployeePersistenceMapperImpl employeePersistenceMapperImpl;
	
	private final EmployeeJpaReposotory employeeJpaReposotory;
	private final EmployeePersistenceMapper employeePersistenceMapper;
    
	@Override
	public List<Employee> findAll() {
		return employeePersistenceMapperImpl.toDomainList(
				employeeJpaReposotory.findAll()
				);
	}

	@Override
	public Optional<Employee> findById(Long id) {
		return employeeJpaReposotory.findById(
				id).map(employeePersistenceMapper::toDomain);
	}

	@Override
	public Employee save(Employee employee) {
		EmployeeEntity employeeEntity = employeePersistenceMapper.toEntity(employee);
		EmployeeEntity saved = employeeJpaReposotory.save(employeeEntity);				
		return employeePersistenceMapper.toDomain(saved);
	}

	@Override
	public List<Employee> saveAll(List<Employee> employees) {
		 List<EmployeeEntity> entityList = employeePersistenceMapper.toEntityList(employees);
		List<EmployeeEntity> saveAll = employeeJpaReposotory.saveAll(entityList);				
		return employeePersistenceMapper.toDomainList(saveAll);
	}

	@Override
	public void deleteById(Long id) {
		employeeJpaReposotory.deleteById(id);
		
	}

	@Override
	public List<Employee> searchByName(String name) {
		return employeePersistenceMapper.toDomainList(
				employeeJpaReposotory.findByNameContainingIgnoreCase(name));
	}

}
