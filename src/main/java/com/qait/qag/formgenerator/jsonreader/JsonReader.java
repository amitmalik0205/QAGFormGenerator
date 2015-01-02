package com.qait.qag.formgenerator.jsonreader;

import java.io.FileReader;

import com.google.gson.Gson;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import com.qait.qag.formgenerator.simpletemplate.constants.SimpleTemplateConstants;
import com.qait.qag.formgenerator.simpletemplate.domain.SimpleTemplateJsonParent;
import com.qait.qag.formgenerator.simpletemplate.generator.SimpleTemplateFormGenerator;

public class JsonReader {

	public static void main(String[] args) {
		
		JsonParser jsonParser = new JsonParser();
		
		try {
			
			JsonObject jsonObject = (JsonObject)jsonParser.parse(new FileReader("C:\\Amit\\QAG\\long_json_2.txt"));
			String jsonStr = jsonObject.toString();
			
			JsonElement jsonElement = jsonObject.get(SimpleTemplateConstants.TEMPLATE_ID_TAG);
			int templateId = jsonElement.getAsInt();
			
			if(templateId == 1) {
				SimpleTemplateJsonParent  jsonParent = new Gson().fromJson(jsonStr, SimpleTemplateJsonParent.class);
				SimpleTemplateFormGenerator formGenerator = new SimpleTemplateFormGenerator(jsonParent);
				formGenerator.generateForm();
			}
			
		} catch (Exception e) {
			e.printStackTrace();
		}
	}	
}
