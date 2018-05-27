package com.natalia.internship.model;

public class SubjectGrade {
	private String subject;
	private int grade;

	public SubjectGrade(String subject, int grade) {
		this.subject = subject;
		this.grade = grade;
	}

	public String getSubject() {
		return subject;
	}

	public void setSubject(String subject) {
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
		final StringBuilder sb = new StringBuilder("SubjectGrade{");
		sb.append("subject='").append(subject).append('\'');
		sb.append(", grade=").append(grade);
		sb.append('}');
		return sb.toString();
	}
}