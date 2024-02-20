package com.masai.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
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

import com.masai.model.Institute;
import com.masai.service.InstituteService;

import jakarta.validation.Valid;

@RestController
@RequestMapping(value = "/institute")
public class InstituteController {
	
	@Autowired
	private InstituteService instituteService;
	
	@PostMapping(value = "/registerInstute")
	public ResponseEntity<Institute> registerInstitute(@Valid @RequestBody Institute instute){
		return new ResponseEntity<Institute>(instituteService.registerInstitute(instute), HttpStatus.CREATED);
	}
	
	@GetMapping(value = "/getAllInstitutes")
	public ResponseEntity<List<Institute>> getAllInstitutes(){
		return new ResponseEntity<List<Institute>>(instituteService.getAllInstitutes(), HttpStatus.ACCEPTED);
	}
	
	@GetMapping(value = "/getInstituteById/{instituteId}")
	public ResponseEntity<Institute> getInstituteById(@PathVariable("instituteId") Long instituteId){
		return new ResponseEntity<Institute>(instituteService.getInstituteById(instituteId), HttpStatus.ACCEPTED);
	}
	
	@PutMapping("/updateInstituteById/{instituteId}")
	public ResponseEntity<Institute> updateInstituteById(@PathVariable("instituteId") Long instituteId, @RequestBody Institute institute){
		return new ResponseEntity<Institute>(instituteService.updateInstituteById(instituteId, institute), HttpStatus.ACCEPTED);
	}
	
	@DeleteMapping("/deleteInstituteById/{instituteId}")
	public ResponseEntity<String> deleteInstituteById(@PathVariable("instituteId") Long instituteId){
		return new ResponseEntity<String>(instituteService.deleteInstituteById(instituteId), HttpStatus.OK);
	}

}
