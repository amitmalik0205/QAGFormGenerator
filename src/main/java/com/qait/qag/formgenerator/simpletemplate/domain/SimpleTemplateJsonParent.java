package com.qait.qag.formgenerator.simpletemplate.domain;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.annotation.Generated;

import com.google.gson.annotations.Expose;

@Generated("org.jsonschema2pojo")
public class SimpleTemplateJsonParent implements Serializable {

	private static final long serialVersionUID = -7047894885514528204L;
	
	@Expose
	private Integer templateId;
	
	@Expose
	private Integer clientId;

	@Expose
	private SimpleTemplateFormSpec formSpec;
	
	@Expose
	private List<SimpleTemplateInstance> instances = new ArrayList<SimpleTemplateInstance>();

	public SimpleTemplateFormSpec getFormSpec() {
		return formSpec;
	}

	public void setFormSpec(SimpleTemplateFormSpec formSpec) {
		this.formSpec = formSpec;
	}

	public List<SimpleTemplateInstance> getInstances() {
		return instances;
	}

	public void setInstances(List<SimpleTemplateInstance> instances) {
		this.instances = instances;
	}

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
}
