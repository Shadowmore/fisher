package de.ar.fisherxmlconverter.model.dataclasses;

public class FisherTest_Model_Answer extends Abstract_FisherTest_Model {

	private FisherTest_Model_Question question;
	private String answertext;
	private boolean isCorrect;

	public FisherTest_Model_Answer(FisherTest_Model_Question question, String answertext, boolean isCorrect) {
		super();
		this.question = question;
		this.answertext = answertext;
		this.isCorrect = isCorrect;
	}

	public FisherTest_Model_Question getQuestion() {
		return question;
	}

	public String getAnswertext() {
		return answertext;
	}

	public boolean isCorrect() {
		return isCorrect;
	}

	@Override
	public String toString() {
		return answertext + " - " + isCorrect;
	}
}