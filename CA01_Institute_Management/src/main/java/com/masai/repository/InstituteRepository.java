package com.masai.repository;


import org.springframework.data.jpa.repository.JpaRepository;

import com.masai.model.Institute;

public interface InstituteRepository extends JpaRepository<Institute, Long> {
	
//	Optional<Institute> findByInstituteName(String instituteName);

}
