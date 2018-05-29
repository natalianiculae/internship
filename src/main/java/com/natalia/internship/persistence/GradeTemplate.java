package com.natalia.internship.persistence;

import com.natalia.internship.model.StudentGrade;
import com.natalia.internship.model.SubjectGrade;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class GradeTemplate {

	private Connection connection;

	@Autowired
	public GradeTemplate(Connection connection) {
		this.connection = connection;
	}

	public List<StudentGrade> getStudentGrades(int studentId) {
		try {
			PreparedStatement statement = connection.prepareStatement("SELECT * FROM subject inner join student_subject on student_subject.subject_id = subject.subject_id where student_subject.student_id = ?");
			statement.setInt(1, studentId);
			ResultSet rs = statement.executeQuery();
			List<StudentGrade> grades = new ArrayList<>();
			while (rs.next()) {
				int grade = rs.getInt("final_grade");
				String subject = rs.getString("subject");
				StudentGrade studentGrade = new StudentGrade(subject, grade);
				grades.add(studentGrade);
			}
			return grades;
		} catch (SQLException e) {
			e.printStackTrace();
			throw new RuntimeException(e.getMessage(), e.getCause());
		}
	}
	
	public List<SubjectGrade> getSubjectGrades(int subjectId) {
		try {
			PreparedStatement statement = connection.prepareStatement("SELECT * FROM student inner join student_subject on student_subject.student_id = subject.student_id where student_subject.subject_id = ?");
			statement.setInt(1, subjectId);
			ResultSet rs = statement.executeQuery();
			List<SubjectGrade> grades = new ArrayList<>();
			while (rs.next()) {
				int grade = rs.getInt("final_grade");
				String firstName = rs.getString("first_name");
				String secondName = rs.getString("second_name");
				SubjectGrade subjectGrade = new SubjectGrade(firstName, secondName, grade);
				grades.add(subjectGrade);
			}
			return grades;
		} catch (SQLException e) {
			e.printStackTrace();
			throw new RuntimeException(e.getMessage(), e.getCause());
		}
	}
}