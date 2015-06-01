package de.ar.fishertest.model.dataclasses;

public class FisherTest_Model_Answer extends Abstract_FisherTest_Model {

	private FisherTest_Model_Question question;
	private String answerText;
	private boolean isCorrect;

	public FisherTest_Model_Answer(FisherTest_Model_Question question, String answerText, boolean isCorrect) {
		super();
		this.question = question;
		this.answerText = answerText;
		this.isCorrect = isCorrect;
	}

	public FisherTest_Model_Question getQuestion() {
		return question;
	}

	public String getAnswerText() {
		return answerText;
	}

	public boolean isCorrect() {
		return isCorrect;
	}

	@Override
	public String toString() {
		return answerText + " - " + isCorrect;
	}
}