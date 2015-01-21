package com.qait.qag.formgenerator.domain;

import java.util.List;

import com.qait.qag.formgenerator.simpletemplate.domain.SimpleTemplateQuestionChoice;

public class FormHashCode {
	
	private Integer templateId;
	
	private Integer clientId;
	
	List<SimpleTemplateQuestionChoice> question_opts;

	public Integer getTemplateId() {
		return templateId;
	}

	public void setTemplateId(Integer templateId) {
		this.templateId = templateId;
	}

	public Integer getClientId() {
		return clientId;
	}

	public void setClientId(Integer clientId) {
		this.clientId = clientId;
	}

	public List<SimpleTemplateQuestionChoice> getQuestion_opts() {
		return question_opts;
	}

	public void setQuestion_opts(List<SimpleTemplateQuestionChoice> question_opts) {
		this.question_opts = question_opts;
	}
}
