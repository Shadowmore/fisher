package de.ar.tools.codenameone.encryption;

import com.codename1.io.Log;

import de.ar.tools.codenameone.Base64;
import de.ar.tools.codenameone.encryption.md5.MD5;

public class Encrypter {

	public static String encryptStringWithMD5(String stringToEncrypt) {

		if (stringToEncrypt.length() < 50) {
			Log.p("Encrypter - String to Encrypt:" + stringToEncrypt);
		} else {
			Log.p("Encrypter - String to Encrypt:" + stringToEncrypt.substring(0, 50));
		}
		MD5 md5 = new MD5(stringToEncrypt);
		byte[] finals = md5.Final();

		String encoded = Base64.encode(finals);
		Log.p("Encrypter - Base64 encoded: " + encoded);

		String replaced = encoded.replace('/', '_');
		replaced = replaced.replace('<', '_');
		replaced = replaced.replace('>', '_');
		replaced = replaced.replace('|', '_');
		replaced = replaced.replace(':', '_');
		replaced = replaced.replace('*', '_');
		replaced = replaced.replace('?', '_');
		replaced = replaced.replace('"', '_');

		replaced = replaced.replace('\n', '_');
		replaced = replaced.replace('\r', '_');
		replaced = replaced.replace('\f', '_');
		replaced = replaced.replace('\b', '_');
		replaced = replaced.replace('\t', '_');
		replaced = replaced.replace('\00', '_');
		replaced = replaced.replace('\\', '_');
		Log.p("Encrypter - Replaced: " + replaced);

		return replaced;
	}
}