package de.ar.tools.codenameone.gui.widgets.dnd.aaainterfaces;

import com.codename1.ui.Component;

public interface IComponentDnD_Dropable {

	public int getHeight();

	public int getWidth();

	public void copyStyleToSelf(Component dragged);

}