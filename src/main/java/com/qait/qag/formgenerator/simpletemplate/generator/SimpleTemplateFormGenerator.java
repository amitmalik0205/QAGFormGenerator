package com.qait.qag.formgenerator.simpletemplate.generator;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.log4j.Logger;

import com.qait.qag.formgenerator.generator.IFormGenerator;
import com.qait.qag.formgenerator.simpletemplate.constants.SimpleTemplateBodyDesignConstants;
import com.qait.qag.formgenerator.simpletemplate.constants.SimpleTemplateGeneralConstants;
import com.qait.qag.formgenerator.simpletemplate.constants.SimpleTemplateHeaderDesignConstants;
import com.qait.qag.formgenerator.simpletemplate.domain.ColumnDetail;
import com.qait.qag.formgenerator.simpletemplate.domain.PageDetail;
import com.qait.qag.formgenerator.simpletemplate.domain.SimpleTemplateInstance;
import com.qait.qag.formgenerator.simpletemplate.domain.SimpleTemplateJsonParent;
import com.qait.qag.formgenerator.simpletemplate.domain.SimpleTemplateQuestionChoice;
import com.qait.qag.formgenerator.simpletemplate.domain.SimpleTemplateQuestionSection;
import com.qait.qag.formgenerator.simpletemplate.domain.SimpleTemplateSectionTopRight;
import com.qait.qag.formgenerator.simpletemplate.dto.FormPageDetailDto;
import com.qait.qag.formgenerator.simpletemplate.dto.StudentFormDetailDto;
import com.qait.qag.formgenerator.simpletemplate.util.SimpleTemplateUtil;

public class SimpleTemplateFormGenerator implements IFormGenerator {
	
	private static final Logger logger =Logger.getLogger(SimpleTemplateFormGenerator.class);
	
	private SimpleTemplateJsonParent jsonParent;

	private List<SimpleTemplateInstance> instances;

	private SimpleTemplateQuestionSection questionSection;

	private SimpleTemplateSectionTopRight sections_topright;

	private StringBuilder formHtmlStr;
	
	private StringBuilder formHeaderHtmlStr;
	
	private StringBuilder formBodyHtmlStr;
	
	private StringBuilder formFooterHtmlStr;
	
	private StringBuilder formStudentIdOptionsHtmlStr;
	
	private StringBuilder formQuestionOptionsHtmlStr;
	
	private String top;
	
	private String bottom;
	
	private List<PageDetail> pageDetailList;
	
	private PageDetail currentPageDetail;
	
	private StringBuilder formIdHtmlStr;
	
	private Map<String, String> numbersMap;
	
	private long formId;
	
	private List<StudentFormDetailDto> response;
	
	private String currentStudentId;
	
	
	public SimpleTemplateFormGenerator(SimpleTemplateJsonParent jsonParent) {	
		
		this.jsonParent = jsonParent;
		
		this.instances = jsonParent.getInstances();
		
		this.questionSection = jsonParent.getFormSpec().getQuestionSection();
		
		this.sections_topright = jsonParent.getFormSpec().getSections_topright();
		
		pageDetailList = createPageDetails();
		
		numbersMap = new HashMap<String, String>();
		
		response = new ArrayList<StudentFormDetailDto>();
	}

	
	@Override
	public List<StudentFormDetailDto> generateForm(long formId) {
		
		this.formId = formId;
		
		initalizeNumbersMap();
		
		for(SimpleTemplateInstance instance : instances) {
			
			StudentFormDetailDto studentDto = new StudentFormDetailDto();
			
			List<FormPageDetailDto> formPageDetailList = new ArrayList<FormPageDetailDto>();
			
			studentDto.setStudentId(instance.getStudentId());
			
			for(PageDetail pageDetail : pageDetailList) {							
				
				currentPageDetail = pageDetail;
				
				FormPageDetailDto formPageDetail = new FormPageDetailDto();
				
				formPageDetail.setPageNumber(currentPageDetail.getPageNo());
				
				//formHtmlStr = new StringBuilder("<html><head><meta http-equiv=\"Content-Type\" content=\"text/html; charset=UTF-8\"></head><body>");
				
				formHtmlStr = new StringBuilder("");
				
				formHtmlStr.append("<div id=\"container\" style=\""+SimpleTemplateGeneralConstants.CONTAINER_DIV_STYLE+"\">");
				
				formHeaderHtmlStr = new StringBuilder("");
				
				formFooterHtmlStr = new StringBuilder("");
				
				currentStudentId = instance.getStudentId();
				
				top = instance.getTop();
				
				bottom = instance.getBottom();
				
				createHeaderSection();	
				
				createFromBody();
				
				createFooter();
				
				formHtmlStr.append(formHeaderHtmlStr);
				
				formHtmlStr.append(formBodyHtmlStr);
				
				formHtmlStr.append(formFooterHtmlStr);
				
				formHtmlStr.append("</div>");
				
				formHtmlStr.append("</br>");
				
				//System.out.println(formHtmlStr);
								
				formPageDetail.setPageString(formHtmlStr.toString());
				
				formPageDetailList.add(formPageDetail);
			}	
			
			studentDto.setFormPageDetails(formPageDetailList);
			
			response.add(studentDto);
		}	
		
		return response;			
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
				
		char studentIdArr[] = currentStudentId.toCharArray();
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
		
		formBodyHtmlStr = new StringBuilder("");
		
		formBodyHtmlStr.append("<div id=\"body-div\" style=\""+SimpleTemplateBodyDesignConstants.BODY_DIV_STYLE+"\">");
		
		createTopDivOfBody();
		
		createBottomOfBodyDiv();
		
		formBodyHtmlStr.append("</div>"); 
	}
	
	
	/**
	 * Method to start creation of top part of body which contains questions
	 * option section and student id options section
	 */
	private void createTopDivOfBody() {
		
		formBodyHtmlStr.append("<div id=\"top-div\" style=\""+SimpleTemplateBodyDesignConstants.TOP_DIV_STYLE+"\">");
		
		createStudentIdOptionsSection();
		
		formBodyHtmlStr.append(formStudentIdOptionsHtmlStr);
		
		createQuestionOptionsSection();
		
		formBodyHtmlStr.append(formQuestionOptionsHtmlStr);
		
		formBodyHtmlStr.append("</div>"); //End top div
	}
	
	
	private void createStudentIdOptionsSection() {
		
		formStudentIdOptionsHtmlStr = new StringBuilder("");
		
		
		if(getHeightOfStudentIdOptionSection() > getHeightOfQuestionOptionSection()) {
			formStudentIdOptionsHtmlStr.append("<div id=\"student-id-option-container\" style=\""+SimpleTemplateBodyDesignConstants.STUDENT_ID_OPTION_CONTAINER_STYLE+"\">");
		} else {
			formStudentIdOptionsHtmlStr.append("<div id=\"student-id-option-container\" style=\""+SimpleTemplateBodyDesignConstants.STUDENT_ID_OPTION_CONTAINER_NO_BORDER_STYLE+"\">");
		}		
		
		char studentIdArr[] = currentStudentId.toCharArray();
		int student_id_length = sections_topright.getDigits();
		char studentOptionsArr[] = sections_topright.getChoices().toCharArray();
		
		for(int i = 0 ; i<student_id_length; i++) {
			
			//For last cloumn remove the dotted border
			if(i == student_id_length-1) {
				
				formStudentIdOptionsHtmlStr.append("<div id=\"student-id-option-column-container\" style=\""+SimpleTemplateBodyDesignConstants.STUDENT_ID_OPTION_COLUMN_CONTAINER_NO_BORDER_STYLE+"\">");
			} else {
				
				formStudentIdOptionsHtmlStr.append("<div id=\"student-id-option-column-container\" style=\""+SimpleTemplateBodyDesignConstants.STUDENT_ID_OPTION_COLUMN_CONTAINER_STYLE+"\">");				
			}
			
			char char_of_student_id = studentIdArr[i];
			
			int j = 0;
			
			for( ; j<studentOptionsArr.length-1; j++) {
				
				if(studentOptionsArr[j] == char_of_student_id) {
					formStudentIdOptionsHtmlStr.append(SimpleTemplateBodyDesignConstants.CIRCLE_BLACK_STYLE);		
								
				} else {
					//Draw text circle
					formStudentIdOptionsHtmlStr.append("<div id=\"student-id-option\" style=\""+SimpleTemplateBodyDesignConstants.CIRCLE_WITH_TEXT_STYLE_FOR_STUDENT_ID+"\">");
					formStudentIdOptionsHtmlStr.append(studentOptionsArr[j]);
					formStudentIdOptionsHtmlStr.append("</div>");
				}
			}
			
			//Last circle need some bottom margin so draw it separately
			if(studentOptionsArr[j] == char_of_student_id) {				
				formStudentIdOptionsHtmlStr.append(SimpleTemplateBodyDesignConstants.CIRCLE_BLACK_STYLE_BOTTOM);
			} else {
				//Draw text circle
				formStudentIdOptionsHtmlStr.append("<div id=\"student-id-option\" style=\""+SimpleTemplateBodyDesignConstants.CIRCLE_WITH_TEXT_STYLE_FOR_STUDENT_ID_BOTTOM+"\">");
				formStudentIdOptionsHtmlStr.append(studentOptionsArr[j]);
				formStudentIdOptionsHtmlStr.append("</div>");
			}
			
			formStudentIdOptionsHtmlStr.append("</div>"); // End student-id-option-column-container div
		}
		
		formStudentIdOptionsHtmlStr.append("</div>"); // End student-id-option-container div
	}
	
	
	/**
	 * Method to start building question option section
	 */
	private void createQuestionOptionsSection() {
		
		formQuestionOptionsHtmlStr = new StringBuilder("");
		
		StringBuilder questionOptionDivStyle = new StringBuilder("");
		
		if(getHeightOfQuestionOptionSection() >= getHeightOfStudentIdOptionSection()) {
			
			questionOptionDivStyle.append(SimpleTemplateBodyDesignConstants.QUESTION_OPTION_DIV_STYLE);
			
		} else {
			
			questionOptionDivStyle.append(SimpleTemplateBodyDesignConstants.QUESTION_OPTION_DIV_NO_BORDER_STYLE);
		}
		
		int avilable_space_for_question_section = getAvailableWidthForQuestionOptionSection();
		
		questionOptionDivStyle.append("width:"+avilable_space_for_question_section+"px");
		
		formQuestionOptionsHtmlStr.append("<div id=\"question-option-div\" style=\""+questionOptionDivStyle.toString()+"\">");
		
		List<ColumnDetail> columnDetailList = currentPageDetail.getColumnList();
		
		for (ColumnDetail columnDetail : columnDetailList) {
			
			createQuestionOptionSectionColumn(columnDetail.getColumnWidth(),
					columnDetail.getLowerBound(), columnDetail.getUppderBound());
		}
		
		formQuestionOptionsHtmlStr.append("</div>");
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
		
		formQuestionOptionsHtmlStr.append("<div id=\"question-option-column-div\" style=\""+questionOptionColumnStyle.toString()+"\">");
		
		List<SimpleTemplateQuestionChoice> question_opts = questionSection.getQuestion_opts();
	
		for (int i = questionLowerBound - 1; i < questionUpperBound; i++) {
			
			if(i < question_opts.size()) {
				
				SimpleTemplateQuestionChoice questionChoice = question_opts.get(i);
				
				createQuestionOptionRow(questionChoice,
						width_of_question_option_column, i+1);
			}			
		}

		formQuestionOptionsHtmlStr.append("</div>");
	}
	
	
	/**
	 * Method to create question options row. Method will create question label
	 * and circles as options
	 */
	private void createQuestionOptionRow(
			SimpleTemplateQuestionChoice questionChoice,
			int width_of_question_option_column, int counter) {
		
		StringBuilder questionOptionRowStyle = new StringBuilder(
				SimpleTemplateBodyDesignConstants.QUESTION_OPTION_ROW_DIV_STYLE);

		int min_width = width_of_question_option_column
				- SimpleTemplateBodyDesignConstants.QUESTION_OPTION_ROW_DIV_MIN_WIDTH_CONSTANT;

		questionOptionRowStyle.append("min-width:" + min_width + "px");
		
		formQuestionOptionsHtmlStr.append("<div id=\"question-option-row-div\" style=\""+questionOptionRowStyle.toString()+"\">");
		
		//If label is not provided in JSON then default label is 1,2,3..
		String questionLabel = questionChoice.getLabel();
		
		if(questionLabel == null) {
			
			questionLabel = new Integer(counter).toString() + ".";
		}
		
		createQuestionOptionLabel(questionLabel);
		
		char choiceArr[] = questionChoice.getChoices().toCharArray();
		
		for(char c : choiceArr) {
			createQuestionOptionCircle(new Character(c).toString());
		}
		
		formQuestionOptionsHtmlStr.append("</div>");
	}
	
	
	/**
	 * Method to create question label
	 * @param label
	 */
	private void createQuestionOptionLabel(String label) {
		
		formQuestionOptionsHtmlStr.append("<div id=\"question-option-column-div\" style=\""+SimpleTemplateBodyDesignConstants.QUESTION_LABEL_DIV_STYLE+"\">");
		
		formQuestionOptionsHtmlStr.append(label);
		
		formQuestionOptionsHtmlStr.append("</div>");
	}
	
	
	private void createQuestionOptionCircle(String text) {
		
		formQuestionOptionsHtmlStr.append("<div id=\"question-option-column-div\" style=\""+SimpleTemplateBodyDesignConstants.CIRCLE_WITH_TEXT_STYLE_FOR_QUESTION+"\">");
		
		formQuestionOptionsHtmlStr.append(text);
		
		formQuestionOptionsHtmlStr.append("</div>");
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
			
			//To avoid ArrayIndexOutOfBoundException
			if(i < question_opts.size()) {
				
				SimpleTemplateQuestionChoice questionChoice = question_opts.get(i);

				char[] chrArr = questionChoice.getChoices().toCharArray();
				int arrLength = chrArr.length;

				if (arrLength > max) {
					max = arrLength;
				}
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


	/**
	 * Method prepares a list of PageDetail objects which contains how many
	 * question option columns are on a page and question ranges in each column
	 */
	@Override
	public List<PageDetail> createPageDetails() {
		
		List<PageDetail> pageDetailList = new ArrayList<PageDetail>();

		int max_avilable_space_for_question_section = getAvailableWidthForQuestionOptionSection();

		int remaining_avilable_space_for_question_section = max_avilable_space_for_question_section;

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

		PageDetail pageDetail = new PageDetail();
		int pageCounter = 1;
		boolean pageCreated = false;

		for (int n = 1; n <= max_columns_can_be_created; n++) {

			int an_lower_bound = a_lower_bound + (n - 1) * d;
			int an_upper_bound = a_upper_bound + (n - 1) * d;

			// Maximum width for a question option column in range
			// an_lower_bound -
			// an_upper_bound
			int an_column_width = calculateMaxWidthForQuestionOptionColumn(
					an_lower_bound, an_upper_bound);

			// If column can be fit in available space
			if (an_column_width > max_avilable_space_for_question_section) {

				System.out.println("Question can not fit in any of the page");

			} else {

				pageCreated = true;

				if (an_column_width <= remaining_avilable_space_for_question_section) {
					
					addColumnToPageDetail(pageDetail, an_column_width,
							an_upper_bound, an_lower_bound);
					
					remaining_avilable_space_for_question_section -= an_column_width;
					
				} else {
					
					// current page is completed so add it to list
					pageDetail.setPageNo(pageCounter);
					pageDetailList.add(pageDetail);
					
					// Create new page
					pageCounter++;
					pageDetail = new PageDetail();
					
					addColumnToPageDetail(pageDetail, an_column_width,
							an_upper_bound, an_lower_bound);
					
					remaining_avilable_space_for_question_section = max_avilable_space_for_question_section
							- an_column_width;
				}
			}
		}

		if (pageCreated) {
			pageDetail.setPageNo(pageCounter);
			pageDetailList.add(pageDetail); // add last page to list
		}		
		
		return pageDetailList;
	}
	
	private void addColumnToPageDetail(PageDetail pageDetail, int columnWidth, int ub, int lb) {
		
		ColumnDetail columnDetail = new ColumnDetail();
		
		columnDetail.setColumnWidth(columnWidth);
		
		columnDetail.setLowerBound(lb);
		
		columnDetail.setUppderBound(ub);
		
		pageDetail.getColumnList().add(columnDetail);
	}
	
	
	/**
	 * Method to start generation of form id section
	 */
	private void createBottomOfBodyDiv() {
	
		formBodyHtmlStr.append("<div id=\"bottom-div\" style=\""+SimpleTemplateBodyDesignConstants.BOTTOM_DIV_STYLE+"\">");
		
		createFromIdLabel();
		
		createFormId();
		
		formBodyHtmlStr.append(formIdHtmlStr);
		
		formBodyHtmlStr.append("</div>"); //End bottom div
	}
	
	
	/**
	 *  Method to create form id section label
	 */
	private void createFromIdLabel() {
		
		formIdHtmlStr = new StringBuilder("");
		
		formIdHtmlStr.append("<div id=\"bottom-label-div\" style=\""+SimpleTemplateBodyDesignConstants.BOTTOM_LABEL_DIV_STYLE+"\">");
		
		formIdHtmlStr.append(SimpleTemplateBodyDesignConstants.BOTTOM_LABEL_DIV_TEXT);
		
		formIdHtmlStr.append("</div>"); //End bottom label div
	}
	
	
	/**
	 * Method to start generation of form id
	 */
	private void createFormId() {
		
		formIdHtmlStr.append("<div id=\"bottom-identifier-div\" style=\""+SimpleTemplateBodyDesignConstants.BOTTOM_IDENTIFIER_DIV_STYLE+"\">");
		
		createTemplateIdSection();
		
		createClientIdSection();
		
		createFormIdSection();
		
		createPageNumberSection();
		
		formIdHtmlStr.append("</div>"); 
	}
	
	
	private void createTemplateIdSection() {
		
		formIdHtmlStr.append("<div id=\"template-id-wrapper-div\" style=\""+SimpleTemplateBodyDesignConstants.TEMPLATE_ID_WRAPPER_DIV_STYLE+"\">");
		
		createNumberCircles(SimpleTemplateBodyDesignConstants.TEMPLATE_ID_LENGTH, jsonParent.getTemplateId());
		
		formIdHtmlStr.append("</div>"); 
	}
	
	
	private void createClientIdSection() {
		
		formIdHtmlStr.append("<div id=\"client-id-wrapper-div\" style=\""+SimpleTemplateBodyDesignConstants.CLIENT_ID_WRAPPER_DIV_STYLE+"\">");		
		
		createNumberCircles(SimpleTemplateBodyDesignConstants.CLIENT_ID_LENGTH, jsonParent.getClientId());
		
		formIdHtmlStr.append("</div>"); 
	}
	
	
	private void createFormIdSection() {
		
		formIdHtmlStr.append("<div id=\"form-id-wrapper-div\" style=\""+SimpleTemplateBodyDesignConstants.FORM_ID_WRAPPER_DIV_STYLE+"\">");		
		
		createNumberCircles(SimpleTemplateBodyDesignConstants.FORM_ID_LENGHT, formId);
		
		formIdHtmlStr.append("</div>");
	}
	
	
	private void createPageNumberSection() {
		
		formIdHtmlStr.append("<div id=\"page-wrapper-div\" style=\""+SimpleTemplateBodyDesignConstants.PAGE_WRAPPER_DIV_STYLE+"\">");		
		
		createNumberCircles(SimpleTemplateBodyDesignConstants.PAGE_NUMBER_LENGTH, currentPageDetail.getPageNo());
		
		formIdHtmlStr.append("</div>");
	}
	
	
	/**
	 * Method to generate a map which contains the styles for number used in
	 * form id section
	 */
	private void initalizeNumbersMap() {
		numbersMap.put("0", SimpleTemplateBodyDesignConstants.DRAW_ZERO);
		numbersMap.put("1", SimpleTemplateBodyDesignConstants.DRAW_ONE);
		numbersMap.put("2", SimpleTemplateBodyDesignConstants.DRAW_TWO);
		numbersMap.put("3", SimpleTemplateBodyDesignConstants.DRAW_THREE);
		numbersMap.put("4", SimpleTemplateBodyDesignConstants.DRAW_FOUR);
		numbersMap.put("5", SimpleTemplateBodyDesignConstants.DRAW_FIVE);
		numbersMap.put("6", SimpleTemplateBodyDesignConstants.DRAW_SIX);
		numbersMap.put("7", SimpleTemplateBodyDesignConstants.DRAW_SEVEN);
		numbersMap.put("8", SimpleTemplateBodyDesignConstants.DRAW_EIGHT);
		numbersMap.put("9", SimpleTemplateBodyDesignConstants.DRAW_NINE);
	}
	
	
	/**
	 * Method to create the circles in form id section
	 * 
	 * @param length
	 *            - Max digits in the id(client id, template id, form id, page
	 *            no)
	 * @param number
	 *            - Current id
	 */
	private void createNumberCircles(int length, Number number) {
		
		String numberStr = SimpleTemplateUtil.converToFormIdString(number, length);
		
		char chrArr[] = numberStr.toCharArray(); 
		
		for(int i=0; i<length; i++) {
			
			formIdHtmlStr.append("<div id=\"number-container\" style=\""+SimpleTemplateBodyDesignConstants.NUMBER_CONTAINER_DIV_STYLE+"\">");
			
			formIdHtmlStr.append(numbersMap.get(new Character(chrArr[i]).toString()));
			
			formIdHtmlStr.append("</div>"); 
		}
	}
	
	
	private void createFooter() {
		
		formFooterHtmlStr.append("<div id=\"footer-div\" style=\""+SimpleTemplateBodyDesignConstants.FOOTER_DIV_STYLE+"\">");
		
		formFooterHtmlStr.append(bottom);
		
		formFooterHtmlStr.append("</div>"); 
	}
	
	
	/**
	 *  Method to calculate the height of student id option section
	 * @return
	 */
	private int getHeightOfStudentIdOptionSection() {
		
		int no_of_options_in_id = sections_topright.getChoices().toCharArray().length;
		
		return SimpleTemplateBodyDesignConstants.STUDENT_ID_OPTION_COLUMN_HEIGHT * no_of_options_in_id;
	}
	
	
	/**
	 * Method to get the height of the question option section for the current
	 * page
	 * 
	 * @return
	 */
	private int getHeightOfQuestionOptionSection() {

		ColumnDetail columnDetail = currentPageDetail.getColumnList().get(0);

		int no_of_questions_in_column = (columnDetail.getUppderBound() - columnDetail
				.getLowerBound()) + 1;

		return (no_of_questions_in_column * SimpleTemplateBodyDesignConstants.QUESTION_OPTION_ROW_HEIGHT)
				+ SimpleTemplateBodyDesignConstants.QUESTION_OPTION_COLUMN_DIV_TOP_MARGIN;
	}


	@Override
	public List<PageDetail> getPageDetailList() {
		return pageDetailList;
	}

}
