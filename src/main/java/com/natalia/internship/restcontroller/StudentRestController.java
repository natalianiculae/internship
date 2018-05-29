package com.natalia.internship.restcontroller;

import com.natalia.internship.NotFoundException;
import com.natalia.internship.model.Grade;
import com.natalia.internship.model.Student;
import com.natalia.internship.model.StudentGrade;
import com.natalia.internship.persistence.GradeTemplate;
import com.natalia.internship.persistence.StudentTemplate;
import com.natalia.internship.persistence.SubjectTemplate;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class StudentRestController {

	@Autowired
	private StudentTemplate studentTemplate;

	@Autowired
	private SubjectTemplate subjectTemplate;

	@Autowired
	private GradeTemplate gradeTemplate;

	@GetMapping("/hello")
	public String hello() {
		return "Hello World!";
	}

	@GetMapping("/students")
	public List<Student> getStudents() {
		return studentTemplate.getStudents();
	}

	@PostMapping("/students")
	public void saveStudents(@RequestBody Student student) {
		studentTemplate.saveStudent(student.getFirstName(),
				student.getLastName(),
				student.getBirthDate()
		);
	}

	@GetMapping("/students/{studentId}")
	public Student getStudent(@PathVariable int studentId) {
		Student student = studentTemplate.getStudent(studentId);
		if (student == null) {
			throw new NotFoundException("Student with id " + studentId + " not found");
		}
		return student;
	}

	@DeleteMapping("/students/{studentId}")
	public void deleteStudent(@PathVariable int studentId) {
		studentTemplate.deleteStudent(studentId);
	}

	@PutMapping("/students/{studentId}")
	public void updateStudent(@PathVariable int studentId, @RequestBody Student student) {
		studentTemplate.updateStudent(studentId, student.getFirstName(),
				student.getLastName(),
				student.getBirthDate()
		);
	}

	@GetMapping("/students/{studentId}/subjects")
	public List<StudentGrade> getStudentGrades(@PathVariable int studentId) {
		return gradeTemplate.getStudentGrades(studentId);
	}

	@GetMapping("/students/{studentId}/subjects/{subjectId}")
	public Grade getGrade(@PathVariable int studentId, @PathVariable int subjectId) {
		Grade grade = gradeTemplate.getGrade(studentId, subjectId);
		if (grade == null) {
			throw new NotFoundException(
					"Grade for student with id " + studentId + " at subject with id " + subjectId + " not found");
		}
		return grade;
	}
	
	@DeleteMapping("/students/{studentId}/subjects/{subjectId}")
	public void deleteGrade(@PathVariable int studentId, @PathVariable int subjectId) {
		gradeTemplate.deleteGrade(studentId, subjectId);
	}

	@PutMapping("/students/{studentId}/subjects/{subjectId}")
	public void updateGrade(@PathVariable int studentId, @PathVariable int subjectId, @RequestBody Grade grade) {
		gradeTemplate.updateGrade(studentId, subjectId, grade.getGrade());
	}

}
