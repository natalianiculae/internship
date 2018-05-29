package com.natalia.internship.model;

public class StudentGrade {

	private Subject subject;
	private int grade;

	public StudentGrade(Subject subject, int grade) {
		this.subject = subject;
		this.grade = grade;
	}

	public Subject getSubject() {
		return subject;
	}

	public void setSubject(Subject subject) {
		this.subject = subject;
	}

	public int getGrade() {
		return grade;
	}

	public void setGrade(int grade) {
		this.grade = grade;
	}

	@Override
	public String toString() {
		final StringBuilder sb = new StringBuilder("StudentGrade{");
		sb.append("subject=").append(subject);
		sb.append(", grade=").append(grade);
		sb.append('}');
		return sb.toString();
	}
}
