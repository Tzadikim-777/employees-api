package com.invex.employees.infrastructure.adapter.out.persistence;

import java.time.LocalDate;
import java.time.LocalDateTime;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.PrePersist;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

@Entity
@Table(name = "employees")
public class EmployeeEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "employee_seq_generator")
    @SequenceGenerator(name = "employee_seq_generator", sequenceName = "employees_seq", allocationSize = 1)
	private Long id;
    @Column(name = "name", nullable = false, length = 200)
    private String name;
    @Column(name = "paternal_surname", length = 100)
    private String paternalSurname;
    @Column(name = "maternal_surname", length = 100)
    private String maternalSurname;
    @Column(name = "age")
    private Integer age;
    @Column(name = "sex",length = 20)
    private String sex;
    @Column(name = "birthdate", nullable = false)
    private LocalDate birthdate;
    @Column(name = "position", nullable = false, length = 100)
    private String position;
    @Column(name = "create_date", nullable = false, updatable = false)
    private LocalDateTime createDate;
    @Column(name = "active")
    private Boolean active;
  
    @PrePersist
    public void prePersist() {
        if (createDate ==null) {
			createDate = LocalDateTime.now();
		}        
    }

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getPaternalSurname() {
		return paternalSurname;
	}

	public void setPaternalSurname(String paternalSurname) {
		this.paternalSurname = paternalSurname;
	}

	public String getMaternalSurname() {
		return maternalSurname;
	}

	public void setMaternalSurname(String maternalSurname) {
		this.maternalSurname = maternalSurname;
	}

	public Integer getAge() {
		return age;
	}

	public void setAge(Integer age) {
		this.age = age;
	}

	public String getSex() {
		return sex;
	}

	public void setSex(String sex) {
		this.sex = sex;
	}

	public LocalDate getBirthdate() {
		return birthdate;
	}

	public void setBirthdate(LocalDate birthdate) {
		this.birthdate = birthdate;
	}

	public String getPosition() {
		return position;
	}

	public void setPosition(String position) {
		this.position = position;
	}

	public LocalDateTime getCreateDate() {
		return createDate;
	}

	public void setCreateDate(LocalDateTime createDate) {
		this.createDate = createDate;
	}

	public Boolean getActive() {
		return active;
	}

	public void setActive(Boolean active) {
		this.active = active;
	}
    
    
   
}
