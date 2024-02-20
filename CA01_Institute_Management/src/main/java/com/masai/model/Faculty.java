package com.masai.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonProperty.Access;

import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
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
public class Faculty {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long facultyId;
	
	@NotBlank(message = "Faculty Name can't be blabk")
	private String facultyName;
	
	@Email(message = "Email should be in proper format")
	private String email;
	
	@NotBlank(message = "Contact Number can't be blank")
	private String contactNumber;
	
	@JsonProperty(access = Access.READ_WRITE)
	@NotBlank(message = "Password can't be blank")
	private String password;
	
	@JsonIgnore
	@ManyToOne
	@JoinColumn(name = "institute_id")
	private Institute institute;
}
