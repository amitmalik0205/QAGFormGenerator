package com.qait.qag.formgenerator.simpletemplate.util;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Properties;

import org.apache.log4j.Logger;

import com.qait.qag.formgenerator.common.exception.QAGFormGeneratorException;
import com.qait.qag.formgenerator.common.util.QAGFormGeneratorUtil;

/**
 * This class defines a utility to read the property file of constants.
 */
public class SimpleTemplatePropertiesFileReaderUtil {

	private static final Logger logger = Logger
			.getLogger(SimpleTemplatePropertiesFileReaderUtil.class);
	
	private static Properties properties = System.getProperties();



	/*
	 * Get property values for Email
	 */

	public static String getValidationProperty(String key) {
		try {
			properties.load(SimpleTemplatePropertiesFileReaderUtil.class
					.getClassLoader().getResourceAsStream(
							"simpleTemplateValidationMessages.properties"));
		} catch (FileNotFoundException e) {
			logger.fatal(QAGFormGeneratorUtil.getExceptionDescriptionString(e));
			throw new QAGFormGeneratorException();
		} catch (IOException e) {
			logger.fatal(QAGFormGeneratorUtil.getExceptionDescriptionString(e));
			throw new QAGFormGeneratorException();
		}
		return properties.getProperty(key) != null ? properties
				.getProperty(key).trim() : "";
	}

}
