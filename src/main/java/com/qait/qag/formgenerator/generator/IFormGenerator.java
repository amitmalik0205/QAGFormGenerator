package com.qait.qag.formgenerator.generator;

import java.util.List;

import com.qait.qag.formgenerator.simpletemplate.domain.PageDetail;

public interface IFormGenerator {

	public void generateForm();
	
	public void createPageDetails();
	
	public List<PageDetail> getPageDetailList();
}
