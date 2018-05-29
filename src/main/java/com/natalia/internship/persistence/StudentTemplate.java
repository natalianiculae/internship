package com.natalia.internship.persistence;

import com.natalia.internship.model.Student;
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
			ResultSet rs = connection.prepareStatement("SELECT * FROM student;").executeQuery();
			List<Student> students = new ArrayList<>();
			while (rs.next()) {
				int id = rs.getInt("student_id");
				String firstName = rs.getString("first_name");
				String lastName = rs.getString("last_name");
				Date birthDate = rs.getDate("birth_date");
				Student student = new Student(id, firstName, lastName, birthDate);
				students.add(student);
			}
			return students;
		} catch (SQLException e) {
			e.printStackTrace();
			throw new RuntimeException(e.getMessage(), e.getCause());
		}
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

	public Student getStudent(int studentId) {
		try {
			PreparedStatement statement = connection.prepareStatement("Select * from student where student_id = ?");
			statement.setInt(1, studentId);
			ResultSet rs = statement.executeQuery();
			while (rs.next()) {
				int id = rs.getInt("student_id");
				String firstName = rs.getString("first_name");
				String lastName = rs.getString("last_name");
				Date birthDate = rs.getDate("birth_date");
				return new Student(id, firstName, lastName, birthDate);
			}
			return null;
		} catch (SQLException e) {
			e.printStackTrace();
			throw new RuntimeException(e.getMessage(), e.getCause());
		}
	}

	public void updateStudent(int studentId, String firstName, String lastName, Date birthDate) {
		try {
			PreparedStatement statement = connection
					.prepareStatement("UPDATE student SET first_name = ?, last_name = ?, birth_date = ? WHERE student_id = ?");
			statement.setString(1, firstName);
			statement.setString(2, lastName);
			statement.setDate(3, birthDate);
			statement.setInt(4, studentId);
			statement.execute();
		} catch (SQLException e) {
			e.printStackTrace();
			throw new RuntimeException(e.getMessage(), e.getCause());
		}
	}

	public void deleteStudent(int id) {
		try {
			PreparedStatement statement = connection
					.prepareStatement("DELETE FROM student WHERE student_id = ?");
			statement.setInt(1, id);
			statement.execute();
		} catch (SQLException e) {
			e.printStackTrace();
			throw new RuntimeException(e.getMessage(), e.getCause());
		}
	}
}