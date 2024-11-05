package com.sangura.student_management_system.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.sangura.student_management_system.entity.Enrollment;
import com.sangura.student_management_system.entity.Student;
import com.sangura.student_management_system.repo.StudentRepo;

@Service
public class StudentService {
	
	
	@Autowired	
	StudentRepo studentRepo;
	
	public List<Student> getAllStudents(){
		return studentRepo.findAll();
	}
	
	public List<Enrollment> getAllStudentCourses(int admissionNumber){
		Student student = studentRepo.findById(admissionNumber).orElseThrow(() -> new RuntimeException ("Given admission number is incorrect"));
		return student.getEnrolledCourses();
	}
	public Student addStudent(Student student) {
		return studentRepo.save(student);
	}
	
	public Student getStudentDetailsById(int admissionNumber) {
		return studentRepo.findById(admissionNumber).orElseThrow(() -> new RuntimeException ("Given admission number is incorrect"));
	}

	public Student updateStudent(Student student) {
		return studentRepo.save(student);
	}
	
	public void deleteStudent(int admissionNumber) {
		Student student = studentRepo.findById(admissionNumber).orElseThrow(() -> new RuntimeException ("Given admission Number is incorrect"));
		studentRepo.deleteById(admissionNumber);
	}
}
