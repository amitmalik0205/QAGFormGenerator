package com.qait.qag.formgenerator.simpletemplate.constants;

public interface SimpleTemplateBodyDesignConstants {

	//Wrapper div for body
	String BODY_DIV_STYLE = "width:100%";
	
	//Wrapper div for question options and student id options
	String TOP_DIV_STYLE = "display:inline-block; width:100%; border: 3px solid";
	
	
	
	/*
	 * Constants for student id options section
	 */	
	//Wrapper div for student id options section
	String STUDENT_ID_OPTION_CONTAINER_STYLE = "display:inline-block; float:right; border:1px solid; border-top:none; border-right:none; ";		
	
	String STUDENT_ID_OPTION_COLUMN_CONTAINER_STYLE = "float:left; text-align:center; border-right-width:1px; "
			+ "border-right-style:solid; border-right-color:rgb(0, 0, 0);";
	
	//Black Circle
	String CIRCLE_BLACK_STYLE = "border:2px solid; width:14px; height:14px; line-height:14px; border-radius:9px; "
			+ "margin:6px 6px 0px; background-color: rgb(0, 0, 0)";
	
	
	//circle with text for student id option
	String CIRCLE_WITH_TEXT_STYLE_FOR_STUDENT_ID = "border:2px solid; width:14px; height:14px; line-height:14px; border-radius:9px; "
			+ "margin:6px 6px 0px; font-family:Arial; font-size:14px; text-align:center;";
	
	int STUDENT_ID_OPTION_COLUMN_WIDTH = 31;
	
	
	/*
	 * Constants for Question option section
	 */
	String QUESTION_OPTION_DIV_STYLE = "float:left; min-width:20%; ";
	
	
	int QUESTION_OPTION_DIV_WIDTH_CONSTANT = 3;
	
	// Vertical distance between container and question option section
	// QUESTION_OPTION_COLUMN_DIV_TOP_MARGIN value must be set for
	// QUESTION_OPTION_COLUMN_DIV_STYLE margin-top
	String QUESTION_OPTION_COLUMN_DIV_STYLE = "margin-top:20px; float:left; ";
	int QUESTION_OPTION_COLUMN_DIV_TOP_MARGIN = 20;

	// Vertical distance between two question options rows
	// MARGIN_BTW_TWO_QUESTION_OPTIONS_ROW value must be set for
	// QUESTION_OPTION_ROW_DIV_STYLE margin-bottom
	String QUESTION_OPTION_ROW_DIV_STYLE = "margin-bottom:6px; line-height:20px; display:inline-block; ";
	int MARGIN_BTW_TWO_QUESTION_OPTIONS_ROW = 6;
	int QUESTION_OPTION_ROW_DIV_MIN_WIDTH_CONSTANT = 10;
	
	// QUESTION_LABEL_WIDTH value must be set for QUESTION_LABEL_DIV_STYLE width
	String QUESTION_LABEL_DIV_STYLE = "float:left; margin-right:3px; width:25px; text-align:right;";
	int QUESTION_LABEL_WIDTH = 25;
	
	//circle with text for student id option
	String CIRCLE_WITH_TEXT_STYLE_FOR_QUESTION = "border:2px solid; width:14px; height:14px; line-height:14px; text-align:center; "
			+ "font-family:Arial; font-size:14px; border-radius:9px; float:left; margin-right:4px; margin-bottom:2px;";
	
	int QUESTION_OPTION_CIRCLE_WIDTH = 23;
	
	
	/*
	 * Constants for Form id section
	 */
	
	String FORM_ID_SECTION_CIRCLE_STYLE = "border:2px solid; width:14px; height:14px; line-height:14px; border-radius:9px; "
			+ "margin:6px 6px 0px; font-family:Arial; font-size:14px; float:left;";
	
	//Height of NUMBER_CONTAINER_DIV_STYLE, TEMPLATE_ID_WRAPPER_DIV_STYLE
	//CLIENT_ID_WRAPPER_DIV_STYLE, FORM_ID_WRAPPER_DIV_STYLE, PAGE_WRAPPER_DIV_STYLE
	//must be same
	String NUMBER_CONTAINER_DIV_STYLE = "width:30px; height:51px; float:left; ";
	
	//Wrapper div for form id section
	String BOTTOM_DIV_STYLE = "border:3px solid; height:70px; margin-top:-3px; width:100%; ";
	
	String BOTTOM_LABEL_DIV_STYLE = "width:63%; margin:auto; text-align:center";
	
	String BOTTOM_LABEL_DIV_TEXT = "Form Identifier - Do not mark";
	
	String BOTTOM_IDENTIFIER_DIV_STYLE = "width:76%; margin:auto; ";
	
	//Wrapper div for template id
	String TEMPLATE_ID_WRAPPER_DIV_STYLE = "height:51px; float:left; display:inline-block; width:90px; ";
	
	int TEMPLATE_ID_LENGTH = 3;
	
	//Wrapper div for client id
	String CLIENT_ID_WRAPPER_DIV_STYLE = "height:51px; float:left; display:inline-block; width:120px;";
	
	int CLIENT_ID_LENGTH = 4;
	
	//Wrapper div for form id
	String FORM_ID_WRAPPER_DIV_STYLE = "height:51px; float:left; display:inline-block; width:210px;";
	
	int FORM_ID_LENGHT = 7;
	
	//Wrapper div for no of pages
	String PAGE_WRAPPER_DIV_STYLE = "height:51px; float:left; display:inline-block; width:30px;";
	
	int PAGE_NUMBER_LENGTH = 1;
	
	
	/*
	 * Form Id number constants
	*/
	//Number of circles to repersent a number
	int CIRCLES_FOR_A_NUMBER = 2;
	
	String CIRCLE_IMAGE_PATH = "file:///C:/Users/amitmalik/Downloads/OMRImage2.png";
	
	String ZERO_IMAGE_POSITION = "-0px -196px";
	
	String ONE_IMAGE_POSITION = "-0px -28px";
	
	String TWO_IMAGE_POSITION = "-0px -56px";
	
	String THREE_IMAGE_POSITION = "-0px -84px";
	
	String FOUR_IMAGE_POSITION = "-0px -112px";
	
	String FIVE_IMAGE_POSITION = "-0px -140px";

	String DRAW_ZERO = "<div style=\"width:18px; height:18px; margin:6px 6px 0px; float:left; background-image:url("+CIRCLE_IMAGE_PATH+"); "
			+ "background-position:"+ZERO_IMAGE_POSITION+"; \"></div>" +
			"<div style=\"width:18px; height:18px; margin:6px 6px 0px; float:left; background-image:url("+CIRCLE_IMAGE_PATH+"); "
			+ "background-position:"+ZERO_IMAGE_POSITION+"; \"></div>";
	
	String DRAW_ONE = "<div style=\"width:18px; height:18px; margin:6px 6px 0px; float:left; background-image:url("+CIRCLE_IMAGE_PATH+"); "
			+ "background-position:"+ONE_IMAGE_POSITION+"; \"></div>" +
			"<div style=\"width:18px; height:18px; margin:6px 6px 0px; float:left; background-image:url("+CIRCLE_IMAGE_PATH+"); "
			+ "background-position:"+ZERO_IMAGE_POSITION+"; \"></div>";
	
	String DRAW_TWO = "<div style=\"width:18px; height:18px; margin:6px 6px 0px; float:left; background-image:url("+CIRCLE_IMAGE_PATH+"); "
			+ "background-position:"+TWO_IMAGE_POSITION+"; \"></div>" +
			"<div style=\"width:18px; height:18px; margin:6px 6px 0px; float:left; background-image:url("+CIRCLE_IMAGE_PATH+"); "
			+ "background-position:"+ZERO_IMAGE_POSITION+"; \"></div>";
	
	String DRAW_THREE = "<div style=\"width:18px; height:18px; margin:6px 6px 0px; float:left; background-image:url("+CIRCLE_IMAGE_PATH+"); "
			+ "background-position:"+THREE_IMAGE_POSITION+"; \"></div>" +
			"<div style=\"width:18px; height:18px; margin:6px 6px 0px; float:left; background-image:url("+CIRCLE_IMAGE_PATH+"); "
			+ "background-position:"+ZERO_IMAGE_POSITION+"; \"></div>";
	
	String DRAW_FOUR = "<div style=\"width:18px; height:18px; margin:6px 6px 0px; float:left; background-image:url("+CIRCLE_IMAGE_PATH+"); "
			+ "background-position:"+FOUR_IMAGE_POSITION+"; \"></div>" +
			"<div style=\"width:18px; height:18px; margin:6px 6px 0px; float:left; background-image:url("+CIRCLE_IMAGE_PATH+"); "
			+ "background-position:"+ZERO_IMAGE_POSITION+"; \"></div>";
	
	String DRAW_FIVE = "<div style=\"width:18px; height:18px; margin:6px 6px 0px; float:left; background-image:url("+CIRCLE_IMAGE_PATH+"); "
			+ "background-position:"+FIVE_IMAGE_POSITION+"; \"></div>" +
			"<div style=\"width:18px; height:18px; margin:6px 6px 0px; float:left; background-image:url("+CIRCLE_IMAGE_PATH+"); "
			+ "background-position:"+ZERO_IMAGE_POSITION+"; \"></div>";
	
	String DRAW_SIX = "<div style=\"width:18px; height:18px; margin:6px 6px 0px; float:left; background-image:url("+CIRCLE_IMAGE_PATH+"); "
			+ "background-position:"+FIVE_IMAGE_POSITION+"; \"></div>" +
			"<div style=\"width:18px; height:18px; margin:6px 6px 0px; float:left; background-image:url("+CIRCLE_IMAGE_PATH+"); "
			+ "background-position:"+ONE_IMAGE_POSITION+"; \"></div>";
	
	String DRAW_SEVEN = "<div style=\"width:18px; height:18px; margin:6px 6px 0px; float:left; background-image:url("+CIRCLE_IMAGE_PATH+"); "
			+ "background-position:"+FIVE_IMAGE_POSITION+"; \"></div>" +
			"<div style=\"width:18px; height:18px; margin:6px 6px 0px; float:left; background-image:url("+CIRCLE_IMAGE_PATH+"); "
			+ "background-position:"+TWO_IMAGE_POSITION+"; \"></div>";
	
	String DRAW_EIGHT = "<div style=\"width:18px; height:18px; margin:6px 6px 0px; float:left; background-image:url("+CIRCLE_IMAGE_PATH+"); "
			+ "background-position:"+FIVE_IMAGE_POSITION+"; \"></div>" +
			"<div style=\"width:18px; height:18px; margin:6px 6px 0px; float:left; background-image:url("+CIRCLE_IMAGE_PATH+"); "
			+ "background-position:"+THREE_IMAGE_POSITION+"; \"></div>";
	
	String DRAW_NINE = "<div style=\"width:18px; height:18px; margin:6px 6px 0px; float:left; background-image:url("+CIRCLE_IMAGE_PATH+"); "
			+ "background-position:"+FIVE_IMAGE_POSITION+"; \"></div>" +
			"<div style=\"width:18px; height:18px; margin:6px 6px 0px; float:left; background-image:url("+CIRCLE_IMAGE_PATH+"); "
			+ "background-position:"+FOUR_IMAGE_POSITION+"; \"></div>";
}
