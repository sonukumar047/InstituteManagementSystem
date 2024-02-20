package com.masai;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.boot.test.context.SpringBootTest;

import com.masai.exception.FacultyNotFoundException;
import com.masai.model.Faculty;
import com.masai.model.Institute;
import com.masai.model.Student;
import com.masai.repository.FacultyRepository;
import com.masai.repository.InstituteRepository;
import com.masai.repository.StudentRepository;
import com.masai.serviceImpl.FacultyServiceImpl;
import com.masai.serviceImpl.InstituteServiceImpl;
import com.masai.serviceImpl.StudentServiceImpl;

@SpringBootTest
class Ca01InstituteManagementApplicationTests {

	@Test
	void contextLoads() {
	}
	
	@Mock
    private FacultyRepository facultyRepository;

    @InjectMocks
    private FacultyServiceImpl facultyService;

    private List<Faculty> facultyList;

    @BeforeEach
    public void setup() {
        facultyList = new ArrayList<>();
        Faculty faculty1 = new Faculty();
        faculty1.setFacultyId(1L);
        faculty1.setFacultyName("John Doe");
        faculty1.setContactNumber("1234567890");
        faculty1.setEmail("john.doe@example.com");
        
        institute = new Institute();
        institute.setInstituteId(1L);
        institute.setInstituteName("Test Institute");
        institute.setEmail("test@test.com");
        institute.setLocation("Test Location");
        
        MockitoAnnotations.openMocks(this);

        facultyList.add(faculty1);
    }

    @Test
    public void testRegisterFaculty() {
        Faculty faculty = new Faculty();
        faculty.setFacultyId(1L);
        faculty.setFacultyName("John Doe");
        faculty.setContactNumber("1234567890");
        faculty.setEmail("john.doe@example.com");

        when(facultyRepository.save(any(Faculty.class))).thenReturn(faculty);

        Faculty registeredFaculty = facultyService.registerFaculty(faculty);
        assertThat(registeredFaculty).isNotNull();
        assertThat(registeredFaculty.getFacultyId()).isEqualTo(1L);
    }

    @Test
    public void testGetAllFaculty() {
        when(facultyRepository.findAll()).thenReturn(facultyList);

        List<Faculty> fetchedFacultyList = facultyService.getAllFaculty();
        assertThat(fetchedFacultyList).isNotNull();
        assertThat(fetchedFacultyList.size()).isEqualTo(1);
    }

    @Test
    public void testGetFacultyById() {
        Long facultyId = 1L;
        Faculty faculty = new Faculty();
        faculty.setFacultyId(facultyId);
        faculty.setFacultyName("John Doe");

        when(facultyRepository.findById(facultyId)).thenReturn(Optional.of(faculty));

        Faculty fetchedFaculty = facultyService.getFacultyById(facultyId);
        assertThat(fetchedFaculty).isNotNull();
        assertThat(fetchedFaculty.getFacultyId()).isEqualTo(facultyId);
    }

    @Test
    public void testUpdateFacultyById() {
        Long facultyId = 1L;
        Faculty faculty = new Faculty();
        faculty.setFacultyId(facultyId);
        faculty.setFacultyName("John Doe");

        when(facultyRepository.findById(facultyId)).thenReturn(Optional.of(faculty));
        when(facultyRepository.save(any(Faculty.class))).thenReturn(faculty);

        Faculty updatedFaculty = facultyService.updateFacultyById(facultyId, faculty);
        assertThat(updatedFaculty).isNotNull();
        assertThat(updatedFaculty.getFacultyId()).isEqualTo(facultyId);
    }

    @Test
    public void testDeleteFacultyById() {
        Long facultyId = 1L;
        when(facultyRepository.findById(facultyId)).thenReturn(Optional.of(new Faculty()));

        String result = facultyService.deleteFacultyById(facultyId);
        assertThat(result).isEqualTo("Faculty deleted successfully");
    }

    @Test
    public void testDeleteFacultyByIdFacultyNotFoundException() {
        Long facultyId = 1L;
        when(facultyRepository.findById(facultyId)).thenReturn(Optional.empty());

        try {
            facultyService.deleteFacultyById(facultyId);
        } catch (FacultyNotFoundException e) {
            assertThat(e.getMessage()).isEqualTo("Faculty not found");
        }
    }
    
    @Mock
    private InstituteRepository instituteRepository;

    @InjectMocks
    private InstituteServiceImpl instituteService;

    private Institute institute;


    @Test
    void testRegisterInstitute() {
        when(instituteRepository.save(any(Institute.class))).thenReturn(institute);

        Institute registeredInstitute = instituteService.registerInstitute(institute);

        assertThat(registeredInstitute).isNotNull();
        assertThat(registeredInstitute.getInstituteName()).isEqualTo(institute.getInstituteName());
        assertThat(registeredInstitute.getEmail()).isEqualTo(institute.getEmail());
        assertThat(registeredInstitute.getLocation()).isEqualTo(institute.getLocation());
    }

    @Test
    void testGetAllInstitutes() {
        List<Institute> institutes = new ArrayList<>();
        institutes.add(institute);
        when(instituteRepository.findAll()).thenReturn(institutes);

        List<Institute> allInstitutes = instituteService.getAllInstitutes();

        assertThat(allInstitutes).isNotEmpty();
        assertThat(allInstitutes.size()).isEqualTo(1);
        assertThat(allInstitutes.get(0)).isEqualTo(institute);
    }

    @Test
    void testGetInstituteById() {
        when(instituteRepository.findById(1L)).thenReturn(Optional.of(institute));

        Institute foundInstitute = instituteService.getInstituteById(1L);

        assertThat(foundInstitute).isNotNull();
        assertThat(foundInstitute).isEqualTo(institute);
    }

    @Test
    void testUpdateInstituteById() {
        Institute updatedInstitute = new Institute();
        updatedInstitute.setInstituteId(1L);
        updatedInstitute.setInstituteName("Updated Institute");
        updatedInstitute.setEmail("updated@test.com");
        updatedInstitute.setLocation("Updated Location");

        when(instituteRepository.findById(1L)).thenReturn(Optional.of(institute));
        when(instituteRepository.save(any(Institute.class))).thenReturn(updatedInstitute);

        Institute result = instituteService.updateInstituteById(1L, updatedInstitute);

        assertThat(result).isNotNull();
        assertThat(result.getInstituteName()).isEqualTo(updatedInstitute.getInstituteName());
        assertThat(result.getEmail()).isEqualTo(updatedInstitute.getEmail());
        assertThat(result.getLocation()).isEqualTo(updatedInstitute.getLocation());
    }

    @Test
    void testDeleteInstituteById() {
        when(instituteRepository.findById(1L)).thenReturn(Optional.of(institute));

        String result = instituteService.deleteInstituteById(1L);

        assertThat(result).isNotNull();
        assertThat(result).isEqualTo("Institute deleted successfully");
    }

    @Test
    void testDeleteInstituteByIdWhenInstituteNotFound() {
        when(instituteRepository.findById(1L)).thenReturn(Optional.empty());

        try {
            instituteService.deleteInstituteById(1L);
        } catch (Exception e) {
            assertThat(e).isInstanceOf(FacultyNotFoundException.class);
            assertThat(e.getMessage()).isEqualTo("Institute not found");
        }
    }
    
    @Mock
    private StudentRepository studentRepository;

    @InjectMocks
    private StudentServiceImpl studentService;

    @Test
    public void testRegisterStudent() {
        Student student = new Student();
        when(studentRepository.save(student)).thenReturn(student);

        Student registeredStudent = studentService.registerStudent(student);

        assertThat(registeredStudent).isSameAs(student);
    }

    @Test
    public void testGetAllStudent() {
        List<Student> students = new ArrayList<>();
        when(studentRepository.findAll()).thenReturn(students);

        List<Student> allStudents = studentService.getAllStudent();

        assertThat(allStudents).isSameAs(students);
    }

    @Test
    public void testGetStudentById() {
        Student student = new Student();
        student.setStudentId(1L);
        when(studentRepository.findById(1L)).thenReturn(Optional.of(student));

        Student foundStudent = studentService.getStudentById(1L);

        assertThat(foundStudent).isSameAs(student);
    }

    @Test
    public void testUpdateStudentById() {
        Student existingStudent = new Student();
        existingStudent.setStudentId(1L);
        existingStudent.setStudentName("John Doe");

        Student updatedStudent = new Student();
        updatedStudent.setStudentId(1L);
        updatedStudent.setStudentName("Jane Doe");

        when(studentRepository.findById(1L)).thenReturn(Optional.of(existingStudent));
        when(studentRepository.save(existingStudent)).thenReturn(updatedStudent);

        Student result = studentService.updateStudentById(1L, updatedStudent);

        assertThat(result.getStudentName()).isEqualTo("Jane Doe");
    }

    @Test
    public void testDeleteStudentById() {
        Student student = new Student();
        student.setStudentId(1L);

        when(studentRepository.findById(1L)).thenReturn(Optional.of(student));

        String result = studentService.deleteStudentById(1L);

        assertThat(result).isEqualTo("Student deleted successfully");
        verify(studentRepository, times(1)).deleteById(1L);
    }

    @Test
    public void testDeleteStudentById_StudentNotFound() {
        when(studentRepository.findById(1L)).thenReturn(Optional.empty());

        assertThrows(FacultyNotFoundException.class, () -> {
            studentService.deleteStudentById(1L);
        });
    }
}
