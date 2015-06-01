package de.ar.tools.codenameone.gui.widgets.dnd.button;

import com.codename1.ui.Button;
import com.codename1.ui.Component;

import de.ar.tools.codenameone.gui.StyleTool;
import de.ar.tools.codenameone.gui.widgets.dnd.ContainerDnD;
import de.ar.tools.codenameone.gui.widgets.dnd.aaainterfaces.IComponentDnD_DragAndDropable;
import de.ar.tools.codenameone.gui.widgets.dnd.aaainterfaces.IComponentDnD_Dropable;

public class ButtonDnDSpace extends Button implements IComponentDnD_Dropable {

	private ContainerDnD contDnDParent;

	public ButtonDnDSpace(ContainerDnD contDnDParent) {
		super();
		this.contDnDParent = contDnDParent;
		setDraggable(false);
		setDropTarget(false);
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
		StyleTool.setOpactity(this, 50, true);

		//		setPreferredH(height);
		//		setPreferredW(width);
		setWidth(width);
		setHeight(height);

		setText(((Button) dragged).getText());
	}
}