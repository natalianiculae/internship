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

}