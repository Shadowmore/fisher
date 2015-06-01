package de.ar.tools.codenameone.gui.widgets.tabs;

import com.codename1.ui.Component;
import com.codename1.ui.Container;
import com.codename1.ui.events.ActionEvent;
import com.codename1.ui.events.ActionListener;
import com.codename1.ui.plaf.Style;

public class Tab_SwipeListener implements ActionListener {

	public final static int PRESS = 0;
	public final static int DRAG = 1;
	public final static int RELEASE = 2;

	private final int type;
	private boolean blockSwipe;
	private boolean riskySwipe;

	private Style originalTabsContainerUnselected, originalTabsContainerSelected;

	private Tabs tabs;

	public Tab_SwipeListener(Tabs tabs, int type) {
		this.type = type;
		this.tabs = tabs;
	}

	public void actionPerformed(ActionEvent evt) {

		if (tabs.animate()) {
			return;
		}

		Container contTabs = tabs.getContTabs();

		final int x = evt.getX();
		final int y = evt.getY();
		switch (type) {
			case PRESS: {
				blockSwipe = false;
				riskySwipe = false;
				if (contTabs.contains(x, y)) {
					Component testCmp = contTabs.getComponentAt(x, y);
					if (testCmp != null && testCmp != contTabs) {
						while (testCmp != null && testCmp != contTabs) {
							boolean shouldBlockSideSwipe = false; // testCmp.shouldBlockSideSwipe()
							if (shouldBlockSideSwipe) {
								tabs.setLastX(-1);
								tabs.setInitialX(-1);
								blockSwipe = true;
								return;
							}
							if (testCmp.isScrollableX() || testCmp.isScrollableY()) {
								if (testCmp.isScrollableX()) {
									// we need to block swipe since the user is trying to scroll a component
									tabs.setLastX(-1);
									tabs.setInitialX(-1);
									blockSwipe = true;
									return;
								}

								// scrollable Y component, we want to make side scrolling
								// slightly harder so it doesn't bother the vertical swipe
								riskySwipe = true;
								break;
							}
							testCmp = testCmp.getParent();
						}
					}
					tabs.setLastX(x);
					tabs.setInitialX(x);
					tabs.setInitialY(y);
				} else {
					tabs.setLastX(-1);
					tabs.setInitialX(-1);
					tabs.setInitialY(-1);
				}
				tabs.setDragStarted(false);
				break;
			}
			case DRAG: {
				if (blockSwipe) {
					return;
				}
				if (!tabs.isDragStarted()) {
					boolean isEagerSwipeMode = false; // isEagerSwipeMode()
					if (isEagerSwipeMode) {
						tabs.setDragStarted(true);
					} else {
						if (riskySwipe) {
							if (Math.abs(x - tabs.getInitialX()) < Math.abs(y - tabs.getInitialY())) {
								return;
							}
							// give heavier weight when we have two axis swipe
							tabs.setDragStarted(Math.abs(x - tabs.getInitialX()) > (contTabs.getWidth() / 5));
						} else {
							// start drag not imediately, giving components some sort
							// of weight.
							tabs.setDragStarted(Math.abs(x - tabs.getInitialX()) > (contTabs.getWidth() / 8));
						}
					}
				}
				if (tabs.getInitialX() != -1 && contTabs.contains(x, y)) {
					int diffX = x - tabs.getLastX();
					if (diffX != 0 && tabs.isDragStarted()) {
						tabs.setLastX(tabs.getLastX() + diffX);
						final int size = contTabs.getComponentCount();
						for (int i = 0; i < size; i++) {
							Component component = contTabs.getComponentAt(i);
							component.setX(component.getX() + diffX);
							component.paintLock(false);
						}
						tabs.repaint();
					}
				}
				break;
			}
			case RELEASE: {
				boolean changeTabContainerStyleOnFocus = true;
				if (changeTabContainerStyleOnFocus) {
					initTabsContainerStyle();
					contTabs.setUnselectedStyle(originalTabsContainerUnselected);
					contTabs.repaint();
				}
				if (blockSwipe) {
					return;
				}
				if (tabs.getInitialX() != -1) {
					//					int diff = x - tabs.getInitialX();
					int diff = tabs.getInitialX() - tabs.getLastX();
					if (diff != 0 && tabs.isDragStarted()) {
						int active = tabs.getSelectionIndex();
						if (Math.abs(diff) > contTabs.getWidth() / 6) {
							if (tabs.isRTL()) {
								diff *= -1;
							}

							if (diff < 0) {
								active--;
								if (active < 0) {
									active = 0;
								}
							} else {
								active++;
								if (active >= contTabs.getComponentCount()) {
									active = contTabs.getComponentCount() - 1;
								}
							}
						}
						int selectionIndex = tabs.getSelectionIndex();
						int index = active;
						if (index == selectionIndex) {
							return;
						}
						// INFO: SlideMotion fails
						//						Form form = tabs.getComponentForm();
						//						if (form != null) {
						//
						//							Component actComp = contTabs.getComponentAt(index);
						//							int width = actComp.getWidth();
						//
						//							int start = contTabs.getComponentAt(active).getAbsoluteX();
						//							int end = 0;
						//
						//							if (index < selectionIndex) {
						//								end = width * index;
						//							} else {
						//								end = width * selectionIndex;
						//							}
						//							tabs.setSlideToDestMotion(tabs.createTabSlideMotion(start, end));
						//							tabs.getSlideToDestMotion().start();
						//
						//							form.registerAnimated(tabs);
						//
						//						} else {
						//							// int oldSelection = selectionIndex;
						//							// selectionListeners.fireSelectionEvent(oldSelection, selectionIndex);
						//							// selectionIndex = index;
						//							// TabButton b = (TabButton) contTabs.getComponentAt(index);
						//							// b.fireClicked();
						//							// b.requestFocus();
						//						}

						//						tabs.updateSelectionIndex(index);
						//						tabs.updateButtons();
						tabs.setSelectedIndex(index);
						evt.consume();
					}
				}
			}
				tabs.setLastX(-1);
				tabs.setInitialX(-1);
				tabs.setDragStarted(false);
				break;
		}
	}

	private void initTabsContainerStyle() {
		if (originalTabsContainerSelected == null) {
			Container contTabs = tabs.getContTabs();
			originalTabsContainerSelected = contTabs.getSelectedStyle();
			originalTabsContainerUnselected = contTabs.getUnselectedStyle();
		}
	}
}