package de.ar.tools.codenameone.gui.widgets.dnd.aaainterfaces;

public interface IComponentDnD_DragAndDropable extends IComponentDnD_Dropable {

	public void cancelDrag();

	public IComponentDnD_DragAndDropable cloneAfterDrag();

	public Object getDataObject();

}