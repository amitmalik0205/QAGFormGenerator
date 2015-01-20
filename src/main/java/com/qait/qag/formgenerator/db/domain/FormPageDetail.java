package com.qait.qag.formgenerator.db.domain;

public class FormPageDetail {
	
	private long formPageDetailId;
	
	private int pageNumber;
	
	private String questionRange;
	
	private String questionData;
	
	private long formId;

	public long getFormPageDetailId() {
		return formPageDetailId;
	}

	public void setFormPageDetailId(long formPageDetailId) {
		this.formPageDetailId = formPageDetailId;
	}

	public int getPageNumber() {
		return pageNumber;
	}

	public void setPageNumber(int pageNumber) {
		this.pageNumber = pageNumber;
	}

	public String getQuestionRange() {
		return questionRange;
	}

	public void setQuestionRange(String questionRange) {
		this.questionRange = questionRange;
	}

	public String getQuestionData() {
		return questionData;
	}

	public void setQuestionData(String questionData) {
		this.questionData = questionData;
	}

	public long getFormId() {
		return formId;
	}

	public void setFormId(long formId) {
		this.formId = formId;
	}
}
