package de.ar.tools.codenameone;

import java.text.SimpleDateFormat;
import java.util.Date;

public class DateTools {

	private static final String DATE_CONVERT_PATTERN = "hh:mm:ss dd:MM:yyyy";

	public static String convertDateToString(Date date) {
		try {
			SimpleDateFormat format = new SimpleDateFormat(DATE_CONVERT_PATTERN);
			return format.format(date);
		} catch (Exception e) {
			return "";
		}
	}

	public static Date convertStringToDate(String dateString) {
		try {
			SimpleDateFormat format = new SimpleDateFormat(DATE_CONVERT_PATTERN);
			return format.parse(dateString);
		} catch (Exception e) {
			return null;
		}
	}
}