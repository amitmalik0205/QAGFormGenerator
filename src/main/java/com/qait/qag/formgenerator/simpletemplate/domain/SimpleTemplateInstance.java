package com.qait.qag.formgenerator.simpletemplate.domain;

import javax.annotation.Generated;

import com.google.gson.annotations.Expose;

@Generated("org.jsonschema2pojo")
public class SimpleTemplateInstance {

	@Expose
	private SimpleTemplateID ids;
	
	@Expose
	private String top;
	
	@Expose
	private String bottom;

	public SimpleTemplateID getIds() {
		return ids;
	}

	public void setIds(SimpleTemplateID ids) {
		this.ids = ids;
	}

	public String getTop() {
		return top;
	}

	public void setTop(String top) {
		this.top = top;
	}

	public String getBottom() {
		return bottom;
	}

	public void setBottom(String bottom) {
		this.bottom = bottom;
	}
}
