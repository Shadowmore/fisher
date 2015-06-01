package de.ar.tools.codenameone.gui.widgets.dnd.button;

import com.codename1.ui.events.ActionEvent;
import com.codename1.ui.events.ActionListener;

import de.ar.tools.codenameone.gui.widgets.dnd.ContainerDnD;

public abstract class ButtonDnDLongpress extends ButtonDnD {

	private boolean wasLong;

	public ButtonDnDLongpress(ContainerDnD contParent, String text) {
		super(contParent, text);

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