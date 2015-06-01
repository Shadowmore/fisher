package de.ar.fisherxmlconverter.model.enums;

public enum FisherTest_Model_Enum_Category {

	FISH("fish"),
	SPECIAL_FISH("specialfish"),
	WATER("water"),
	TOOLS("tools"),
	LAWS("laws");

	private static final String XML_STRING_FISH = "fisch";
	private static final String XML_STRING_SPECIAL_FISH = "sfisch";
	private static final String XML_STRING_SPECIAL_WATER = "wasser";
	private static final String XML_STRING_SPECIAL_TOOLS = "gerät";
	private static final String XML_STRING_SPECIAL_LAWS = "gesetz";

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