package de.ar.tools.codenameone.gui.widgets.dnd.container;

import com.codename1.ui.Button;
import com.codename1.ui.Component;
import com.codename1.ui.Container;
import com.codename1.ui.TextArea;
import com.codename1.ui.layouts.Layout;

import de.ar.tools.codenameone.gui.StyleTool;
import de.ar.tools.codenameone.gui.widgets.dnd.ContainerDnD;
import de.ar.tools.codenameone.gui.widgets.dnd.aaainterfaces.IComponentDnD_DragAndDropable;
import de.ar.tools.codenameone.gui.widgets.dnd.aaainterfaces.IComponentDnD_Dropable;

public class ExampleContainerDnDSpace extends Container implements IComponentDnD_Dropable {

	private ContainerDnD contDnDParent;
	private TextArea ta;

	public ExampleContainerDnDSpace(ContainerDnD contDnDParent, Layout layoutSpace) {
		super(layoutSpace);
		this.contDnDParent = contDnDParent;
		setDraggable(false);
		setDropTarget(false);

		Button btn = new Button("-");
		addComponent(btn);

		ta = new TextArea("");
		addComponent(ta);
	}

	@Override
	protected void dragEnter(Component dragged) {
		if (dragged instanceof IComponentDnD_DragAndDropable) {
			contDnDParent.dragEnterLabelSpace((IComponentDnD_DragAndDropable) dragged);
		}
	}

	@Override
	public void drop(Component dragged, int x, int y) {
		if (dragged instanceof IComponentDnD_DragAndDropable) {
			contDnDParent.dropLabelSpace((IComponentDnD_DragAndDropable) dragged, this);
		}
	}

	@Override
	public void copyStyleToSelf(Component dragged) {

		ExampleContainerDnD contDnD = (ExampleContainerDnD) dragged;

		int height = dragged.getHeight();
		int width = dragged.getWidth();

		StyleTool.copyStyle(dragged, this);
		StyleTool.setOpactity(this, 50, true);

		String dataObject = contDnD.getDataObject();

		ta.setText(dataObject);

		setHeight(height);
		setWidth(width);
	}
}