package com.qait.qag.formgenerator.simpletemplate.constants;

public interface SimpleTemplateGeneralConstants {

	//container div constants	
	int CONTAINER_DIV_MAX_WIDTH = 600;
	
	int CONTAINER_DIV_MIN_WIDTH = 500;
	
	String CONTAINER_DIV_STYLE = "min-width:"+CONTAINER_DIV_MIN_WIDTH+"px; max-width:"+CONTAINER_DIV_MAX_WIDTH+"px";
	
	int MAX_QUESTION_IN_COLUMN = 20;
	
	int INITIAL_LOWER_BOUND = 1;	
	
	int INITIAL_UPPER_BOUND = MAX_QUESTION_IN_COLUMN;
	
	int COMMON_DIFFERENCE = MAX_QUESTION_IN_COLUMN;
}
