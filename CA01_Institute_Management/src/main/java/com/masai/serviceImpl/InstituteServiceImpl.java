package com.masai.serviceImpl;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.masai.exception.FacultyNotFoundException;
import com.masai.exception.InstituteNotFoundException;
import com.masai.model.Institute;
import com.masai.repository.InstituteRepository;
import com.masai.service.InstituteService;

@Service
public class InstituteServiceImpl implements InstituteService{
	
	@Autowired
	private InstituteRepository instituteRepository;

	@Override
	public Institute registerInstitute(Institute institute) {
		try {
			return instituteRepository.save(institute);
		} catch (Exception e) {
			throw new InstituteNotFoundException("Failed to register institute: " + e.getMessage());
		}
	}

	@Override
	public List<Institute> getAllInstitutes() {
		try {
			return instituteRepository.findAll();
		} catch (Exception e) {
			throw new InstituteNotFoundException("Failed to find institutes: " + e.getMessage());
		}
	}

	@Override
	public Institute getInstituteById(Long instituteId) {
		try {
			return instituteRepository.findById(instituteId)
	                .orElseThrow(() -> new FacultyNotFoundException("Institute not found with id: " + instituteId));
		} catch (Exception e) {
			throw new InstituteNotFoundException("Failed to find institute: " + e.getMessage());
		}
	}

	@Override
	public Institute updateInstituteById(Long instituteId, Institute institute) {
		try {
			Institute instituteById = getInstituteById(instituteId);
			instituteById.setInstituteName(institute.getInstituteName());
			instituteById.setEmail(institute.getEmail());
			instituteById.setLocation(institute.getLocation());
			
			return instituteRepository.save(instituteById);
		} catch (Exception e) {
			throw new InstituteNotFoundException("Failed to institute institute details: " + e.getMessage());
		}
	}

	@Override
	public String deleteInstituteById(Long instituteId) {
		try {
	        Optional<Institute> institute = instituteRepository.findById(instituteId);
	        if (institute.isPresent()) {
	        	instituteRepository.deleteById(instituteId);
	            return "Institute deleted successfully";
	        } else {
	            throw new FacultyNotFoundException("Institute not found");
	        }
	    } catch (Exception e) {
	        throw new FacultyNotFoundException("Institute not found");
	    }
	}

}
