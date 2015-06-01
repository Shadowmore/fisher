package de.ar.tools.codenameone.gui.widgets.prefferedSize;

import com.codename1.ui.Container;
import com.codename1.ui.geom.Dimension;
import com.codename1.ui.layouts.Layout;

public class ContainerWithMyPrefSize extends Container implements IComponentWithMyPrefSize {

	private MyPrefSize myPrefSize;

	public ContainerWithMyPrefSize(Layout layout, int myPrefWidth, int myPrefHeight, boolean percentageWidth, boolean percentageHeight) {
		super(layout);
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