package com.qait.qag.formgenerator.simpletemplate.generator;

import java.util.ArrayList;
import java.util.List;

import org.apache.log4j.Logger;

import com.google.gson.Gson;
import com.qait.qag.formgenerator.common.dao.FormDaoImpl;
import com.qait.qag.formgenerator.common.dao.IFormDao;
import com.qait.qag.formgenerator.db.domain.Form;
import com.qait.qag.formgenerator.db.domain.FormPageDetail;
import com.qait.qag.formgenerator.domain.FormHashCode;
import com.qait.qag.formgenerator.generator.IFormGenerator;
import com.qait.qag.formgenerator.generator.ITemplateFrontController;
import com.qait.qag.formgenerator.simpletemplate.domain.ColumnDetail;
import com.qait.qag.formgenerator.simpletemplate.domain.PageDetail;
import com.qait.qag.formgenerator.simpletemplate.domain.SimpleTemplateJsonParent;
import com.qait.qag.formgenerator.simpletemplate.domain.SimpleTemplateQuestionChoice;

public class SimpleTemplateFrontController implements ITemplateFrontController {

	private static final Logger logger = Logger.getLogger(SimpleTemplateFrontController.class);
	
	private SimpleTemplateJsonParent jsonParent;
	
	private IFormGenerator formGenerator;
	
	private int hashCode;
	
	private IFormDao formDao;
	
	
	public SimpleTemplateFrontController(SimpleTemplateJsonParent jsonParent) {
		
		this.jsonParent = jsonParent;
		
		this.formGenerator = new SimpleTemplateFormGenerator(jsonParent);
		
		this.formDao = new FormDaoImpl();
	}
	
	
	@Override
	public void startFormGeneration() {
		
		long formId = 0;

		if (!checkFormExists()) {

			formId = formDao.saveForm(prepareFormData());
		}

		if(formId != 0) {
			
			formGenerator.generateForm(formId);
		} else {
			System.out.println("Error while saving form");
		}
	}
	
	
	private boolean checkFormExists() {		
		
		boolean formExists = false;
		
		String hashCodeJsonStr = new Gson().toJson(preapareFormHashCodeObject());
		
		hashCode = hashCodeJsonStr.hashCode();
		
		Form savedForm = formDao.getFormByHashCode(hashCode);
		
		if(savedForm.getHashCode() == hashCode) {
			
			formExists = true;
		}
		
		return formExists;
	}
	
	
	private FormHashCode preapareFormHashCodeObject() {
		
		FormHashCode hashCode = new FormHashCode();
		
		hashCode.setClientId(jsonParent.getClientId());
		
		hashCode.setTemplateId(jsonParent.getTemplateId());
		
		hashCode.setQuestion_opts(jsonParent.getFormSpec().getQuestionSection().getQuestion_opts());
		
		return hashCode;
	}
	
	
	private Form prepareFormData() {
		
		Form form = new Form();
		
		form.setClientId(jsonParent.getClientId());
		
		form.setTemplateId(jsonParent.getTemplateId());
		
		form.setNoOfPages(formGenerator.getPageDetailList().size());
			
		form.setHashCode(hashCode);
		
		form.setPageDetails(prepareFormPageDetailData());
		
		return form;
	}
	
	
	private List<FormPageDetail> prepareFormPageDetailData() {
		
		List<PageDetail> pageDetailList = formGenerator.getPageDetailList();
		
		List<FormPageDetail> pageDetailListToSave = new ArrayList<FormPageDetail>();
		
		int no_of_pages = pageDetailList.size();
		
		for (int i = 1; i <= no_of_pages; i++) {
			
			StringBuilder pageRangeStr = new StringBuilder();
			
			StringBuilder pageQuestionJsonStr = new StringBuilder();
			
			List<SimpleTemplateQuestionChoice> pageQuestionList = new ArrayList<SimpleTemplateQuestionChoice>();
			
			PageDetail pageDetail = pageDetailList.get(i-1);
			
			List<ColumnDetail> columnList = pageDetail.getColumnList();
						
			int no_of_columns_on_page = columnList.size();
			
			int lower_bound_for_page = columnList.get(0).getLowerBound();
			
			int upper_bound_for_page = columnList.get(no_of_columns_on_page - 1).getUppderBound();
			
			pageRangeStr.append(lower_bound_for_page+"-");
			
			List<SimpleTemplateQuestionChoice> question_opts = jsonParent.getFormSpec().getQuestionSection().getQuestion_opts();
			
			for(int j = lower_bound_for_page-1; j <= upper_bound_for_page-1; j++) {
				
				if(j < question_opts.size()) {																		
					SimpleTemplateQuestionChoice questionChoice = question_opts.get(j);									
					pageQuestionList.add(questionChoice);									
				} else {
					upper_bound_for_page = j;									
					break;
				}														
			}
			
			pageRangeStr.append(upper_bound_for_page);
			pageQuestionJsonStr.append(new Gson().toJson(pageQuestionList));
			
			FormPageDetail formPageDetail = new FormPageDetail();
			formPageDetail.setPageNumber(i);
			formPageDetail.setQuestionRange(pageRangeStr.toString());
			formPageDetail.setQuestionData(pageQuestionJsonStr.toString());
			
			pageDetailListToSave.add(formPageDetail);
		}
		
		return pageDetailListToSave;
	}
}
