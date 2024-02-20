package com.masai.service;

import java.util.List;

import com.masai.model.Faculty;

public interface FacultyService {
	
	public Faculty registerFaculty(Faculty faculty);
	
	public List<Faculty> getAllFaculty();
	
	public Faculty getFacultyById(Long facultyId);
	
	public Faculty updateFacultyById(Long facultyId, Faculty faculty);
	
	public String deleteFacultyById(Long facultyId);
}
