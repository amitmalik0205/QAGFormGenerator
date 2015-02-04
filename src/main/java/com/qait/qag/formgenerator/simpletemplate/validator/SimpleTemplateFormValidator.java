package com.qait.qag.formgenerator.simpletemplate.validator;

import java.util.List;

import com.qait.qag.formgenerator.common.util.QAGFormGeneratorUtil;
import com.qait.qag.formgenerator.simpletemplate.domain.SimpleTemplateFormSpec;
import com.qait.qag.formgenerator.simpletemplate.domain.SimpleTemplateInstance;
import com.qait.qag.formgenerator.simpletemplate.domain.SimpleTemplateJsonParent;
import com.qait.qag.formgenerator.simpletemplate.domain.SimpleTemplateQuestionChoice;
import com.qait.qag.formgenerator.simpletemplate.domain.SimpleTemplateQuestionSection;
import com.qait.qag.formgenerator.simpletemplate.domain.SimpleTemplateSectionTopRight;

public class SimpleTemplateFormValidator {	
	
	private Integer digits = null;
	
	private SimpleTemplateJsonParent jsonParent;
	
	
	public SimpleTemplateFormValidator(SimpleTemplateJsonParent jsonParent) {
		this.jsonParent = jsonParent;
	}

	
	public String validateJson() {
		
		StringBuilder errors = new StringBuilder();
		
		Integer templateId = jsonParent.getTemplateId();
		
		if(templateId == null) {			
			
			errors.append("templateId can not be empty");
			
			errors.append("\n");
		}
		
		Integer clientId = jsonParent.getClientId();
		
		if(clientId == null) {		
			
			errors.append("clientId can not be empty");
			
			errors.append("\n");
		}
		
		SimpleTemplateFormSpec formSpec = jsonParent.getFormSpec();
		
		validateFormSpec(formSpec, errors);
		
		if(formSpec != null) {
			
			validateSimpleTemplateInstance(jsonParent.getInstances(), errors);
		}		
		
		return errors.toString();		
	}
	
	
	private void validateSimpleTemplateQuestionLabels(List<SimpleTemplateQuestionChoice> question_opts, StringBuilder errors) {
		
		boolean lastLabel = false;
		boolean currentLabel = false; 
		
		SimpleTemplateQuestionChoice firstQuestion = question_opts.get(0);
		
		String firstQuestionLabel = firstQuestion.getLabel();
		
		if(!QAGFormGeneratorUtil.checkForEmptyString(firstQuestionLabel)) {
			
			lastLabel = currentLabel = true;
		}
		
		for(int i = 1; i<question_opts.size() ; i++) {
			
			SimpleTemplateQuestionChoice currentQuestion = question_opts.get(i);
			
			String currentQuestionLabel = currentQuestion.getLabel();
			
			//If empty
			if(QAGFormGeneratorUtil.checkForEmptyString(currentQuestionLabel)) {
				
				currentLabel = false;
				
			} else {
				
				currentLabel = true;
				
			}
			
			if(currentLabel != lastLabel) {
				
				errors.append("Either provide label for all the questions or for none");
				
				errors.append("\n");
				
				break;
			} else {
				
				lastLabel = currentLabel;
				
			}
		}		
	}
	
	private void validateSimpleTemplateQuestionChoices(SimpleTemplateQuestionChoice choice, StringBuilder errors, int counter) {	
		
		if(choice == null) {
			
			errors.append("No choices are provided for question number "+counter);
			
			errors.append("\n");
			
		} else {
			
			String choices = choice.getChoices();			
			
			if(QAGFormGeneratorUtil.checkForEmptyString(choices)) {
				
				errors.append("No choices are provided for question number "+counter);
				
				errors.append("\n");
			}				
		}
	}
	
	
	private void validateSimpleTemplateQuestionChoiceList(List<SimpleTemplateQuestionChoice> question_opts, StringBuilder errors, int count) {

		if(question_opts == null || (question_opts != null && question_opts.size() < 1)) {
			
			errors.append("question_opts can not be empty");
			
			errors.append("\n");
			
		} else {
			
			int no_of_questions = question_opts.size();
			
			if(no_of_questions != count) {							
				errors.append("questions in question_opts must be same as value of count");
				
				errors.append("\n");
			}
			
			validateSimpleTemplateQuestionLabels(question_opts, errors);
			
			int counter = 0;
			
			for(SimpleTemplateQuestionChoice choice : question_opts) {							
				counter++;							
				
				validateSimpleTemplateQuestionChoices(choice, errors, counter);
			}
		}	
	}
	
	
	private void validateSimpleTemplateQuestionSection(SimpleTemplateQuestionSection questionSection, StringBuilder errors) {
		
		if(questionSection == null) {				
			errors.append("questionSection can not be empty");
			
			errors.append("\n");
			
		}  else {
			
			Integer count = questionSection.getCount();
			
			if(count == null) {
				
				errors.append("count can not be empty");
				
				errors.append("\n");				
				
			} else {
				
				validateSimpleTemplateQuestionChoiceList(questionSection.getQuestion_opts(), errors, count);
			}						
		}								
	}
	

	private void validateSimpleTemplateSectionTopRight(SimpleTemplateSectionTopRight topRight, StringBuilder errors) {
		
		if(topRight == null) {
			
			errors.append("sections_topright can not be empty");
			
			errors.append("\n");
			
		} else {
			
			String choices = topRight.getChoices();
			
			if(QAGFormGeneratorUtil.checkForEmptyString(choices)) {
				
				errors.append("sections_topright must have choices");
				
				errors.append("\n");
				
			}  else {
				
				digits = topRight.getDigits();
				
				if(digits == null) {
					
					errors.append("sections_topright must have digits");
					
					errors.append("\n");
				} 				
			}								
		}
	}
	
	
	private void validateFormSpec(SimpleTemplateFormSpec formSpec, StringBuilder errors) {

		if(formSpec == null) {
			
			errors.append("formSpec can not be empty");
			
			errors.append("\n");
			
		} else {
			
			validateSimpleTemplateQuestionSection(formSpec.getQuestionSection(), errors);
			
			validateSimpleTemplateSectionTopRight(formSpec.getSections_topright(), errors);
		}
	}
	
	
	private void validateSimpleTemplateInstance(List<SimpleTemplateInstance> instances, StringBuilder errors) {
		
		if(instances == null || (instances != null && instances.size() < 1)) {
			
			errors.append("instances can not be empty");
			
			errors.append("\n");
			
		} else {
			
			int counter = 0;
			
			for(SimpleTemplateInstance instance : instances) {
				
				counter++;
				
				String studentId = instance.getStudentId();
				String top = instance.getTop();
				String bottom = instance.getBottom();
				
				if(QAGFormGeneratorUtil.checkForEmptyString(studentId)) {
					
					errors.append("studentId can not be empty");
					
					errors.append("\n");
					
				} else {
					
					int studentIdLength = studentId.length();
					
					if(digits !=  null && studentIdLength != digits) {
						
						errors.append("Number of digits in studentId must be "+digits+" for instance "+counter);
						
						errors.append("\n");
					}
				}
				
				if(QAGFormGeneratorUtil.checkForEmptyString(top)) {
					
					errors.append("top can not be empty");
					
					errors.append("\n");
				}
				
				if(QAGFormGeneratorUtil.checkForEmptyString(bottom)) {
					
					errors.append("bottom can not be empty");
					
					errors.append("\n");
				}
			}
		}	
	}
}
