package com.qait.qag.formgenerator.common.enums;

public enum ResponseType {

	HTML("html"),  JSON("json"); 
	
	String value;

	private ResponseType(String val) {
		this.value = val;
	}

	public String getValue() {
		return value;
	}
}
