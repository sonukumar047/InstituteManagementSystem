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

import com.masai.model.Faculty;
import com.masai.service.FacultyService;

import jakarta.validation.Valid;

@RestController
@RequestMapping(value = "/faculty")
public class FacultyController {

	@Autowired
	private FacultyService facultyService;
	
	@PostMapping(value = "/registerFaculty")
	public ResponseEntity<Faculty> registerFaculty(@Valid @RequestBody Faculty faculty){
		return new ResponseEntity<Faculty>(facultyService.registerFaculty(faculty), HttpStatus.CREATED);
	}
	
	@GetMapping(value = "/getAllFaculties")
	public ResponseEntity<List<Faculty>> getAllFaculties(){
		return new ResponseEntity<List<Faculty>>(facultyService.getAllFaculty(), HttpStatus.ACCEPTED);
	}
	
	@GetMapping(value = "/getFacultyById/{facultyId}")
	public ResponseEntity<Faculty> getFacultyById(@PathVariable("facultyId") Long facultyId){
		return new ResponseEntity<Faculty>(facultyService.getFacultyById(facultyId), HttpStatus.ACCEPTED);
	}
	
	@PutMapping(value = "/updateFacultyById/{facultyId}")
	public ResponseEntity<Faculty> updateFacultyById(@PathVariable("facultyId") Long facultyId, @RequestBody Faculty faculty){
		return new ResponseEntity<Faculty>(facultyService.updateFacultyById(facultyId, faculty), HttpStatus.ACCEPTED);
	}
	
	@DeleteMapping(value = "/deleteFacultyById/{facultyId}")
	public ResponseEntity<String> deleteFacultyById(@PathVariable("facultyId") Long facultyId){
		return new ResponseEntity<String>(facultyService.deleteFacultyById(facultyId), HttpStatus.OK);
	}
	
	@PostMapping("/login")
	public ResponseEntity<?> login(Authentication authentication){
		
		return new ResponseEntity<>("Logged In Successfully as a faculty!", HttpStatus.ACCEPTED);
	}
}
