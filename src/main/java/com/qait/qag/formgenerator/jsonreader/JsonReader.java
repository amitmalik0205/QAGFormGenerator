package com.qait.qag.formgenerator.jsonreader;

import java.io.FileReader;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.List;

import org.apache.log4j.Logger;

import com.google.gson.Gson;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import com.qait.qag.formgenerator.common.util.QAGFormGeneratorDBUtil;
import com.qait.qag.formgenerator.common.util.QAGFormGeneratorUtil;
import com.qait.qag.formgenerator.simpletemplate.constants.SimpleTemplateGeneralConstants;
import com.qait.qag.formgenerator.simpletemplate.domain.PageDetail;
import com.qait.qag.formgenerator.simpletemplate.domain.SimpleTemplateJsonParent;
import com.qait.qag.formgenerator.simpletemplate.generator.SimpleTemplateFormGenerator;

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
				
				/*List<SimpleTemplateQuestionChoice> question_opts = jsonParent.getFormSpec().getQuestionSection().getQuestion_opts();
				String str = new Gson().toJson(question_opts); 
				System.out.println(str);
				
				
				Type type = new TypeToken<List<SimpleTemplateQuestionChoice>>() {}.getType();
				List<SimpleTemplateQuestionChoice> choices = new Gson().fromJson(str, type);*/
				
				SimpleTemplateFormGenerator formGenerator = new SimpleTemplateFormGenerator(jsonParent);
				formGenerator.generateForm();
				
				JsonElement clientIdJsonElement = jsonObject.get(SimpleTemplateGeneralConstants.CLIENT_ID_TAG);
				int clientId = clientIdJsonElement.getAsInt();
				
				Connection conn = QAGFormGeneratorDBUtil.getDBConnection();
				PreparedStatement  pstmt = null;
										
				if (conn != null) {
					
					List<PageDetail> pageDetailList = formGenerator.getPageDetailList();
					
					long generatedFormId = 0;

					try {

						String insertFormSql = "INSERT INTO form(generated_form_id, no_of_pages, client_id, fk_template_id) VALUES (100,"
								+ pageDetailList.size() + ","+clientId+ ","+templateId+")";

						pstmt = conn.prepareStatement(insertFormSql, Statement.RETURN_GENERATED_KEYS);
				
						int affectedRows = pstmt.executeUpdate();

				        if (affectedRows == 0) {
				            throw new SQLException("Creating form failed, no rows affected.");
				        }
				        
				        ResultSet generatedKeys = pstmt.getGeneratedKeys();

				        if (generatedKeys.next()) {
			                generatedFormId = generatedKeys.getLong(1);
			            }
			            else {
			                throw new SQLException("Creating form failed, no ID obtained.");
			            }
				        
				        
				        String insertFormPageDetailStr = "INSERT INTO form_page_detail(page_number, question_range, question_data, fk_form_id)"
				        		+"VALUES()";
				        
						conn.commit();

					} catch (SQLException se) {
						// Handle errors for JDBC
						se.printStackTrace();
						conn.rollback();
						logger.fatal(QAGFormGeneratorUtil
								.getExceptionDescriptionString(se));

					} finally {
						// finally block used to close resources
						try {
							if (pstmt != null)
								pstmt.close();
						} catch (SQLException se) {
							conn.rollback();
							logger.fatal(QAGFormGeneratorUtil
									.getExceptionDescriptionString(se));
						}// do nothing
						try {
							if (conn != null)
								conn.close();
						} catch (SQLException se) {
							conn.rollback();
							se.printStackTrace();
							logger.fatal(QAGFormGeneratorUtil
									.getExceptionDescriptionString(se));
						}// end finally try
					}// end try

				}
			}
			
		} catch (Exception e) {
			e.printStackTrace();
			logger.fatal(QAGFormGeneratorUtil.getExceptionDescriptionString(e));
		}
	}	
}
