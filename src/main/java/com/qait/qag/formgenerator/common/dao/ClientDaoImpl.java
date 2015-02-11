package com.qait.qag.formgenerator.common.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import org.apache.log4j.Logger;

import com.qait.qag.formgenerator.common.util.QAGFormGeneratorDBUtil;
import com.qait.qag.formgenerator.common.util.QAGFormGeneratorUtil;
import com.qait.qag.formgenerator.db.domain.Client;

public class ClientDaoImpl implements IClientDao {

	private static final Logger logger = Logger.getLogger(ClientDaoImpl.class);
	

	@Override
	public Client getClientByKey(String key) {	
		
		Client client = new Client();
		
		Connection conn = QAGFormGeneratorDBUtil.getDBConnection();
		
		PreparedStatement  pstmt1 = null;
		ResultSet resultSet = null;
	
		if (conn != null) {				
			try {
							
				String sql = "SELECT client_id, client_name, api_key FROM client WHERE api_key = ?";
						
				pstmt1 = conn.prepareStatement(sql);
				
				pstmt1.setString(1, key);				
		
				resultSet = pstmt1.executeQuery();
				
				while(resultSet.next()) {	
					client.setId(resultSet.getInt(1));
					client.setName(resultSet.getString(2));
					client.setKey(resultSet.getString(3));
				}

			} catch (SQLException se) {				
				se.printStackTrace();
				
				try {
					conn.rollback();
					
				} catch (SQLException e) {
					logger.fatal(QAGFormGeneratorUtil.getExceptionDescriptionString(e));
					e.printStackTrace();
				}
				
				client = null;
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
		
		return client;
	}

}
