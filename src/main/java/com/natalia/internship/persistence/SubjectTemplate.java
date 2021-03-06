package com.natalia.internship.persistence;

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
public class SubjectTemplate {

	private Connection connection;

	@Autowired
	public SubjectTemplate(Connection connection) {
		this.connection = connection;
	}

	public List<Subject> getSubjects() {
		try {
			ResultSet rs = connection.prepareStatement("Select * from subject;").executeQuery();
			List<Subject> subjects = new ArrayList<>();
			while (rs.next()) {
				int id = rs.getInt("subject_id");
				String subjectName = rs.getString("subject");
				
				Subject subject = new Subject(id, subjectName);
				subjects.add(subject);
			}
			return subjects;
		} catch (SQLException e) {
			e.printStackTrace();
			throw new RuntimeException(e.getMessage(), e.getCause());
		}
	}

	public void saveSubject(String subject) {
		try {
			PreparedStatement statement = connection
					.prepareStatement("INSERT INTO subject (subject) VALUES(?)");
			statement.setString(1, subject);
			statement.execute();
		} catch (SQLException e) {
			e.printStackTrace();
			throw new RuntimeException(e.getMessage(), e.getCause());
		}
	}

	public Subject getSubject(int subjectId) {
		try {
			PreparedStatement statement = connection.prepareStatement("SELECT * FROM subject WHERE subject_id = ?");
			statement.setInt(1, subjectId);
			ResultSet rs = statement.executeQuery();
			while (rs.next()) {
				int id = rs.getInt("subject_id");
				String subjectName = rs.getString("subject");

				return new Subject(id, subjectName);
			}
			return null;
		} catch (SQLException e) {
			e.printStackTrace();
			throw new RuntimeException(e.getMessage(), e.getCause());
		}
	}

	public void updateSubject(int studentId, String subject) {
		try {
			PreparedStatement statement = connection
					.prepareStatement("INSERT INTO subject (subject_id, subject) VALUES (?, ?) ON CONFLICT (subject_id) DO UPDATE SET subject = excluded.subject;");
			statement.setInt(1, studentId);
			statement.setString(2, subject);
			statement.execute();
		} catch (SQLException e) {
			e.printStackTrace();
			throw new RuntimeException(e.getMessage(), e.getCause());
		}
	}

	public void deleteSubject(int id) {
		try {
			PreparedStatement statement = connection
					.prepareStatement("DELETE FROM subject WHERE subject_id = ?");
			statement.setInt(1, id);
			statement.execute();
		} catch (SQLException e) {
			e.printStackTrace();
			throw new RuntimeException(e.getMessage(), e.getCause());
		}
	}

}