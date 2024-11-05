package com.sangura.student_management_system.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.sangura.student_management_system.entity.Course;
import com.sangura.student_management_system.repo.CourseRepo;

@Service
public class CourseService {
	
	@Autowired
	CourseRepo courseRepo;
	
	public List<Course> getAllCourses(){
		return courseRepo.findAll();
	}
	
	public Course getCourseById(int id) {
		return courseRepo.findById(id).orElseThrow(() -> new RuntimeException ("Given Id is incorrect"));
	}
	
	public Course addCourse(Course course) {
		return courseRepo.save(course);
	}
	
	public Course updateCourse(int id, Course course) {
		Course existingCourse = courseRepo.findById(id).orElseThrow(() -> new RuntimeException ("Given id is incorrect"));
		existingCourse.setTitle(course.getTitle());
		existingCourse.setStudentsEnrolled(course.getStudentsEnrolled());
	
		return courseRepo.save(existingCourse);
	}
	
	public void deleteCourseById(int id) {
		courseRepo.findById(id).orElseThrow(() -> new RuntimeException ("Given id is incorrect"));
		courseRepo.deleteById(id);
	}

}
