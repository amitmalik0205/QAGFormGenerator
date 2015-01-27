package com.qait.qag.formgenerator.simpletemplate.dto;

import java.util.List;

public class StudentFormDetailDto {
	
	private String studentId;
	
	private List<FormPageDetailDto> formPageDetails;

	public String getStudentId() {
		return studentId;
	}

	public void setStudentId(String studentId) {
		this.studentId = studentId;
	}

	public List<FormPageDetailDto> getFormPageDetails() {
		return formPageDetails;
	}

	public void setFormPageDetails(List<FormPageDetailDto> formPageDetails) {
		this.formPageDetails = formPageDetails;
	}
}
