package com.qait.qag.formgenerator.simpletemplate.constants;


//header-div - Div containing the student name section(student-name-div), student id section(student-id-div)

//body-div -- Div containing the top-div and bottom-div
//top-div - Contains two parts question options section(left-option-div), student id options section(right-option-div)
//bottom-div - contains form id section

public interface SimpleTemplateHeaderDesignConstants {
	
	//Styles for student name section under header section
	String HEADER_DIV_STYLE = "display:inline-block; width:600px";
	
	String STUDENT_NAME_DIV_STYLE = "float:left; width:300px; margin-top:20px";
	
	
	//Styles for student name section under header section
	String STUDENT_ID_DIV_STYLE = "float:right";
	
	String STUDENT_ID_TABLE_WRAPPER_DIV_STYLE = "width:100%; border:1px solid #000000";
	
	String STUDENT_ID_TABLE_STYLE = "border-collapse:collapse; border-spacing:0; width:100%; height:100%";
	
	int STUDENT_ID_TABLE_TD_WIDTH = 25;
	
	String STUDENT_ID_LABEL = "Student Id";
	

	/*String BODY_DIV_STYLE = "height:540px; width:600px; border:4px solid; border-radius:5px";
	
	String TOP_DIV_STYLE = "display:inline-block; height:447px; width:600px;";
	
	String LEFT_OPTION_DIV_STYLE = "float:left; height:420px; width:130px; margin:30px 0 0 30px;";
	
	String RIGHT_OPTION_DIV_STYLE = "float:right; height:416px; width:185px; border:2px solid; border-right: none; border-top: none; padding-top: 10px";*/
}
