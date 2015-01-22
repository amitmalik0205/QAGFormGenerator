package com.qait.qag.formgenerator.generator;

import java.util.List;

import com.qait.qag.formgenerator.simpletemplate.domain.PageDetail;

public interface IFormGenerator {

	public void generateForm(long formId);
	
	public List<PageDetail> createPageDetails();
	
	public List<PageDetail> getPageDetailList();
}
