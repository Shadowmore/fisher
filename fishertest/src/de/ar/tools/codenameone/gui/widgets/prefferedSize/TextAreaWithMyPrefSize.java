package de.ar.tools.codenameone.gui.widgets.prefferedSize;

import com.codename1.ui.TextArea;
import com.codename1.ui.geom.Dimension;

import de.ar.tools.codenameone.gui.GUI_Tools;

public class TextAreaWithMyPrefSize extends TextArea implements IComponentWithMyPrefSize {

	private MyPrefSize myPrefSize;

	public TextAreaWithMyPrefSize(int myPrefWidth, int myPrefHeight, boolean percentageWidth, boolean percentageHeight) {
		this("", 1, 1, GUI_Tools.DEFAULT_SIZE, myPrefWidth, myPrefHeight, percentageWidth, percentageHeight);
	}

	public TextAreaWithMyPrefSize(String text, int columns, int rows, int maxZeichen, int myPrefWidth, int myPrefHeight, boolean percentageWidth, boolean percentageHeight) {
		super(text, columns, rows);
		if (maxZeichen != GUI_Tools.DEFAULT_SIZE) {
			this.setMaxSize(maxZeichen);
		}
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
