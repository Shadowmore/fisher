package de.ar.tools.codenameone.gui.widgets.prefferedSize;

import com.codename1.ui.Label;
import com.codename1.ui.geom.Dimension;

public class LabelWithMyPrefSize extends Label implements IComponentWithMyPrefSize {

	private MyPrefSize myPrefSize;

	public LabelWithMyPrefSize(String text, int myPrefWidth, int myPrefHeight, boolean percentageWidth, boolean percentageHeight) {
		super(text);
		this.myPrefSize = new MyPrefSize(this, myPrefWidth, myPrefHeight, percentageWidth, percentageHeight);
	}

	@Override
	public void setMyPrefWidthHeight(int myPrefWidth, int myPrefHeight) {
		myPrefSize.setMyPrefWidthHeight(myPrefWidth, myPrefHeight);
	}

	@Override
	protected Dimension calcPreferredSize() {
		return myPrefSize.calcPreferredSize(super.calcPreferredSize());
	}

	@Override
	public void doCalcNew() {
		myPrefSize.doCalcNew();
	}
}