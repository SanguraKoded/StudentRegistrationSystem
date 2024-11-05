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

import com.sangura.student_management_system.entity.Course;
import com.sangura.student_management_system.service.CourseService;

@RestController
@RequestMapping("/courses")
public class CourseController {
	
	@Autowired
	CourseService courseService;

	@GetMapping
	public ResponseEntity<List<Course>> getAllCourses(){
		return ResponseEntity.ok(courseService.getAllCourses());
	}
	
	@GetMapping("/{id}")
	public ResponseEntity<Course> getCourseById(@PathVariable int id){
		return ResponseEntity.ok(courseService.getCourseById(id));
	}
	
	@PostMapping("/add")
	public ResponseEntity<Course> addCourse(@RequestBody Course course){
		Course newCourse = courseService.addCourse(course);
		return ResponseEntity.status(HttpStatus.CREATED).body(newCourse);
	}
	
	@PutMapping("/update/{id}")
	public ResponseEntity<Course> updateCourse(@PathVariable int id, @RequestBody Course course){
		return ResponseEntity.ok(courseService.updateCourse(id, course));
	}
	
	@DeleteMapping("/delete/{id}")
	public ResponseEntity<Void> deleteCourseById(@PathVariable int id){
		courseService.deleteCourseById(id);
		return ResponseEntity.noContent().build();
	}
}
