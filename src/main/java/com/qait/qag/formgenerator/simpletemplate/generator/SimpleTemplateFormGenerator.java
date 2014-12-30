package com.qait.qag.formgenerator.simpletemplate.generator;

import java.util.List;

import com.qait.qag.formgenerator.simpletemplate.constants.SimpleTemplateHeaderDesignConstants;
import com.qait.qag.formgenerator.simpletemplate.domain.SimpleTemplateID;
import com.qait.qag.formgenerator.simpletemplate.domain.SimpleTemplateInstance;
import com.qait.qag.formgenerator.simpletemplate.domain.SimpleTemplateJsonParent;
import com.qait.qag.formgenerator.simpletemplate.domain.SimpleTemplateQuestionSection;
import com.qait.qag.formgenerator.simpletemplate.domain.SimpleTemplateSectionTopRight;
import com.qait.qag.formgenerator.simpletemplate.util.SimpleTemplateUtil;

public class SimpleTemplateFormGenerator {
	
	private SimpleTemplateJsonParent jsonParent;

	private List<SimpleTemplateInstance> instances;

	private Integer formCode;

	private SimpleTemplateQuestionSection questionSection;

	private SimpleTemplateSectionTopRight sections_topright;

	private int max_number_of_options;

	private int no_of_questions;
	
	private StringBuilder formHtmlStr;
	
	private StringBuilder formHeaderHtmlStr;
	
	private StringBuilder formBodyHtmlStr;
	
	private StringBuilder formFooterHtmlStr;
	
	private String top;
	
	private String bottom;
	
	private SimpleTemplateID simpleTemplateId;
	

	public SimpleTemplateFormGenerator(SimpleTemplateJsonParent jsonParent) {	
		
		this.jsonParent = jsonParent;
		this.instances = jsonParent.getInstances();
		this.formCode = jsonParent.getFormSpec().getFormCode();
		this.questionSection = jsonParent.getFormSpec().getQuestionSection();
		
		this.sections_topright = jsonParent.getFormSpec().getSections_topright();
		this.no_of_questions = questionSection.getCount();

		this.max_number_of_options = SimpleTemplateUtil
				.getMaximumQuestionOptions(questionSection.getQuestion_opts());		
		
		formBodyHtmlStr = new StringBuilder("");
	}

	
	public void generateForm() {
		
		createFromBody();
		
		for(SimpleTemplateInstance instance : instances) {
			
			formHtmlStr = new StringBuilder("<html><body><head><meta http-equiv=\"Content-Type\" content=\"text/html; charset=UTF-8\"></head>");
			
			formHeaderHtmlStr = new StringBuilder("");
			
			formFooterHtmlStr = new StringBuilder("");
			
			simpleTemplateId = instance.getIds();
			
			top = instance.getTop();
			
			bottom = instance.getBottom();
			
			createHeaderSection();	
			
			formHtmlStr.append(formHeaderHtmlStr);
			
			formHtmlStr.append(formBodyHtmlStr);
			
			formHtmlStr.append(formFooterHtmlStr);
			
			formHtmlStr.append("</body></html>");
			
			System.out.println(formHtmlStr);
			
			break;
		}					
	}
	
	
	/**
	 * Method to start creation of header section
	 */
	private void createHeaderSection() {
		
		formHeaderHtmlStr.append("<div style=\""+SimpleTemplateHeaderDesignConstants.HEADER_DIV_STYLE+"\">");
		
		createStudentNameSection();
		
		createStudentIdSection();
		
		formHeaderHtmlStr.append("</div>");
	}
	
	
	/**
	 * Method to create the student name section on top left
	 */
	private void createStudentNameSection() {
		
		formHeaderHtmlStr.append("<div style=\""+SimpleTemplateHeaderDesignConstants.STUDENT_NAME_DIV_STYLE+"\">");
		
		formHeaderHtmlStr.append(top);
		
		formHeaderHtmlStr.append("</div>");
	}
	
	
	/**
	 * Method to create the student id section on top right
	 */
	private void createStudentIdSection() {
		
		formHeaderHtmlStr.append("<div style=\""+SimpleTemplateHeaderDesignConstants.STUDENT_ID_DIV_STYLE+"\">");
		
		formHeaderHtmlStr.append("<div>"+sections_topright.getLabel()+"</div>");
		
		createStudentIdTable();
		
		formHeaderHtmlStr.append("</div>");
	}
	
	
	/**
	 * Method to create the table where we put the student Id
	 */
	private void createStudentIdTable() {
		
		formHeaderHtmlStr.append("<div style=\""+SimpleTemplateHeaderDesignConstants.STUDENT_ID_TABLE_WRAPPER_DIV_STYLE+"\">");
		
		//Start Table
		formHeaderHtmlStr.append("<table style=\""+SimpleTemplateHeaderDesignConstants.STUDENT_ID_TABLE_STYLE+"\"><tbody>");
		formHeaderHtmlStr.append("<tr>");
				
		char studentIdArr[] = simpleTemplateId.getStudentId().toCharArray();
		for(char ch : studentIdArr) {
			formHeaderHtmlStr.append("<td>");
			formHeaderHtmlStr.append(ch);
			formHeaderHtmlStr.append("</td>");
		}
		
		formHeaderHtmlStr.append("</tr>");
		formHeaderHtmlStr.append("</tbody></table>");
		
		formHeaderHtmlStr.append("</div>");
	}
	
	
	private void createFromBody() {
		
		formBodyHtmlStr.append("<div style=\""+SimpleTemplateHeaderDesignConstants.STUDENT_ID_TABLE_WRAPPER_DIV_STYLE+"\">");
		
	}
	
	
	private void createStudentIdOptionsSection() {
		
		char studentIdArr[] = simpleTemplateId.getStudentId().toCharArray();
		int student_id_length = sections_topright.getDigits();
		char studentOptionsArr[] = sections_topright.getChoices().toCharArray();
		
		for(int i = 0 ; i<student_id_length; i++) {
			
			char char_of_student_id = studentIdArr[i];
			
			for(int j = 0 ; j<studentOptionsArr.length; j++) {
				
				if(studentOptionsArr[j] == char_of_student_id) {
					//Draw black circle
				} else {
					//Draw text circle
				}
			}
		}
	}
}
