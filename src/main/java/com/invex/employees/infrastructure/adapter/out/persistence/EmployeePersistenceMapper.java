package com.invex.employees.infrastructure.adapter.out.persistence;

import java.util.List;

import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;

import com.invex.employees.domain.model.Employee;

@Mapper(componentModel = "spring",
		unmappedTargetPolicy = ReportingPolicy.ERROR)
public interface EmployeePersistenceMapper {
	
	Employee toDomain(EmployeeEntity entity);

	EmployeeEntity toEntity(Employee employee);
	
	List<Employee> toDomainList(List<EmployeeEntity> entities);

	List<EmployeeEntity> toEntityList(List<Employee> employees);

	
}
