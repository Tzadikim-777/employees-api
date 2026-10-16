package com.invex.employees.infrastructure.adapter.in.rest.dto;

import java.time.LocalDate;
import java.time.LocalDateTime;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.PastOrPresent;
import javax.validation.constraints.Size;

import com.fasterxml.jackson.annotation.JsonFormat;

import lombok.Data;

@Data
public class EmployeeRequest {

	@NotBlank(message = "Name is required")
	@Size(max = 200)
	private String name;
	@NotBlank(message = "Paternal last name is required")
	@Size(max = 100)
	private String paternalSurname;
	@Size(max = 100)
	private String maternalSurname;
	@NotNull(message = "Age is required")
	@Min(value = 0, message = "Age cannot be negative")	
	private Integer age;
	@NotBlank(message = "Sex is required")
	@Size(max = 20)
	private String sex;
	@NotNull(message = "Birthdate is required")
	@PastOrPresent(message = "Birth cannot be in the future")	
	@JsonFormat(pattern = "dd-MM-yyyy")
	private LocalDate birthdate;
	@NotBlank(message = "Position is required")
	@Size(max = 100)
	private String position;
	
	private Boolean active;

}
