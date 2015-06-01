package de.ar.tools.codenameone.gui.widgets.dnd;

import com.codename1.ui.Component;
import com.codename1.ui.Label;

import de.ar.tools.codenameone.gui.StyleTool;
import de.ar.tools.codenameone.gui.widgets.dnd.aaainterfaces.IComponentDnD_DragAndDropable;
import de.ar.tools.codenameone.gui.widgets.dnd.aaainterfaces.IComponentDnD_Dropable;

public class LabelDnDSpace extends Label implements IComponentDnD_Dropable {

	private ContainerDnD contDnDParent;

	public LabelDnDSpace(ContainerDnD contDnDParent) {
		super();
		this.contDnDParent = contDnDParent;
		setDraggable(false);
		setDropTarget(true);
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

		int height = dragged.getHeight();
		int width = dragged.getWidth();

		StyleTool.copyStyle(dragged, this);
		StyleTool.setOpactity(this, 100, true);

		setHeight(height);
		dragged.setHeight(0);

		setWidth(width);
		dragged.setWidth(0);

		setText(((Label) dragged).getText());
	}
}