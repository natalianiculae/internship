package com.natalia.internship.model;

public class SubjectGrade {

	private String firstName;
	private String lastName;
	private int grade;

	public SubjectGrade(String firstName, String lastName, int grade) {
		this.firstName = firstName;
		this.lastName = lastName;
		this.grade = grade;
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

	public int getGrade() {
		return grade;
	}

	public void setGrade(int grade) {
		this.grade = grade;
	}

	@Override
	public String toString() {
		final StringBuilder sb = new StringBuilder("SubjectGrade{");
		sb.append("firstName='").append(firstName).append('\'');
		sb.append(", lastName='").append(lastName).append('\'');
		sb.append(", grade=").append(grade);
		sb.append('}');
		return sb.toString();
	}
}
