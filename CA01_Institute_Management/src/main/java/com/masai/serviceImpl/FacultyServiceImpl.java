package com.masai.serviceImpl;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.masai.exception.FacultyNotFoundException;
import com.masai.model.Faculty;
import com.masai.repository.FacultyRepository;
import com.masai.service.FacultyService;

@Service
public class FacultyServiceImpl implements FacultyService{
	
	@Autowired
	private FacultyRepository facultyRepository;
	
	@Autowired
	private PasswordEncoder passwordEncoder;

	@Override
	public Faculty registerFaculty(Faculty faculty) {
		try {
			faculty.setPassword(passwordEncoder.encode(faculty.getPassword()));
			return facultyRepository.save(faculty);
		} catch (Exception e) {
			throw new FacultyNotFoundException("Failed to register faculty: " + e.getMessage());
		}
	}

	@Override
	public List<Faculty> getAllFaculty() {
		try {
			return facultyRepository.findAll();
		} catch (Exception e) {
			throw new FacultyNotFoundException("Failed to find faculties: " + e.getMessage());
		}
	}

	@Override
	public Faculty getFacultyById(Long facultyId) {
	    try {
	        return facultyRepository.findById(facultyId)
	                .orElseThrow(() -> new FacultyNotFoundException("Faculty not found"));
	    } catch (Exception e) {
	        throw new FacultyNotFoundException("Faculty not found");
	    }
	}

	@Override
	public Faculty updateFacultyById(Long facultyId, Faculty faculty) {
		try {
			Faculty facultyById = getFacultyById(facultyId);
			facultyById.setFacultyName(faculty.getFacultyName());
			facultyById.setContactNumber(faculty.getContactNumber());
			facultyById.setEmail(faculty.getEmail());
			facultyById.setInstitute(faculty.getInstitute());
			
			return facultyRepository.save(facultyById);
		} catch (Exception e) {
			throw new FacultyNotFoundException("Failed to update faculty details: " + e.getMessage());
		}
	}

	@Override
	public String deleteFacultyById(Long facultyId) {
		try {
	        Optional<Faculty> faculty = facultyRepository.findById(facultyId);
	        if (faculty.isPresent()) {
	            facultyRepository.deleteById(facultyId);
	            return "Faculty deleted successfully";
	        } else {
	            throw new FacultyNotFoundException("Faculty not found");
	        }
	    } catch (Exception e) {
	        throw new FacultyNotFoundException("Faculty not found");
	    }
	}

}
