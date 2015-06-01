package de.ar.tools.codenameone.gui.widgets.tabs;

import java.util.ArrayList;
import java.util.List;

import com.codename1.ui.Button;
import com.codename1.ui.Component;
import com.codename1.ui.Container;
import com.codename1.ui.Form;
import com.codename1.ui.animations.Motion;
import com.codename1.ui.events.ActionEvent;
import com.codename1.ui.events.ActionListener;
import com.codename1.ui.events.SelectionListener;
import com.codename1.ui.layouts.BorderLayout;
import com.codename1.ui.layouts.GridLayout;
import com.codename1.ui.util.EventDispatcher;

import de.ar.tools.codenameone.gui.GUI_Tools;
import de.ar.tools.codenameone.gui.StyleTool;

public class Tabs extends Container {

	private int selectionIndex;
	private List<AbstractTabContainer> listTabContainers;
	private List<TabButton> listButtons;
	private Container contTabs;
	private Container contButtons;

	private EventDispatcher selectionListeners;
	private Motion slideToDestMotion;

	private Tab_SwipeListener press, drag, release;
	private int initialX = -1;
	private int initialY = -1;
	private int lastX = -1;
	private boolean dragStarted = false;

	public Tabs() {
		super(new BorderLayout());
		listTabContainers = new ArrayList<AbstractTabContainer>();
		listButtons = new ArrayList<TabButton>();

		StyleTool.setMargin(this, 0, 0, 0, 0);
		StyleTool.setPadding(this, 0, 0, 0, 0);

		press = new Tab_SwipeListener(this, Tab_SwipeListener.PRESS);
		drag = new Tab_SwipeListener(this, Tab_SwipeListener.DRAG);
		release = new Tab_SwipeListener(this, Tab_SwipeListener.RELEASE);

		contTabs = GUI_Tools.makeTransparentContainer(this, new TabsLayout(), BorderLayout.CENTER);
		contButtons = GUI_Tools.makeTransparentContainer(this, new GridLayout(1, 1), BorderLayout.SOUTH);

		selectionListeners = new EventDispatcher();
	}

	public void setSelectedIndex(int index) {
		if (index < 0 || index >= contTabs.getComponentCount()) {
			throw new IndexOutOfBoundsException("Index: " + index + ", Tab count: " + contTabs.getComponentCount());
		}
		if (index == selectionIndex) {
			return;
		}

		int oldSelection = selectionIndex;
		Form form = getComponentForm();
		if (form != null) {
			//INFO: SlideMotion fails
			//			createSlideMotion(index);
			selectionIndex = index;
		} else {
			selectionIndex = index;
			TabButton b = (TabButton) contTabs.getComponentAt(index);
			b.fireClicked();
			b.requestFocus();
		}
		selectionListeners.fireSelectionEvent(oldSelection, selectionIndex);
		updateButtons();
	}

	public void createSlideMotion(int index) {
		Form form = getComponentForm();
		if (form != null) {
			Component actComp = contTabs.getComponentAt(index);
			int width = actComp.getWidth();

			int start = 0;
			int end = 0;

			if (index < selectionIndex) {
				start = -(width * selectionIndex);
				end = width * index;
			} else {
				start = width * selectionIndex;
				end = width * index;
			}

			slideToDestMotion = createTabSlideMotion(start, end);
			slideToDestMotion.start();

			form.registerAnimated(this);
		}
	}

	@Override
	public boolean animate() {
		boolean b = super.animate();

		if (slideToDestMotion != null) {
			int motionX = slideToDestMotion.getValue();
			final int size = contTabs.getComponentCount();
			int tabWidth = contTabs.getWidth();
			for (int i = 0; i < size; i++) {
				int xOffset;
				if (isRTL()) {
					xOffset = (size - i) * tabWidth;
					xOffset -= ((size - selectionIndex) * tabWidth);
				} else {
					xOffset = i * tabWidth;
					xOffset -= (selectionIndex * tabWidth);
				}
				xOffset += motionX;
				Component component = contTabs.getComponentAt(i);
				component.setX(xOffset);
			}
			if (slideToDestMotion.isFinished()) {
				for (int i = 0; i < contTabs.getComponentCount(); i++) {
					Component component = contTabs.getComponentAt(i);
					component.paintLockRelease();
				}
				slideToDestMotion = null;
				deregisterAnimatedInternal1();
				setSelectedIndex(selectionIndex);
			}
			return true;
		}
		return b;
	}

	@Override
	protected void initComponent() {
		super.initComponent();
		Form form = this.getComponentForm();
		if (form != null) {
			form.addPointerPressedListener(press);
			form.addPointerReleasedListener(release);
			form.addPointerDraggedListener(drag);
		}
	}

	@Override
	protected void deinitialize() {
		Form form = this.getComponentForm();
		if (form != null) {
			form.removePointerPressedListener(press);
			form.removePointerReleasedListener(release);
			form.removePointerDraggedListener(drag);
		}
		super.deinitialize();
	}

	private void deregisterAnimatedInternal1() {
		if (slideToDestMotion == null || (slideToDestMotion.isFinished())) {
			Form f = getComponentForm();
			if (f != null) {
				f.deregisterAnimated(this);
			}
		}
	}

	public void addTab(String tabname, AbstractTabContainer tab) {
		listTabContainers.add(tab);
		contTabs.addComponent(tab);

		final TabButton btnTab = new TabButton(tabname, listButtons.size());
		btnTab.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent evt) {
				btnTab_ActionPerformed(btnTab);
			}
		});
		listButtons.add(btnTab);
		contButtons.setLayout(new GridLayout(1, listButtons.size()));
		contButtons.addComponent(btnTab);

		if (listTabContainers.size() > 0 && selectionIndex == -1) {
			selectionIndex = 0;
		}
		updateButtons(listButtons.get(0));
	}

	private void btnTab_ActionPerformed(TabButton btnTab) {
		//				Dialog.show("!!!", "Not implemented", "OK", null);

		setSelectedIndex(btnTab.getIndex());
		//		selectionIndex = btnTab.getIndex();

		updateButtons(btnTab);
	}

	public void updateButtons() {
		updateButtons(listButtons.get(selectionIndex));
	}

	private void updateButtons(TabButton btnTabActive) {
		if (selectionIndex != -1) {
			for (TabButton btn : listButtons) {
				StyleTool.setOpactity(btn, 50, false);
			}
			StyleTool.setOpactity(btnTabActive, 255, false);
		}
		revalidate();
	}

	public void removeTab(AbstractTabContainer tab) {
		int index = listTabContainers.indexOf(tab);
		removeTabAt(index);
	}

	public void removeTabAt(int index) {
		AbstractTabContainer tabRemoved = listTabContainers.remove(index);
		contTabs.removeComponent(tabRemoved);

		TabButton btnRemoved = listButtons.remove(index);
		contButtons.removeComponent(btnRemoved);

		if (listTabContainers.size() == 0 && selectionIndex != -1) {
			selectionIndex = -1;
		}
	}

	public Motion createTabSlideMotion(int start, int end) {
		return Motion.createSplineMotion(start, end, 2000);
	}

	public int getSelectionIndex() {
		return selectionIndex;
	}

	public void updateSelectionIndex(int index) {
		selectionIndex = index;
	}

	public void addSelectionListener(SelectionListener selectionListener) {
		selectionListeners.addListener(selectionListener);
	}

	public void removeSelectionListener(SelectionListener selectionListener) {
		selectionListeners.removeListener(selectionListener);
	}

	public AbstractTabContainer getTabContainer(int index) {
		return listTabContainers.get(index);
	}

	public Button getTabButton(int index) {
		return listButtons.get(index);
	}

	public List<TabButton> getListButtons() {
		return listButtons;
	}

	public int getTabCount() {
		return listTabContainers.size();
	}

	public Container getContTabs() {
		return contTabs;
	}

	public Container getContButtons() {
		return contButtons;
	}

	public Motion getSlideToDestMotion() {
		return slideToDestMotion;
	}

	public void setSlideToDestMotion(Motion slideToDestMotion) {
		this.slideToDestMotion = slideToDestMotion;
	}

	public int getInitialX() {
		return initialX;
	}

	public void setInitialX(int initialX) {
		this.initialX = initialX;
	}

	public int getInitialY() {
		return initialY;
	}

	public void setInitialY(int initialY) {
		this.initialY = initialY;
	}

	public int getLastX() {
		return lastX;
	}

	public void setLastX(int lastX) {
		this.lastX = lastX;
	}

	public boolean isDragStarted() {
		return dragStarted;
	}

	public void setDragStarted(boolean dragStarted) {
		this.dragStarted = dragStarted;
	}
}