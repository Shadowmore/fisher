package de.ar.tools.codenameone.gui.widgets.dnd.textarea;

import com.codename1.ui.Component;
import com.codename1.ui.TextArea;
import com.codename1.ui.events.ActionEvent;
import com.codename1.ui.events.ActionListener;

import de.ar.tools.codenameone.gui.widgets.dnd.ContainerDnD;
import de.ar.tools.codenameone.gui.widgets.dnd.aaainterfaces.IComponentDnD_DragAndDropable;
import de.ar.tools.codenameone.gui.widgets.dnd.aaainterfaces.IComponentDnD_Dropable;

public abstract class TextAreaDnD extends TextArea implements IComponentDnD_DragAndDropable {

	private ActionListener dragOverActionListener;
	private ContainerDnD contParent;
	private IComponentDnD_Dropable lastDnDTarget;

	public TextAreaDnD(ContainerDnD contParent, String text) {
		super(text);
		this.contParent = contParent;
		setDraggable(true);
		setDropTarget(false);
	}

	@Override
	protected void dragEnter(Component dragged) {
		if (dragged instanceof IComponentDnD_DragAndDropable) {
			getParentContainer().dragEnterButton((IComponentDnD_DragAndDropable) dragged, this);
		}
	}

	@Override
	public void drop(Component dragged, int x, int y) {

		if (dragged instanceof IComponentDnD_DragAndDropable) {
			getParentContainer().dropButton((IComponentDnD_DragAndDropable) dragged, this);
		}

		getParentContainer().setAllDropTarget(false);
		repaint();
	}

	@Override
	protected boolean isDragAndDropOperation(int x, int y) {
		if (!isDraggable()) {
			return false;
		}
		boolean isInDragArea = true;
		if (isInDragArea) {
			myBeginDrag();
		}
		return isInDragArea;
	}

	private void myBeginDrag() {
		getParentContainer().dragBeginButton(this);
		dragOverActionListener = new ActionListener() {
			public void actionPerformed(ActionEvent evt) {

				dragOverListener(evt);
			}
		};
		addDragOverListener(dragOverActionListener);
	}

	protected void dragOverListener(ActionEvent evt) {
		Component dropTarget = evt.getDropTarget();

		if (dropTarget != null) {
			if (dropTarget instanceof IComponentDnD_Dropable) {
				lastDnDTarget = (IComponentDnD_Dropable) dropTarget;
			}
		}
		boolean dragFinished = (dropTarget == null);
		if (dragFinished) {
			getParentContainer().dropButton(this, lastDnDTarget);
		}
	}

	public void cancelDrag() {
		removeDragOverListener(dragOverActionListener);
	}

	protected ContainerDnD getParentContainer() {
		return contParent;
	}

	@Override
	public void copyStyleToSelf(Component dragged) {
		// ignore
	}
}