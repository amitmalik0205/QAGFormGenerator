package com.qait.qag.formgenerator.simpletemplate.constants;

public interface SimpleTemplateBodyDesignConstants {

	//Wrapper div for body
	String BODY_DIV_STYLE = "width:100%";
	
	//Wrapper div for question options and student id options
	String TOP_DIV_STYLE = "display:inline-block; width:100%";
	
	//Wrapper div for form id section
	String BOTTOM_DIV_STYLE = "border-top:2px solid; height:98px; width:600px;";
	
	
	/*
	 * Constants for student id options section
	 */	
	//Wrapper div for student id options section
	String STUDENT_ID_OPTION_CONTAINER_STYLE = "display:inline-block; float:right";		
	
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
	String QUESTION_OPTION_COLUMN_DIV_STYLE = "margin-top: 20px; ";
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
}
