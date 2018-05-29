package com.natalia.internship;

import com.natalia.internship.model.Student;
import com.natalia.internship.model.StudentGrade;
import com.natalia.internship.model.Subject;
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
public class BaseRestController {
	
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
	
	@GetMapping("/students/{id}")
	public Student getStudent(@PathVariable int id) {
		Student student = studentTemplate.getStudent(id);
		if (student == null) {
			throw new NotFoundException("Student with id " + id + " not found");
		}
		return student;
	}	
	
	@DeleteMapping("/students/{id}")
	public void deleteStudent(@PathVariable int id) {
		studentTemplate.deleteStudent(id);
	}
	
	@PutMapping("/students/{id}")
	public void updateStudent(@PathVariable int id, @RequestBody Student student) {
		studentTemplate.updateStudent(id, student.getFirstName(), 
				student.getLastName(),
				student.getBirthDate()
		);
	}

	@GetMapping("/students/{id}/subjects")
	public List<StudentGrade> getStudentGrades(@PathVariable int id) {
		return gradeTemplate.getStudentGrades(id);
	}
	
	@GetMapping("/subjects")
	public List<Subject> getSubjects() {
		return subjectTemplate.getSubjects();
	}
	
	@PostMapping("/subjects")
	public void saveSubjects(@RequestBody Subject subject) {
		subjectTemplate.saveSubject(subject.getSubject());
	}

	@GetMapping("/subjects/{id}")
	public Subject getSubject(@PathVariable int id) {
		Subject subject = subjectTemplate.getSubject(id);
		if (subject == null) {
			throw new NotFoundException("Subject with id " + id + " not found");
		}
		return subject;
	}	
	
	@DeleteMapping("/subjects/{id}")
	public void deleteSubject(@PathVariable int id) {
		subjectTemplate.deleteSubject(id);
	}

	@PutMapping("/subjects/{id}")
	public void updateSubject(@PathVariable int id, @RequestBody Subject subject) {
		subjectTemplate.updateSubject(id, subject.getSubject());
	}

}
