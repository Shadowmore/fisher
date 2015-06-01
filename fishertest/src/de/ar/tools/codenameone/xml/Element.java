package de.ar.tools.codenameone.xml;

import java.util.ArrayList;
import java.util.Enumeration;
import java.util.Hashtable;
import java.util.List;
import java.util.Vector;

import com.codename1.util.StringUtil;

public class Element extends com.codename1.xml.Element {

	private static final int MAX_VALUE_LENGTH = 250;

	private static final String[][] escapes = { { "&", "&amp;" }, // Achtung, das muss als erstes!
			{ "\"", "&quot;" }, { "<", "&lt;" }, { ">", "&gt;" }, { "'", "&apos;" }, { "\r", "&#13;" }, { "\n", "&#10;" } };

	public Element() {
	}

	public Element(String name) {
		super(name);
	}

	public Element(String tagName, boolean isTextElement) {
		super(tagName, isTextElement);
	}

	public void addContent(Element childNode) {
		addChild(childNode);
	}

	public void addContent(String nodeText) {
		addContent(new Element(nodeText));
	}

	public String getAttributeValue(String attributeName) {
		return getAttribute(attributeName);
	}

	@SuppressWarnings("unchecked")
	public List<Element> getChildren(String xmlElementName) {
		List<Element> listChildren = new ArrayList<Element>();

		Vector<Element> vectorChildren = getChildrenByTagName(xmlElementName);

		if (vectorChildren != null) {
			for (Element element : vectorChildren) {
				listChildren.add(element);
			}
		}
		return listChildren;
	}

	public Element getChild(String xmlElementName) {
		return (Element) getFirstChildByTagName(xmlElementName);
	}

	@Override
	public Element getChildAt(int index) {
		return (Element) super.getChildAt(index);
	}

	@SuppressWarnings("unchecked")
	public static Element convertElementToElement(com.codename1.xml.Element element) {

		boolean textElement = false;
		Element elementNew;
		try {
			element.getTagName();
			elementNew = new Element(element.getTagName(), textElement);
		} catch (Exception e) {
			textElement = true;
			elementNew = new Element(element.getText(), textElement);
		}

		Hashtable<Object, String> attributes = element.getAttributes();
		if (attributes != null) {
			for (Object key : attributes.keySet()) {
				String attribute = attributes.get(key);
				elementNew.setAttribute(key, attribute);
			}
		}

		for (int i = 0; i < element.getNumChildren(); i++) {
			com.codename1.xml.Element elementChild = element.getChildAt(i);
			Element elementChildNew = convertElementToElement(elementChild);
			elementNew.addChild(elementChildNew);
		}

		return elementNew;
	}

	@Override
	public String getText() {

		String retVal = null;
		try {
			retVal = super.getText();
		} catch (Exception e) {
			retVal = null;
			// ignore
		}

		return retVal;
	}

	@Override
	public String toString() {
		StringBuffer sb = new StringBuffer();
		makeStringFromXml(this, sb, "");
		return sb.toString();
	}

	private void makeStringFromXml(Element node, StringBuffer sb, String indent) {
		sb.append(indent);
		sb.append("<");
		sb.append(node.getTagName());
		{
			@SuppressWarnings("rawtypes")
			Hashtable attributes = node.getAttributes();
			if (attributes != null) {
				for (@SuppressWarnings("rawtypes")
				Enumeration e = attributes.keys(); e.hasMoreElements();) {
					String attrname = (String) e.nextElement();
					String attrval = (String) attributes.get(attrname);
					attrval = escapeAttrVal(attrval);
					if (attrval.length() > MAX_VALUE_LENGTH) {
						attrval = attrval.substring(0, MAX_VALUE_LENGTH) + "...";
					}
					sb.append(" " + attrname + "=\"" + attrval + "\"");
				}
			}
		}
		int numChildren = node.getNumChildren();
		if (numChildren > 0) {
			sb.append(">\n");
			for (int i = 0; i < numChildren; i++) {
				Element child = node.getChildAt(i);
				makeStringFromXml(child, sb, indent + " ");
			}
			sb.append(indent);
			sb.append("</" + node.getTagName() + ">\n");
		} else {
			sb.append("/>\n");
		}
	}

	private String escapeAttrVal(String attrval) {
		String text = attrval;
		for (int i = 0; i < escapes.length; i++) {
			text = StringUtil.replaceAll(text, escapes[i][0], escapes[i][1]);
		}
		return text;
	}

	public Element copy() {
		return convertElementToElement(this);
	}
}