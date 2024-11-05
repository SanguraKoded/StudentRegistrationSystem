package com.sangura.student_management_system.repo;

import org.springframework.data.jpa.repository.JpaRepository;

import com.sangura.student_management_system.entity.Course;

public interface CourseRepo extends JpaRepository<Course, Integer>{

}
