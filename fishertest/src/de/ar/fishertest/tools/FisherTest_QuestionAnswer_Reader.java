package de.ar.fishertest.tools;

import java.io.ByteArrayInputStream;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import com.codename1.io.Util;
import com.codename1.ui.util.Resources;
import com.codename1.xml.XMLParser;

import de.ar.fishertest.model.dataclasses.FisherTest_Model_Answer;
import de.ar.fishertest.model.dataclasses.FisherTest_Model_Question;
import de.ar.fishertest.model.enums.FisherTest_Model_Enum_Category;
import de.ar.tools.codenameone.xml.Document;
import de.ar.tools.codenameone.xml.Element;

public class FisherTest_QuestionAnswer_Reader {

	public static void readFromXml(String xmlPath, Map<FisherTest_Model_Enum_Category, List<FisherTest_Model_Question>> mapKategoryQuestions) {

		Document docQuestionsAnswers;
		try {
			Resources theme = Resources.openLayered("/theme");
			InputStream is = theme.getData(xmlPath);

			String encodedStreamAsString = Util.readToString(is, "UTF-8");

			byte[] bytes = encodedStreamAsString.getBytes("UTF-8");
			while (bytes[0] != 60) {
				encodedStreamAsString = encodedStreamAsString.substring(1);
				bytes = encodedStreamAsString.getBytes("UTF-8");
			}

			InputStream byteIs = new ByteArrayInputStream(bytes);
			InputStreamReader isr = new InputStreamReader(byteIs, "UTF-8");

			XMLParser parser = new XMLParser();

			com.codename1.xml.Element parsedElement = parser.parse(isr);
			Element xmlUserPicture = Element.convertElementToElement(parsedElement);
			docQuestionsAnswers = new Document(xmlUserPicture);
		} catch (Exception e) {
			e.printStackTrace();
			throw new RuntimeException();
		}

		Element elementRoot = docQuestionsAnswers.getRootElement();
		List<Element> listElementQuestion = elementRoot.getChildren("QUESTION");

		for (Element elementQuestion : listElementQuestion) {

			String questionText = elementQuestion.getAttributeValue("text");
			String numberAllAsString = elementQuestion.getAttributeValue("numberinall");
			String categoryAsString = elementQuestion.getAttributeValue("category");

			int numberAll = Integer.parseInt(numberAllAsString);
			FisherTest_Model_Enum_Category category = FisherTest_Model_Enum_Category.getCategoryToXmlString(categoryAsString);

			FisherTest_Model_Question question = new FisherTest_Model_Question(numberAll, questionText, category);

			List<Element> listElementAnswer = elementQuestion.getChildren("ANSWER");
			for (Element elementAnswer : listElementAnswer) {

				String answerText = elementAnswer.getAttributeValue("text");
				String correctAsString = elementAnswer.getAttributeValue("correct");
				boolean isCorrect = Boolean.parseBoolean(correctAsString);

				question.addAnswer(new FisherTest_Model_Answer(question, answerText, isCorrect));
			}

			List<FisherTest_Model_Question> listQuestionsToCategory = mapKategoryQuestions.get(category);
			if (listQuestionsToCategory == null) {
				listQuestionsToCategory = new ArrayList<FisherTest_Model_Question>();
				mapKategoryQuestions.put(category, listQuestionsToCategory);
			}

			listQuestionsToCategory.add(question);
		}
	}
}