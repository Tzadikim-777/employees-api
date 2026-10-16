package com.invex.employees.infrastructure.adapter.in.rest;

import java.util.List;

import javax.validation.Valid;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.invex.employees.domain.model.Employee;
import com.invex.employees.domain.port.in.EmployeeUseCase;
import com.invex.employees.infrastructure.adapter.in.rest.dto.EmployeeRequest;
import com.invex.employees.infrastructure.adapter.in.rest.dto.EmployeeResponse;
import com.invex.employees.infrastructure.adapter.in.rest.dto.EmployeesRequest;
import com.invex.employees.infrastructure.adapter.in.rest.mapper.EmployeeRestMapper;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;

@Tag(
		name = "Employees",
		description = "Employee management REST API"
)
@RestController
@RequestMapping("/employees")
@RequiredArgsConstructor
public class EmployeeController {

	private final EmployeeUseCase employeeUseCase;
	
	private final EmployeeRestMapper mapper;
	
	

	
	@Operation(
			summary = "Find all employees",
			description = "Returns a list of employees")
	@ApiResponses({ 
		@ApiResponse(responseCode = "200",
				description = "All employees successfully"),
			@ApiResponse(responseCode = "500",
			description = "Internal server error")

		})
	@GetMapping
	public ResponseEntity<List<EmployeeResponse>> findAll(){
		List<Employee> employees = employeeUseCase.findAll();
		return ResponseEntity.ok(mapper.toResponseList(employees));
	}
	
	@Operation(
			summary = "Find employee by Id",
			description = "Returns an employee using its unique Id")
	@ApiResponses({ 
		@ApiResponse(responseCode = "200",
				description = "Employee found"),
			@ApiResponse(responseCode = "404",
			description = "Employee not found"),
			@ApiResponse(responseCode = "400",
			description = "Invalid employee Id")
		})
	@GetMapping("/{id}")
	public ResponseEntity<EmployeeResponse> findById(@PathVariable Long id){
		Employee employee = employeeUseCase.findById(id);
		return  ResponseEntity.ok(mapper.toResponse(employee));
	}
	
	@Operation(
			summary = "Create employee",
			description = "Returns an employee created")
	@ApiResponses({ 
		@ApiResponse(responseCode = "201",
				description = "Employee created"),
			@ApiResponse(responseCode = "400",
			description = "Invalid employee data"),
			@ApiResponse(responseCode = "500",
			description = "Internal server error")
		})
	@PostMapping
	public ResponseEntity<EmployeeResponse> create(@Valid @RequestBody EmployeeRequest request ){
		Employee employee = mapper.toDomain(request);
		Employee employeed = employeeUseCase.create(employee);
		
		return ResponseEntity.status(HttpStatus.CREATED).body(mapper.toResponse(employeed));
	}
	
	@Operation(
			summary = "Update employee",
			description = "Update an employee by Id")
	@ApiResponses({ 
		@ApiResponse(responseCode = "200",
				description = "Employee updated"),
			@ApiResponse(responseCode = "400",
			description = "Invalid employee data"),
			@ApiResponse(responseCode = "404",
			description = "Employee not found")
		})
	@PutMapping("/{id}")
	public ResponseEntity<EmployeeResponse> update(@PathVariable Long id,
			@Valid @RequestBody EmployeeRequest request){
		Employee employee = mapper.toDomain(request);
		Employee update = employeeUseCase.update(id, employee);
		return  ResponseEntity.ok(mapper.toResponse(update));
	}
	
	@Operation(
			summary = "Delete employee",
			description = "Delete an employee by Id")
	@ApiResponses({ 
		@ApiResponse(responseCode = "204",
				description = "Employee deleted"),
			@ApiResponse(responseCode = "404",
			description = "Employee not found"),
			@ApiResponse(responseCode = "500",
			description = "Internal server error")
		})
	@DeleteMapping("/{id}")
	public ResponseEntity<Void> deleteById(@PathVariable Long id){
		employeeUseCase.deleteById(id);
		return  ResponseEntity.noContent().build();
	}
	
	@Operation(
			summary = "Search employees by name",
			description = "Searches employee by partial name")
	@ApiResponses({ 
		@ApiResponse(responseCode = "200",
				description = "Search executed"),
			@ApiResponse(responseCode = "400",
			description = "Invalid search parameter"),
			@ApiResponse(responseCode = "500",
			description = "Internal server error")
		})
	@GetMapping("/search")
	public ResponseEntity<List<EmployeeResponse>> searchByName(@RequestParam String name ){
		
		List<Employee> employees = employeeUseCase.searchByName(name);
		
		return ResponseEntity.ok(mapper.toResponseList(employees));
	}
	
	
	@Operation(
			summary = "Create multiple employees",
			description = "Creates multiple employees in a single transaction")
	@ApiResponses({ 
		@ApiResponse(responseCode = "201",
				description = "Employees created"),
			@ApiResponse(responseCode = "400",
			description = "Invalid employee data list"),
			@ApiResponse(responseCode = "500",
			description = "Internal server error")
		})
	@PostMapping("/createall")
	public ResponseEntity<List<EmployeeResponse>> createAll(
			@Valid @RequestBody EmployeesRequest request){
		 List<Employee> employees = mapper.toDomainList(request.getEmployees());
		List<Employee> employeesCreated = employeeUseCase.createAll(employees);
		return  ResponseEntity.status(HttpStatus.CREATED).body(mapper.toResponseList(employeesCreated));
	}

}
