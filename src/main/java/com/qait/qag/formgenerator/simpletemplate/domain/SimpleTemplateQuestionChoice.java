package com.qait.qag.formgenerator.simpletemplate.domain;

import java.io.Serializable;

import javax.annotation.Generated;

import com.google.gson.annotations.Expose;

@Generated("org.jsonschema2pojo")
public class SimpleTemplateQuestionChoice implements Serializable {

	private static final long serialVersionUID = -7404162167322017211L;
	
	@Expose
	private String label;

	@Expose
	private String choices;

	public String getChoices() {
		return choices;
	}

	public void setChoices(String choices) {
		this.choices = choices;
	}

	public String getLabel() {
		return label;
	}

	public void setLabel(String label) {
		this.label = label;
	}	
}
