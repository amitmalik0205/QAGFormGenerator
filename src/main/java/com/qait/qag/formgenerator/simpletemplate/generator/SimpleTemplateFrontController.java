package com.qait.qag.formgenerator.simpletemplate.generator;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import org.apache.log4j.Logger;

import com.google.gson.Gson;
import com.qait.qag.formgenerator.common.util.QAGFormGeneratorDBUtil;
import com.qait.qag.formgenerator.common.util.QAGFormGeneratorUtil;
import com.qait.qag.formgenerator.db.domain.Form;
import com.qait.qag.formgenerator.db.domain.FormPageDetail;
import com.qait.qag.formgenerator.generator.IFormGenerator;
import com.qait.qag.formgenerator.generator.ITemplateFrontController;
import com.qait.qag.formgenerator.simpletemplate.domain.ColumnDetail;
import com.qait.qag.formgenerator.simpletemplate.domain.PageDetail;
import com.qait.qag.formgenerator.simpletemplate.domain.SimpleTemplateJsonParent;
import com.qait.qag.formgenerator.simpletemplate.domain.SimpleTemplateQuestionChoice;

public class SimpleTemplateFrontController implements ITemplateFrontController {

	private static final Logger logger = Logger.getLogger(SimpleTemplateFrontController.class);
	
	private SimpleTemplateJsonParent jsonParent;
	
	private IFormGenerator formGenerator;
	
	
	public SimpleTemplateFrontController(SimpleTemplateJsonParent jsonParent) {
		
		this.jsonParent = jsonParent;
		
		this.formGenerator = new SimpleTemplateFormGenerator(jsonParent);
	}
	
	
	@Override
	public void startFormGeneration() {	
		
		formGenerator.generateForm();
		
		saveFormData();
	}
	
	
	@Override
	public void saveFormData() {
	
		Connection conn = QAGFormGeneratorDBUtil.getDBConnection();
		
		PreparedStatement  pstmt1 = null;
		PreparedStatement  pstmt2 = null;
		
		if (conn != null) {
						
			long savedFormId = 0;
		
			try {
				
				Form form = prepareFormData();

				String insertFormSql = "INSERT INTO form(generated_form_id, no_of_pages, client_id, fk_template_id) VALUES (?, ?, ?, ?)";
						
				pstmt1 = conn.prepareStatement(insertFormSql, Statement.RETURN_GENERATED_KEYS);
				
				pstmt1.setInt(1, form.getGeneratedFormId());
				pstmt1.setInt(2, form.getNoOfPages());
				pstmt1.setInt(3, form.getClientId());
				pstmt1.setInt(4, form.getTemplateId());
		
				int affectedRows = pstmt1.executeUpdate();
				
		        if (affectedRows == 0) {
		            throw new SQLException("Creating form failed, no rows affected.");
		        }
		        
		        ResultSet generatedKeys = pstmt1.getGeneratedKeys();

		        if (generatedKeys.next()) {
	                savedFormId = generatedKeys.getLong(1);
	            }
	            else {
	                throw new SQLException("Creating form failed, no ID obtained.");
	            }
		        
		        
		        List<FormPageDetail> pageDetailListToSave = prepareFormPageDetailData();
		        
		        String insertFormPageDetailStr = "INSERT INTO form_page_detail(page_number, question_range, question_data, fk_form_id)"
		        		+"VALUES(?, ?, ?, ?)";
		        
		        pstmt2 = conn.prepareStatement(insertFormPageDetailStr);
		     
		        for(FormPageDetail formPageDetail :  pageDetailListToSave) {		        			        	 		        	
		        	pstmt2.setInt(1, formPageDetail.getPageNumber());
		        	pstmt2.setString(2, formPageDetail.getQuestionRange());
		        	pstmt2.setString(3, formPageDetail.getQuestionData());
		        	pstmt2.setLong(4, savedFormId);
		        	
		        	pstmt2.executeUpdate();
		        }
		        
				conn.commit();

			} catch (SQLException se) {				
				se.printStackTrace();
				
				try {
					conn.rollback();
					
				} catch (SQLException e) {
					logger.fatal(QAGFormGeneratorUtil.getExceptionDescriptionString(e));
					e.printStackTrace();
				}
				
				logger.fatal(QAGFormGeneratorUtil.getExceptionDescriptionString(se));

			} finally {
				// finally block used to close resources
				try {
					
					if (pstmt1 != null) {
						pstmt1.close();
					}
					
					if(pstmt2 != null) {
						pstmt2.close();
					}
						
				} catch (SQLException se) {				
					se.printStackTrace();
					logger.fatal(QAGFormGeneratorUtil.getExceptionDescriptionString(se));
				}
				
				try {
					if (conn != null) {
						conn.close();
					}
						
				} catch (SQLException se) {
					
					se.printStackTrace();
					logger.fatal(QAGFormGeneratorUtil.getExceptionDescriptionString(se));
				}// end finally try
			}// end try

		}
	}
	
	
	private Form prepareFormData() {
		
		Form form = new Form();
		
		form.setClientId(jsonParent.getClientId());
		
		form.setTemplateId(jsonParent.getTemplateId());
		
		form.setNoOfPages(formGenerator.getPageDetailList().size());
		
		form.setGeneratedFormId(generateFormId());
		
		return form;
	}
	
	
	private int generateFormId() {
		
		return 100;
	}
	
	
	private List<FormPageDetail> prepareFormPageDetailData() {
		
		List<PageDetail> pageDetailList = formGenerator.getPageDetailList();
		
		List<FormPageDetail> pageDetailListToSave = new ArrayList<FormPageDetail>();
		
		int no_of_pages = pageDetailList.size();
		
		for (int i = 1; i <= no_of_pages; i++) {
			
			StringBuilder pageRangeStr = new StringBuilder();
			
			StringBuilder pageQuestionJsonStr = new StringBuilder();
			
			List<SimpleTemplateQuestionChoice> pageQuestionList = new ArrayList<SimpleTemplateQuestionChoice>();
			
			PageDetail pageDetail = pageDetailList.get(i-1);
			
			List<ColumnDetail> columnList = pageDetail.getColumnList();
						
			int no_of_columns_on_page = columnList.size();
			
			int lower_bound_for_page = columnList.get(0).getLowerBound();
			
			int upper_bound_for_page = columnList.get(no_of_columns_on_page - 1).getUppderBound();
			
			pageRangeStr.append(lower_bound_for_page+"-");
			
			List<SimpleTemplateQuestionChoice> question_opts = jsonParent.getFormSpec().getQuestionSection().getQuestion_opts();
			
			for(int j = lower_bound_for_page-1; j <= upper_bound_for_page-1; j++) {
				
				if(j < question_opts.size()) {																		
					SimpleTemplateQuestionChoice questionChoice = question_opts.get(j);									
					pageQuestionList.add(questionChoice);									
				} else {
					upper_bound_for_page = j;									
					break;
				}														
			}
			
			pageRangeStr.append(upper_bound_for_page);
			pageQuestionJsonStr.append(new Gson().toJson(pageQuestionList));
			
			FormPageDetail formPageDetail = new FormPageDetail();
			formPageDetail.setPageNumber(i);
			formPageDetail.setQuestionRange(pageRangeStr.toString());
			formPageDetail.setQuestionData(pageQuestionJsonStr.toString());
			
			pageDetailListToSave.add(formPageDetail);
		}
		
		return pageDetailListToSave;
	}
}
