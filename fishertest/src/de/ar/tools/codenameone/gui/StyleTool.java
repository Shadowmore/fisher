package de.ar.tools.codenameone.gui;

import com.codename1.ui.Component;
import com.codename1.ui.Font;
import com.codename1.ui.Image;
import com.codename1.ui.plaf.Border;
import com.codename1.ui.plaf.Style;

public class StyleTool {

	public static void copyStyle(Component from, Component to) {
		//		to.setStyle(new Style(from.getStyle()));
		to.setSelectedStyle(new Style(from.getSelectedStyle()));
		to.setUnselectedStyle(new Style(from.getUnselectedStyle()));
		to.setPressedStyle(new Style(from.getPressedStyle()));
		to.setDisabledStyle(new Style(from.getDisabledStyle()));

		to.setShouldCalcPreferredSize(false);
	}

	public static void setAlignment(Component comp, int alignment) {
		comp.getStyle().setAlignment(alignment);
		comp.getSelectedStyle().setAlignment(alignment);
		comp.getUnselectedStyle().setAlignment(alignment);
		comp.getPressedStyle().setAlignment(alignment);
		comp.getDisabledStyle().setAlignment(alignment);
	}

	public static void setFont(Component comp, Font font, boolean override) {
		comp.getStyle().setFont(font, override);
		comp.getSelectedStyle().setFont(font, override);
		comp.getUnselectedStyle().setFont(font, override);
		comp.getPressedStyle().setFont(font, override);
		comp.getDisabledStyle().setFont(font, override);
	}

	public static void setBgColor(Component comp, int r, int g, int b) {
		int rgb = calcRBGToInt(r, g, b);
		setBgColor(comp, rgb);
	}

	public static void setBgColor(Component comp, int color) {
		setBgColor(comp, color, true);
		setBgTransparency(comp, 255, false);
	}

	public static void setBgColor(Component comp, int color, boolean override) {
		comp.getStyle().setBgColor(color, override);
		comp.getSelectedStyle().setBgColor(color, override);
		comp.getUnselectedStyle().setBgColor(color, override);
		comp.getPressedStyle().setBgColor(color, override);
		comp.getDisabledStyle().setBgColor(color, override);
	}

	public static void setFgColor(Component comp, int r, int g, int b) {
		int rgb = calcRBGToInt(r, g, b);
		setFgColor(comp, rgb, true);
	}

	public static void setFgColor(Component comp, int color) {
		setFgColor(comp, color, true);
	}

	public static void setFgColor(Component comp, int color, boolean override) {
		comp.getStyle().setFgColor(color, override);
		comp.getSelectedStyle().setFgColor(color, override);
		comp.getUnselectedStyle().setFgColor(color, override);
		comp.getPressedStyle().setFgColor(color, override);
		comp.getDisabledStyle().setFgColor(color, override);
	}

	private static int calcRBGToInt(int r, int g, int b) {
		int rgb = r;
		rgb = (rgb << 8) + g;
		rgb = (rgb << 8) + b;
		// int rgb = ((r & 0x0ff) << 16) | ((g & 0x0ff) << 8) | (b & 0x0ff);
		return rgb;
	}

	public static void setBorder(Component comp, Border border, boolean override) {
		comp.getStyle().setBorder(border, override);
		comp.getSelectedStyle().setBorder(border, override);
		comp.getUnselectedStyle().setBorder(border, override);
		comp.getPressedStyle().setBorder(border, override);
		comp.getDisabledStyle().setBorder(border, override);
	}

	public static void setBorder(Component comp, Border borderTop, Border borderBot, Border borderLeft, Border borderRight) {
		comp.getStyle().setBorder(Border.createCompoundBorder(borderTop, borderBot, borderLeft, borderRight));
		comp.getSelectedStyle().setBorder(Border.createCompoundBorder(borderTop, borderBot, borderLeft, borderRight));
		comp.getUnselectedStyle().setBorder(Border.createCompoundBorder(borderTop, borderBot, borderLeft, borderRight));
		comp.getPressedStyle().setBorder(Border.createCompoundBorder(borderTop, borderBot, borderLeft, borderRight));
		comp.getDisabledStyle().setBorder(Border.createCompoundBorder(borderTop, borderBot, borderLeft, borderRight));
	}

	public static void setBgTransparency(Component comp, int transparency, boolean override) {
		comp.getStyle().setBgTransparency(transparency, override);
		comp.getSelectedStyle().setBgTransparency(transparency, override);
		comp.getUnselectedStyle().setBgTransparency(transparency, override);
		comp.getPressedStyle().setBgTransparency(transparency, override);
		comp.getDisabledStyle().setBgTransparency(transparency, override);
	}

	public static void setBgImage(Component comp, Image img, boolean override) {
		comp.getStyle().setBgImage(img, override);
		comp.getSelectedStyle().setBgImage(img, override);
		comp.getUnselectedStyle().setBgImage(img, override);
		comp.getPressedStyle().setBgImage(img, override);
		comp.getDisabledStyle().setBgImage(img, override);
	}

	public static void setMargin(Component comp, int top, int bottom, int left, int right) {
		comp.getStyle().setMargin(top, bottom, left, right);
		comp.getSelectedStyle().setMargin(top, bottom, left, right);
		comp.getUnselectedStyle().setMargin(top, bottom, left, right);
		comp.getPressedStyle().setMargin(top, bottom, left, right);
		comp.getDisabledStyle().setMargin(top, bottom, left, right);
	}

	public static void setPadding(Component comp, int top, int bottom, int left, int right) {
		comp.getStyle().setPadding(top, bottom, left, right);
		comp.getSelectedStyle().setPadding(top, bottom, left, right);
		comp.getUnselectedStyle().setPadding(top, bottom, left, right);
		comp.getPressedStyle().setPadding(top, bottom, left, right);
		comp.getDisabledStyle().setPadding(top, bottom, left, right);
	}

	public static void setOpactity(Component comp, int opacity, boolean override) {
		comp.getStyle().setOpacity(opacity, override);
		comp.getSelectedStyle().setOpacity(opacity, override);
		comp.getUnselectedStyle().setOpacity(opacity, override);
		comp.getPressedStyle().setOpacity(opacity, override);
		comp.getDisabledStyle().setOpacity(opacity, override);
	}

	/**
	* Indicates the unit type for padding/margin, the default is in device specific pixels
	* public static final byte UNIT_TYPE_PIXELS = 0;
	* 
	* Indicates the unit type for padding/margin in percentage of the size of the screen
	* public static final byte UNIT_TYPE_SCREEN_PERCENTAGE = 1;

	* Indicates the unit type for padding/margin in device independent pixels. Device independent pixels try to aim
	* at roghly 1 milimeter of the screen per DIP but make no guarantee for accuracy.
	* public static final byte UNIT_TYPE_DIPS = 2;
	*/

	//Unit Default bei Android: 0 => 1 Pixel // bei iOS: 2 => 1 Milliemter
	//null => pixels are used
	public static void clearPaddingUnits(Component comp) {
		comp.getStyle().setPaddingUnit(null);
		comp.getSelectedStyle().setPaddingUnit(null);
		comp.getUnselectedStyle().setPaddingUnit(null);
		comp.getPressedStyle().setPaddingUnit(null);
		comp.getDisabledStyle().setPaddingUnit(null);
	}

	public static void clearMarginUnits(Component comp) {
		comp.getStyle().setMarginUnit(null);
		comp.getSelectedStyle().setMarginUnit(null);
		comp.getUnselectedStyle().setMarginUnit(null);
		comp.getPressedStyle().setMarginUnit(null);
		comp.getDisabledStyle().setMarginUnit(null);
	}

	public static void removePaddingsAndMargin(Component comp) {
		removePaddings(comp);
		removeMargins(comp);
		clearPaddingUnits(comp);
		clearMarginUnits(comp);
	}

	private static void removePaddings(Component comp) {
		setPadding(comp, 0, 0, 0, 0);
	}

	private static void removeMargins(Component comp) {
		setMargin(comp, 0, 0, 0, 0);
	}
	/*
	 * zum kopieren, falls man etwas hinzufügt
	comp.getStyle().
	comp.getSelectedStyle().
	comp.getUnselectedStyle().
	comp.getPressedStyle().
	comp.getDisabledStyle().
	*/
}