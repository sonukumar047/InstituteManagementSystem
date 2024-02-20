package com.masai.model;

import java.util.ArrayList;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnore;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Institute {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long instituteId;
	
	@NotBlank(message = "Name can't be blank")
	private String instituteName;
	
	@NotBlank(message = "Location can't be blank")
	private String location;
	
	@Email(message = "Email should be in proper format")
	private String email;
	
	@JsonIgnore
	@OneToMany(mappedBy = "institute")
	private List<Faculty> faculties = new ArrayList<>();
	
	@JsonIgnore
	@OneToMany(mappedBy = "institute")
	private List<Student> students = new ArrayList<>();
	
	
}
