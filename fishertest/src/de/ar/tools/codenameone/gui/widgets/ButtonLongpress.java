package de.ar.tools.codenameone.gui.widgets;

import com.codename1.ui.Button;
import com.codename1.ui.events.ActionEvent;
import com.codename1.ui.events.ActionListener;

public abstract class ButtonLongpress extends Button {

	private boolean wasLong;

	public ButtonLongpress(String text) {
		super(text);

		super.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent evt) {
				if (wasLong) {
					wasLong = false;
				} else {
					myActionFired(evt);
				}
			}
		});
	}

	@Override
	public void longPointerPress(int x, int y) {
		wasLong = true;
		ActionEvent evt = new ActionEvent(this, x, y, true);
		myActionFired(evt);
	}

	@Override
	public void addActionListener(ActionListener l) {
		// ignored
	}

	protected abstract void myActionFired(ActionEvent evt);
}