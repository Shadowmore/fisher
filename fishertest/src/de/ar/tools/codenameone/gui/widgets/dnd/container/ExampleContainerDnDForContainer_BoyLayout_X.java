package de.ar.tools.codenameone.gui.widgets.dnd.container;

import com.codename1.io.Log;
import com.codename1.ui.Form;
import com.codename1.ui.layouts.BoxLayout;
import com.codename1.ui.layouts.Layout;

import de.ar.tools.codenameone.gui.widgets.dnd.ContainerDnD;
import de.ar.tools.codenameone.gui.widgets.dnd.aaainterfaces.IComponentDnD_DragAndDropable;
import de.ar.tools.codenameone.gui.widgets.dnd.aaainterfaces.IComponentDnD_Dropable;

public class ExampleContainerDnDForContainer_BoyLayout_X extends ContainerDnD {

	public ExampleContainerDnDForContainer_BoyLayout_X(Form formParent, Layout layout) {
		super(formParent, layout);
	}

	@Override
	protected void updateListAfterDrop(IComponentDnD_DragAndDropable dragged, int destIndex) {
		Log.p("ExampleContainerDnDForContainer_BoyLayout_X.updateListAfterDrop()");
	}

	@Override
	protected IComponentDnD_Dropable generateComponentSpace(ContainerDnD containerDnD) {
		return new ExampleContainerDnDSpace(containerDnD, new BoxLayout(BoxLayout.X_AXIS));
	}
}