package com.sangura.student_management_system.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
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

import com.sangura.student_management_system.entity.Course;
import com.sangura.student_management_system.entity.Enrollment;
import com.sangura.student_management_system.entity.Student;
import com.sangura.student_management_system.service.EnrollmentService;

@RestController
@RequestMapping("/enrollment")
public class EnrollmentController {
	
	@Autowired
	EnrollmentService enrollmentService;
	
	@GetMapping
	public ResponseEntity<List<Enrollment>> getAllEnrollmentDetails(){
		return ResponseEntity.ok(enrollmentService.getAllEnrollmentDetails());
	}
	
	@GetMapping("/students")
	public ResponseEntity<List<Student>> getAllEnrolledStudents(){
		return ResponseEntity.ok(enrollmentService.getAllEnrolledStudents());
	}
	
	@PutMapping("/updateGrade/{id}")
	public ResponseEntity<Enrollment> updateGrade(@PathVariable int id, @PathVariable int courseId, @RequestParam String grade ){
		Enrollment updatedEnrollment = enrollmentService.updateGrade(id, courseId, grade);
		return ResponseEntity.ok(updatedEnrollment);
	}
	@PutMapping ("/update/{id}")
	public ResponseEntity<Enrollment> updateEnrollment(@PathVariable int id, @RequestBody Enrollment enrollment){
		Enrollment updatedEnrollment = enrollmentService.updateEnrollment(id, enrollment);
		return ResponseEntity.ok(updatedEnrollment);
	}
	
	@GetMapping("/studentCourses/{id}")
	public ResponseEntity<List<Course>> getAllStudentCourses(@PathVariable int admissionNumber){
		return ResponseEntity.ok(enrollmentService.getAllCoursesStudentEnrolled(admissionNumber));
	}
	@PostMapping("/add")
	public ResponseEntity<Enrollment> enrollStudent(@RequestParam int admissionNumber, @RequestParam int courseId ){
		Enrollment newEnrollment = enrollmentService.enrollStudent(admissionNumber, courseId);
		return ResponseEntity.status(HttpStatus.CREATED).body(newEnrollment);
	}
	@DeleteMapping("/remove/{id}")
	public ResponseEntity<Void> unenrollStudent(@PathVariable int admissionNumber, @RequestParam int courseId){
		enrollmentService.unenrollStudent(admissionNumber, courseId);
		return ResponseEntity.noContent().build();
	}
	@GetMapping("/checkStatus/{id}")
	public ResponseEntity<Boolean> checkEnrollmentStatus(@PathVariable int admissionNumber, @RequestParam int courseId){
		return ResponseEntity.ok(enrollmentService.checkEnrollmentStatus(admissionNumber, courseId));
	}
	@GetMapping("/countStudents/{id}")
	public ResponseEntity<Integer> countNumberOfStudentsEnrolled(@PathVariable int courseId){
		return ResponseEntity.ok(enrollmentService.countNumberOfStudentsEnrolled(courseId));
	}
}
