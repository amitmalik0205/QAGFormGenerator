package com.qait.qag.formgenerator.jsonreader;

import java.io.FileReader;

import org.apache.log4j.Logger;

import com.google.gson.Gson;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import com.qait.qag.formgenerator.common.util.QAGFormGeneratorUtil;
import com.qait.qag.formgenerator.generator.ITemplateFrontController;
import com.qait.qag.formgenerator.simpletemplate.constants.SimpleTemplateGeneralConstants;
import com.qait.qag.formgenerator.simpletemplate.domain.SimpleTemplateJsonParent;
import com.qait.qag.formgenerator.simpletemplate.generator.SimpleTemplateFrontController;

public class JsonReader {
	
	final static Logger logger = Logger.getLogger(JsonReader.class);

	public static void main(String[] args) {
		
		JsonParser jsonParser = new JsonParser();
		
		try {
					
			JsonObject jsonObject = (JsonObject)jsonParser.parse(new FileReader("C:\\Amit\\QAG\\long_json_2.txt"));
			String jsonStr = jsonObject.toString();
								
			JsonElement templateIdJsonElement = jsonObject.get(SimpleTemplateGeneralConstants.TEMPLATE_ID_TAG);
			int templateId = templateIdJsonElement.getAsInt();
			
			if(templateId == 2) {
				
				SimpleTemplateJsonParent  jsonParent = new Gson().fromJson(jsonStr, SimpleTemplateJsonParent.class);
							
				/*Type type = new TypeToken<List<SimpleTemplateQuestionChoice>>() {}.getType();
				List<SimpleTemplateQuestionChoice> choices = new Gson().fromJson(str, type);*/
				
				ITemplateFrontController frontController = new SimpleTemplateFrontController(jsonParent);
				
				frontController.startFormGeneration();
			}
			
		} catch (Exception e) {
			e.printStackTrace();
			logger.fatal(QAGFormGeneratorUtil.getExceptionDescriptionString(e));
		}
	}	
}
