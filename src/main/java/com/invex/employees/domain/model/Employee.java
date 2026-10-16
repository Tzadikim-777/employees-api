package com.invex.employees.domain.model;

import java.time.LocalDate;
import java.time.LocalDateTime;

import lombok.Data;

@Data
public class Employee {
	
	private Long id;
    private String name;
    private String paternalSurname;
    private String maternalSurname;
    private Integer age;
    private String sex;
    private LocalDate birthdate;
    private String position;
    private LocalDateTime createDate;
    private Boolean active;

}
