package com.qait.qag.formgenerator.simpletemplate.constants;

public interface SimpleTemplateConstants {
	
	//General constants
	public static int RADIUS_OF_CIRCLE = 9;
	
	public static int DIAMETER_OF_CIRCLE = 2*RADIUS_OF_CIRCLE;
	
	public static int DISTANCE_BTW_TWO_CIRCLE_COLUMNS = 20;
	
	public static int DISTANCE_BTW_TWO_CIRCLE_ROWS = 20;
	
	public static String TEMPLATE_ID_TAG = "templateId";
	
		
	//Outer Boundary Design constants
	public static int HORIZENTAL_DISTANCE_BTW_PAGE_AND_OUTER_LINE = 70;  // x for outer rectangle
	
	public static int VERTICAL_DISTANCE_BTW_PAGE_AND_OUTER_LINE = 100;   // y for outer rectangle
	
	public static String OUTER_LINE_STROKE = "black";
	
	public static String OUTER_LINE_FILL = "white";
	
	public static int OUTER_LINE_STROKE_WIDTH = 6;
	
		
	//Student Name Label Design Constants
	public static int VERTICAL_DISTANCE_BTW_NAME_LABEL_AND_OUTER_LINE = 10;
	
	public static int HORIZENTAL_DISTANCE_BTW_NAME_LABEL_AND_OUTER_LINE = 5;
	
	public static int NAME_LABEL_X = HORIZENTAL_DISTANCE_BTW_PAGE_AND_OUTER_LINE + HORIZENTAL_DISTANCE_BTW_NAME_LABEL_AND_OUTER_LINE;
			
	public static int NAME_LABEL_Y = VERTICAL_DISTANCE_BTW_PAGE_AND_OUTER_LINE - VERTICAL_DISTANCE_BTW_NAME_LABEL_AND_OUTER_LINE;
	
	public static String NAME_LABEL_FONT_FAMILY = "Verdana";
	
	public static String NAME_LABEL_FILL = "Black";
	
	public static int NAME_LABEL_FONT_SIZE = 12;
	
	
	// Question Section Label Constants
	public static int HORIZENTAL_DISTANCE_BTW_LABEL_AND_OUTER_LINE = 40;
	
	public static int VERTICAL_DISTANCE_BTW_LABEL_AND_OUTER_LINE = 60;
	
	public static int WIDTH_OF_QUESTION_LABEL = 10;
	
	public static int DISTANCE_BTW_LABEL_AND_FIRST_CIRCLE = 10;
	

	//Right Section constants
	public static int DISTANCE_BTW_LAST_CIRCLE_AND_RIGHT_SECTION = 40;
	
	public static int DISTANCE_BTW_FIRST_INNER_CIRCLE_AND_RIGHT_SECTION_LEFT_LINE=10;
	
	public static int DISTANCE_BTW_INNER_CIRCLE_AND_INNER_LINE = 10;
	
	public static int VERTICAL_DISTANCE_OF_INNER_CIRCLE_FROM_OUTER_LINE = 5;
	
	public static int VERTICAL_DISTANCE_BTW_TWO_INNER_CIRCLES = 5;
	

	// Form ID section constants
	public static int VERTICAL_DISTANCE_OF_FORM_ID_SECTION_FROM_UPPER_SECTION = 50;
	
	public static int HEIGHT_OF_FORM_ID_SECTION = 20;
	
	public static int DISTANCE_BTW_FORM_ID_LABEL_AND_UPPER_SECTION_LINE = 10;
	
	public static int HEIGHT_OF_FORM_ID_LABEL = 20;
	
	public static int DISTANCE_BTW_FORM_ID_LABEL_AND_FORM_ID_CIRCLES = 10;
	
	public static int DISTANCE_BTW_FORM_ID_SECTION_AND_LOWER_OUTER_LINE = 10;
}