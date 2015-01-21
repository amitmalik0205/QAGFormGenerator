package com.qait.qag.formgenerator.common.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.List;

import org.apache.log4j.Logger;

import com.qait.qag.formgenerator.common.util.QAGFormGeneratorDBUtil;
import com.qait.qag.formgenerator.common.util.QAGFormGeneratorUtil;
import com.qait.qag.formgenerator.db.domain.Form;
import com.qait.qag.formgenerator.db.domain.FormPageDetail;

public class FormDaoImpl implements IFormDao {
	

	private static final Logger logger = Logger.getLogger(FormDaoImpl.class);
	

	@Override
	public void saveForm(Form form) {
	
		Connection conn = QAGFormGeneratorDBUtil.getDBConnection();
		
		PreparedStatement  pstmt1 = null;
		PreparedStatement  pstmt2 = null;
		
		if (conn != null) {
						
			long savedFormId = 0;
		
			try {
								
				String insertFormSql = "INSERT INTO form(no_of_pages, client_id, fk_template_id, form_hashcode) VALUES (?, ?, ?, ?)";
						
				pstmt1 = conn.prepareStatement(insertFormSql, Statement.RETURN_GENERATED_KEYS);
				
				pstmt1.setInt(1, form.getNoOfPages());
				pstmt1.setInt(2, form.getClientId());
				pstmt1.setInt(3, form.getTemplateId());
				pstmt1.setInt(4, form.getHashCode());
		
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
		        
		        
		        List<FormPageDetail> pageDetailListToSave = form.getPageDetails();
		        
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

	@Override
	public Form getFormByHashCode(int hashCode) {	
		
		Form form = new Form();
		
		Connection conn = QAGFormGeneratorDBUtil.getDBConnection();
		
		PreparedStatement  pstmt1 = null;
		ResultSet resultSet = null;
	
		if (conn != null) {				
			try {
							
				String sql = "SELECT form_id, no_of_pages, client_id, fk_template_id, form_hashcode FROM form WHERE form_hashcode = ?";
						
				pstmt1 = conn.prepareStatement(sql);
				
				pstmt1.setInt(1, hashCode);				
		
				resultSet = pstmt1.executeQuery();
				
				while(resultSet.next()) {	
					form.setFormId(resultSet.getLong(1));
					form.setNoOfPages(resultSet.getInt(2));	
					form.setClientId(resultSet.getInt(3));					
					form.setTemplateId(resultSet.getInt(4));					
					form.setHashCode(resultSet.getInt(5));																		
				}

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
		
		return form;
	}
}
