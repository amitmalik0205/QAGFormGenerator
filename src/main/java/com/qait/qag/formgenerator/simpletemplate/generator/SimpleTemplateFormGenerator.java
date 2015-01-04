package com.qait.qag.formgenerator.simpletemplate.generator;

import java.util.List;

import com.qait.qag.formgenerator.simpletemplate.constants.SimpleTemplateBodyDesignConstants;
import com.qait.qag.formgenerator.simpletemplate.constants.SimpleTemplateGeneralConstants;
import com.qait.qag.formgenerator.simpletemplate.constants.SimpleTemplateHeaderDesignConstants;
import com.qait.qag.formgenerator.simpletemplate.domain.SimpleTemplateID;
import com.qait.qag.formgenerator.simpletemplate.domain.SimpleTemplateInstance;
import com.qait.qag.formgenerator.simpletemplate.domain.SimpleTemplateJsonParent;
import com.qait.qag.formgenerator.simpletemplate.domain.SimpleTemplateQuestionChoice;
import com.qait.qag.formgenerator.simpletemplate.domain.SimpleTemplateQuestionSection;
import com.qait.qag.formgenerator.simpletemplate.domain.SimpleTemplateSectionTopRight;

public class SimpleTemplateFormGenerator {
	
	private SimpleTemplateJsonParent jsonParent;

	private List<SimpleTemplateInstance> instances;

	private Integer formCode;

	private SimpleTemplateQuestionSection questionSection;

	private SimpleTemplateSectionTopRight sections_topright;

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
		
		createQuestionOptionsSection();
		
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
					formBodyHtmlStr.append("<div id=\"student-id-option\" style=\""+SimpleTemplateBodyDesignConstants.CIRCLE_BLACK_STYLE+"\">");				
					formBodyHtmlStr.append("</div>");
				} else {
					//Draw text circle
					formBodyHtmlStr.append("<div id=\"student-id-option\" style=\""+SimpleTemplateBodyDesignConstants.CIRCLE_WITH_TEXT_STYLE_FOR_STUDENT_ID+"\">");
					formBodyHtmlStr.append(studentOptionsArr[j]);
					formBodyHtmlStr.append("</div>");
				}
			}
			
			formBodyHtmlStr.append("</div>"); // End student-id-option-column-container div
		}
		
		formBodyHtmlStr.append("</div>"); // End student-id-option-container div
	}
	
	
	/**
	 * Method to start building question option section
	 */
	private void createQuestionOptionsSection() {
		
		StringBuilder questionOptionDivStyle = new StringBuilder(
				SimpleTemplateBodyDesignConstants.QUESTION_OPTION_DIV_STYLE);
		
		int avilable_space_for_question_section = getAvailableWidthForQuestionOptionSection();
		
		questionOptionDivStyle.append("width:"+avilable_space_for_question_section+"px");
		
		formBodyHtmlStr.append("<div id=\"question-option-div\" style=\""+questionOptionDivStyle.toString()+"\">");
		
		/**
		 * Below we will check how many columns can be created in the available
		 * space. If a column can be accommodated then create it.
		 */
		int max_columns_can_be_created = getMaxColumnCount();

		// AP formula a+(n-1)d
		// Here we will check if all the questions can be arranged in
		// max_columns with in avilable_space_for_question_section
		int a_lower_bound = 1;
		int a_upper_bound = SimpleTemplateGeneralConstants.MAX_QUESTION_IN_COLUMN;
		int d = SimpleTemplateGeneralConstants.MAX_QUESTION_IN_COLUMN;

		for (int n = 1; n <= max_columns_can_be_created; n++) {

			int an_lower_bound = a_lower_bound + (n - 1) * d;
			int an_upper_bound = a_upper_bound + (n - 1) * d;

			// Maximum width for a question option column in range
			// an_lower_bound -
			// an_upper_bound
			int an_column_width = calculateMaxWidthForQuestionOptionColumn(
					an_lower_bound, an_upper_bound);

			// If column can be fit in available space then create it and update
			// available space
			if (an_column_width <= avilable_space_for_question_section) {
				avilable_space_for_question_section -= an_column_width;

				createQuestionOptionSectionColumn(an_column_width,
						an_lower_bound, an_upper_bound);
			}
		}
		
		formBodyHtmlStr.append("</div>");
	}
	
	
	/**
	 * Method to create question option column. Column will contain the question
	 * in a range specified by @param questionLowerBound, @param
	 * questionUpperBound
	 * 
	 * @param width_of_question_option_column
	 *            -- Width of the div to set
	 * @param questionLowerBound
	 *            -- First question number in column
	 * @param questionUpperBound
	 *            -- Last question number in column
	 */
	private void createQuestionOptionSectionColumn(
			int width_of_question_option_column, int questionLowerBound,
			int questionUpperBound) {
		
		StringBuilder questionOptionColumnStyle = new StringBuilder(
				SimpleTemplateBodyDesignConstants.QUESTION_OPTION_COLUMN_DIV_STYLE);
		
		questionOptionColumnStyle.append("width:"+width_of_question_option_column+"px");
		
		formBodyHtmlStr.append("<div id=\"question-option-column-div\" style=\""+questionOptionColumnStyle.toString()+"\">");
		
		List<SimpleTemplateQuestionChoice> question_opts = questionSection.getQuestion_opts();
	
		for (int i = questionLowerBound - 1; i < questionUpperBound; i++) {
			SimpleTemplateQuestionChoice questionChoice = question_opts.get(i);
			
			createQuestionOptionRow(questionChoice,
					width_of_question_option_column);
		}

		formBodyHtmlStr.append("</div>");
	}
	
	
	/**
	 * Method to create question options row. Method will create question label
	 * and circles as options
	 */
	private void createQuestionOptionRow(
			SimpleTemplateQuestionChoice questionChoice,
			int width_of_question_option_column) {
		
		StringBuilder questionOptionRowStyle = new StringBuilder(
				SimpleTemplateBodyDesignConstants.QUESTION_OPTION_ROW_DIV_STYLE);

		int min_width = width_of_question_option_column
				- SimpleTemplateBodyDesignConstants.QUESTION_OPTION_ROW_DIV_MIN_WIDTH_CONSTANT;

		questionOptionRowStyle.append("min-width:" + min_width + "px");
		
		formBodyHtmlStr.append("<div id=\"question-option-row-div\" style=\""+questionOptionRowStyle.toString()+"\">");
		
		createQuestionOptionLabel(questionChoice.getLabel());
		
		char choiceArr[] = questionChoice.getChoices().toCharArray();
		
		for(char c : choiceArr) {
			createQuestionOptionCircle(new Character(c).toString());
		}
		
		formBodyHtmlStr.append("</div>");
	}
	
	
	/**
	 * Method to create question label
	 * @param label
	 */
	private void createQuestionOptionLabel(String label) {
		
		formBodyHtmlStr.append("<div id=\"question-option-column-div\" style=\""+SimpleTemplateBodyDesignConstants.QUESTION_LABEL_DIV_STYLE+"\">");
		
		formBodyHtmlStr.append(label);
		
		formBodyHtmlStr.append("</div>");
	}
	
	
	private void createQuestionOptionCircle(String text) {
		
		formBodyHtmlStr.append("<div id=\"question-option-column-div\" style=\""+SimpleTemplateBodyDesignConstants.CIRCLE_WITH_TEXT_STYLE_FOR_QUESTION+"\">");
		
		formBodyHtmlStr.append(text);
		
		formBodyHtmlStr.append("</div>");
	}
	
	
	/**
	 * Method will calculate the width of Student id option section
	 * @return
	 */
	private int getStudentIdOptionsSectionWidth() {
		
		int student_id_length = sections_topright.getDigits();
		
		return student_id_length
				* SimpleTemplateBodyDesignConstants.STUDENT_ID_OPTION_COLUMN_WIDTH;
	}
	
	
	/**
	 * Method will calculate how much space is available for question option
	 * section after student id section is constructed
	 * @return
	 */
	private int getAvailableWidthForQuestionOptionSection() {		
		int student_id_option_section_width = getStudentIdOptionsSectionWidth();
		int container_max_width = SimpleTemplateGeneralConstants.CONTAINER_DIV_MAX_WIDTH;
		
		return container_max_width - student_id_option_section_width - 
				SimpleTemplateBodyDesignConstants.QUESTION_OPTION_DIV_WIDTH_CONSTANT;
	}
	
	
	/**
	 * Method will first find which question has maximum number of options and
	 * then calculate the width for that row. This will be set as the width of
	 * question option column section div
	 * @return
	 */
	private int calculateMaxWidthForQuestionOptionColumn(int lowerBound, int upperBound) {		
		int max_options = getMaximumQuestionOptions(lowerBound, upperBound);
		
		return (max_options*SimpleTemplateBodyDesignConstants.QUESTION_OPTION_CIRCLE_WIDTH)
				+ SimpleTemplateBodyDesignConstants.QUESTION_LABEL_WIDTH;
	}
	
	
	/**
	 * Method to get the question which has max num of options with in a range.
	 * We will iterate through loop in between @param lowerBound and @param
	 * upperBound
	 * 
	 * @param lowerBound
	 *            -- lower bound for list
	 * @param upperBound
	 *            -- upper bound for list
	 * @return
	 */
	private int getMaximumQuestionOptions(int lowerBound, int upperBound) {

		int max = 0;

		List<SimpleTemplateQuestionChoice> question_opts = questionSection
				.getQuestion_opts();

		for (int i = lowerBound - 1; i < upperBound; i++) {
			SimpleTemplateQuestionChoice questionChoice = question_opts.get(i);

			char[] chrArr = questionChoice.getChoices().toCharArray();
			int arrLength = chrArr.length;

			if (arrLength > max) {
				max = arrLength;
			}
		}

		return max;
	}
	
	
	/**
	 * Method find in how many columns all the question can be arranged
	 * 
	 * @return
	 */
	private int getMaxColumnCount() {

		int no_of_questions = questionSection.getCount();
		
		int max_columns = no_of_questions
				/ SimpleTemplateGeneralConstants.MAX_QUESTION_IN_COLUMN;
		
		int mod = no_of_questions
				% SimpleTemplateGeneralConstants.MAX_QUESTION_IN_COLUMN;

		if (mod > 0) {
			max_columns = max_columns + 1;
		}

		return max_columns;
	}
}
