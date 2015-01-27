package com.qait.qag.formgenerator.generator;

import java.util.List;

import com.qait.qag.formgenerator.simpletemplate.domain.PageDetail;
import com.qait.qag.formgenerator.simpletemplate.dto.StudentFormDetailDto;

public interface IFormGenerator {

	public List<StudentFormDetailDto> generateForm(long formId);
	
	public List<PageDetail> createPageDetails();
	
	public List<PageDetail> getPageDetailList();
}
