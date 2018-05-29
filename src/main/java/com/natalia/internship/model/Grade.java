package com.natalia.internship.model;

public class Grade {

	private int grade;

	public Grade() {
	}

	public Grade(int grade) {
		this.grade = grade;
	}

	public int getGrade() {
		return grade;
	}

	public void setGrade(int grade) {
		this.grade = grade;
	}

	@Override
	public String toString() {
		final StringBuilder sb = new StringBuilder("Grade{");
		sb.append("grade=").append(grade);
		sb.append('}');
		return sb.toString();
	}
}
