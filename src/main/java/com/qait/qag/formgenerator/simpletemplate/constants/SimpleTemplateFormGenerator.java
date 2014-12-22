package com.qait.qag.formgenerator.simpletemplate.constants;

import com.qait.qag.formgenerator.simpletemplate.domain.SimpleTemplateJsonParent;

public class SimpleTemplateFormGenerator {
	
	private SimpleTemplateJsonParent jsonParent;
	
	public SimpleTemplateFormGenerator(SimpleTemplateJsonParent jsonParent) {
		this.jsonParent = jsonParent;
	}
	
	public void generateForm() {
		
	}
	
	public void createOuterBoundary() {
		
		int width_of_question_options_section = SimpleTemplateConstants.WIDTH_OF_QUESTION_LABEL 
				+ SimpleTemplateConstants.DISTANCE_BTW_LABEL_AND_FIRST_CIRCLE
				+ (SimpleTemplateConstants.NO_OF_QUESTIONS * (SimpleTemplateConstants.DIAMETER_OF_CIRCLE + SimpleTemplateConstants.DISTANCE_BTW_TWO_CIRCLE_COLUMNS));
		
		int width_of_left_section = SimpleTemplateConstants.HORIZENTAL_DISTANCE_BTW_LABEL_AND_OUTER_LINE
				+ width_of_question_options_section + SimpleTemplateConstants.DISTANCE_BTW_LAST_CIRCLE_AND_RIGHT_SECTION;
		
		int width_of_right_section_first_column = SimpleTemplateConstants.DISTANCE_BTW_FIRST_INNER_CIRCLE_AND_RIGHT_SECTION_LEFT_LINE 
				+ SimpleTemplateConstants.DIAMETER_OF_CIRCLE + SimpleTemplateConstants.DISTANCE_BTW_INNER_CIRCLE_AND_INNER_LINE;
		
		int width_of_right_section_inner_column = 2 * SimpleTemplateConstants.DISTANCE_BTW_INNER_CIRCLE_AND_INNER_LINE 
				+ SimpleTemplateConstants.DIAMETER_OF_CIRCLE;
		
		int width_of_right_section;
		String studentIdOptions = SimpleTemplateConstants.STUDENT_ID_OPTIONS;
		char [] chrArr = studentIdOptions.toCharArray();
		
		int length_of_student_Id = chrArr.length;
		int no_of_inner_columns_in_right_section = length_of_student_Id -2;
		
		if(no_of_inner_columns_in_right_section >= 0) {
			 width_of_right_section = width_of_right_section_first_column
					 + (no_of_inner_columns_in_right_section * width_of_right_section_inner_column)
					+ width_of_right_section_first_column;
		} else {
			
			width_of_right_section = width_of_right_section_first_column + width_of_right_section_first_column;
		}
		
		
		
		int width_of_outer_boundary = width_of_right_section + width_of_left_section;
		
		
		int length_of_question_options_section = SimpleTemplateConstants.NO_OF_QUESTIONS *
				(SimpleTemplateConstants.DIAMETER_OF_CIRCLE + SimpleTemplateConstants.DISTANCE_BTW_TWO_CIRCLE_ROWS);
		
		int length_of_right_section = SimpleTemplateConstants.VERTICALL_DISTANCE_BTW_LABEL_AND_OUTER_LINE
				+ length_of_question_options_section;
		
	}
	
}
