package de.ar.tools.codenameone.gui.widgets.dnd;

import com.codename1.ui.Component;
import com.codename1.ui.Container;
import com.codename1.ui.Form;
import com.codename1.ui.layouts.Layout;

import de.ar.tools.codenameone.gui.widgets.dnd.aaainterfaces.IComponentDnD_DragAndDropable;
import de.ar.tools.codenameone.gui.widgets.dnd.aaainterfaces.IComponentDnD_Dropable;

public abstract class ContainerDnD extends Container {

	private Form formParent;

	private int beginDragIndex;
	private int lastDragEnterIndex;
	private boolean dragging;
	private boolean dropped;
	private IComponentDnD_DragAndDropable currentDraggedObject;

	private boolean canDropBeforeFirst;
	private boolean canDropBehindLast;

	protected IComponentDnD_Dropable componentDnDSpace;
	private boolean spaceComponentRemoved;

	public ContainerDnD(Form formParent, Layout layout) {
		super(layout);
		this.formParent = formParent;
		this.canDropBeforeFirst = true;
		this.canDropBehindLast = true;
		componentDnDSpace = generateComponentSpace(this);
	}

	public boolean getCanDropBeforeFirst() {
		return canDropBeforeFirst;
	}

	public void setCanDropBeforeFirst(boolean canDropBeforeFirst) {
		this.canDropBeforeFirst = canDropBeforeFirst;
	}

	public boolean getCanDropBehindLast() {
		return canDropBehindLast;
	}

	public void setCanDropBehindLast(boolean canDropBehindLast) {
		this.canDropBehindLast = canDropBehindLast;
	}

	public Form getFormParent() {
		return formParent;
	}

	public void dragEnterLabelSpace(IComponentDnD_DragAndDropable dragged) {
		lastDragEnterIndex = getComponentIndex((Component) componentDnDSpace);
	}

	public void dropLabelSpace(IComponentDnD_DragAndDropable dragged, IComponentDnD_Dropable dest) {
		dropButton(dragged, dest);
	}

	public void dragBeginButton(IComponentDnD_DragAndDropable dragged) {
		if (dragging) {
			if (currentDraggedObject != null) {
				if (currentDraggedObject != dragged) {
					currentDraggedObject.cancelDrag();
					currentDraggedObject = null;
				}
			}
		}
		dragging = true;
		currentDraggedObject = dragged;
		beginDragIndex = getComponentIndex((Component) dragged);
		lastDragEnterIndex = beginDragIndex;
		dropped = false;
		setAllDropTarget(true);
	}

	public void dragEnterButton(IComponentDnD_DragAndDropable dragged, IComponentDnD_DragAndDropable target) {

		Component dnDSpaceComponent = (Component) componentDnDSpace;
		Component draggedComponent = (Component) dragged;

		if (dnDSpaceComponent.getParent() != null) {
			removeComponent(dnDSpaceComponent);
		}

		if (dragged == target) {
			componentDnDSpace.copyStyleToSelf(draggedComponent);
			draggedComponent.setHeight(1);
			draggedComponent.setWidth(1);
		}
		int componentIndex = getComponentIndex((Component) target);
		int lueckeComponentIndex = componentIndex;
		if (lastDragEnterIndex <= componentIndex) {
			lueckeComponentIndex += 1;
		}
		lastDragEnterIndex = componentIndex;
		addComponent(lueckeComponentIndex, dnDSpaceComponent);
	}

	public void dropButton(IComponentDnD_DragAndDropable dragged, IComponentDnD_Dropable dest) {
		if (dropped) {
			return;
		}

		dragging = false;
		currentDraggedObject = null;
		dropped = true;

		Component compDnDSpaceComponent = (Component) componentDnDSpace;
		Component draggedComponent = (Component) dragged;
		Component destComponent = (Component) dest;

		int extened = 0;
		spaceComponentRemoved = false;
		if (compDnDSpaceComponent.getParent() != null) {
			removeComponent(compDnDSpaceComponent);
			spaceComponentRemoved = true;
		}

		int destIndex = getComponentIndex(destComponent);

		removeComponent(draggedComponent);

		int dropIndex = -1;
		if (destIndex == -1) {
			dropIndex = beginDragIndex;
		} else {
			dropIndex = destIndex + extened;
			if (dragged != dest) {
				int componentCount = getComponentCount();
				if (!canDropBeforeFirst && (dropIndex == 0 || componentCount >= 2)) {
					dropIndex = 1;
				}

				int custCompCount = componentCount;
				if (spaceComponentRemoved) {
					//	custCompCount--;
				}
				if (!canDropBehindLast && dropIndex == custCompCount) {
					dropIndex = dropIndex - 1;
				}
			}
		}
		IComponentDnD_DragAndDropable cloneAfterDrag = dragged.cloneAfterDrag();
		addComponent(dropIndex, (Component) cloneAfterDrag);
		//		animateLayout(500); INFO: Exception if additonal dropButton()-Calls while animateLayout()

		updateListAfterDrop(cloneAfterDrag, dropIndex);

		setAllDropTarget(false);
		repaint();
	}

	public void setAllDropTarget(boolean b) {
		int componentCount = getComponentCount();
		for (int i = 0; i < componentCount; i++) {
			getComponentAt(i).setDropTarget(b);
		}
		if (!canDropBeforeFirst) {
			getComponentAt(0).setDropTarget(false);
		}
		if (!canDropBehindLast) {
			getComponentAt(getComponentCount() - 1).setDropTarget(false);
		}
	}

	@Override
	public void scrollRectToVisible(int x, int y, int width, int height, Component coordinateSpace) {
		boolean keinScroll = dragging;
		if (keinScroll) {
			repaint();
		} else {
			super.scrollRectToVisible(x, y, width, height, coordinateSpace);
		}
	}

	@Override
	protected boolean draggingOver(Component dragged, int x, int y) {
		repaint();
		return super.draggingOver(dragged, x, y);
	}

	@Override
	public void repaint() {
		formParent.repaint();
		super.repaint();
	}

	protected abstract void updateListAfterDrop(IComponentDnD_DragAndDropable dragged, int destIndex);

	protected abstract IComponentDnD_Dropable generateComponentSpace(ContainerDnD containerDnD);

}