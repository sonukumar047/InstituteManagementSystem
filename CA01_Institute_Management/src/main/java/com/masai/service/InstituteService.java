package com.masai.service;

import java.util.List;

import com.masai.model.Institute;

public interface InstituteService {
	
	public Institute registerInstitute(Institute institute);
	
	public List<Institute> getAllInstitutes();
	
	public Institute getInstituteById(Long instituteId);
	
	public Institute updateInstituteById(Long instituteId, Institute institute);
	
	public String deleteInstituteById(Long instituteId);

}
