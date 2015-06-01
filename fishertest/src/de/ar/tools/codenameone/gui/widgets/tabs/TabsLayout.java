package de.ar.tools.codenameone.gui.widgets.tabs;

import com.codename1.ui.Component;
import com.codename1.ui.Container;
import com.codename1.ui.geom.Dimension;
import com.codename1.ui.layouts.Layout;
import com.codename1.ui.plaf.Style;

public class TabsLayout extends Layout {

	public void layoutContainer(Container parent) {
		final int size = parent.getComponentCount();
		int activeComponent = 0;
		if (parent instanceof Tabs) {
			activeComponent = ((Tabs) parent).getSelectionIndex();
		} else {
			Container parent2 = parent.getParent();
			if (parent2 instanceof Tabs) {
				activeComponent = ((Tabs) parent2).getSelectionIndex();
			}
		}

		int tabWidth = parent.getWidth();
		for (int i = 0; i < size; i++) {
			int xOffset;
			if (parent.isRTL()) {
				xOffset = (size - i) * tabWidth;
				xOffset -= ((size - activeComponent) * tabWidth);
			} else {
				xOffset = i * tabWidth;
				xOffset -= (activeComponent * tabWidth);
			}

			Component component = parent.getComponentAt(i);
			component.setX(component.getStyle().getMargin(Component.LEFT) + xOffset);
			component.setY(component.getStyle().getMargin(Component.TOP));
			component.setWidth(tabWidth - component.getStyle().getMargin(Component.LEFT) - component.getStyle().getMargin(Component.RIGHT));
			component.setHeight(parent.getHeight() - component.getStyle().getMargin(Component.TOP) - component.getStyle().getMargin(Component.BOTTOM));
		}
	}

	public Dimension getPreferredSize(Container parent) {
		// fill
		Dimension dim = new Dimension(0, 0);
		dim.setWidth(parent.getWidth() + parent.getStyle().getPadding(false, Component.LEFT) + parent.getStyle().getPadding(false, Component.RIGHT));
		dim.setHeight(parent.getHeight() + parent.getStyle().getPadding(false, Component.TOP) + parent.getStyle().getPadding(false, Component.BOTTOM));
		int compCount = parent.getComponentCount();
		for (int iter = 0; iter < compCount; iter++) {
			Dimension d = getPreferredSizeWithMargin(parent.getComponentAt(iter));
			dim.setWidth(Math.max(d.getWidth(), dim.getWidth()));
			dim.setHeight(Math.max(d.getHeight(), dim.getHeight()));
		}
		return dim;
	}

	private Dimension getPreferredSizeWithMargin(Component comp) {
		Dimension d = comp.getPreferredSize();
		Style s = comp.getStyle();
		return new Dimension(d.getWidth() + s.getMargin(Component.LEFT) + s.getMargin(Component.RIGHT), d.getHeight() + s.getMargin(Component.TOP) + s.getMargin(Component.BOTTOM));
	}
}