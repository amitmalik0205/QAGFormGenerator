package com.qait.qag.formgenerator.db.domain;

import java.util.List;

public class Form {

	private long formId;
	
	private int generatedFormId;
	
	private int noOfPages;
	
	private int clientId;
	
	private int templateId;
	
	private List<FormPageDetail> pageDetails;

	public long getFormId() {
		return formId;
	}

	public void setFormId(long formId) {
		this.formId = formId;
	}

	public int getGeneratedFormId() {
		return generatedFormId;
	}

	public void setGeneratedFormId(int generatedFormId) {
		this.generatedFormId = generatedFormId;
	}

	public int getNoOfPages() {
		return noOfPages;
	}

	public void setNoOfPages(int noOfPages) {
		this.noOfPages = noOfPages;
	}

	public int getClientId() {
		return clientId;
	}

	public void setClientId(int clientId) {
		this.clientId = clientId;
	}

	public int getTemplateId() {
		return templateId;
	}

	public void setTemplateId(int templateId) {
		this.templateId = templateId;
	}

	public List<FormPageDetail> getPageDetails() {
		return pageDetails;
	}

	public void setPageDetails(List<FormPageDetail> pageDetails) {
		this.pageDetails = pageDetails;
	}
}
