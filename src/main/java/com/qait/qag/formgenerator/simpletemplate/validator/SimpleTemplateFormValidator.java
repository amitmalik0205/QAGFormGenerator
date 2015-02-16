package com.qait.qag.formgenerator.simpletemplate.validator;

import java.util.List;

import com.qait.qag.formgenerator.common.dao.ClientDaoImpl;
import com.qait.qag.formgenerator.common.dao.IClientDao;
import com.qait.qag.formgenerator.common.util.QAGFormGeneratorUtil;
import com.qait.qag.formgenerator.db.domain.Client;
import com.qait.qag.formgenerator.simpletemplate.domain.SimpleTemplateFormSpec;
import com.qait.qag.formgenerator.simpletemplate.domain.SimpleTemplateInstance;
import com.qait.qag.formgenerator.simpletemplate.domain.SimpleTemplateJsonParent;
import com.qait.qag.formgenerator.simpletemplate.domain.SimpleTemplateQuestionChoice;
import com.qait.qag.formgenerator.simpletemplate.domain.SimpleTemplateQuestionSection;
import com.qait.qag.formgenerator.simpletemplate.domain.SimpleTemplateSectionTopRight;
import com.qait.qag.formgenerator.simpletemplate.util.SimpleTemplatePropertiesFileReaderUtil;

public class SimpleTemplateFormValidator {

	private Integer digits = null;

	private SimpleTemplateJsonParent jsonParent;
	

	public SimpleTemplateFormValidator(SimpleTemplateJsonParent jsonParent) {

		this.jsonParent = jsonParent;
	}
	

	public String validateKey() {

		StringBuilder errors = new StringBuilder();

		Client client = null;

		IClientDao clientDao = new ClientDaoImpl();

		client = clientDao.getClientByKey(jsonParent.getKey());

		if (client == null || (client != null && client.getId() == 0)) {

			errors.append(SimpleTemplatePropertiesFileReaderUtil
					.getValidationProperty("key.not.match"));
		}

		return errors.toString();
	}

	
	public String validateJson() {

		StringBuilder errors = new StringBuilder();

		/*Integer templateId = jsonParent.getTemplateId();

		if (templateId == null) {

			errors.append(SimpleTemplatePropertiesFileReaderUtil
					.getValidationProperty("SimpleTemplateJsonParent.templateId.empty"));

			errors.append("\n");
		}*/

		SimpleTemplateFormSpec formSpec = jsonParent.getFormSpec();

		validateFormSpec(formSpec, errors);

		if (formSpec != null) {

			validateSimpleTemplateInstance(jsonParent.getInstances(), errors);
		}

		return errors.toString();
	}

	
	private void validateSimpleTemplateQuestionLabels(
			List<SimpleTemplateQuestionChoice> question_opts,
			StringBuilder errors) {

		boolean lastLabel = false;
		boolean currentLabel = false;

		SimpleTemplateQuestionChoice firstQuestion = question_opts.get(0);

		String firstQuestionLabel = firstQuestion.getLabel();

		if (!QAGFormGeneratorUtil.checkForEmptyString(firstQuestionLabel)) {

			lastLabel = currentLabel = true;
		}

		for (int i = 1; i < question_opts.size(); i++) {

			SimpleTemplateQuestionChoice currentQuestion = question_opts.get(i);

			String currentQuestionLabel = currentQuestion.getLabel();

			// If empty
			if (QAGFormGeneratorUtil.checkForEmptyString(currentQuestionLabel)) {

				currentLabel = false;

			} else {

				currentLabel = true;

			}

			if (currentLabel != lastLabel) {

				errors.append(SimpleTemplatePropertiesFileReaderUtil
						.getValidationProperty("SimpleTemplateQuestionChoice.questions.label"));

				errors.append(", ");

				break;
			} else {

				lastLabel = currentLabel;

			}
		}
	}

	private void validateSimpleTemplateQuestionChoices(
			SimpleTemplateQuestionChoice choice, StringBuilder errors,
			int counter) {

		if (choice == null) {

			errors.append(SimpleTemplatePropertiesFileReaderUtil
					.getValidationProperty("SimpleTemplateQuestionChoice.questions.choice")
					+ " " + counter);

			errors.append(", ");

		} else {

			String choices = choice.getChoices();

			if (QAGFormGeneratorUtil.checkForEmptyString(choices)) {

				errors.append(SimpleTemplatePropertiesFileReaderUtil
						.getValidationProperty("SimpleTemplateQuestionChoice.questions.choice")
						+ " " + counter);

				errors.append(", ");
			}
		}
	}
	

	private void validateSimpleTemplateQuestionChoiceList(
			List<SimpleTemplateQuestionChoice> question_opts,
			StringBuilder errors, int count) {

		if (question_opts == null
				|| (question_opts != null && question_opts.size() < 1)) {

			errors.append(SimpleTemplatePropertiesFileReaderUtil
					.getValidationProperty("SimpleTemplateQuestionChoice.empty"));

			errors.append(", ");

		} else {

			int no_of_questions = question_opts.size();

			if (no_of_questions != count) {

				errors.append(SimpleTemplatePropertiesFileReaderUtil
						.getValidationProperty("SimpleTemplateQuestionChoice.questions.count"));

				errors.append(", ");
			}

			validateSimpleTemplateQuestionLabels(question_opts, errors);

			int counter = 0;

			for (SimpleTemplateQuestionChoice choice : question_opts) {
				counter++;

				validateSimpleTemplateQuestionChoices(choice, errors, counter);
			}
		}
	}
	

	private void validateSimpleTemplateQuestionSection(
			SimpleTemplateQuestionSection questionSection, StringBuilder errors) {

		if (questionSection == null) {
			errors.append(SimpleTemplatePropertiesFileReaderUtil
					.getValidationProperty("SimpleTemplateQuestionSection.empty"));

			errors.append(", ");

		} else {

			Integer count = questionSection.getCount();

			if (count == null) {

				errors.append(SimpleTemplatePropertiesFileReaderUtil
						.getValidationProperty("SimpleTemplateQuestionSection.count.empty"));

				errors.append(", ");

			} else {

				validateSimpleTemplateQuestionChoiceList(
						questionSection.getQuestion_opts(), errors, count);
			}
		}
	}
	

	private void validateSimpleTemplateSectionTopRight(
			SimpleTemplateSectionTopRight topRight, StringBuilder errors) {

		if (topRight == null) {

			errors.append(SimpleTemplatePropertiesFileReaderUtil
					.getValidationProperty("SimpleTemplateSectionTopRight.empty"));

			errors.append("\n");

		} else {

			String choices = topRight.getChoices();

			if (QAGFormGeneratorUtil.checkForEmptyString(choices)) {

				errors.append(SimpleTemplatePropertiesFileReaderUtil
						.getValidationProperty("SimpleTemplateSectionTopRight.choices"));

				errors.append(", ");

			} else {

				digits = topRight.getDigits();

				if (digits == null) {

					errors.append(SimpleTemplatePropertiesFileReaderUtil
							.getValidationProperty("SimpleTemplateSectionTopRight.digits"));

					errors.append(", ");
				}
			}
		}
	}

	
	private void validateFormSpec(SimpleTemplateFormSpec formSpec,
			StringBuilder errors) {

		if (formSpec == null) {

			errors.append(SimpleTemplatePropertiesFileReaderUtil
					.getValidationProperty("SimpleTemplateJsonParent.formSpec.empty"));

			errors.append(", ");

		} else {

			validateSimpleTemplateQuestionSection(
					formSpec.getQuestionSection(), errors);

			validateSimpleTemplateSectionTopRight(
					formSpec.getSections_topright(), errors);
		}
	}
	

	private void validateSimpleTemplateInstance(
			List<SimpleTemplateInstance> instances, StringBuilder errors) {

		if (instances == null || (instances != null && instances.size() < 1)) {

			errors.append(SimpleTemplatePropertiesFileReaderUtil
					.getValidationProperty("SimpleTemplateInstance.empty"));

			errors.append(", ");

		} else {

			int counter = 0;

			for (SimpleTemplateInstance instance : instances) {

				counter++;

				String studentId = instance.getStudentId();
				String top = instance.getTop();
				String bottom = instance.getBottom();

				if (QAGFormGeneratorUtil.checkForEmptyString(studentId)) {

					errors.append(SimpleTemplatePropertiesFileReaderUtil
							.getValidationProperty("SimpleTemplateInstance.studentId.empty"));

					errors.append(", ");

				} else {

					int studentIdLength = studentId.length();

					if (digits != null && studentIdLength != digits) {

						errors.append(SimpleTemplatePropertiesFileReaderUtil
								.getValidationProperty("SimpleTemplateInstance.studentId.digits.1")
								+ " "
								+ digits
								+ " "
								+ SimpleTemplatePropertiesFileReaderUtil
										.getValidationProperty("SimpleTemplateInstance.studentId.digits.2")
								+ " " + counter);

						errors.append(", ");
					}
				}

				if (QAGFormGeneratorUtil.checkForEmptyString(top)) {

					errors.append(SimpleTemplatePropertiesFileReaderUtil
							.getValidationProperty("SimpleTemplateInstance.top.empty"));

					errors.append(", ");
				}

				if (QAGFormGeneratorUtil.checkForEmptyString(bottom)) {

					errors.append(SimpleTemplatePropertiesFileReaderUtil
							.getValidationProperty("SimpleTemplateInstance.bottom.empty"));

					errors.append(", ");
				}
			}
		}
	}
}
