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
import org.springframework.web.bind.annotation.RestController;

import com.sangura.student_management_system.entity.Enrollment;
import com.sangura.student_management_system.entity.Student;
import com.sangura.student_management_system.service.StudentService;

@RestController
@RequestMapping("/students")
public class StudentController {
	
	@Autowired
	StudentService studentService;
	
	@GetMapping
	public ResponseEntity<List<Student>> getAllStudents(){
		return ResponseEntity.ok(studentService.getAllStudents());
	}

	@GetMapping("/courses/{admissionNumber}")
	public ResponseEntity<List<Enrollment>> getAllStudentCourses(@PathVariable int admissionNumber){
		return ResponseEntity.ok(studentService.getAllStudentCourses(admissionNumber));
	}
	
	@PostMapping("/add")
	public ResponseEntity<Student> addStudent(@RequestBody Student student){
		Student newStudent = studentService.addStudent(student);
		return ResponseEntity.status(HttpStatus.CREATED).body(newStudent);
	}
	
	@GetMapping("/{admissionNumber}")
	public ResponseEntity<Student> getStudentDetailsById(@PathVariable int admissionNumber){
		return ResponseEntity.ok(studentService.getStudentDetailsById(admissionNumber));
	}
	
	@PutMapping("/update/{admissionNumber}")
	public ResponseEntity<Student> updateStudent(@PathVariable int admissionNumber, @RequestBody Student student){
		return ResponseEntity.ok(studentService.updateStudent(student));
	}
	
	@DeleteMapping("/delete/{admissionNumber}")
	public ResponseEntity<Void> deleteStudent(@PathVariable int admissionNumber) {
		studentService.deleteStudent(admissionNumber);
		return ResponseEntity.noContent().build();
	}
}
