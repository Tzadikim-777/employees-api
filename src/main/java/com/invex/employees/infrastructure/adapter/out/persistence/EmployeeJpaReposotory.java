package com.invex.employees.infrastructure.adapter.out.persistence;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

public interface EmployeeJpaReposotory extends JpaRepository<EmployeeEntity, Long>{
	
	List<EmployeeEntity> findByNameContainingIgnoreCase(String name);

}
