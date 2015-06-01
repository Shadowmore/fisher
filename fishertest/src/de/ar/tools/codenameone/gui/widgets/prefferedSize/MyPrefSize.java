package de.ar.tools.codenameone.gui.widgets.prefferedSize;

import com.codename1.ui.Component;
import com.codename1.ui.Display;
import com.codename1.ui.geom.Dimension;

public class MyPrefSize {

	private Component myComp;

	private boolean percentageWidth;
	private boolean percentageHeight;
	private int myPrefWidth;
	private int myPrefHeight;

	public MyPrefSize(Component myComp, int myPrefWidth, int myPrefHeight, boolean percentageWidth, boolean percentageHeight) {
		super();
		this.myComp = myComp;
		this.myPrefWidth = myPrefWidth;
		this.myPrefHeight = myPrefHeight;
		this.percentageWidth = percentageWidth;
		this.percentageHeight = percentageHeight;
	}

	public int getMyPrefWidth() {
		return myPrefWidth;
	}

	public int getMyPrefHeight() {
		return myPrefHeight;
	}

	public boolean isPercentageWidth() {
		return percentageWidth;
	}

	public boolean isPercentageHeight() {
		return percentageHeight;
	}

	public void setMyPrefWidthHeight(int myPrefWidth, int myPrefHeight) {
		this.myPrefWidth = myPrefWidth;
		this.myPrefHeight = myPrefHeight;
		doCalcNew();
	}

	public void doCalcNew() {
		myComp.setShouldCalcPreferredSize(true);
	}

	public Dimension calcPreferredSize(Dimension dimFromSuper) {
		return calcPrefSize(myPrefWidth, myPrefHeight, dimFromSuper, percentageWidth, percentageHeight);
	}

	public static Dimension calcPrefSize(final int width, final int height, Dimension dim) {
		return calcPrefSize(width, height, dim, false, false);
	}

	public static Dimension calcPrefSize(final int width, final int height, Dimension dim, boolean percentageWidth, boolean percentageHeight) {

		int widthToSet = width;
		int heightToSet = height;

		if (percentageWidth) {
			int displayWidth = Display.getInstance().getDisplayWidth();
			widthToSet = (displayWidth / 100) * width;
		}

		if (percentageHeight) {
			int displayHeight = Display.getInstance().getDisplayHeight();
			heightToSet = (displayHeight / 100) * height;
		}

		if (widthToSet > 0) {
			dim.setWidth(widthToSet);
		}
		if (heightToSet > 0) {
			dim.setHeight(heightToSet);
		}
		return dim;
	}
}