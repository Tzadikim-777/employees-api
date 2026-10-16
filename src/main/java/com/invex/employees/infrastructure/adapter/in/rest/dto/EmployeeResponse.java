package com.invex.employees.infrastructure.adapter.in.rest.dto;

import java.time.LocalDate;
import java.time.LocalDateTime;

import com.fasterxml.jackson.annotation.JsonFormat;

import lombok.Data;
@Data
public class EmployeeResponse {
	
	private Long id;
    private String name;
    private String paternalSurname;
    private String maternalSurname;
    private Integer age;
    private String sex;
    @JsonFormat(pattern = "dd-MM-yyyy")
    private LocalDate birthdate;
    private String position;
    private LocalDateTime createDate;
    private Boolean active;

}
