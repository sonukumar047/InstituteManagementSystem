# Institute Management System Controllers

This repository contains the REST API controllers for the Institutel Management System, managing faculties, institutes, and students.

## Technologies Used

- Spring Boot
- Java
- JPA/Hibernate
- Spring Data JPA
- Spring Security 

## Controllers

### FacultyController

This controller manages faculty members of the institute.

**Endpoints:**

- `/faculty/registerFaculty` (POST): Registers a new faculty member.
- `/faculty/getAllFaculties` (GET): Retrieves all registered faculty members.
- `/faculty/getFacultyById/{facultyId}` (GET): Retrieves a faculty member by ID.
- `/faculty/updateFacultyById/{facultyId}` (PUT): Updates a faculty member's details.
- `/faculty/deleteFacultyById/{facultyId}` (DELETE): Deletes a faculty member.

**Authentication:**

- Faculty login is supported (see `InstituteController` for details).

### InstituteController

This controller manages institutes in the system.

**Endpoints:**

- `/institute/registerInstute` (POST): Registers a new institute.
- `/institute/getAllInstitutes` (GET): Retrieves all registered institutes.
- `/institute/getInstituteById/{instituteId}` (GET): Retrieves an institute by ID.
- `/institute/updateInstituteById/{instituteId}` (PUT): Updates an institute's details.
- `/institute/deleteInstituteById/{instituteId}` (DELETE): Deletes an institute.

**Authentication:**

- Institute login is supported (see `StudentController` for details).

### StudentController

This controller manages students enrolled in the institute.

**Endpoints:**

- `/student/registerStudent` (POST): Registers a new student.
- `/student/getAllStudents` (GET): Retrieves all registered students.
- `/student/getStudentById/{studentId}` (GET): Retrieves a student by ID.
- `/student/updateStudent/{studentId}` (PUT): Updates a student's details.
- `/student/deleteStudentById/{studentId}` (DELETE): Deletes a student.

**Authentication:**

- Student login is supported (see `FacultyController` and `InstituteController` for details on login implementation).

## Important Notes

- Replace placeholders (e.g., `{facultyId}`, `{instituteId}`, `{studentId}`) with actual values in API calls.
- Ensure you have the necessary dependencies and configurations set up in your Spring Boot project.
- Implement the `Authentication` class and login functionality according to your specific requirements.

