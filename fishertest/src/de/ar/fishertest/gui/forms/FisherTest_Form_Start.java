package de.ar.fishertest.gui.forms;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Map;

import com.codename1.ui.Button;
import com.codename1.ui.Container;
import com.codename1.ui.Font;
import com.codename1.ui.TextArea;
import com.codename1.ui.events.ActionEvent;
import com.codename1.ui.events.ActionListener;
import com.codename1.ui.layouts.BorderLayout;
import com.codename1.ui.layouts.BoxLayout;
import com.codename1.ui.layouts.Layout;

import de.ar.fishertest.FisherTest_DataDistributor;
import de.ar.fishertest.gui.widgets.Fishtest_Widget_Button_Answer;
import de.ar.fishertest.model.dataclasses.FisherTest_Model_Answer;
import de.ar.fishertest.model.dataclasses.FisherTest_Model_Question;
import de.ar.fishertest.model.enums.FisherTest_Model_Enum_Category;
import de.ar.tools.codenameone.gui.GUI_Tools;
import de.ar.tools.codenameone.gui.StyleTool;

public class FisherTest_Form_Start extends Abstract_FisherTest_Form {

	private static final String BTN_CHECK_TEXT_CHECK = "Check";
	private static final String BTN_CHECK_TEXT_NEXT = "Next";

	private TextArea taQuestion;
	private Fishtest_Widget_Button_Answer btnAnswerA;
	private Fishtest_Widget_Button_Answer btnAnswerB;
	private Fishtest_Widget_Button_Answer btnAnswerC;
	private Button btnCheck;

	private List<FisherTest_Model_Question> listQuestions;
	private FisherTest_Model_Question actualQuestion;

	public FisherTest_Form_Start() {
		super(null, "Start");
	}

	@Override
	public void init() {
		resetListAll(true);

		Container contQuestionAnswer = GUI_Tools.makeTransparentContainer(this, new BorderLayout(), BorderLayout.CENTER);

		taQuestion = GUI_Tools.makeTransparentTextArea(contQuestionAnswer, BorderLayout.NORTH, "", "");
		taQuestion.setEditable(false);

		Container contAnswers = GUI_Tools.makeTransparentContainer(contQuestionAnswer, new BoxLayout(BoxLayout.Y_AXIS), BorderLayout.CENTER);

		ActionListener btnAnswers_ActionListener_ForEnable = generateActionListenerAnswers();

		btnAnswerA = new Fishtest_Widget_Button_Answer("");
		btnAnswerA.addActionListener(btnAnswers_ActionListener_ForEnable);
		contAnswers.addComponent(btnAnswerA);

		btnAnswerB = new Fishtest_Widget_Button_Answer("");
		btnAnswerB.addActionListener(btnAnswers_ActionListener_ForEnable);
		contAnswers.addComponent(btnAnswerB);

		btnAnswerC = new Fishtest_Widget_Button_Answer("");
		btnAnswerC.addActionListener(btnAnswers_ActionListener_ForEnable);
		contAnswers.addComponent(btnAnswerC);

		btnCheck = GUI_Tools.makeTransparentButton(contAnswers, BTN_CHECK_TEXT_CHECK);
		btnCheck.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent evt) {
				btnCheck_ActionPerformed(evt);
			}
		});
		StyleTool.setFont(btnCheck, Font.createSystemFont(Font.FACE_SYSTEM, Font.STYLE_BOLD, Font.SIZE_LARGE), true);
		StyleTool.setFgColor(btnCheck, 0, 0, 0);

		nextQuestion();

		revalidate();
	}

	private void btnCheck_ActionPerformed(ActionEvent evt) {

		if (btnCheck.getText().equals(BTN_CHECK_TEXT_CHECK)) {

			List<FisherTest_Model_Answer> listAnswers = actualQuestion.getListAnswers();
			FisherTest_Model_Answer answerA = listAnswers.get(0);
			FisherTest_Model_Answer answerB = listAnswers.get(1);
			FisherTest_Model_Answer answerC = listAnswers.get(2);

			boolean btnASelection = btnAnswerA.isSelected();
			boolean btnBSelection = btnAnswerB.isSelected();
			boolean btnCSelection = btnAnswerC.isSelected();

			boolean answerACorrect = answerA.isCorrect();
			boolean answerBCorrect = answerB.isCorrect();
			boolean answerCCorrect = answerC.isCorrect();

			boolean isAOK = btnASelection == answerACorrect;
			boolean isBOK = btnBSelection == answerBCorrect;
			boolean isCOK = btnCSelection == answerCCorrect;

			boolean isCorrect = isAOK && isBOK && isCOK;

			setBtnColor_Bla(btnAnswerA, answerACorrect, isAOK, isCorrect);
			setBtnColor_Bla(btnAnswerB, answerBCorrect, isBOK, isCorrect);
			setBtnColor_Bla(btnAnswerC, answerCCorrect, isCOK, isCorrect);

			btnCheck.setText(BTN_CHECK_TEXT_NEXT);
		} else if (btnCheck.getText().equals(BTN_CHECK_TEXT_NEXT)) {
			listQuestions.remove(actualQuestion);
			nextQuestion();
		} else {
			throw new RuntimeException("Button State not supported: " + btnCheck.getText());
		}
		revalidate();
	}

	private void setBtnColor_Bla(Fishtest_Widget_Button_Answer btn, boolean answerCorrect, boolean isBtnOK, boolean isQuestionOK) {

		if (isQuestionOK) {
			if (answerCorrect) {
				btn.setBgColor(0, 255, 0);
			}
		} else {
			if (!isBtnOK) {
				if (answerCorrect) {
					btn.setBgColor(255, 0, 0);
				} else {
					btn.setBgColor(255, 255, 255);
				}
			} else if (answerCorrect) {
				btn.setBgColor(255, 0, 0);
			}
		}
	}

	private void nextQuestion() {
		resetButtons();

		if (listQuestions.size() != 0) {
			actualQuestion = listQuestions.get(0);

			taQuestion.setText(actualQuestion.getQuestionTest());
			btnAnswerA.setText("a) " + actualQuestion.getListAnswers().get(0).getAnswerText());
			btnAnswerB.setText("b) " + actualQuestion.getListAnswers().get(1).getAnswerText());
			btnAnswerC.setText("c) " + actualQuestion.getListAnswers().get(2).getAnswerText());

			btnCheck.setText(BTN_CHECK_TEXT_CHECK);
		} else {
			throw new RuntimeException("TODO: Auswertung!!!");
		}
	}

	private void resetButtons() {
		btnAnswerA.reset();
		btnAnswerB.reset();
		btnAnswerC.reset();
		setBtnChekSelectionStyles();

		revalidate();
	}

	private void setBtnChekSelectionStyles() {

		boolean selection = btnAnswerA.isSelected() || btnAnswerB.isSelected() || btnAnswerC.isSelected();

		if (selection) {
			btnCheck.setEnabled(true);
			StyleTool.setOpactity(btnCheck, 255, true);
		} else {
			btnCheck.setEnabled(false);
			StyleTool.setOpactity(btnCheck, 100, true);
		}
	}

	private void resetListAll(boolean shuffle) {
		listQuestions = new ArrayList<FisherTest_Model_Question>();

		Map<FisherTest_Model_Enum_Category, List<FisherTest_Model_Question>> mapQuestions = FisherTest_DataDistributor.getInstance().getMapCategoryQuestions();
		for (FisherTest_Model_Enum_Category category : FisherTest_Model_Enum_Category.values()) {
			listQuestions.addAll(mapQuestions.get(category));
		}

		if (shuffle) {
			Collections.shuffle(listQuestions);
		}
	}

	private ActionListener generateActionListenerAnswers() {
		ActionListener btnAnswers_ActionListener_ForEnable = new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent evt) {
				setBtnChekSelectionStyles();
			}
		};
		return btnAnswers_ActionListener_ForEnable;
	}

	@Override
	protected Layout getFormLayout() {
		return new BorderLayout();
	}
}