package com.natalia.internship.model;

import java.sql.Date;
import java.util.List;

public class Student {

	private int id;
	private String firstName;
	private String lastName;
	private Date birthDate;
	private List<SubjectGrade> grades;

	public Student() {
	}

	public Student(int id, String firstName, String lastName, Date birthDate,
			List<SubjectGrade> grades) {
		this.id = id;
		this.firstName = firstName;
		this.lastName = lastName;
		this.birthDate = birthDate;
		this.grades = grades;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public Date getBirthDate() {
		return birthDate;
	}

	public void setBirthDate(Date birthDate) {
		this.birthDate = birthDate;
	}

	public List<SubjectGrade> getGrades() {
		return grades;
	}

	public void setGrades(List<SubjectGrade> grades) {
		this.grades = grades;
	}

	@Override
	public String toString() {
		final StringBuilder sb = new StringBuilder("Student{");
		sb.append("id=").append(id);
		sb.append(", firstName='").append(firstName).append('\'');
		sb.append(", lastName='").append(lastName).append('\'');
		sb.append(", birthDate=").append(birthDate);
		sb.append(", grades=").append(grades);
		sb.append('}');
		return sb.toString();
	}
}