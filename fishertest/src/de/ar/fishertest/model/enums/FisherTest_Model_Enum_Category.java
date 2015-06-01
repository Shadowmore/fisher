package de.ar.fishertest.model.enums;

public enum FisherTest_Model_Enum_Category {

	FISH("Fischkunde"),
	SPECIAL_FISH("Spezielle Fischkunde"),
	WATER("Gewässerökologie"),
	TOOLS("Gerätekunde"),
	LAWS("Gesetzeskunde");

	private static final String XML_STRING_FISH = "fish";
	private static final String XML_STRING_SPECIAL_FISH = "specialfish";
	private static final String XML_STRING_SPECIAL_WATER = "water";
	private static final String XML_STRING_SPECIAL_TOOLS = "tools";
	private static final String XML_STRING_SPECIAL_LAWS = "laws";

	private String name;

	private FisherTest_Model_Enum_Category(String name) {
		this.name = name;
	}

	public String getName() {
		return name;
	}

	@Override
	public String toString() {
		return name;
	}

	public static FisherTest_Model_Enum_Category getCategoryToXmlString(String categoryAsString) {
		if (categoryAsString.equals(XML_STRING_FISH)) {
			return FISH;
		} else if (categoryAsString.equals(XML_STRING_SPECIAL_FISH)) {
			return SPECIAL_FISH;
		} else if (categoryAsString.equals(XML_STRING_SPECIAL_WATER)) {
			return WATER;
		} else if (categoryAsString.equals(XML_STRING_SPECIAL_TOOLS)) {
			return TOOLS;
		} else if (categoryAsString.equals(XML_STRING_SPECIAL_LAWS)) {
			return LAWS;
		} else {
			throw new RuntimeException("Categorytyp not supported: " + categoryAsString);
		}
	}
}