package com.assignment.employeeservice.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

@Entity
@Table(name = "employee") 
public class Employee {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotNull
    @Size(min = 3, max = 100)
    @Column(nullable = false)
    private String name;

    @NotNull
    @Column(nullable = false)
    private Integer age;

    @NotNull
    @Column(nullable = false)
    private Gender gender;

    @NotNull
    @Size(min = 2, max = 100)
    @Column(nullable = false)
    private String city;

    @NotNull
    @Size(min = 5, max = 10)
    @Column(name = "pin_code", nullable = false)
    private String pinCode;

    // Default constructor (required for JPA)
    public Employee() {}

    public Employee(String name, Integer age, Gender gender, String city, String pinCode) {
        this.name = name;
        this.age = age;
        this.gender = gender;
        this.city = city;
        this.pinCode = pinCode;
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

	public Integer getAge() {
		return age;
	}

	public void setAge(Integer age) {
		this.age = age;
	}

	public Gender getGender() {
		return gender;
	}

	public void setGender(Gender gender) {
		this.gender = gender;
	}

	public String getCity() {
		return city;
	}

	public void setCity(String city) {
		this.city = city;
	}

	public String getPinCode() {
		return pinCode;
	}

	public void setPinCode(String pinCode) {
		this.pinCode = pinCode;
	}


	public enum Gender {
        MALE, FEMALE, OTHER
    }
}

