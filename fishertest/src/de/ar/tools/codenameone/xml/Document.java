package de.ar.tools.codenameone.xml;

public class Document {

	private Element rootElement;

	public Document(Element rootElement) {
		this.rootElement = rootElement;
	}

	public Element getRootElement() {
		return rootElement;
	}

	public void setRootElement(Element rootElement) {
		this.rootElement = rootElement;
	}

	@Override
	public String toString() {
		return rootElement.toString();
	}
}