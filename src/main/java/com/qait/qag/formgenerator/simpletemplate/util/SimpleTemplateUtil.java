package com.qait.qag.formgenerator.simpletemplate.util;

import java.util.List;

import com.qait.qag.formgenerator.simpletemplate.domain.SimpleTemplateQuestionChoice;

public class SimpleTemplateUtil {

	public static int getMaximumQuestionOptions(
			List<SimpleTemplateQuestionChoice> question_opts) {
		int max = 0;

		for (SimpleTemplateQuestionChoice questionChoice : question_opts) {

			char[] chrArr = questionChoice.getChoices().toCharArray();
			int arrLength = chrArr.length;

			if (arrLength > max) {
				max = arrLength;
			}
		}
		return max;
	}
	
	
	public static String converToFormIdString(Number id, int maxLength) {
		
		StringBuilder builder = new StringBuilder("");
		
		String idStr = id.toString();
		
		int idLength = idStr.length();
		
		if(idLength < maxLength) {
			int diff = maxLength - idLength;
			
			for(int i=1; i<=diff; i++) {
				builder.append("0");
			}
		}
		
		builder.append(idStr);
		
		return builder.toString();
	}
}
