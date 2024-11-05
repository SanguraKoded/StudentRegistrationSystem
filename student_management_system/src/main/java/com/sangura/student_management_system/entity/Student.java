package com.sangura.student_management_system.entity;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonManagedReference;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.validation.constraints.Digits;
import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
public class Student {
	
	@Id
	@Digits(integer=4, fraction=0)
	private int admissionNumber;
	
	private String name;
	
	private String email;
	
	private int age;
	
	@OneToMany(mappedBy = "student")
	@JsonManagedReference
	private List<Enrollment> enrolledCourses;

	public int getAdmissionNumber() {
		return admissionNumber;
	}

	public void setAdmissionNumber(int admissionNumber) {
		this.admissionNumber = admissionNumber;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public int getAge() {
		return age;
	}

	public void setAge(int age) {
		this.age = age;
	}

	public List<Enrollment> getEnrolledCourses() {
		return enrolledCourses;
	}

	public void setEnrolledCourses(List<Enrollment> enrollmentS) {
		this.enrolledCourses = enrolledCourses;
	}
	

}
