package com.qait.qag.formgenerator.simpletemplate.domain;

import java.io.Serializable;

import javax.annotation.Generated;

import com.google.gson.annotations.Expose;

@Generated("org.jsonschema2pojo")
public class SimpleTemplateFormSpec implements Serializable {

	private static final long serialVersionUID = 199881477510904969L;

	@Expose
	private Integer formCode;
	
	@Expose
	private SimpleTemplateQuestionSection questionSection;
	
	@Expose
	private SimpleTemplateSectionTopRight sections_topright = new SimpleTemplateSectionTopRight();

	public Integer getFormCode() {
		return formCode;
	}

	public void setFormCode(Integer formCode) {
		this.formCode = formCode;
	}

	public SimpleTemplateQuestionSection getQuestionSection() {
		return questionSection;
	}

	public void setQuestionSection(SimpleTemplateQuestionSection questionSection) {
		this.questionSection = questionSection;
	}

	public SimpleTemplateSectionTopRight getSections_topright() {
		return sections_topright;
	}

	public void setSections_topright(SimpleTemplateSectionTopRight sections_topright) {
		this.sections_topright = sections_topright;
	}
}
