package com.qait.qag.formgenerator.common.dao;

import com.qait.qag.formgenerator.db.domain.Form;

public interface IFormDao {

	public long saveForm(Form form);
	
	public Form getFormByHashCode(int hashCode);
}
