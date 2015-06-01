package de.ar.fishertest;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import de.ar.fishertest.model.dataclasses.FisherTest_Model_Question;
import de.ar.fishertest.model.enums.FisherTest_Model_Enum_Category;

public class FisherTest_DataDistributor {

	private Map<FisherTest_Model_Enum_Category, List<FisherTest_Model_Question>> mapKategoryQuestions;

	private static FisherTest_DataDistributor instance;

	public FisherTest_DataDistributor() {
		mapKategoryQuestions = new HashMap<FisherTest_Model_Enum_Category, List<FisherTest_Model_Question>>();
	}

	public static FisherTest_DataDistributor getInstance() {
		if (instance == null) {
			instance = new FisherTest_DataDistributor();
		}
		return instance;
	}

	public static void reset() {
		instance = null;
	}

	public Map<FisherTest_Model_Enum_Category, List<FisherTest_Model_Question>> getMapCategoryQuestions() {
		return mapKategoryQuestions;
	}
}