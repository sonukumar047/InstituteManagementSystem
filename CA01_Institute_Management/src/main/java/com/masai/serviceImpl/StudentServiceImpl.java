package com.masai.serviceImpl;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.masai.exception.FacultyNotFoundException;
import com.masai.exception.StudentNotFoundException;
import com.masai.model.Student;
import com.masai.repository.StudentRepository;
import com.masai.service.StudentService;

@Service
public class StudentServiceImpl implements StudentService{
	
	@Autowired
	private StudentRepository studentRepository;
	
	@Autowired
	private PasswordEncoder passwordEncoder;

	@Override
	public Student registerStudent(Student student) {
		try {
			student.setPassword(passwordEncoder.encode(student.getPassword()));
			return studentRepository.save(student);
		} catch (Exception e) {
			throw new StudentNotFoundException("Failed to register student: " + e.getMessage());
		}
	}

	@Override
	public List<Student> getAllStudent() {
		try {
			return studentRepository.findAll();
		} catch (Exception e) {
			throw new StudentNotFoundException("Failed to find students: " + e.getMessage());
		}
	}

	@Override
	public Student getStudentById(Long studentId) {
		try {
	        return studentRepository.findById(studentId)
	                .orElseThrow(() -> new FacultyNotFoundException("Student not found with id: " + studentId));
	    } catch (Exception e) {
	        throw new FacultyNotFoundException("Failed to find student: " + e.getMessage());
	    }
	}

	@Override
	public Student updateStudentById(Long studentId, Student student) {
		try {
			Student studentById = getStudentById(studentId);
			studentById.setStudentName(student.getStudentName());
			studentById.setContactNumber(student.getContactNumber());
			studentById.setEmail(student.getEmail());
			studentById.setInstitute(student.getInstitute());
			
			return studentRepository.save(studentById);
		} catch (Exception e) {
			throw new FacultyNotFoundException("Failed to update student details: " + e.getMessage());
		}
	}

	@Override
	public String deleteStudentById(Long studentId) {
		try {
	        Optional<Student> student = studentRepository.findById(studentId);
	        if (student.isPresent()) {
	        	studentRepository.deleteById(studentId);
	            return "Student deleted successfully";
	        } else {
	            throw new FacultyNotFoundException("Student not found with id: " + studentId);
	        }
	    } catch (Exception e) {
	        throw new FacultyNotFoundException("Failed to delete student: " + e.getMessage());
	    }
	}

}
