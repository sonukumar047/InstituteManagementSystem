package com.masai.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.neo4j.Neo4jProperties.Authentication;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.masai.model.Student;
import com.masai.service.StudentService;

import jakarta.validation.Valid;

@RestController
@RequestMapping(value = "/student")
public class StudentController {

	@Autowired
	private StudentService studentService;
	
	@PostMapping("/registerStudent")
	public ResponseEntity<Student> registerStudent(@Valid @RequestBody Student student){
		return new ResponseEntity<Student>(studentService.registerStudent(student), HttpStatus.CREATED);
	}
	
	@GetMapping(value = "/getAllStudents")
	public ResponseEntity<List<Student>> getAllStudents(){
		return new ResponseEntity<List<Student>>(studentService.getAllStudent(), HttpStatus.ACCEPTED);
	}
	
	@GetMapping(value = "/getStudentById/{studentId}")
	public ResponseEntity<Student> getStudentById(@PathVariable("studentId") Long StudentId){
		return new ResponseEntity<Student>(studentService.getStudentById(StudentId), HttpStatus.ACCEPTED);
	}
	
	@PutMapping(value = "/updateStudent/{studentId}")
	public ResponseEntity<Student> updateStudentById(@PathVariable("studentId") Long instituteId, @RequestBody Student student){
		return new ResponseEntity<Student>(studentService.updateStudentById(instituteId, student), HttpStatus.ACCEPTED);
	}
	
	@DeleteMapping(value = "deleteStudentById/{studentId}")
	public ResponseEntity<String> deleteStudentById(@PathVariable("studentId") Long studentId){
		return new ResponseEntity<String>(studentService.deleteStudentById(studentId), HttpStatus.OK);
	}
	
	@PostMapping("/login")
	public ResponseEntity<?> login(Authentication authentication){
//		System.out.println("HIII");
		return new ResponseEntity<>("Logged In Successfully as a student!", HttpStatus.ACCEPTED);
	}

}
