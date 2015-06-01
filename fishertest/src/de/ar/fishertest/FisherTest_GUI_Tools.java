package de.ar.fishertest;

import com.codename1.ui.Button;
import com.codename1.ui.CheckBox;
import com.codename1.ui.ComponentGroup;
import com.codename1.ui.Container;
import com.codename1.ui.Label;
import com.codename1.ui.RadioButton;
import com.codename1.ui.TextArea;

import de.ar.tools.codenameone.gui.GUI_Tools;
import de.ar.tools.codenameone.gui.StyleTool;

public class FisherTest_GUI_Tools extends GUI_Tools {

	@Override
	protected void addStylesToButton(Button button) {
	}

	@Override
	protected void addStylesToCheckBox(CheckBox checkBox) {
	}

	@Override
	protected void addStylesToComponentGroup(ComponentGroup componentGroup) {
	}

	@Override
	protected void addStylesToContainer(Container container) {
		StyleTool.setBgColor(container, 255, 255, 255);
	}

	@Override
	protected void addStylesToLabel(Label label) {
	}

	@Override
	protected void addStylesToRadioButton(RadioButton radioButton) {
	}

	@Override
	protected void addStylesToTextArea(TextArea textArea) {
	}
}