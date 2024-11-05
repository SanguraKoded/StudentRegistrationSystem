package com.sangura.student_management_system.repo;

import org.springframework.data.jpa.repository.JpaRepository;

import com.sangura.student_management_system.entity.Course;
import com.sangura.student_management_system.entity.Enrollment;
import com.sangura.student_management_system.entity.Student;

public interface EnrollmentRepo extends JpaRepository<Enrollment, Integer>{
	
	Enrollment findByStudentAndCourse (Student student, Course course);
}
