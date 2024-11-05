package com.sangura.student_management_system.service;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.sangura.student_management_system.entity.Course;
import com.sangura.student_management_system.entity.Enrollment;
import com.sangura.student_management_system.entity.Student;
import com.sangura.student_management_system.repo.EnrollmentRepo;

@Service
public class EnrollmentService {
	

	@Autowired
	CourseService courseService;
	
	@Autowired
	StudentService studentService;
	
	@Autowired
	EnrollmentRepo enrollmentRepo;
	
	
	public List<Enrollment> getAllEnrollmentDetails(){
		return enrollmentRepo.findAll();
	}
	
	public List<Student> getAllEnrolledStudents(){
		List<Enrollment> enrollments = enrollmentRepo.findAll();
		List<Student> students = enrollments.stream()
										.map(Enrollment::getStudent)
										.collect(Collectors.toList());
		return students;
	}
	
	public Enrollment updateGrade(int admissionNumber, int courseId, String grade) {
		Student student = studentService.getStudentDetailsById(admissionNumber);
		Course course = courseService.getCourseById(courseId);
		Enrollment enrollment = enrollmentRepo.findByStudentAndCourse(student, course);
		enrollment.setGrade(grade);
		return enrollmentRepo.save(enrollment);
	}
	
	public Enrollment updateEnrollment(int id, Enrollment updatedEnrollment) {
		Enrollment enrollment = enrollmentRepo.findById(id).orElseThrow(() -> new RuntimeException ("Given id is incorrect"));
		enrollment.setStudent(updatedEnrollment.getStudent());
		enrollment.setCourse(updatedEnrollment.getCourse());
		enrollment.setGrade(updatedEnrollment.getGrade());
		return enrollmentRepo.save(enrollment);
	}

	public List<Course> getAllCoursesStudentEnrolled(int admissionNumber){
		Student student = studentService.getStudentDetailsById(admissionNumber);
		List<Course> courses = student.getEnrolledCourses().stream()
											.map(Enrollment::getCourse)
											.collect(Collectors.toList());
		return courses;
	}
	
	public Enrollment enrollStudent(int admissionNumber, int courseId) {
		Student student = studentService.getStudentDetailsById(admissionNumber);
		Course course = courseService.getCourseById(courseId);
		Enrollment newEnrollment = new Enrollment();
		newEnrollment.setStudent(student);
		newEnrollment.setCourse(course);;
		return enrollmentRepo.save(newEnrollment);
	}
	
	public void unenrollStudent(int admissionNumber, int courseId) {
		Student student = studentService.getStudentDetailsById(admissionNumber);
		Course course = courseService.getCourseById(courseId);
		Enrollment enrollment = enrollmentRepo.findByStudentAndCourse(student, course);
		enrollmentRepo.delete(enrollment);
		
	}
	
	public Boolean checkEnrollmentStatus(int admissionNumber, int courseId) {
		Student student = studentService.getStudentDetailsById(admissionNumber);
		Course course = courseService.getCourseById(courseId);
		Enrollment enrollment = enrollmentRepo.findByStudentAndCourse(student, course);
		if (enrollment == null) {
			return false;
		}
		
		return true;
	}
	
	public int countNumberOfStudentsEnrolled(int courseId) {
		Course course = courseService.getCourseById(courseId);
		int studentCount = 0;
		for(Enrollment enrollment : course.getStudentsEnrolled()) {
			if(course.getStudentsEnrolled() != null) {
				studentCount++;
			}
			
		}
		return studentCount;
	}
	
	
	
	
	
	
	
}
