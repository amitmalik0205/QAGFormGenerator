package com.qait.qag.formgenerator.simpletemplate.constants;

public interface SimpleTemplateConstants {
	
	public static String TEMPLATE_ID_TAG = "templateId";
	
	public static int NO_OF_QUESTIONS = 20;
	
	public static String QUESTION_OPTIONS = "abcde";
	
	public static String STUDENT_ID_OPTIONS = "abcdest23456789";
	
	public static int HORIZENTAL_DISTANCE_BTW_PAGE_AND_OUTER_LINE = 20;  // x for outer rectangle
	
	public static int VERTICAL_DISTANCE_BTW_PAGE_AND_OUTER_LINE = 30;   // y for outer rectangle

	public static int HORIZENTAL_DISTANCE_BTW_LABEL_AND_OUTER_LINE = 30;
	
	public static int VERTICALL_DISTANCE_BTW_LABEL_AND_OUTER_LINE = 30;
	
	public static int WIDTH_OF_QUESTION_LABEL = 10;
	
	public static int DISTANCE_BTW_LABEL_AND_FIRST_CIRCLE = 10;
	
	public static int DISTANCE_BTW_TWO_CIRCLE_COLUMNS = 20;
	
	public static int DISTANCE_BTW_TWO_CIRCLE_ROWS = 20;
	
	public static int RADIUS_OF_CIRCLE = 9;
	
	public static int DIAMETER_OF_CIRCLE = 2*RADIUS_OF_CIRCLE;
	
	public static int DISTANCE_BTW_LAST_CIRCLE_AND_RIGHT_SECTION = 40;
	
	public static int DISTANCE_BTW_FIRST_INNER_CIRCLE_AND_RIGHT_SECTION_LEFT_LINE=10;
	
	public static int DISTANCE_BTW_INNER_CIRCLE_AND_INNER_LINE = 10;
	
	public static int WIDTH_OF_RIGHT_SECTION_COLUMN =  2*DISTANCE_BTW_INNER_CIRCLE_AND_INNER_LINE + 2*RADIUS_OF_CIRCLE;
	
	public static int WIDTH_OF_RIGHT_SECTION_FIRST_COLUMN = DISTANCE_BTW_FIRST_INNER_CIRCLE_AND_RIGHT_SECTION_LEFT_LINE +
			2*RADIUS_OF_CIRCLE + DISTANCE_BTW_INNER_CIRCLE_AND_INNER_LINE;
	
	public static int HORIZENTAL_DISTANCE_OF_FORM_SECTION_FROM_UPPER_SECTION = 50;
	
	public static int HEIGHT_OF_FORM_SECTION = 20;
	
}