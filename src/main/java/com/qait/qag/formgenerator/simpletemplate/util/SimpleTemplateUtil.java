package com.qait.qag.formgenerator.simpletemplate.util;

import java.util.List;

import com.qait.qag.formgenerator.simpletemplate.domain.SimpleTemplateQuestionChoice;

public class SimpleTemplateUtil {

	public static int getMaximumQuestionOptions(List<SimpleTemplateQuestionChoice> question_opts) {
		int max = 0;
		
		for(SimpleTemplateQuestionChoice questionChoice : question_opts) {
			
			char [] chrArr = questionChoice.getChoices().toCharArray();
			int arrLength = chrArr.length;
			
			if(arrLength > max) {
				max = arrLength;
			}
		}		
		return max;
	}
	
	
}
