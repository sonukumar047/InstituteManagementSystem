package com.masai.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonProperty.Access;

import jakarta.persistence.AccessType;
import jakarta.persistence.Entity;

import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Student {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long studentId;
	
	@NotBlank(message = "Student name can't be blank")
	private String studentName;
	
	@Email(message = "Email should me in proper email format")
	private String email;
	
	@NotBlank(message = "Contact number can't be blank")
	private String contactNumber;
	
	@JsonProperty(access = Access.READ_WRITE)
	@NotBlank(message = "Password can't be blank")
	private String password;
	
	@JsonIgnore
	@ManyToOne
	@JoinColumn(name = "institute_id")
	private Institute institute;
	
	
}
