package com.natalia.internship.persistence;

import com.natalia.internship.model.Grade;
import com.natalia.internship.model.StudentGrade;
import com.natalia.internship.model.Subject;
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
			PreparedStatement statement = connection.prepareStatement(
					"SELECT * FROM subject inner join student_subject on student_subject.subject_id = subject.subject_id where student_subject.student_id = ?");
			statement.setInt(1, studentId);
			ResultSet rs = statement.executeQuery();
			List<StudentGrade> grades = new ArrayList<>();
			while (rs.next()) {
				int grade = rs.getInt("final_grade");

				int id = rs.getInt("subject_id");
				String subjectName = rs.getString("subject");
				Subject subject = new Subject(id, subjectName);

				StudentGrade studentGrade = new StudentGrade(subject, grade);
				grades.add(studentGrade);
			}
			return grades;
		} catch (SQLException e) {
			e.printStackTrace();
			throw new RuntimeException(e.getMessage(), e.getCause());
		}
	}

	public Grade getGrade(int studentId, int subjectId) {
		try {
			PreparedStatement statement = connection.prepareStatement(
					"SELECT final_grade FROM student_subject WHERE student_id = ? AND subject_id = ?");
			statement.setInt(1, studentId);
			statement.setInt(2, subjectId);
			ResultSet rs = statement.executeQuery();
			while (rs.next()) {
				Grade grade = new Grade(rs.getInt("final_grade"));
				return grade;
			}
			return null;
		} catch (SQLException e) {
			e.printStackTrace();
			throw new RuntimeException(e.getMessage(), e.getCause());
		}
	}

	public void updateGrade(int studentId, int subjectId, int grade) {
		try {
			PreparedStatement statement = connection.prepareStatement(
					"INSERT INTO student_subject (student_id, subject_id, final_grade) VALUES (?, ?, ?) ON CONFLICT (student_id, subject_id) DO UPDATE SET final_grade = excluded.final_grade;");
			statement.setInt(1, studentId);
			statement.setInt(2, subjectId);
			statement.setInt(3, grade);
			statement.execute();
		} catch (SQLException e) {
			e.printStackTrace();
			throw new RuntimeException(e.getMessage(), e.getCause());
		}
	}

	public void deleteGrade(int studentId, int subjectId) {
		try {
			PreparedStatement statement = connection
					.prepareStatement("DELETE  FROM student_subject WHERE student_id = ? AND subject_id = ?");
			statement.setInt(1, studentId);
			statement.setInt(2, subjectId);
			statement.execute();
		} catch (SQLException e) {
			e.printStackTrace();
			throw new RuntimeException(e.getMessage(), e.getCause());
		}
	}
}