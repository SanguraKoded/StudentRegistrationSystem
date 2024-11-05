package com.sangura.student_management_system.repo;

import org.springframework.data.jpa.repository.JpaRepository;

import com.sangura.student_management_system.entity.Student;

public interface StudentRepo extends JpaRepository<Student, Integer>{

}
