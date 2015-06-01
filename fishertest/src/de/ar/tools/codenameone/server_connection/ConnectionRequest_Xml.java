package de.ar.tools.codenameone.server_connection;

import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;

import com.codename1.xml.XMLParser;

import de.ar.tools.codenameone.xml.Element;

public class ConnectionRequest_Xml extends Abstract_ConnectionRequest {

	private Element requestXml;
	private Element responseXml;

	public ConnectionRequest_Xml(String url, Element requestXml) {
		super(url);
		this.requestXml = requestXml;
	}

	@Override
	protected void doBuildRequestBody(OutputStream os, Abstract_ConnectionRequest connectionRequest) {
		try {
			byte[] xmlBytes = requestXml.toString().getBytes("UTF-8");
			os.write(xmlBytes);
		} catch (Exception e) {
			throw new RuntimeException("Error while writing data into request body.");
		}
	}

	@Override
	protected void doReadPesponse(InputStream input, Abstract_ConnectionRequest connectionRequest) {
		InputStreamReader isr;
		try {
			isr = new InputStreamReader(input, "UTF-8");
		} catch (Exception e) {
			throw new RuntimeException("Error while reading data from response body.");
		}

		XMLParser parser = new XMLParser();
		com.codename1.xml.Element elementRootParsed = parser.parse(isr);
		responseXml = Element.convertElementToElement(elementRootParsed);
	}

	public Element getResponseXml() {

		waitTillRequestWorked();

		if (isHandled && !hasError) {
			return responseXml;
		} else {
			return null;
		}
	}
}