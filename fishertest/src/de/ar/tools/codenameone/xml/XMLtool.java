package de.ar.tools.codenameone.xml;

public class XMLtool {

	public static Document createEmptyXMLDoc() {
		return createEmptyXMLDoc("ROOT");
	}

	public static Document createEmptyXMLDoc(String rootName) {
		Document xmlDoc = new Document(new Element(rootName));
		return xmlDoc;
	}

	public static Element addChildNode(Element parentNode, String nodeName) {
		Element childNode = new Element(nodeName);
		parentNode.addContent(childNode);
		return childNode;
	}

	public static Element addChildNode(Element parentNode, String nodeName, String nodeText) {
		Element childNode = addChildNode(parentNode, nodeName);
		childNode.addContent(nodeText);
		return childNode;
	}
}