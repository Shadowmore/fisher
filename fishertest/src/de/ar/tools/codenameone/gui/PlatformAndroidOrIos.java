package de.ar.tools.codenameone.gui;

import com.codename1.ui.Display;

public class PlatformAndroidOrIos {

	private static final String PLATFORM_ANDROID = "and";
	private static final String PLATFORM_IOS = "ios";

	public static boolean isAndroid() {
		return Display.getInstance().getPlatformName().equals(PLATFORM_ANDROID);
	}

	public static boolean isIOS() {
		return Display.getInstance().getPlatformName().equals(PLATFORM_IOS);
	}

}
