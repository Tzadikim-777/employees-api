package com.invex.employees.application.service;

import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.Optional;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import com.invex.employees.domain.model.Employee;
import com.invex.employees.domain.port.out.EmployeeRepositoryPort;

@ExtendWith(MockitoExtension.class)
public class EmployeeServiceTest {
	
	@Mock
	private EmployeeRepositoryPort repository;
	
	@InjectMocks
	private EmployeeService service;
	
	@Test
	void shouldFindEmployeeById() {
		Employee employee = new Employee();
		
		employee.setId(1L);
		employee.setName("Nelly");
		
		when(repository.findById(1L))
		.thenReturn(Optional.of(employee));
		
		Employee result = service.findById(1L);
		
		assertEquals(1L,result.getId());
		assertEquals("Nelly",result.getName());
		
		verify(repository).findById(1L);
		
	}

}
