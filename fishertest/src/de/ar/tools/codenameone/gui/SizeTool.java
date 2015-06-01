package de.ar.tools.codenameone.gui;

import com.codename1.ui.Display;

public class SizeTool {

	private static final int SKALIERUNG_EIN_DRITTEL = 100 / 3;
	private static final int SKALIERUNG_EIN_HALB = 100 / 2;
	private static final int SKALIERUNG_EIN_VIERTEL = 100 / 4;
	private static final int SKALIERUNG_BUTTON = 10;
	private static final int SKALIERUNG_FOOTER = 10;

	private static final int SKALIERUNG_NAVIGATION_BUTTON_WIDTH = 50;
	private static final int SKALIERUNG_NAVIGATION_BUTTON_HEIGHT = 10;

	private static final int SKALIERUNG_ERRORDIALOG_WIDTH = 70;
	private static final int SKALIERUNG_MENUDIALOG_WIDTH = 50;
	private static final int SKALIERUNG_MENUDIALOG_BUTTON_WIDTH = 50;

	private static final int SKALIERUNG_BIG_BUTTON_HEIGHT = 8;
	private static final int SKALIERUNG_NAVIGATION_SPACING_HEIGHT = 5;
	private static final int SKALIERUNG_NAVIGATION_BANNER_HEIGHT = 20;

	private static final int SKALIERUNG_TITLE_AREA_HEIGHT = 20;

	//	private static final int SKALIERUNG_FILTER_TEXT = SKALIERUNG_BUTTON/2;
	private static final int SKALIERUNG_FILTER_SPACE = 1;

	public static final int SKALIERUNG_HILFE_DIALOG_WIDTH = 70;
	public static final int SKALIERUNG_HILFE_DIALOG_HEIGHT = 50;
	public static final int SKALIERUNG_HILFE_DIALOG_SPACING_WIDTH = 9;

	public static int getDisplayWidthEinDrittel() {
		return getDisplayWidthScaledPercent(SKALIERUNG_EIN_DRITTEL, true);
	}

	public static int getDisplayWidthEinHalb() {
		return getDisplayWidthScaledPercent(SKALIERUNG_EIN_HALB, true);
	}

	public static int getDisplayWidthEinViertel() {
		int displayWidthScaledPercent = getDisplayWidthScaledPercent(SKALIERUNG_EIN_VIERTEL, true);
		return displayWidthScaledPercent;
	}

	public static int getNavigationButtonCenterWidth() {
		return SizeTool.getDisplayWidthScaledPercent(SKALIERUNG_NAVIGATION_BUTTON_WIDTH, false);
	}

	public static int getNavigationButtonCenterHeight() {
		return SizeTool.getDisplayWidthScaledPercent(SKALIERUNG_NAVIGATION_BUTTON_HEIGHT, false);
	}

	public static int getWidthForButton() {
		return (getDisplayWidthScaledPercent(SKALIERUNG_BUTTON, false) * getScaleFactorPercentForDeviceType()) / 100;
	}

	//	public static int getDisplayHeightFilterTextScaled() {
	//		return getDisplayHeightScaledPercent(SKALIERUNG_FILTER_TEXT, false, false);
	//	}

	public static int getFilterSpaceLabelHeight() {
		return getDisplayHeightScaledPercent(SKALIERUNG_FILTER_SPACE, false);
	}

	public static int getWidthForFooterButton() {
		return (getDisplayWidthScaledPercent(SKALIERUNG_FOOTER, false) * getScaleFactorPercentForDeviceType()) / 100;
	}

	public static int getErrorDialogTextWidth() {
		return getDisplayWidthScaledPercent(SKALIERUNG_ERRORDIALOG_WIDTH, false);
	}

	public static int getMenuDialogWidth() {
		return getDisplayWidthScaledPercent(SKALIERUNG_MENUDIALOG_WIDTH, false);
	}

	public static int getMenuDialogButtonWidth() {
		return getDisplayWidthScaledPercent(SKALIERUNG_MENUDIALOG_BUTTON_WIDTH, false);
	}

	public static int getBigButtonHeight() {
		return (getDisplayHeightScaledPercent(SKALIERUNG_BIG_BUTTON_HEIGHT, false) * getScaleFactorPercentForDeviceType()) / 100;
	}

	public static int getNavigationSpacingHeight() {
		return getDisplayHeightScaledPercent(SKALIERUNG_NAVIGATION_SPACING_HEIGHT, true);
	}

	public static int getNavigationBannerHeight() {
		return getDisplayHeightScaledPercent(SKALIERUNG_NAVIGATION_BANNER_HEIGHT, true);
	}

	public static int getTitleAreaHeight() {
		return getDisplayHeightScaledPercent(SKALIERUNG_TITLE_AREA_HEIGHT, true);
	}

	public static int getHilfeDialogWidth() {
		return getDisplayWidthScaledPercent(SKALIERUNG_HILFE_DIALOG_WIDTH, false);
	}

	public static int getHilfeDialogHeight() {
		return getDisplayHeightScaledPercent(SKALIERUNG_HILFE_DIALOG_HEIGHT, false);
	}

	public static int getHilfeDialogSpaceWidth() {
		return getHilfeDialogWidth() * SKALIERUNG_HILFE_DIALOG_SPACING_WIDTH / 100;
	}

	public static int getDialogListingWidth() {
		return getDisplayWidthScaledPercent(35, true);
	}

	////////////////////////////////////////////private//////////////////////////////////////////////

	private static int getDisplayWidthScaledPercent(int percent, boolean withOrientation) {
		int widthScaled = ((getDisplayWidth(withOrientation) * percent) / 100);
		return widthScaled;
	}

	private static int getDisplayHeightScaledPercent(int percent, boolean withOrientation) {
		int heightScaled = (getDisplayHeight(withOrientation) * percent) / 100;
		return heightScaled;
	}

	private static int getDisplayHeight(boolean withOrientation) {
		//Log.p("getDisplayHeight: orientation = "+ withOrientation + " tablet = " + tablet);
		int height = 0;
		if (withOrientation) {
			height = Display.getInstance().getDisplayHeight();
		} else {
			height = getDisplayBiggerHeight();
		}
		return height;
	}

	public static int getDisplayWidth(boolean withOrientation) {
		//Log.p("getDisplayWidth: orientation = "+ withOrientation + " tablet = " + tablet);
		int width = 0;
		if (withOrientation) {
			width = Display.getInstance().getDisplayWidth();
		} else {
			width = getDisplaySmallerWidth();
		}
		return width;
	}

	private static int getDisplaySmallerWidth() {
		int w = Display.getInstance().getDisplayWidth();
		int h = Display.getInstance().getDisplayHeight();
		int useWidth = ((w < h) ? w : h);
		return useWidth;
	}

	public static int getDisplayBiggerHeight() {
		int w = Display.getInstance().getDisplayWidth();
		int h = Display.getInstance().getDisplayHeight();
		int useHeight = ((w > h) ? w : h);
		return useHeight;
	}

	private static int getScaleFactorPercentForDeviceType() {
		if (isTablet()) {
			return 50;
		} else {
			return 100;
		}
	}

	private static boolean isTablet() {
		return Display.getInstance().isTablet();
	}

	public static int getListRandPixelsLeftRight() {
		return 2;
	}

	public static int getListRandPixelsTopBottom() {
		return 2;
	}

	public static int getInnerHorizontalMargin() {
		return 5;
	}

}
