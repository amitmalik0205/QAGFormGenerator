package com.qait.qag.formgenerator.common.util;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

import org.apache.log4j.Logger;

public class QAGFormGeneratorDBUtil {

	final static Logger logger = Logger.getLogger(QAGFormGeneratorDBUtil.class);

	static {

		try {

			Class.forName("com.mysql.jdbc.Driver");

		} catch (ClassNotFoundException ex) {
			ex.printStackTrace();
			logger.fatal(QAGFormGeneratorUtil.getExceptionDescriptionString(ex));
		}
	}

	
	public static Connection getDBConnection() {

		String connectionUrl = "jdbc:mysql://localhost:3306/qag_db";
		String dbUser = "root";
		String dbPwd = "";
		Connection con = null;

		try {
			
			con = DriverManager.getConnection(connectionUrl, dbUser, dbPwd);
			
		} catch (SQLException e) {
			e.printStackTrace();
			logger.fatal(QAGFormGeneratorUtil.getExceptionDescriptionString(e));
		}

		return con;
	}
}
