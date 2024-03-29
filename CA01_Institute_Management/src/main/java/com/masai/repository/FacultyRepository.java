package com.masai.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.masai.model.Faculty;

public interface FacultyRepository extends JpaRepository<Faculty, Long>{
	Optional<Faculty> findByEmail(String email);
}
