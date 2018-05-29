package com.natalia.internship.restcontroller;

import com.natalia.internship.NotFoundException;
import com.natalia.internship.model.Grade;
import com.natalia.internship.model.StudentGrade;
import com.natalia.internship.persistence.GradeTemplate;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class GradeRestController {

	@Autowired
	private GradeTemplate gradeTemplate;

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
