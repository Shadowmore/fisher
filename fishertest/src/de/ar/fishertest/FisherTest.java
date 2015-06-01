package de.ar.fishertest;

import java.io.IOException;

import com.codename1.ui.Display;
import com.codename1.ui.Form;
import com.codename1.ui.plaf.UIManager;
import com.codename1.ui.util.Resources;

import de.ar.fishertest.gui.forms.FisherTest_Form_Start;
import de.ar.fishertest.tools.FisherTest_QuestionAnswer_Reader;

public class FisherTest {

	private Form current;

	public void init(Object context) {
		try {
			Resources theme = Resources.openLayered("/theme");
			UIManager.getInstance().setThemeProps(theme.getTheme(theme.getThemeResourceNames()[0]));
		} catch (IOException e) {
			e.printStackTrace();
		}
		// Pro users - uncomment this code to get crash reports sent to you automatically
		/*Display.getInstance().addEdtErrorHandler(new ActionListener() {
		    public void actionPerformed(ActionEvent evt) {
		        evt.consume();
		        Log.p("Exception in AppName version " + Display.getInstance().getProperty("AppVersion", "Unknown"));
		        Log.p("OS " + Display.getInstance().getPlatformName());
		        Log.p("Error " + evt.getSource());
		        Log.p("Current Form " + Display.getInstance().getCurrent().getName());
		        Log.e((Throwable)evt.getSource());
		        Log.sendLog();
		    }
		});*/
	}

	public void start() {
		if (current != null) {
			current.show();
			return;
		}

		FisherTest_QuestionAnswer_Reader.readFromXml("data.xml", FisherTest_DataDistributor.getInstance().getMapCategoryQuestions());

		FisherTest_Form_Start formStart = new FisherTest_Form_Start();
		formStart.init();
		formStart.show();
	}

	public void stop() {
		current = Display.getInstance().getCurrent();
	}

	public void destroy() {
	}

}
