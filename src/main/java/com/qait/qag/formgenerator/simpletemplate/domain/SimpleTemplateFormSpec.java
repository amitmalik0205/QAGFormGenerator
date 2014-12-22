package com.qait.qag.formgenerator.simpletemplate.domain;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.annotation.Generated;

import com.google.gson.annotations.Expose;

@Generated("org.jsonschema2pojo")
public class SimpleTemplateFormSpec implements Serializable {

	private static final long serialVersionUID = 199881477510904969L;

	@Expose
	private Integer formCode;
	
	@Expose
	private List<SimpleTemplateSections> sections = new ArrayList<SimpleTemplateSections>();
	
	@Expose
	private List<SimpleTemplateSectionTopRight> sections_topright = new ArrayList<SimpleTemplateSectionTopRight>();

	public Integer getFormCode() {
		return formCode;
	}

	public void setFormCode(Integer formCode) {
		this.formCode = formCode;
	}

	public List<SimpleTemplateSections> getSections() {
		return sections;
	}

	public void setSections(List<SimpleTemplateSections> sections) {
		this.sections = sections;
	}

	public List<SimpleTemplateSectionTopRight> getSections_topright() {
		return sections_topright;
	}

	public void setSections_topright(
			List<SimpleTemplateSectionTopRight> sections_topright) {
		this.sections_topright = sections_topright;
	}
}
