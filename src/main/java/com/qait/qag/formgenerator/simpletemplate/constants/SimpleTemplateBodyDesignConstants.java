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
	
	int STUDENT_ID_OPTION_COLUMN_WIDTH = 31;
}
