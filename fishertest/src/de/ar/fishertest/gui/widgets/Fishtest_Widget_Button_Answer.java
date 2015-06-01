package de.ar.fishertest.gui.widgets;

import com.codename1.components.MultiButton;
import com.codename1.ui.TextArea;
import com.codename1.ui.events.ActionEvent;
import com.codename1.ui.events.ActionListener;
import com.codename1.ui.layouts.BorderLayout;

import de.ar.tools.codenameone.gui.StyleTool;

public class Fishtest_Widget_Button_Answer extends MultiButton {

	private boolean selected;
	private TextArea ta;

	public Fishtest_Widget_Button_Answer(String text) {
		// super(text, 2, 2);
		super();

		addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent evt) {
				actionPerfomed(evt);
			}
		});

		StyleTool.setFgColor(this, 0, 0, 0);
		StyleTool.setBgColor(this, 255, 255, 255);
		StyleTool.setBorder(this, null, true);

		ta = new TextArea(text);
		ta.setEditable(false);

		StyleTool.setFgColor(ta, 0, 0, 0);
		StyleTool.setBgColor(ta, 255, 255, 255);
		StyleTool.setBorder(ta, null, true);
		StyleTool.setBgTransparency(ta, 255, true);
		StyleTool.setBgImage(ta, null, true);
		// setFocusable(false);
		addComponent(BorderLayout.CENTER, ta);

	}

	private void actionPerfomed(ActionEvent evt) {
		selected = !selected;

		if (selected) {
			setBgColor(0, 191, 255);
		} else {
			setBgColor(255, 255, 255);
		}
		revalidate();
	}

	public void setBgColor(int r, int g, int b) {
		StyleTool.setBgColor(ta, r, g, b);
		StyleTool.setBgColor(this, r, g, b);
	}

	public void reset() {
		selected = false;
		setBgColor(255, 255, 255);
	}

	public boolean isSelected() {
		return selected;
	}

	public void setText(String text) {
		ta.setText(text);
	}
}