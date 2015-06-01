package de.ar.tools.codenameone.gui.widgets.tabs;

import com.codename1.ui.Button;

public class TabButton extends Button {

	private int index;

	public TabButton(String text, int index) {
		super(text);
		this.index = index;
	}

	public int getIndex() {
		return index;
	}

	public void fireClicked() {
		pressed();
		released();
	}
}
