package com.natalia.internship.persistence;

import com.natalia.internship.model.Student;
import com.natalia.internship.model.SubjectGrade;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.sql.Date;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class StudentTemplate {

	private Connection connection;

	@Autowired
	public StudentTemplate(Connection connection) {
		this.connection = connection;
	}


	public List<Student> getStudents() {
		try {
			ResultSet rs = connection.prepareStatement("Select * from student;").executeQuery();
			List<Student> students = new ArrayList<>();
			while (rs.next()) {
				int id = rs.getInt("student_id");
				String firstName = rs.getString("first_name");
				String lastName = rs.getString("last_name");
				Date birthDate = rs.getDate("birth_date");
				Student student = new Student(id, firstName, lastName, birthDate, getGrades(id));
				students.add(student);
			}
			return students;
		} catch (SQLException e) {
			e.printStackTrace();
			throw new RuntimeException(e.getMessage(), e.getCause());
		}
	}

	public List<SubjectGrade> getGrades(int studentId) throws SQLException {
		PreparedStatement statement = connection.prepareStatement("SELECT * FROM subject inner join student_subject on student_subject.subject_id = subject.subject_id where student_subject.student_id = ?");
		statement.setInt(1, studentId);
		ResultSet rs = statement.executeQuery();
		List<SubjectGrade> grades = new ArrayList<>();
		while (rs.next()) {
			int id = rs.getInt("student_id");
			int grade = rs.getInt("final_grade");
			String subject = rs.getString("subject");
			SubjectGrade subjectGrade = new SubjectGrade(subject, grade);
			grades.add(subjectGrade);
		}
		return grades;
	}

	public void saveStudent(String firstName, String lastName, Date birthDate) {
		try {
			PreparedStatement statement = connection
					.prepareStatement("INSERT INTO student (first_name, last_name, birth_date) VALUES(?, ?, ?)");
			statement.setString(1, firstName);
			statement.setString(2, lastName);
			statement.setDate(3, birthDate);
			statement.execute();
		} catch (SQLException e) {
			e.printStackTrace();
			throw new RuntimeException(e.getMessage(), e.getCause());
		}
	}
	
	
}