package de.ar.fishertest.model.dataclasses;

import java.util.ArrayList;
import java.util.List;

import de.ar.fishertest.model.enums.FisherTest_Model_Enum_Category;

public class FisherTest_Model_Question extends Abstract_FisherTest_Model {

	private int numberInAll;
	private String questionTest;

	private FisherTest_Model_Enum_Category category;

	private List<FisherTest_Model_Answer> listAnswers;

	public FisherTest_Model_Question(int numberInAll, String questionTest, FisherTest_Model_Enum_Category category) {
		super();
		this.numberInAll = numberInAll;
		this.questionTest = questionTest;
		this.category = category;

		this.listAnswers = new ArrayList<FisherTest_Model_Answer>();
	}

	public void addAnswer(FisherTest_Model_Answer answer) {
		this.listAnswers.add(answer);
	}

	public int getNumberInAll() {
		return numberInAll;
	}

	public String getQuestionTest() {
		return questionTest;
	}

	public FisherTest_Model_Enum_Category getCategory() {
		return category;
	}

	public List<FisherTest_Model_Answer> getListAnswers() {
		return listAnswers;
	}

	@Override
	public String toString() {
		return questionTest;
	}
}