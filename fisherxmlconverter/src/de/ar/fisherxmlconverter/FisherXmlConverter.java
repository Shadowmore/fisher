package de.ar.fisherxmlconverter;

import java.awt.FileDialog;
import java.awt.Frame;
import java.io.File;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.jdom.Document;
import org.jdom.Element;

import de.ar.fisherxmlconverter.model.dataclasses.FisherTest_Model_Answer;
import de.ar.fisherxmlconverter.model.dataclasses.FisherTest_Model_Question;
import de.ar.fisherxmlconverter.model.enums.FisherTest_Model_Enum_Category;

public class FisherXmlConverter {

	public static void main(String[] args) {

		FileDialog fd = new FileDialog(new Frame());
		fd.setVisible(true);

		File selectedFile = fd.getFiles()[0];
		System.out.println("File selected: " + selectedFile.getAbsolutePath());

		convertFile_BuildAndSaveNew(selectedFile);

		System.exit(0);
	}

	private static void convertFile_BuildAndSaveNew(File selectedFile) {
		Map<FisherTest_Model_Enum_Category, List<FisherTest_Model_Question>> mapKategoryQuestions = convertFile(selectedFile);

		int amount = 0;
		for (FisherTest_Model_Enum_Category category : FisherTest_Model_Enum_Category.values()) {
			amount += mapKategoryQuestions.get(category).size();
		}
		System.out.println("Amount Questions found: " + amount);

		Comparator<FisherTest_Model_Question> comparator = generateComparatorSortQuestionsByNumberAll();
		for (FisherTest_Model_Enum_Category category : FisherTest_Model_Enum_Category.values()) {
			Collections.sort(mapKategoryQuestions.get(category), comparator);
		}
		System.out.println("Questions sorted");

		Document docNew = buildNewXml(mapKategoryQuestions);

		String path = selectedFile.getParentFile().getAbsolutePath() + "/";
		String fileName = selectedFile.getName().substring(0, selectedFile.getName().lastIndexOf(".")) + "_new.xml";
		String fullPath = path + fileName;
		System.out.println("Save new xml to: " + fullPath);

		XMLtool.saveXMLDocumentToFileWithIndent(docNew, fullPath);
	}

	@SuppressWarnings("unchecked")
	private static Map<FisherTest_Model_Enum_Category, List<FisherTest_Model_Question>> convertFile(File f) {

		Map<FisherTest_Model_Enum_Category, List<FisherTest_Model_Question>> mapKategoryQuestions = new HashMap<FisherTest_Model_Enum_Category, List<FisherTest_Model_Question>>();

		Document docQuestionsAnswers = XMLtool.loadXMLFile(f);

		Element elementAufgaben = docQuestionsAnswers.getRootElement();
		List<Element> listElementAufgabe = elementAufgaben.getChildren("aufgabe");

		for (Element elementAufgabe : listElementAufgabe) {
			String numberAllAsString = elementAufgabe.getAttributeValue("id");
			String categoryAsString = elementAufgabe.getAttributeValue("gebiet");
			String answerCharactersAsString = elementAufgabe.getAttributeValue("lösung");

			Element elementQuestion = elementAufgabe.getChild("frage");
			Element elementAnswerA = elementAufgabe.getChild("antwortA");
			Element elementAnswerB = elementAufgabe.getChild("antwortB");
			Element elementAnswerC = elementAufgabe.getChild("antwortC");

			String questionTest = elementQuestion.getText();
			String answerTextA = elementAnswerA.getText();
			String answerTextB = elementAnswerB.getText();
			String answerTextC = elementAnswerC.getText();

			int numberAll = Integer.parseInt(numberAllAsString);
			FisherTest_Model_Enum_Category category = FisherTest_Model_Enum_Category.getCategoryToXmlString(categoryAsString);

			List<FisherTest_Model_Question> listQuestionsToCategory = mapKategoryQuestions.get(category);
			if (listQuestionsToCategory == null) {
				listQuestionsToCategory = new ArrayList<FisherTest_Model_Question>();
				mapKategoryQuestions.put(category, listQuestionsToCategory);
			}

			FisherTest_Model_Question question = new FisherTest_Model_Question(numberAll, questionTest, category);

			generateAndAddAnswer(question, answerCharactersAsString, answerTextA, "a");
			generateAndAddAnswer(question, answerCharactersAsString, answerTextB, "b");
			generateAndAddAnswer(question, answerCharactersAsString, answerTextC, "c");

			listQuestionsToCategory.add(question);
		}

		return mapKategoryQuestions;
	}

	private static Document buildNewXml(Map<FisherTest_Model_Enum_Category, List<FisherTest_Model_Question>> mapKategoryQuestions) {
		Document docNew = XMLtool.createEmptyXMLDoc();
		Element elementRoot = docNew.getRootElement();

		for (FisherTest_Model_Enum_Category category : FisherTest_Model_Enum_Category.values()) {
			List<FisherTest_Model_Question> listQuestions = mapKategoryQuestions.get(category);

			for (FisherTest_Model_Question question : listQuestions) {

				Element elementQuestion = XMLtool.addChildNode(elementRoot, "QUESTION");
				elementQuestion.setAttribute("text", question.getQuestionTest());
				elementQuestion.setAttribute("numberinall", question.getNumberInAll() + "");
				elementQuestion.setAttribute("category", question.getCategory().getName());

				for (FisherTest_Model_Answer answer : question.getListAnswers()) {
					Element elementAnswer = XMLtool.addChildNode(elementQuestion, "ANSWER");
					elementAnswer.setAttribute("text", answer.getAnswertext());
					elementAnswer.setAttribute("correct", answer.isCorrect() + "");
				}
			}
		}
		return docNew;
	}

	private static void generateAndAddAnswer(FisherTest_Model_Question question, String answerCharactersAsString, String answerTextA, String answerChar) {
		boolean isCorrect = false;
		if (answerCharactersAsString.indexOf(answerChar) != -1) {
			isCorrect = true;
		}
		FisherTest_Model_Answer answer = new FisherTest_Model_Answer(question, answerTextA, isCorrect);
		question.addAnswer(answer);
	}

	private static Comparator<FisherTest_Model_Question> generateComparatorSortQuestionsByNumberAll() {
		Comparator<FisherTest_Model_Question> comparator = new Comparator<FisherTest_Model_Question>() {

			@Override
			public int compare(FisherTest_Model_Question q1, FisherTest_Model_Question q2) {

				int n1 = q1.getNumberInAll();
				int n2 = q2.getNumberInAll();

				if (n1 < n2) {
					return -1;
				} else if (n1 > n2) {
					return 1;
				}

				return 0;
			}
		};
		return comparator;
	}
}