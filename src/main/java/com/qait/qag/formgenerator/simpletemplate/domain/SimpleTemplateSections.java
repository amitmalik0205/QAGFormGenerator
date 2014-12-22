package com.qait.qag.formgenerator.simpletemplate.domain;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.annotation.Generated;

import com.google.gson.annotations.Expose;

@Generated("org.jsonschema2pojo")
public class SimpleTemplateSections implements Serializable {

	private static final long serialVersionUID = -5206934179197797440L;
		
	@Expose
	private Integer count;
	
	@Expose
	private List<SimpleTemplateQuestionChoice> question_opts = new ArrayList<SimpleTemplateQuestionChoice>();

	public Integer getCount() {
		return count;
	}

	public void setCount(Integer count) {
		this.count = count;
	}

	public List<SimpleTemplateQuestionChoice> getQuestion_opts() {
		return question_opts;
	}

	public void setQuestion_opts(List<SimpleTemplateQuestionChoice> question_opts) {
		this.question_opts = question_opts;
	}
}