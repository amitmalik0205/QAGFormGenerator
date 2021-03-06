package com.qait.qag.formgenerator.simpletemplate.generator;

import java.util.ArrayList;
import java.util.List;

import org.apache.log4j.Logger;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.qait.qag.formgenerator.common.dao.ClientDaoImpl;
import com.qait.qag.formgenerator.common.dao.FormDaoImpl;
import com.qait.qag.formgenerator.common.dao.IClientDao;
import com.qait.qag.formgenerator.common.dao.IFormDao;
import com.qait.qag.formgenerator.common.enums.ResponseType;
import com.qait.qag.formgenerator.common.util.QAGFormGeneratorUtil;
import com.qait.qag.formgenerator.db.domain.Client;
import com.qait.qag.formgenerator.db.domain.Form;
import com.qait.qag.formgenerator.db.domain.FormPageDetail;
import com.qait.qag.formgenerator.domain.FormHashCode;
import com.qait.qag.formgenerator.generator.IFormGenerator;
import com.qait.qag.formgenerator.generator.ITemplateFrontController;
import com.qait.qag.formgenerator.simpletemplate.domain.ColumnDetail;
import com.qait.qag.formgenerator.simpletemplate.domain.PageDetail;
import com.qait.qag.formgenerator.simpletemplate.domain.SimpleTemplateJsonParent;
import com.qait.qag.formgenerator.simpletemplate.domain.SimpleTemplateQuestionChoice;
import com.qait.qag.formgenerator.simpletemplate.dto.FormPageDetailDto;
import com.qait.qag.formgenerator.simpletemplate.dto.StudentFormDetailDto;
import com.qait.qag.formgenerator.simpletemplate.validator.SimpleTemplateFormValidator;

public class SimpleTemplateFrontController implements ITemplateFrontController {

	private static final Logger logger = Logger.getLogger(SimpleTemplateFrontController.class);
	
	private SimpleTemplateJsonParent jsonParent;
	
	private IFormGenerator formGenerator;
	
	private int hashCode;
	
	private IFormDao formDao;
	
	private Client client;
	
	
	public SimpleTemplateFrontController(SimpleTemplateJsonParent jsonParent) {
		
		this.jsonParent = jsonParent;
	
	}
	
	
	@Override
	public String validateFormData() {
		
		SimpleTemplateFormValidator validator = new SimpleTemplateFormValidator(jsonParent);
		
		String errors = validator.validateKey();
		
		//If key is validate then validate json
		if(QAGFormGeneratorUtil.checkForEmptyString(errors)) {
			
			errors = validator.validateJson();
		}				
		
		return errors;
	}
	
	
	@Override
	public String startFormGeneration() {
		
		formGenerator = new SimpleTemplateFormGenerator(jsonParent);
		
		formDao = new FormDaoImpl();
		
		IClientDao clientDao = new ClientDaoImpl();

		client = clientDao.getClientByKey(jsonParent.getKey());
		
		long formId = -1;
		
		String responseStr = null;

		Form savedForm = getFormByHashCode();
		
		if(savedForm != null) {		
			
			if(savedForm.getHashCode() == 0) {
				//Form does not exist save it
				formId = formDao.saveForm(prepareFormData());
				
			} else {				
				//Form exists set form id from database
				formId = savedForm.getFormId();
			}	
			
			if(formId > 0) {
				
				List<StudentFormDetailDto> reponse = formGenerator.generateForm(formId);							
				
				String responseType = jsonParent.getResponseType();
				

				if(responseType != null && responseType.equalsIgnoreCase(ResponseType.JSON.getValue())) {
					
					Gson gson = new GsonBuilder().disableHtmlEscaping().create();
					
					responseStr = gson.toJson(reponse);
				} else {
					
					StringBuilder htmlResponse = new StringBuilder("<html><head><meta http-equiv=\"Content-Type\" content=\"text/html; charset=UTF-8\"></head><body>");
					
					for(StudentFormDetailDto dto : reponse) {
						
						List<FormPageDetailDto> formPageDetails  = dto.getFormPageDetails();
						
						for(FormPageDetailDto detailDto : formPageDetails) {
							
							htmlResponse.append(detailDto.getPageString());
						}
					}
					
					responseStr = htmlResponse.append("</body></html>").toString();
				}																
				
				System.out.println(responseStr);
				
			} else if (formId == 0) {
				logger.error("Error while saving form");
				System.out.println("Error while saving form");
			}
			
		}  else {
			logger.error("Error while fetching form by hashcode");
			System.out.println("Error while fetching form by hashcode");
		}
		
		return responseStr;		
	}
	
	
	/**
	 *  Method will check if the form already saved
	 * @return
	 */
	private Form getFormByHashCode() {		
		
		String hashCodeJsonStr = new Gson().toJson(preapareFormHashCodeObject());
		
		hashCode = hashCodeJsonStr.hashCode();
		
		Form savedForm = formDao.getFormByHashCode(hashCode);
		
		return savedForm;
	}
	
	
	private FormHashCode preapareFormHashCodeObject() {
		
		FormHashCode hashCode = new FormHashCode();
		
		hashCode.setClientId(client.getId());
		
		hashCode.setTemplateId(jsonParent.getTemplateId());
		
		hashCode.setQuestion_opts(jsonParent.getFormSpec().getQuestionSection().getQuestion_opts());
		
		return hashCode;
	}
	
	
	private Form prepareFormData() {
		
		Form form = new Form();
		
		form.setClientId(client.getId());
		
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
