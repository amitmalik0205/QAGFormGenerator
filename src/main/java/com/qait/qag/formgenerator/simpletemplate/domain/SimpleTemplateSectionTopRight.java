package com.qait.qag.formgenerator.simpletemplate.domain;

import java.io.Serializable;

import javax.annotation.Generated;

import com.google.gson.annotations.Expose;

@Generated("org.jsonschema2pojo")
public class SimpleTemplateSectionTopRight implements Serializable {

	private static final long serialVersionUID = -1905656058496661706L;
	
	@Expose
	private String choices;
	
	@Expose
	private Integer digits;
	
	@Expose
	private String label;
	
	@Expose
	private String type;

	public String getChoices() {
		return choices;
	}

	public void setChoices(String choices) {
		this.choices = choices;
	}

	public Integer getDigits() {
		return digits;
	}

	public void setDigits(Integer digits) {
		this.digits = digits;
	}

	public String getLabel() {
		return label;
	}

	public void setLabel(String label) {
		this.label = label;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}
}
