package com.masai.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.masai.model.Student;

public interface StudentRepository extends JpaRepository<Student, Long>{
	Optional<Student> findByEmail(String email);
}
