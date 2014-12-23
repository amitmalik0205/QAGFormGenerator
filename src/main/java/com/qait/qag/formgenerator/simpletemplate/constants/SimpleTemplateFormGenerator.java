package com.qait.qag.formgenerator.simpletemplate.constants;

import java.util.List;

import com.google.gson.annotations.Expose;
import com.qait.qag.formgenerator.simpletemplate.domain.SimpleTemplateFormSpec;
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

	public SimpleTemplateFormGenerator(SimpleTemplateJsonParent jsonParent) {
		this.jsonParent = jsonParent;
		this.instances = jsonParent.getInstances();
		this.formCode = jsonParent.getFormSpec().getFormCode();
		this.questionSection = jsonParent.getFormSpec().getQuestionSection();
		this.sections_topright = jsonParent.getFormSpec().getSections_topright();
		this.no_of_questions = questionSection.getCount();

		this.max_number_of_options = SimpleTemplateUtil
				.getMaximumQuestionOptions(questionSection.getQuestion_opts());
	}

	public void generateForm() {

	}
	
	
	/**
	 * Method to find the width of one question option column
	 * 
	 * @param num_of_options
	 *            - no of options in a row
	 * @return
	 */
	private int getQuestionOptionSectionColumnWidth() {
		int width = SimpleTemplateConstants.WIDTH_OF_QUESTION_LABEL
				+ SimpleTemplateConstants.DISTANCE_BTW_LABEL_AND_FIRST_CIRCLE
				+ (max_number_of_options * (SimpleTemplateConstants.DIAMETER_OF_CIRCLE + SimpleTemplateConstants.DISTANCE_BTW_TWO_CIRCLE_COLUMNS));

		return width;
	}
	
	
	/**
	 * Method to get in how many columns we have to show the question options
	 * 
	 * @param no_of_questions
	 * @return
	 */
	private int getCountOfQuestionOptionSectionColumns() {
		// Need to do validations
		return no_of_questions / 2;
	}

	
	/**
	 * Method to get the width of the left section
	 * @return
	 */
	private int getWidthOfLeftSection() {
		int no_of_columns = getCountOfQuestionOptionSectionColumns();

		return (no_of_columns * SimpleTemplateConstants.HORIZENTAL_DISTANCE_BTW_LABEL_AND_OUTER_LINE)
				+ (no_of_columns * getQuestionOptionSectionColumnWidth())
				+ SimpleTemplateConstants.DISTANCE_BTW_LAST_CIRCLE_AND_RIGHT_SECTION;
	}
	
	
	/**
	 * Method to get the width of the column in right section
	 * @return
	 */
	private int getWidthOfColumnInRightSection() {
		return 2
				* SimpleTemplateConstants.DISTANCE_BTW_INNER_CIRCLE_AND_INNER_LINE
				+ SimpleTemplateConstants.DIAMETER_OF_CIRCLE;
	}
	
	/**
	 * Method will return width of the right section
	 * @return
	 */
	private int getWidthOfRightSection() {
		int no_of_digits_in_id = sections_topright.getDigits();
		
		return no_of_digits_in_id * getWidthOfColumnInRightSection();
	}
	
	
	/**
	 * Method to get height of the question option section
	 * @return
	 */
	private int getHeightOfQuestionSection() {
		return no_of_questions
				* (SimpleTemplateConstants.DIAMETER_OF_CIRCLE + SimpleTemplateConstants.DISTANCE_BTW_TWO_CIRCLE_ROWS);
	}
	
	
	/**
	 * Method to calculate the height of Form ID Section
	 * @return
	 */
	private int getHeightOfFormIdSection() {
		return SimpleTemplateConstants.DISTANCE_BTW_FORM_ID_LABEL_AND_UPPER_SECTION_LINE
				+ SimpleTemplateConstants.HEIGHT_OF_FORM_ID_LABEL
				+ SimpleTemplateConstants.DISTANCE_BTW_FORM_ID_LABEL_AND_FORM_ID_CIRCLES
				+ SimpleTemplateConstants.DISTANCE_BTW_TWO_CIRCLE_ROWS
				+ SimpleTemplateConstants.DISTANCE_BTW_FORM_ID_SECTION_AND_LOWER_OUTER_LINE;
	}
	
	/**
	 * Method to calculate the height of the Left section
	 * @return
	 */
	private int getHeightOfLeftSection() {
		return SimpleTemplateConstants.VERTICAL_DISTANCE_BTW_LABEL_AND_OUTER_LINE
				+ getHeightOfQuestionSection()
				+ SimpleTemplateConstants.VERTICAL_DISTANCE_OF_FORM_ID_SECTION_FROM_UPPER_SECTION
				+ getHeightOfFormIdSection();
	}
	
	
	private int getNumberOfCirclesInColumnInRightSection() {
		return sections_topright.getChoices().length();
	}
	
	/**
	 * Method to get the height of the Right Section
	 * 
	 * @return
	 */
	private int getHeightOfRightSection() {
		int no_of_circles_in_column = getNumberOfCirclesInColumnInRightSection();

		return SimpleTemplateConstants.VERTICAL_DISTANCE_OF_INNER_CIRCLE_FROM_OUTER_LINE
				+ (no_of_circles_in_column * (SimpleTemplateConstants.DIAMETER_OF_CIRCLE + SimpleTemplateConstants.VERTICAL_DISTANCE_BTW_TWO_INNER_CIRCLES));
	}
	
	public void createOuterBoundary() {

	}

}
