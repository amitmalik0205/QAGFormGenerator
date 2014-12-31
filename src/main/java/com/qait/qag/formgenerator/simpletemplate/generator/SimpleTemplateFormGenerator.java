package com.qait.qag.formgenerator.simpletemplate.generator;

import java.util.List;

import com.qait.qag.formgenerator.simpletemplate.constants.SimpleTemplateBodyDesignConstants;
import com.qait.qag.formgenerator.simpletemplate.constants.SimpleTemplateGeneralConstants;
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
		
		int count = 0;
		
		for(SimpleTemplateInstance instance : instances) {
			
			count++;
			
			formHtmlStr = new StringBuilder("<html><head><meta http-equiv=\"Content-Type\" content=\"text/html; charset=UTF-8\"></head><body>");
			
			formHtmlStr.append("<div id=\"container\" style=\""+SimpleTemplateGeneralConstants.CONTAINER_DIV_STYLE+"\">");
			
			formHeaderHtmlStr = new StringBuilder("");
			
			formFooterHtmlStr = new StringBuilder("");
			
			simpleTemplateId = instance.getIds();
			
			top = instance.getTop();
			
			bottom = instance.getBottom();
			
			createHeaderSection();	
			
			//Generate form body only once and reuse it
			if(count == 1) {
				createFromBody();
			}
			
			formHtmlStr.append(formHeaderHtmlStr);
			
			formHtmlStr.append(formBodyHtmlStr);
			
			formHtmlStr.append(formFooterHtmlStr);
			
			formHtmlStr.append("</div></body></html>");
			
			System.out.println(formHtmlStr);
			
			break;
		}					
	}
	
	
	/**
	 * Method to start creation of header section
	 */
	private void createHeaderSection() {
		
		formHeaderHtmlStr.append("<div id=\"header-div\" style=\""+SimpleTemplateHeaderDesignConstants.HEADER_DIV_STYLE+"\">");
		
		createStudentNameSection();
		
		createStudentIdSection();
		
		formHeaderHtmlStr.append("</div>");
	}
	
	
	/**
	 * Method to create the student name section on top left
	 */
	private void createStudentNameSection() {
		
		formHeaderHtmlStr.append("<div id=\"student-name-div\" style=\""+SimpleTemplateHeaderDesignConstants.STUDENT_NAME_DIV_STYLE+"\">");
		
		formHeaderHtmlStr.append(top);
		
		formHeaderHtmlStr.append("</div>");
	}
	
	
	/**
	 * Method to create the student id section on top right
	 */
	private void createStudentIdSection() {
		
		formHeaderHtmlStr.append("<div id=\"student-id-div\" style=\""+SimpleTemplateHeaderDesignConstants.STUDENT_ID_DIV_STYLE+"\">");
		
		formHeaderHtmlStr.append("<div id=\"student-id-label\">"+sections_topright.getLabel()+"</div>");
		
		createStudentIdTable();
		
		formHeaderHtmlStr.append("</div>");
	}
	
	
	/**
	 * Method to create the table where we put the student Id
	 */
	private void createStudentIdTable() {
		
		formHeaderHtmlStr.append("<div id=\"student-id-table-wrapper-div\" style=\""+SimpleTemplateHeaderDesignConstants.STUDENT_ID_TABLE_WRAPPER_DIV_STYLE+"\">");
		
		//Start Table
		formHeaderHtmlStr.append("<table id=\"student-id-table\" style=\""+SimpleTemplateHeaderDesignConstants.STUDENT_ID_TABLE_STYLE+"\">");
		formHeaderHtmlStr.append("<tr>");
				
		char studentIdArr[] = simpleTemplateId.getStudentId().toCharArray();
		for(char ch : studentIdArr) {
			formHeaderHtmlStr.append("<td style=\""+SimpleTemplateHeaderDesignConstants.STUDENT_ID_TABLE_TD_STYLE+"\">");
			formHeaderHtmlStr.append(ch);
			formHeaderHtmlStr.append("</td>");
		}
		
		formHeaderHtmlStr.append("</tr>");
		formHeaderHtmlStr.append("</table>");
		
		formHeaderHtmlStr.append("</div>");
	}
	
	
	/**
	 * Method to start creation of form body
	 */
	private void createFromBody() {
		
		formBodyHtmlStr.append("<div id=\"body-div\" style=\""+SimpleTemplateBodyDesignConstants.BODY_DIV_STYLE+"\">");
		
		createTopDivOfBody();
		
		formBodyHtmlStr.append("</div>"); //End doy div
	}
	
	
	/**
	 * Method to start creation of top part of body which contains questions
	 * option section and student id options section
	 */
	private void createTopDivOfBody() {
		
		formBodyHtmlStr.append("<div id=\"top-div\" style=\""+SimpleTemplateBodyDesignConstants.TOP_DIV_STYLE+"\">");
		
		createStudentIdOptionsSection();
		
		formBodyHtmlStr.append("</div>"); //End top div
	}
	
	
	private void createStudentIdOptionsSection() {
		
		formBodyHtmlStr.append("<div id=\"student-id-option-container\" style=\""+SimpleTemplateBodyDesignConstants.STUDENT_ID_OPTION_CONTAINER_STYLE+"\">");		
		
		char studentIdArr[] = simpleTemplateId.getStudentId().toCharArray();
		int student_id_length = sections_topright.getDigits();
		char studentOptionsArr[] = sections_topright.getChoices().toCharArray();
		
		for(int i = 0 ; i<student_id_length; i++) {
			
			formBodyHtmlStr.append("<div id=\"student-id-option-column-container\" style=\""+SimpleTemplateBodyDesignConstants.STUDENT_ID_OPTION_COLUMN_CONTAINER_STYLE+"\">");
			
			char char_of_student_id = studentIdArr[i];
			
			for(int j = 0 ; j<studentOptionsArr.length; j++) {
				
				if(studentOptionsArr[j] == char_of_student_id) {
					formBodyHtmlStr.append("<div id=\"student-id-option\" style=\""+SimpleTemplateGeneralConstants.CIRCLE_BLACK_STYLE+"\">");				
					formBodyHtmlStr.append("</div>");
				} else {
					//Draw text circle
					formBodyHtmlStr.append("<div id=\"student-id-option\" style=\""+SimpleTemplateGeneralConstants.CIRCLE_WITH_TEXT_STYLE+"\">");
					formBodyHtmlStr.append(studentOptionsArr[j]);
					formBodyHtmlStr.append("</div>");
				}
			}
			
			formBodyHtmlStr.append("</div>"); // End student-id-option-column-container div
		}
		
		formBodyHtmlStr.append("</div>"); // End student-id-option-container div
	}
}
