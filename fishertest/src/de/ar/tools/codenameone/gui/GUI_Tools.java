package de.ar.tools.codenameone.gui;

import com.codename1.ui.Button;
import com.codename1.ui.CheckBox;
import com.codename1.ui.ComponentGroup;
import com.codename1.ui.Container;
import com.codename1.ui.Font;
import com.codename1.ui.Image;
import com.codename1.ui.Label;
import com.codename1.ui.RadioButton;
import com.codename1.ui.TextArea;
import com.codename1.ui.layouts.Layout;

import de.ar.tools.codenameone.gui.widgets.prefferedSize.ButtonWithMyPrefSize;
import de.ar.tools.codenameone.gui.widgets.prefferedSize.CheckBoxWithMyPrefSize;
import de.ar.tools.codenameone.gui.widgets.prefferedSize.ComponentGroupWithMyPrefSize;
import de.ar.tools.codenameone.gui.widgets.prefferedSize.ContainerWithMyPrefSize;
import de.ar.tools.codenameone.gui.widgets.prefferedSize.LabelWithMyPrefSize;
import de.ar.tools.codenameone.gui.widgets.prefferedSize.RadioButtonWithMyPrefSize;
import de.ar.tools.codenameone.gui.widgets.prefferedSize.TextAreaWithMyPrefSize;

public class GUI_Tools {

	public static final int DEFAULT_ROWS = -1;
	public static final int DEFAULT_COMULMS = -1;
	public static final int DEFAULT_SIZE = -1;
	public static final int MINIMAL_SIZE = 1;
	public static final Font DEFAULT_FONT = Font.getDefaultFont();

	public static int defaultColor = -1;

	protected static GUI_Tools instance;

	private static GUI_Tools getInstance() {
		if (instance == null) {
			instance = new GUI_Tools();
		}
		return instance;
	}

	//BUTTON
	// Without LayoutAlignment
	public static Button makeTransparentButton(Container contParent, String text) {
		return getInstance().makeTransparentButton(contParent, null, text, null, Button.LEFT, defaultColor, null, DEFAULT_SIZE, DEFAULT_SIZE, false, false);
	}

	public static Button makeTransparentButton(Container contParent, String text, int alignment, Font font) {
		return getInstance().makeTransparentButton(contParent, null, text, null, alignment, defaultColor, font, DEFAULT_SIZE, DEFAULT_SIZE, false, false);
	}

	public static Button makeTransparentButton(Container contParent, Image img, int alignment, Font font) {
		return getInstance().makeTransparentButton(contParent, null, "", img, alignment, defaultColor, font, DEFAULT_SIZE, DEFAULT_SIZE, false, false);
	}

	public static Button makeTransparentButton(Container contParent, String text, int alignment, int color, Font font) {
		return getInstance().makeTransparentButton(contParent, null, text, null, alignment, color, font, DEFAULT_SIZE, DEFAULT_SIZE, false, false);
	}

	public static Button makeTransparentButton(Container contParent, String text, int alignment, int width, int height, boolean percentageWidth, boolean percentageHeight) {
		return getInstance().makeTransparentButton(contParent, null, text, null, alignment, defaultColor, null, width, height, percentageWidth, percentageHeight);
	}

	public static Button makeTransparentButton(Container contParent, String text, int alignment, Font font, int width, int height, boolean percentageWidth, boolean percentageHeight) {
		return getInstance().makeTransparentButton(contParent, null, text, null, alignment, defaultColor, font, width, height, percentageWidth, percentageHeight);
	}

	public static Button makeTransparentButton(Container contParent, Image img, int alignment, int width, int height, boolean percentageWidth, boolean percentageHeight) {
		return getInstance().makeTransparentButton(contParent, null, "", img, alignment, defaultColor, null, width, height, percentageWidth, percentageHeight);
	}

	// With LayoutAlignment
	public static Button makeTransparentButton(Container contParent, String layoutAlignment, String text) {
		return getInstance().makeTransparentButton(contParent, layoutAlignment, text, null, Button.LEFT, defaultColor, null, DEFAULT_SIZE, DEFAULT_SIZE, false, false);
	}

	public static Button makeTransparentButton(Container contParent, String layoutAlignment, String text, int alignment, Font font) {
		return getInstance().makeTransparentButton(contParent, layoutAlignment, text, null, alignment, defaultColor, font, DEFAULT_SIZE, DEFAULT_SIZE, false, false);
	}

	public static Button makeTransparentButton(Container contParent, String layoutAlignment, Image img, int alignment, Font font) {
		return getInstance().makeTransparentButton(contParent, layoutAlignment, "", img, alignment, defaultColor, font, DEFAULT_SIZE, DEFAULT_SIZE, false, false);
	}

	public static Button makeTransparentButton(Container contParent, String layoutAlignment, String text, int alignment, int color, Font font) {
		return getInstance().makeTransparentButton(contParent, layoutAlignment, text, null, alignment, color, font, DEFAULT_SIZE, DEFAULT_SIZE, false, false);
	}

	public static Button makeTransparentButton(Container contParent, String layoutAlignment, String text, int alignment, int width, int height, boolean percentageWidth, boolean percentageHeight) {
		return getInstance().makeTransparentButton(contParent, layoutAlignment, text, null, alignment, defaultColor, null, width, height, percentageWidth, percentageHeight);
	}

	public static Button makeTransparentButton(Container contParent, String layoutAlignment, String text, int alignment, Font font, int width, int height, boolean percentageWidth,
			boolean percentageHeight) {
		return getInstance().makeTransparentButton(contParent, layoutAlignment, text, null, alignment, defaultColor, font, width, height, percentageWidth, percentageHeight);
	}

	public static Button makeTransparentButton(Container contParent, String layoutAlignment, Image img, int alignment, int width, int height, boolean percentageWidth, boolean percentageHeight) {
		return getInstance().makeTransparentButton(contParent, layoutAlignment, "", img, alignment, defaultColor, null, width, height, percentageWidth, percentageHeight);
	}

	protected Button makeTransparentButton(Container contParent, String layoutAlignment, String text, Image img, int alignment, int color, Font font, final int width, final int height,
			boolean percentageWidth, boolean percentageHeight) {
		Button button;
		if (width == DEFAULT_SIZE && height == DEFAULT_SIZE) {
			button = new Button(text);
		} else {
			button = new ButtonWithMyPrefSize(text, width, height, percentageWidth, percentageHeight);
		}
		button.setIcon(img);
		//		button.setUIID("MenuButton");
		StyleTool.setAlignment(button, alignment);
		if (color != defaultColor) {
			StyleTool.setFgColor(button, color, true);
		}
		if (font != null) {
			StyleTool.setFont(button, font, true);
			//		font = DEFAULT_FONT;
		}
		if (layoutAlignment != null) {
			contParent.addComponent(layoutAlignment, button);
		} else {
			contParent.addComponent(button);
		}
		addStylesToButton(button);
		return button;
	}

	protected void addStylesToButton(Button button) {
		// override in child class
	}

	//LABEL
	// Without LayoutAlignment
	public static Label makeTransparentLabel(Container contParent, String text) {
		return getInstance().makeTransparentLabel(contParent, null, text, null, Label.LEFT, defaultColor, null, DEFAULT_SIZE, DEFAULT_SIZE, false, false);
	}

	public static Label makeTransparentLabel(Container contParent, String text, int alignment, Font font) {
		return getInstance().makeTransparentLabel(contParent, null, text, null, alignment, defaultColor, font, DEFAULT_SIZE, DEFAULT_SIZE, false, false);
	}

	public static Label makeTransparentLabel(Container contParent, String text, int alignment, int color, Font font) {
		return getInstance().makeTransparentLabel(contParent, null, text, null, alignment, color, font, DEFAULT_SIZE, DEFAULT_SIZE, false, false);
	}

	public static Label makeTransparentLabel(Container contParent, Image img, int alignment, Font font) {
		return getInstance().makeTransparentLabel(contParent, null, "", img, alignment, defaultColor, font, DEFAULT_SIZE, DEFAULT_SIZE, false, false);
	}

	public static Label makeTransparentLabel(Container contParent, Image img, int alignment, int color, Font font) {
		return getInstance().makeTransparentLabel(contParent, null, "", img, alignment, color, font, DEFAULT_SIZE, DEFAULT_SIZE, false, false);
	}

	public static Label makeTransparentLabel(Container contParent, String text, int alignment, int width, int height, boolean percentageWidth, boolean percentageHeight) {
		return getInstance().makeTransparentLabel(contParent, null, text, null, alignment, defaultColor, null, width, height, percentageWidth, percentageHeight);
	}

	public static Label makeTransparentLabel(Container contParent, Image img, int alignment, int width, int height, boolean percentageWidth, boolean percentageHeight) {
		return getInstance().makeTransparentLabel(contParent, null, "", img, alignment, defaultColor, null, width, height, percentageWidth, percentageHeight);
	}

	public static Label makeTransparentLabel(Container contParent, String text, int alignment, Font font, int width, int height, boolean percentageWidth, boolean percentageHeight) {
		return getInstance().makeTransparentLabel(contParent, null, text, null, alignment, defaultColor, font, width, height, percentageWidth, percentageHeight);
	}

	// With LayoutAlignment
	public static Label makeTransparentLabel(Container contParent, String layoutAlignment, String text) {
		return getInstance().makeTransparentLabel(contParent, layoutAlignment, text, null, Label.LEFT, defaultColor, null, DEFAULT_SIZE, DEFAULT_SIZE, false, false);
	}

	public static Label makeTransparentLabel(Container contParent, String layoutAlignment, String text, int alignment) {
		return getInstance().makeTransparentLabel(contParent, layoutAlignment, text, null, alignment, defaultColor, null, DEFAULT_SIZE, DEFAULT_SIZE, false, false);
	}

	public static Label makeTransparentLabel(Container contParent, String layoutAlignment, String text, int alignment, Font font) {
		return getInstance().makeTransparentLabel(contParent, layoutAlignment, text, null, alignment, defaultColor, font, DEFAULT_SIZE, DEFAULT_SIZE, false, false);
	}

	public static Label makeTransparentLabel(Container contParent, String layoutAlignment, String text, int alignment, int color, Font font) {
		return getInstance().makeTransparentLabel(contParent, layoutAlignment, text, null, alignment, color, font, DEFAULT_SIZE, DEFAULT_SIZE, false, false);
	}

	public static Label makeTransparentLabel(Container contParent, String layoutAlignment, Image img, int alignment, Font font) {
		return getInstance().makeTransparentLabel(contParent, layoutAlignment, "", img, alignment, defaultColor, font, DEFAULT_SIZE, DEFAULT_SIZE, false, false);
	}

	public static Label makeTransparentLabel(Container contParent, String layoutAlignment, Image img, int alignment, int color, Font font) {
		return getInstance().makeTransparentLabel(contParent, layoutAlignment, "", img, alignment, color, font, DEFAULT_SIZE, DEFAULT_SIZE, false, false);
	}

	public static Label makeTransparentLabel(Container contParent, String layoutAlignment, String text, int alignment, int width, int height, boolean percentageWidth, boolean percentageHeight) {
		return getInstance().makeTransparentLabel(contParent, layoutAlignment, text, null, alignment, defaultColor, null, width, height, percentageWidth, percentageHeight);
	}

	public static Label makeTransparentLabel(Container contParent, String layoutAlignment, Image img, int alignment, int width, int height, boolean percentageWidth, boolean percentageHeight) {
		return getInstance().makeTransparentLabel(contParent, layoutAlignment, "", img, alignment, defaultColor, null, width, height, percentageWidth, percentageHeight);
	}

	public static Label makeTransparentLabel(Container contParent, String layoutAlignment, String text, int alignment, Font font, int width, int height, boolean percentageWidth,
			boolean percentageHeight) {
		return getInstance().makeTransparentLabel(contParent, layoutAlignment, text, null, alignment, defaultColor, font, width, height, percentageWidth, percentageHeight);
	}

	protected Label makeTransparentLabel(Container contParent, String layoutAlignment, String text, Image img, int alignment, int color, Font font, final int width, final int height,
			boolean percentageWidth, boolean percentageHeight) {
		Label label;
		if (width == DEFAULT_SIZE && height == DEFAULT_SIZE) {
			label = new Label(text);
		} else {
			label = new LabelWithMyPrefSize(text, width, height, percentageWidth, percentageHeight);
		}

		label.setIcon(img);
		StyleTool.setAlignment(label, alignment);
		if (color == 0) {
			color = ColorTool.BLACK;
		}
		if (color != defaultColor) {
			StyleTool.setFgColor(label, color, true);
		}
		if (font != null) {
			StyleTool.setFont(label, font, true);
			//			font = DEFAULT_FONT;
		}
		StyleTool.setBgTransparency(label, 0, true);
		//label.setUIID("TransparentLabel");

		if (layoutAlignment != null) {
			contParent.addComponent(layoutAlignment, label);
		} else {
			contParent.addComponent(label);
		}
		addStylesToLabel(label);
		return label;
	}

	protected void addStylesToLabel(Label label) {
		// override in child class
	}

	//CONTAINER

	// Without LayoutAlignment
	public static Container makeTransparentContainer(Container contParent, Layout layout) {
		return getInstance().makeTransparentContainer(contParent, layout, null, DEFAULT_SIZE, DEFAULT_SIZE, false, false);
	}

	// With LayoutAlignment
	public static Container makeTransparentContainer(Container contParent, Layout layout, String layoutAlignment) {
		return getInstance().makeTransparentContainer(contParent, layout, layoutAlignment, DEFAULT_SIZE, DEFAULT_SIZE, false, false);
	}

	protected Container makeTransparentContainer(Container contParent, Layout layout, String layoutAlignment, final int width, final int height, boolean percentageWidth, boolean percentageHeight) {
		Container cont;
		if (width == DEFAULT_SIZE && height == DEFAULT_SIZE) {
			cont = new Container(layout);
		} else {
			cont = new ContainerWithMyPrefSize(layout, width, height, percentageWidth, percentageHeight);
		}
		//		cont.setUIID("TransparentContainer");

		if (layoutAlignment != null) {
			contParent.addComponent(layoutAlignment, cont);
		} else {
			contParent.addComponent(cont);
		}
		addStylesToContainer(cont);
		return cont;
	}

	protected void addStylesToContainer(Container container) {
		// override in child class
	}

	// Without LayoutAlignment
	public static ComponentGroup makeTransparentComponentGroup(Container contParent, final int width, final int height, boolean percentageWidth, boolean percentageHeight) {
		return getInstance().makeTransparentComponentGroup_P(contParent, null, width, height, percentageWidth, percentageHeight);
	}

	// With LayoutAlignment
	public static ComponentGroup makeTransparentComponentGroup(Container contParent, String layoutAlignment, final int width, final int height, boolean percentageWidth, boolean percentageHeight) {
		return getInstance().makeTransparentComponentGroup_P(contParent, layoutAlignment, width, height, percentageWidth, percentageHeight);
	}

	protected ComponentGroup makeTransparentComponentGroup_P(Container contParent, String layoutAlignment, final int width, final int height, boolean percentageWidth, boolean percentageHeight) {
		ComponentGroup group;
		if (width == DEFAULT_SIZE && height == DEFAULT_SIZE) {
			group = new ComponentGroup();
		} else {
			group = new ComponentGroupWithMyPrefSize(width, height, percentageWidth, percentageHeight);
		}
		//		group.setUIID("TransparentContainer");

		if (layoutAlignment != null) {
			contParent.addComponent(layoutAlignment, group);
		} else {
			contParent.addComponent(group);
		}
		addStylesToComponentGroup(group);
		return group;
	}

	protected void addStylesToComponentGroup(ComponentGroup componentGroup) {
		// override in child class
	}

	// Without LayoutAlignment
	public static TextArea makeTransparentTextArea(Container contParent, String text, String hint) {
		return getInstance().makeTransparentTextArea(contParent, null, text, hint, DEFAULT_COMULMS, DEFAULT_ROWS, 0, DEFAULT_SIZE, DEFAULT_SIZE, false, false);
	}

	public static TextArea makeTransparentTextArea(Container contParent, String text, String hint, int maxZeichen, final int width, final int height, boolean percentageWidth, boolean percentageHeight) {
		return getInstance().makeTransparentTextArea(contParent, null, text, hint, 1, 1, maxZeichen, width, height, percentageWidth, percentageHeight);
	}

	public static TextArea makeTransparentTextArea(Container contParent, String text, String hint, final int width, final int height, boolean percentageWidth, boolean percentageHeight) {
		return getInstance().makeTransparentTextArea(contParent, null, text, hint, 1, 1, GUI_Tools.DEFAULT_SIZE, width, height, percentageWidth, percentageHeight);
	}

	public static TextArea makeTransparentTextArea(Container contParent, String text, String hint, int maxZeichen, int colums, int rows) {
		return getInstance().makeTransparentTextArea(contParent, null, text, hint, colums, rows, maxZeichen, DEFAULT_SIZE, DEFAULT_SIZE, false, false);
	}

	public static TextArea makeTransparentTextArea(Container contParent, String text, String hint, int colums, int rows) {
		return getInstance().makeTransparentTextArea(contParent, null, text, hint, colums, rows, GUI_Tools.DEFAULT_SIZE, DEFAULT_SIZE, DEFAULT_SIZE, false, false);
	}

	public static TextArea makeTransparentTextArea(Container contParent, String text, String hint, int colums, int rows, final int width, final int height, boolean percentageWidth,
			boolean percentageHeight) {
		return getInstance().makeTransparentTextArea(contParent, null, text, hint, colums, rows, GUI_Tools.DEFAULT_SIZE, width, height, percentageWidth, percentageHeight);
	}

	// With LayoutAlignment
	public static TextArea makeTransparentTextArea(Container contParent, String layoutAlignment, String text, String hint) {
		return getInstance().makeTransparentTextArea(contParent, layoutAlignment, text, hint, DEFAULT_COMULMS, DEFAULT_ROWS, 0, DEFAULT_SIZE, DEFAULT_SIZE, false, false);
	}

	public static TextArea makeTransparentTextArea(Container contParent, String layoutAlignment, String text, String hint, int maxZeichen, final int width, final int height, boolean percentageWidth,
			boolean percentageHeight) {
		return getInstance().makeTransparentTextArea(contParent, layoutAlignment, text, hint, 1, 1, maxZeichen, width, height, percentageWidth, percentageHeight);
	}

	public static TextArea makeTransparentTextArea(Container contParent, String layoutAlignment, String text, String hint, final int width, final int height, boolean percentageWidth,
			boolean percentageHeight) {
		return getInstance().makeTransparentTextArea(contParent, null, text, hint, 1, 1, GUI_Tools.DEFAULT_SIZE, width, height, percentageWidth, percentageHeight);
	}

	public static TextArea makeTransparentTextArea(Container contParent, String layoutAlignment, String text, String hint, int maxZeichen, int colums, int rows) {
		return getInstance().makeTransparentTextArea(contParent, layoutAlignment, text, hint, colums, rows, maxZeichen, DEFAULT_SIZE, DEFAULT_SIZE, false, false);
	}

	public static TextArea makeTransparentTextArea(Container contParent, String layoutAlignment, String text, String hint, int colums, int rows) {
		return getInstance().makeTransparentTextArea(contParent, layoutAlignment, text, hint, colums, rows, GUI_Tools.DEFAULT_SIZE, DEFAULT_SIZE, DEFAULT_SIZE, false, false);
	}

	public static TextArea makeTransparentTextArea(Container contParent, String layoutAlignment, String text, String hint, int colums, int rows, final int width, final int height,
			boolean percentageWidth, boolean percentageHeight) {
		return getInstance().makeTransparentTextArea(contParent, layoutAlignment, text, hint, colums, rows, GUI_Tools.DEFAULT_SIZE, width, height, percentageWidth, percentageHeight);
	}

	protected TextArea makeTransparentTextArea(Container contParent, String layoutAlignment, String text, String hint, int columns, int rows, int maxZeichen, final int width, final int height,
			boolean percentageWidth, boolean percentageHeight) {
		TextArea textArea;
		if (width == DEFAULT_SIZE && height == DEFAULT_SIZE && columns == DEFAULT_COMULMS && rows == DEFAULT_ROWS) {
			textArea = new TextArea(text);
		} else if (width == DEFAULT_SIZE && height == DEFAULT_SIZE) {
			textArea = new TextArea(text, columns, rows);
			textArea.setSingleLineTextArea(true);
			if (maxZeichen != DEFAULT_SIZE) {
				textArea.setMaxSize(maxZeichen);
			}
		} else {
			textArea = new TextAreaWithMyPrefSize(text, columns, rows, maxZeichen, width, height, percentageWidth, percentageHeight);
		}
		textArea.setHint(hint);
		StyleTool.setBgTransparency(textArea, 0, true);

		if (layoutAlignment != null) {
			contParent.addComponent(layoutAlignment, textArea);
		} else {
			contParent.addComponent(textArea);
		}
		addStylesToTextArea(textArea);
		return textArea;
	}

	protected void addStylesToTextArea(TextArea textArea) {
		// override in child class
	}

	// Without LayoutAlignment
	public static RadioButton makeTransparentRadioButton(Container contParent, String text, boolean selection) {
		return getInstance().generateTransparentRadioButton(contParent, null, text, selection, null, Button.LEFT, defaultColor, null, DEFAULT_SIZE, DEFAULT_SIZE, false, false);
	}

	// With LayoutAlignment
	public RadioButton makeTransparentRadioButton(Container contParent, String layoutAlignment, String text, boolean selection) {
		return getInstance().generateTransparentRadioButton(contParent, layoutAlignment, text, selection, null, Button.LEFT, defaultColor, null, DEFAULT_SIZE, DEFAULT_SIZE, false, false);
	}

	protected RadioButton generateTransparentRadioButton(Container contParent, String layoutAlignment, String text, boolean selection, Image img, int alignment, int color, Font font, final int width,
			int height, boolean percentageWidth, boolean percentageHeight) {
		RadioButton radioButton;
		if (width == DEFAULT_SIZE && height == DEFAULT_SIZE) {
			radioButton = new RadioButton(text);
		} else {
			radioButton = new RadioButtonWithMyPrefSize(text, width, height, percentageWidth, percentageHeight);
		}
		radioButton.setIcon(img);
		//		button.setUIID("MenuButton");
		StyleTool.setAlignment(radioButton, alignment);
		if (color != defaultColor) {
			StyleTool.setFgColor(radioButton, color, true);
		}
		if (font != null) {
			StyleTool.setFont(radioButton, font, true);
			//		font = DEFAULT_FONT;
		}
		if (layoutAlignment != null) {
			contParent.addComponent(layoutAlignment, radioButton);
		} else {
			contParent.addComponent(radioButton);
		}
		addStylesToRadioButton(radioButton);
		radioButton.setSelected(selection);
		return radioButton;
	}

	protected void addStylesToRadioButton(RadioButton radioButton) {
		// override in child class
	}

	// Without LayoutAlignment
	public static CheckBox makeTransparentCheckBox(Container contParent, String text, boolean selection) {
		return getInstance().generateTransparentCheckBox(contParent, null, text, selection, null, Button.LEFT, defaultColor, null, DEFAULT_SIZE, DEFAULT_SIZE, false, false);
	}

	// With LayoutAlignment
	public CheckBox makeTransparentCheckBox(Container contParent, String layoutAlignment, String text, boolean selection) {
		return getInstance().generateTransparentCheckBox(contParent, layoutAlignment, text, selection, null, Button.LEFT, defaultColor, null, DEFAULT_SIZE, DEFAULT_SIZE, false, false);
	}

	protected CheckBox generateTransparentCheckBox(Container contParent, String layoutAlignment, String text, boolean selection, Image img, int alignment, int color, Font font, final int width,
			int height, boolean percentageWidth, boolean percentageHeight) {
		CheckBox checkBox;
		if (width == DEFAULT_SIZE && height == DEFAULT_SIZE) {
			checkBox = new CheckBox(text);
		} else {
			checkBox = new CheckBoxWithMyPrefSize(text, width, height, percentageWidth, percentageHeight);
		}
		checkBox.setIcon(img);
		//		button.setUIID("MenuButton");
		StyleTool.setAlignment(checkBox, alignment);
		if (color != defaultColor) {
			StyleTool.setFgColor(checkBox, color, true);
		}
		if (font != null) {
			StyleTool.setFont(checkBox, font, true);
			//		font = DEFAULT_FONT;
		}
		if (layoutAlignment != null) {
			contParent.addComponent(layoutAlignment, checkBox);
		} else {
			contParent.addComponent(checkBox);
		}
		addStylesToCheckBox(checkBox);
		checkBox.setSelected(selection);
		return checkBox;
	}

	protected void addStylesToCheckBox(CheckBox checkBox) {
		// TODO Auto-generated method stub

	}
}