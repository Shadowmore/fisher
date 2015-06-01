package de.ar.tools.codenameone;

public class Base64 extends com.codename1.util.Base64 {

	public static byte[] decode(String attributeValue) {
		return decode(attributeValue.getBytes());
	}
}