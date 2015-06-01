package de.ar.fisherxmlconverter;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.StringReader;
import java.net.URL;
import java.util.Iterator;
import java.util.ListIterator;
import java.util.Map;

import javax.xml.transform.ErrorListener;
import javax.xml.transform.Source;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerException;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.stream.StreamSource;

import org.jdom.Document;
import org.jdom.Element;
import org.jdom.JDOMException;
import org.jdom.input.SAXBuilder;
import org.jdom.output.Format;
import org.jdom.output.XMLOutputter;
import org.jdom.transform.JDOMResult;
import org.jdom.transform.JDOMSource;
import org.jdom.xpath.XPath;

public class XMLtool {

	public static Document createEmptyXMLDoc() {
		return createEmptyXMLDoc("ROOT");
	}

	public static Document createEmptyXMLDoc(String rootName) {
		Document xmlDoc = new Document();
		Element rootNode = new Element(rootName);
		xmlDoc.setRootElement(rootNode);
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

	public static void saveXMLDocumentToFile(Document xmlDoc, String FullFilename) {
		try {
			FileOutputStream fileOutputStream = new FileOutputStream(new File(FullFilename));
			XMLOutputter xmlOutputter = new XMLOutputter();

			Format myFormat;
			myFormat = xmlOutputter.getFormat();
			myFormat.setOmitEncoding(true);
			myFormat.setOmitDeclaration(true);
			xmlOutputter.setFormat(myFormat);

			xmlOutputter.output(xmlDoc, fileOutputStream);
			fileOutputStream.close();
		} catch (FileNotFoundException e) {
			throw new RuntimeException(e);
		} catch (IOException e) {
			throw new RuntimeException(e);
		}
	}

	public static void saveXMLDocumentToFileWithIndent(Document xmlDoc, String FullFilename) {
		// Achtung: Formatierung mit FOP liefert anderes Ergebnis wenn mit Indent gespeichert wurde! (&#xA0; gehen verloren, aber auch bei normal)
		try {
			FileOutputStream fileOutputStream = new FileOutputStream(new File(FullFilename));
			XMLOutputter xmlOutputter = new XMLOutputter();

			Format myFormat = Format.getPrettyFormat();
			xmlOutputter.setFormat(myFormat);

			xmlOutputter.output(xmlDoc, fileOutputStream);
			fileOutputStream.close();
		} catch (FileNotFoundException e) {
			throw new RuntimeException(e);
		} catch (IOException e) {
			throw new RuntimeException(e);
		}
	}

	public static Document loadXMLFile(URL xmlURL) {
		Document xmlDoc = null;
		SAXBuilder saxBuilder = new SAXBuilder();

		try {
			xmlDoc = saxBuilder.build(xmlURL);
		} catch (JDOMException e) {
			throw new RuntimeException(e);
		} catch (IOException e) {
			throw new RuntimeException(e);
		}
		return xmlDoc;
	}

	public static Document loadXMLFile(String xmlFilename) {
		Document xmlDoc = null;
		SAXBuilder saxBuilder = new SAXBuilder();

		try {
			xmlDoc = saxBuilder.build(xmlFilename);
		} catch (JDOMException e) {
			throw new RuntimeException(e);
		} catch (IOException e) {
			throw new RuntimeException(e);
		}
		return xmlDoc;
	}

	public static Document loadXMLFile(File xmlFile) {
		Document xmlDoc = null;
		SAXBuilder saxBuilder = new SAXBuilder();

		try {
			xmlDoc = saxBuilder.build(xmlFile);
		} catch (JDOMException e) {
			throw new RuntimeException(e);
		} catch (IOException e) {
			throw new RuntimeException(e);
		}
		return xmlDoc;
	}

	public static boolean isXmlStringValid(String xmlDocString) throws Exception {
		Document xmlDoc = null;
		SAXBuilder saxBuilder = new SAXBuilder();
		StringReader MyStringReader = new StringReader(xmlDocString);
		xmlDoc = saxBuilder.build(MyStringReader);
		return (xmlDoc != null); // => true oder Exception;
	}

	private static SAXBuilder saxBuilder = new SAXBuilder();

	// static SAXBuilder nicht threadsafe (eingebaut wg. Performance)
	// darum loadXML synchronized

	public synchronized static Document loadXML(String xmlDocString) {
		// bei 1000 mal aufrufen hat man einen Overhead bei new SAXBuilder(), Faktor 2-3
		// SAXBuilder saxBuilder = new SAXBuilder();
		Document xmlDoc = null;
		StringReader MyStringReader = new StringReader(xmlDocString);

		try {
			xmlDoc = saxBuilder.build(MyStringReader);
		} catch (JDOMException e) {
			throw new RuntimeException(e);
		} catch (IOException e) {
			throw new RuntimeException(e);
		}
		return xmlDoc;
	}

	public static Document transformXSLsafe(Document xmlDoc, String xslFilename) {
		Document xmlDocResult = null;
		try {
			xmlDocResult = transformXSL(xmlDoc, xslFilename);
		} catch (TransformerException e) {
			throw new RuntimeException(e);
		}
		return xmlDocResult;
	}

	public static Document transformXSL(Document xmlDoc, String xslFilename) throws TransformerException {
		return transformXSL(xmlDoc, xslFilename, makeCollectWarningsErrorListener());
	}

	public static Document transformXSL(Document xmlDoc, String xslFilename, ErrorListener errorListener) throws TransformerException {
		Source source = new JDOMSource(xmlDoc);
		JDOMResult jdomResult = new JDOMResult();
		Transformer transformer = TransformerFactory.newInstance().newTransformer(new StreamSource(xslFilename));
		if (errorListener != null)
			transformer.setErrorListener(errorListener);
		transformer.transform(source, jdomResult);
		return jdomResult.getDocument();
	}

	@SuppressWarnings("rawtypes")
	public static Document transformXslWithParametersSafe(Document xmlDoc, String xslFilename, Map parameterMap) {
		Document xmlDocResult = null;
		try {
			xmlDocResult = transformXslWithParameters(xmlDoc, xslFilename, parameterMap);
		} catch (TransformerException e) {
			throw new RuntimeException(e);
		}
		return xmlDocResult;
	}

	@SuppressWarnings("rawtypes")
	public static Document transformXslWithParameters(Document xmlDoc, String xslFilename, Map parameterMap) throws TransformerException {
		return transformXslWithParameters(xmlDoc, xslFilename, parameterMap, makeCollectWarningsErrorListener());
	}

	@SuppressWarnings("rawtypes")
	public static Document transformXslWithParameters(Document xmlDoc, String xslFilename, Map parameterMap, ErrorListener errorListener) throws TransformerException {
		Document xslDoc = XMLtool.loadXMLFile(xslFilename);
		Source source = new JDOMSource(xmlDoc);
		JDOMResult jdomResult = new JDOMResult();
		Transformer transformer = TransformerFactory.newInstance().newTransformer(new StreamSource(xslFilename));
		if (errorListener != null)
			transformer.setErrorListener(errorListener);

		for (Iterator iter = parameterMap.entrySet().iterator(); iter.hasNext();) {
			Map.Entry element = (Map.Entry) iter.next();
			String parameterName = element.getKey().toString();
			Element nodeParam = XMLtool.selectSingleNode(xslDoc, "//xsl:param[@name='" + parameterName + "']");
			if (nodeParam != null)
				transformer.setParameter(parameterName, ((String[]) element.getValue())[0]);
		}

		transformer.transform(source, jdomResult);
		return jdomResult.getDocument();
	}

	public static ErrorListener makeCollectWarningsErrorListener() {
		ErrorListener errorListener = new ErrorListener() {
			String collectedWarnings;

			public void error(TransformerException exception) throws TransformerException {
				fatalError(exception);
			}

			public void fatalError(TransformerException exception) throws TransformerException {
				// wird zweimal aufgerufen, wegen des throw (ist normal)
				if (collectedWarnings == null) {
					throw exception;
				} else {
					String newMessage = exception.getMessage() + "/" + collectedWarnings;
					collectedWarnings = null;
					throw new TransformerException(newMessage, exception);
				}
			}

			public void warning(TransformerException exception) throws TransformerException {
				// oder exception.getMessageAndLocation();
				if (collectedWarnings == null) {
					collectedWarnings = exception.getMessage();
				} else {
					collectedWarnings = collectedWarnings + "/" + exception.getMessage();
				}
			}
		};
		return errorListener;
	}

	public static String getXmlAsStr(Element Node) {

		String resultXmlString;
		XMLOutputter xmlOutputter = new XMLOutputter();

		Format myFormat;
		myFormat = xmlOutputter.getFormat();
		myFormat.setOmitEncoding(true);
		myFormat.setOmitDeclaration(true);
		xmlOutputter.setFormat(myFormat);

		try {
			resultXmlString = xmlOutputter.outputString(Node);
		} catch (RuntimeException e) {
			resultXmlString = null;
		}
		return resultXmlString;
	}

	public static String getXmlAsStr(Document xmlDoc) {
		return getXmlAsStr(xmlDoc.getRootElement());
	}

	public static String getXmlAsStr(Element Node, boolean pretty) {
		String resultXmlString;
		XMLOutputter xmlOutputter = new XMLOutputter();

		if (pretty) {
			xmlOutputter.setFormat(Format.getPrettyFormat());
		} else {
			xmlOutputter.setFormat(Format.getCompactFormat());
		}

		try {
			resultXmlString = xmlOutputter.outputString(Node);
		} catch (RuntimeException e) {
			resultXmlString = null;
		}
		return resultXmlString;
	}

	@SuppressWarnings("rawtypes")
	public static void appendDocRootNodes(Document xmlDocBase, Document xmlDocToAdd) {
		for (ListIterator it = xmlDocToAdd.getRootElement().getChildren().listIterator(); it.hasNext();) {
			Element node = (Element) it.next();
			xmlDocBase.getRootElement().addContent((Element) node.clone());
		}
	}

	@SuppressWarnings("rawtypes")
	public static void mergeXmlDoc(Document xmlDocMergeDest, Document xmlDocMergeSource) {
		for (ListIterator it = xmlDocMergeSource.getRootElement().getChildren().listIterator(); it.hasNext();) {
			Element node = (Element) it.next();
			xmlDocMergeDest.getRootElement().addContent((Element) node.clone());
		}
	}

	public static Element selectSingleNode(Object context, String xPathQuery) {
		try {
			return (Element) XPath.selectSingleNode(context, xPathQuery);
		} catch (JDOMException e) {
			throw new RuntimeException(e);
		}
	}
}