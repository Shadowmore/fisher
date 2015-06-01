package de.ar.fishertest.gui.forms;

import com.codename1.io.Log;
import com.codename1.ui.Command;
import com.codename1.ui.Form;
import com.codename1.ui.events.ActionEvent;
import com.codename1.ui.layouts.Layout;

public abstract class Abstract_FisherTest_Form extends Form {

	private Form formParent;
	private Command backCommand;

	public Abstract_FisherTest_Form(final Form formParent, String formName) {
		super(formName);
		this.formParent = formParent;
		setLayout(getFormLayout());

		if (formParent != null) {

			backCommand = new Command("Back") {
				@Override
				public void actionPerformed(ActionEvent evt) {
					closeForm();
				}
			};
			setBackCommand(backCommand);
		}
	}

	public void openForm() {
		show();
	}

	public void closeForm() {
		if (formParent == null) {
			Log.p("Exit");
			System.exit(0);
		} else {
			formParent.showBack();
		}
	}

	public Form getFormParent() {
		return formParent;
	}

	public abstract void init();

	protected abstract Layout getFormLayout();

}