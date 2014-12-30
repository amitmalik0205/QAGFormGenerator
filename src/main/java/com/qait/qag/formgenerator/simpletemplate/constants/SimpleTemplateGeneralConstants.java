package com.qait.qag.formgenerator.simpletemplate.constants;

public interface SimpleTemplateGeneralConstants {

	//container div constants	
	int CONTAINER_DIV_MAX_WIDTH = 600;
	
	int CONTAINER_DIV_MIN_WIDTH = 500;
	
	String CONTAINER_DIV_STYLE = "min-width:"+CONTAINER_DIV_MIN_WIDTH+"px; max-width:"+CONTAINER_DIV_MAX_WIDTH+"px";
	
	//Black Circle
	String CIRCLE_BLACK_STYLE = "border:2px solid; width:14px; height:14px; line-height:14px; border-radius:9px; "
			+ "margin:6px 6px 0px; background-color: rgb(0, 0, 0)";
	
	
	//circle with text
	String CIRCLE_WITH_TEXT_STYLE = "border:2px solid; width:14px; height:14px; line-height:14px; border-radius:9px; "
			+ "margin:6px 6px 0px; font-family:Arial; font-size:14px; text-align:center;";
}
