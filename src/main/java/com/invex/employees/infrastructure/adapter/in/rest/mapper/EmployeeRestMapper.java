package com.invex.employees.infrastructure.adapter.in.rest.mapper;

import java.util.List;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.ReportingPolicy;

import com.invex.employees.domain.model.Employee;
import com.invex.employees.infrastructure.adapter.in.rest.dto.EmployeeRequest;
import com.invex.employees.infrastructure.adapter.in.rest.dto.EmployeeResponse;

@Mapper(
		componentModel = "spring",
		unmappedTargetPolicy = ReportingPolicy.ERROR
		)
public interface EmployeeRestMapper {
	
	@Mapping(target="id",ignore = true)
	@Mapping(target="createDate",ignore = true)
	Employee toDomain(EmployeeRequest request);
	
	List<Employee> toDomainList(List<EmployeeRequest> requests);
	
	EmployeeResponse toResponse(Employee employee);
	
	List<EmployeeResponse> toResponseList(List<Employee> employees);

}
