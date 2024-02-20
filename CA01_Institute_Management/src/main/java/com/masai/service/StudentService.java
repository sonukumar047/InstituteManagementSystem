package com.masai.service;

import java.util.List;

import com.masai.model.Student;

public interface StudentService {
	
	public Student registerStudent(Student student);
	
	public List<Student> getAllStudent();
	
	public Student getStudentById(Long studentId);
	
	public Student updateStudentById(Long studentId, Student student);
	
	public String deleteStudentById(Long studentId);
}
