package src.enamel;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.ArrayList;
import java.util.HashMap;
import javafx.application.Application;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.geometry.Insets;
import javafx.scene.AccessibleRole;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.Menu;
import javafx.scene.control.MenuBar;
import javafx.scene.control.MenuItem;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.control.ToggleGroup;
import javafx.scene.effect.DropShadow;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyCodeCombination;
import javafx.scene.input.KeyCombination;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.layout.CornerRadii;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Region;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import java.util.logging.*;

public class ScenarioCreator extends Application {

	Printer printer;
	ArrayList<Block> blockList = new ArrayList<>();
	HashMap<String, Block> blockMap = new HashMap<String, Block>();
	GridPane layout, layout1, layout2, layout3, layout4, layout5, layout6, layout7, layout8, layout9, layout10,
			layout11, layout12, layout13, layout14, layout15, layout16, layout17, layout18, layout19;
	Scene scene, scene1, scene2, scene3, scene4, scene5, scene6, scene7, scene8, scene9, scene10, scene11, scene12,
			scene13, scene14, scene15, scene16, scene17, scene18, scene19;
	Button createButton, testButton, sound, saveButton, scenarioMenuButton, clearSectionButton, errorMessageButton,
			okayStart, warningOkay, warningCancel, soundRecord, soundImport, soundExit, answerOkay, brailleOkay,
			emptyNameButton, buttonsUsedWindowOkay, emptyStoryOkay, noSectionSavedOkay, saveOkayButton,
			scenarioSavedOkay, clearSectionButtonOkay, clearSectionButtonCancel, soundErrorButton, soundNameOkay,
			soundNameCancel, nameSoundErrorButton, soundImportedOkay;
	Stage scenarioCreator, errorWindow, brailleCellsUsedWindow, soundWindow, notANumberWindow, brailleWindow,
			emptyNameWindow, buttonsUsedWindow, emptyStoryWindow, noSectionsSavedWindow, saveWindow,
			scenarioSavedWindow, warningWindow, playerSelectionWindow, scenarioMenuWindow, soundErrorWindow,
			clearSectionWarning, nameSoundFileWindow, nameSoundErrorWindow, soundImportedWindow;
	Text startWindowText, sectionName, answerButtonsUsedText, correct, story, braille, answer, incorrect,
			scenarioNameText, nameBrailleAnswer, brailleCellsText, answerButtonsText, blank1, errorMessage, warningText,
			soundMessage, answerIsNumber, brailleEntry, emptyName, buttonsUsedError, emptyStoryText, noSectionsSaved,
			saveConfirmed, playerSelectionText, projectSavedConfirmed, clearSectionText, soundErrorText, soundNameText,
			soundNameErrorText, soundTimeText, soundImportedText;
	Label nameSectionLabel, answerButtonsUsedFieldLabel, storyLabel, brailleLabel, answerLabel, correctLabel,
			playerLabel, incorrectLabel, scenarioNameFieldLabel, brailleCellsUsedLabel, answerButtonsUsedLabel;
	Menu scenarioMenu, sectionMenu, goToMenu, soundMenu;
	MenuItem newProject, loadProject, saveProject, testProject, saveSection, clearSection, goToSectionName,
			goToAnswerButtonsUsed, goToStory, goToBraille, goToAnswer, goToCorrect, goToIncorrect, addSound;
	MenuBar menuBar;
	DropShadow borderGlow;
	TextField nameSectionField, answerButtonsUsedField, brailleText, answerText, scenarioNameField, brailleCellsField,
			answerButtonsField, soundNameField, soundTimeField;
	TextArea storyText, correctText, incorrectText;
	ObservableList<String> comboBoxList;
	ComboBox<String> comboBox;
	RadioButton audioButton, visualButton;
	private Stage recordWindow;
	private Scene recordScene;
	private Button record;
	private GridPane recordLayout;
	private Button exitButton;
	private boolean recording;
	Integer recordingTime;

	/*
	 * GUI for start Window / primary stage
	 *
	 *
	 * Adding components to GUI (comp, col, row, col span, row span)
	 *
	 *
	 */
	private void createPrimaryStage() {
		layout1 = new GridPane();
		layout1.setHgap(10);
		layout1.setVgap(10);
		layout1.setPadding(new Insets(5, 15, 10, 15));
		scene1 = new Scene(layout1, 550, 200);

		startWindowText = new Text("                       Welcome to Scenario Creator");
		startWindowText.setFont(Font.font("Arial", FontWeight.BOLD, 20));
		startWindowText.setFill(Color.WHITE);
		layout1.add(startWindowText, 0, 2, 3, 1);
		createButton = new Button("Create New Scenario");
		createButton.setMinSize(150, 60);
		createButton.setStyle("-fx-base: #87ceeb;"); // sky blue
		createButton.setAccessibleRoleDescription("Create new scenario button");
		createButton.setAccessibleText("Welcome to scenario creator, sound .wav to create a new scenario press enter");
		layout1.add(createButton, 0, 6);
		testButton = new Button("Test Scenario");
		testButton.setMinSize(150, 60);
		testButton.setStyle("-fx-base: #87ceeb;"); // sky blue
		testButton.setAccessibleRoleDescription("Test Scenario button");
		testButton.setAccessibleText("To test a scenario press enter");
		layout1.add(testButton, 3, 6);

		// GUI for scenario Creator
		scenarioCreator = new Stage();
		layout = new GridPane();
		layout.setHgap(10);
		layout.setVgap(5);
		layout.setPadding(new Insets(0, 5, 5, 5));

		scene = new Scene(layout, 1000, 655);
		scenarioCreator.setScene(scene);
		scenarioCreator.setTitle("Scenario Creator");
		scene.setFill(Color.TRANSPARENT);
		layout.setBackground(
				new Background(new BackgroundFill(Color.gray(0.05, 0.8), CornerRadii.EMPTY, Insets.EMPTY)));
	}

	/*
	 * _________ Scenario menu
	 * 
	 * 
	 * create new scenario load scenario save scenario
	 * 
	 * 
	 */
	private void createScenarioMenu() {
		scenarioMenu = new Menu("Scenario");
		newProject = new MenuItem("New Scenario");
		loadProject = new MenuItem("Load Scenario");
		saveProject = new MenuItem("Save Scenario");
		testProject = new MenuItem("Test Scenario");

		scenarioMenu.getItems().add(newProject);
		scenarioMenu.getItems().add(loadProject);
		scenarioMenu.getItems().add(saveProject);
		scenarioMenu.getItems().add(testProject);
	}

	/*
	 * Section menu
	 * 
	 * 
	 * save section clear section
	 * 
	 * 
	 */
	private void createSectionMenu() {
		sectionMenu = new Menu("Section");
		saveSection = new MenuItem("Save Section");
		clearSection = new MenuItem("Clear Section");
		sectionMenu.getItems().add(saveSection);
		sectionMenu.getItems().add(clearSection);
	}

	/**
	 * Go to
	 * 
	 * 
	 * Goes to text field or area
	 * 
	 * 
	 */
	private void createGotoMenu() {

		goToMenu = new Menu("Go To");

		goToSectionName = new MenuItem("Section Name");
		goToAnswerButtonsUsed = new MenuItem("Answer Buttons Used");
		goToStory = new MenuItem("Story");
		goToBraille = new MenuItem("Braille");
		goToAnswer = new MenuItem("Answer");
		goToCorrect = new MenuItem("Correct");
		goToIncorrect = new MenuItem("Incorrect");

		goToMenu.getItems().add(goToSectionName);
		goToMenu.getItems().add(goToAnswerButtonsUsed);
		goToMenu.getItems().add(goToStory);
		goToMenu.getItems().add(goToBraille);
		goToMenu.getItems().add(goToAnswer);
		goToMenu.getItems().add(goToCorrect);
		goToMenu.getItems().add(goToIncorrect);
	}

	private void storyTextSetup() {
		// Story text area
		story = new Text(" Story");
		story.setFont(Font.font("Arial", FontWeight.BOLD, 13));
		story.setFill(Color.WHITE);
		layout.add(story, 0, 2, 6, 1);

		storyText = new TextArea();
		storyText.setAccessibleRole(AccessibleRole.TOOLTIP);
		storyText.setAccessibleRoleDescription("story text area, press control tab to skip to next field");
		storyText.setPrefHeight(250);
		storyText.setPrefWidth(500);
		storyText.setOpacity(0.9);
		storyText.setWrapText(true);
		storyText.setEffect(borderGlow);
		layout.add(storyText, 0, 3, 8, 4);
		storyLabel = new Label("Enter your story here");
		storyLabel.setLabelFor(storyText);
		storyLabel.setVisible(false);
		layout.add(storyLabel, 0, 3, 8, 4);
	}

	private void blank() {
		// blank text field for spacing
		blank1 = new Text("                                         ");
		blank1.setFont(Font.font("Arial", FontWeight.SEMI_BOLD, 10));
		layout.add(blank1, 6, 7);
	}

	private void saveSectionSetup() {

		// save section button
		saveButton = new Button("Save Section");
		saveButton.setAccessibleRoleDescription("Save button");
		saveButton.setAccessibleText("Press enter to save section");
		saveButton.setStyle("-fx-base: #87ceeb;"); // sky blue
		layout.add(saveButton, 0, 19);
	}

	private void correctIncorrectSetup() {
		// Correct text area
		correct = new Text(" Correct");
		correct.setFont(Font.font("Arial", FontWeight.BOLD, 13));
		correct.setFill(Color.WHITE);
		layout.add(correct, 0, 8);

		correctText = new TextArea();
		correctText.setAccessibleRole(AccessibleRole.TOOLTIP);
		correctText
				.setAccessibleRoleDescription("Correct response text field, press control tab to skip to next field");
		correctText.setPrefHeight(100);
		correctText.setPrefWidth(500);
		correctText.setOpacity(0.95);
		correctText.setWrapText(true);
		correctText.setEffect(borderGlow);
		layout.add(correctText, 0, 9, 8, 4);

		// accessibility for correct text area
		correctLabel = new Label("Enter what happens \n when the right \n answer is triggered");
		correctLabel.setLabelFor(correctText);
		correctLabel.setVisible(false);
		layout.add(correctLabel, 0, 9, 8, 4);

		// Incorrect text area
		incorrect = new Text(" Incorrect");
		incorrect.setFont(Font.font("Arial", FontWeight.BOLD, 13));
		incorrect.setFill(Color.WHITE);
		layout.add(incorrect, 0, 14);

		incorrectText = new TextArea();
		incorrectText.setAccessibleRole(AccessibleRole.TOOLTIP);
		incorrectText
				.setAccessibleRoleDescription("incorrect response text field, press control tab to skip to next field");
		incorrectText.setPrefHeight(100);
		incorrectText.setPrefWidth(500);
		incorrectText.setOpacity(0.95);
		incorrectText.setWrapText(true);
		incorrectText.setEffect(borderGlow);
		layout.add(incorrectText, 0, 15, 8, 3);

		// accessibility for incorrect text area
		incorrectLabel = new Label("Enter what happens \n when the wrong\n answer is triggered");
		incorrectLabel.setLabelFor(incorrectText);
		incorrectLabel.setVisible(false);
		layout.add(incorrectLabel, 0, 8, 8, 4);
	}

	private void clearSectionSetup() {
		// Clear Section button
		clearSectionButton = new Button("Clear Section");
		clearSectionButton.setAccessibleRoleDescription("Clear button");
		clearSectionButton.setAccessibleText("Press enter to clear all fields");
		clearSectionButton.setStyle("-fx-base: #87ceeb;"); // sky blue
		layout.add(clearSectionButton, 7, 19);
	}

	private void soundButtonSetup() {
		// sound button
		sound = new Button("  Add Sound   ");
		sound.setAccessibleRoleDescription("Sound button");
		sound.setAccessibleText("Sound option is currently not available in this version");
		sound.setStyle("-fx-base: #87ceeb;"); // sky blue
		layout.add(sound, 7, 7);

		// sound button events
		sound.setOnMouseClicked(e -> {
			soundWindow.show();
		});

		sound.setOnKeyPressed(e -> {
			if (e.getCode() == KeyCode.ENTER) {
				soundWindow.show();
			}
		});

		sound.setOnKeyPressed(e -> {
			new KeyCodeCombination(KeyCode.TAB, KeyCodeCombination.CONTROL_DOWN);
			correctText.requestFocus();
		});
	}

	private void answerSetup() {
		// answer text field
		answer = new Text("Answer");
		answer.setFont(Font.font("Arial", FontWeight.BOLD, 11.5));
		answer.setFill(Color.WHITE);
		layout.add(answer, 4, 7);

		answerText = new TextField();
		answerText.setAccessibleRole(AccessibleRole.TOOLTIP);
		answerText.setAccessibleRoleDescription(
				"answer button number field, please enter a number less than or equal to the number of answer buttons used");
		answerText.setPrefWidth(50);
		layout.add(answerText, 3, 7);

		// accessibility for answer field
		answerLabel = new Label("Enter the button\nnumber users should\npress for the\ncorrect response");
		answerLabel.setLabelFor(answerText);
		answerLabel.setVisible(false);
		layout.add(answerLabel, 3, 7);

	}

	private void brailleInputSetup() {
		// Braille text field
		braille = new Text("Braille");
		braille.setFont(Font.font("Arial", FontWeight.BOLD, 11.5));
		braille.setFill(Color.GHOSTWHITE);
		layout.add(braille, 2, 7);

		brailleText = new TextField();
		brailleText.setAccessibleRole(AccessibleRole.TOOLTIP);
		brailleText.setAccessibleRoleDescription(
				"braille text field, the word must be shorter or equal to the number of braille cells available");
		brailleText.setPrefWidth(40);
		layout.add(brailleText, 0, 7, 2, 1);

		// accessibility for braille text field
		brailleLabel = new Label("Enter the word \nyou want displayed \n on the braille cell");
		brailleLabel.setLabelFor(brailleText);
		brailleLabel.setVisible(false);
		layout.add(brailleLabel, 2, 7);
	}

	private void sectionNameAndButtonsUsed() {
		// Block title
		nameSectionField = new TextField();
		nameSectionField.setAccessibleRole(AccessibleRole.TOOLTIP);
		nameSectionField.setAccessibleRoleDescription("Section name text field");
		nameSectionField.setPrefWidth(30);
		layout.add(nameSectionField, 0, 1);

		sectionName = new Text("Section Name");
		sectionName.setFont(Font.font("Arial", FontWeight.BOLD, 11.5));
		sectionName.setFill(Color.GHOSTWHITE);
		layout.add(sectionName, 1, 1, 2, 1);

		// accessibility for answer text field
		nameSectionLabel = new Label("Please Enter the name\n for this section \n of your story");
		nameSectionLabel.setLabelFor(nameSectionField);
		nameSectionLabel.setVisible(false);
		layout.add(nameSectionLabel, 0, 1);

		// number of answer buttons used
		answerButtonsUsedField = new TextField();
		answerButtonsUsedField.setAccessibleRole(AccessibleRole.TOOLTIP);
		answerButtonsUsedField.setAccessibleRoleDescription(
				"answer buttons used number field, please enter a number less than or equal to the number of answer buttons available");
		answerButtonsUsedField.setPrefWidth(30);
		layout.add(answerButtonsUsedField, 3, 1);
		answerButtonsUsedText = new Text("Answer Buttons Used");
		answerButtonsUsedText.setFont(Font.font("Arial", FontWeight.BOLD, 11.5));
		answerButtonsUsedText.setFill(Color.GHOSTWHITE);
		layout.add(answerButtonsUsedText, 4, 1);

		// accessibility for answer buttons used field
		answerButtonsUsedFieldLabel = new Label("Enter the number\n of answer buttons \n you want to use");
		answerButtonsUsedFieldLabel.setLabelFor(answerButtonsUsedField);
		answerButtonsUsedFieldLabel.setVisible(false);
		layout.add(answerButtonsUsedFieldLabel, 3, 1);
	}

	private void borderGlowSetup() {
		borderGlow = new DropShadow();
		int depth = 40;
		borderGlow.setColor(Color.LIGHTBLUE);
		borderGlow.setWidth(depth);
		borderGlow.setHeight(depth);
		borderGlow.setOffsetX(0f);
		borderGlow.setOffsetY(0f);
	}

	/**
	 * menu bar
	 * 
	 * Scenario Section Go to
	 * 
	 * 
	 */

	private void createMenuBar() {

		menuBar = new MenuBar();
		menuBar.getMenus().addAll(scenarioMenu, sectionMenu, goToMenu, soundMenu);
		menuBar.setOpacity(0.7);
		layout.add(menuBar, 0, 0, 8, 1);
	}

	/**
	 * Sound menu
	 * 
	 * 
	 */
	private void createSoundMenu() {

		soundMenu = new Menu("Sound");
		addSound = new MenuItem("Add Sound");
		soundMenu.getItems().add(addSound);
	}

	/**
	 * visual or audio
	 * 
	 * 
	 */

	private void visualOrAudioPlayerGUI() {

		playerSelectionWindow = new Stage();
		layout9 = new GridPane();
		layout9.setHgap(10);
		layout9.setVgap(10);
		layout9.setPadding(new Insets(0, 5, 5, 5));
		layout9.setBackground(
				new Background(new BackgroundFill(Color.gray(0.6, 0.8), CornerRadii.EMPTY, Insets.EMPTY)));

		scene9 = new Scene(layout9);
		playerSelectionWindow.setScene(scene9);
		playerSelectionWindow.setTitle("Player selection");
		playerSelectionText = new Text("       Would you like to test with\n     visual player or audio player?");
		layout9.add(playerSelectionText, 0, 1, 3, 1);

		playerLabel = new Label("Would you like to test with visual player or audio player?");
		playerLabel.setLabelFor(playerSelectionText);
		playerLabel.setVisible(false);
		layout9.add(playerLabel, 0, 1);

		final ToggleGroup group = new ToggleGroup();
		visualButton = new RadioButton("Visual Player");
		visualButton.setToggleGroup(group);
		visualButton.setAccessibleText("Press enter to select visual player");
		audioButton = new RadioButton("Audio Player");
		audioButton.setToggleGroup(group);
		audioButton.setAccessibleText("Press enter to select audio player");
		layout9.add(visualButton, 0, 2);
		layout9.add(audioButton, 1, 2);
	}

	/**
	 * confirm section save GUI
	 * 
	 * 
	 * 
	 */

	private void savingSectionGUI() {

		saveWindow = new Stage();
		layout7 = new GridPane();
		layout7.setHgap(10);
		layout7.setVgap(10);
		layout7.setPadding(new Insets(0, 5, 5, 5));

		scene7 = new Scene(layout7);
		saveWindow.setScene(scene7);
		saveWindow.setTitle("Saved");
		saveConfirmed = new Text("This section has been saved");
		saveConfirmed.setFill(Color.WHITE);
		layout7.add(saveConfirmed, 0, 0);
		saveOkayButton = new Button("Okay");
		saveOkayButton.setStyle("-fx-base: #87ceeb;"); // sky blue
		layout7.add(saveOkayButton, 1, 1);
		layout7.setBackground(
				new Background(new BackgroundFill(Color.gray(0.3, 0.8), CornerRadii.EMPTY, Insets.EMPTY)));
		saveOkayButton.setAccessibleText("This section has been saved, press enter to return to main window");

		// action events
		saveOkayButton.setOnAction(e1 -> {
			saveWindow.close();
		});
		saveOkayButton.setOnKeyPressed(e2 -> {
			if (e2.getCode() == KeyCode.ENTER) {
				saveWindow.close();
			}
		});
	}

	private void savingScenarioGUI() {

		// confirm save scenario
		scenarioSavedWindow = new Stage();
		layout13 = new GridPane();
		layout13.setHgap(10);
		layout13.setVgap(10);
		layout13.setPadding(new Insets(0, 5, 5, 5));

		scene13 = new Scene(layout13);
		scenarioSavedWindow.setScene(scene13);
		scenarioSavedWindow.setTitle("Saved");
		projectSavedConfirmed = new Text("This scenario has been saved");
		projectSavedConfirmed.setFill(Color.WHITE);
		layout13.add(projectSavedConfirmed, 0, 0);
		scenarioSavedOkay = new Button("Okay");
		scenarioSavedOkay.setStyle("-fx-base: #87ceeb;"); // sky blue
		layout13.add(scenarioSavedOkay, 1, 1);
		layout13.setBackground(
				new Background(new BackgroundFill(Color.gray(0.3, 0.8), CornerRadii.EMPTY, Insets.EMPTY)));
		scenarioSavedOkay.setAccessibleText("This scenario has been saved, press enter to return to main window");

		// action events
		scenarioSavedOkay.setOnAction(e1 -> {
			scenarioSavedWindow.close();
		});
		scenarioSavedOkay.setOnKeyPressed(e2 -> {
			if (e2.getCode() == KeyCode.ENTER) {
				scenarioSavedWindow.close();
			}
		});
	}

	/**
	 * ----------------------<[warning and error GUIs]
	 */
	/**
	 * new project warning window GUI
	 * 
	 * 
	 * warning that every field will be cleared
	 * 
	 */

	private void newProjectWarningGUI() {

		warningWindow = new Stage();
		layout10 = new GridPane();
		layout10.setHgap(10);
		layout10.setVgap(10);
		layout10.setPadding(new Insets(5, 5, 5, 5));

		scene10 = new Scene(layout10);
		warningWindow.setScene(scene10);
		warningWindow.setTitle("Warning");
		warningText = new Text(
				"	       Are you sure you want to start a new project?\n			any unsaved projects will be lost");
		warningText.setFill(Color.WHITE);
		layout10.add(warningText, 0, 0, 2, 2);
		warningOkay = new Button("Okay");
		warningOkay.setStyle("-fx-base: #87ceeb;"); // sky blue
		warningOkay.setAccessibleRoleDescription("Okay button");
		warningOkay.setAccessibleText(
				"Are you sure you want to start a new project? any unsaved projects will be lost, press enter to continue");
		layout10.add(warningOkay, 0, 4);
		warningCancel = new Button("Cancel");
		warningCancel.setStyle("-fx-base: #ffffff;");
		warningCancel.setAccessibleRoleDescription("Cancel button");
		warningCancel.setAccessibleText("Press enter to return to main window");
		layout10.add(warningCancel, 2, 4);
		layout10.setBackground(
				new Background(new BackgroundFill(Color.gray(0.3, 0.8), CornerRadii.EMPTY, Insets.EMPTY)));
	}

	/**
	 * confirm section clear GUI
	 * 
	 * 
	 * 
	 */
	private void clearSectionWarningGUI() {

		clearSectionWarning = new Stage();
		layout15 = new GridPane();
		layout15.setHgap(10);
		layout15.setVgap(10);
		layout15.setPadding(new Insets(0, 5, 5, 5));

		scene15 = new Scene(layout15);
		clearSectionWarning.setScene(scene15);
		clearSectionWarning.setTitle("Warning");
		clearSectionText = new Text(
				"                 Selecting okay will clear all text fields, all unsaved progress will be lost");
		clearSectionText.setFill(Color.WHITE);
		layout15.add(clearSectionText, 0, 0);
		clearSectionButtonOkay = new Button("Okay");
		clearSectionButtonOkay.setStyle("-fx-base: #87ceeb;"); // sky blue
		clearSectionButtonOkay.setAccessibleRoleDescription("Okay Button");
		clearSectionButtonOkay.setAccessibleText(
				"Selecting okay will clear all text fields, all unsaved progress will be lost, press enter to clear all fields and return to previous screen");
		layout15.add(clearSectionButtonOkay, 0, 1);
		clearSectionButtonCancel = new Button("Cancel");
		clearSectionButtonCancel.setAccessibleRoleDescription("Cancel Button");

		clearSectionButtonCancel.setStyle("-fx-base: #ffffff;");
		clearSectionButtonCancel.setAccessibleText("Press enter to return to previous screen");
		layout15.add(clearSectionButtonCancel, 1, 1);
		layout15.setBackground(
				new Background(new BackgroundFill(Color.gray(0.3, 0.8), CornerRadii.EMPTY, Insets.EMPTY)));

		// action events
		clearSectionButtonOkay.setOnAction(e1 -> {
			clearSection();
		});

		clearSectionButtonOkay.setOnKeyPressed(e2 -> {
			if (e2.getCode() == KeyCode.ENTER) {
				clearSection();
			}
		});

		clearSectionButtonCancel.setOnAction(e1 -> {
			clearSectionWarning.close();
		});
		clearSectionButtonCancel.setOnKeyPressed(e2 -> {
			if (e2.getCode() == KeyCode.ENTER) {
				clearSectionWarning.close();
			}
		});
	}

	/**
	 * no section saved GUI
	 * 
	 * pop up when user tries to save a scenario but there are no saved sections
	 * 
	 * 
	 */

	private void noSectionErrorGUI() {

		noSectionsSavedWindow = new Stage();
		layout14 = new GridPane();
		layout14.setHgap(10);
		layout14.setVgap(10);
		layout14.setPadding(new Insets(5, 5, 5, 5));

		scene14 = new Scene(layout14);
		noSectionsSavedWindow.setScene(scene14);
		noSectionsSavedWindow.setTitle("Error");
		noSectionsSaved = new Text("Scenario can only be saved after you have created and saved at least one section");
		noSectionsSaved.setFill(Color.WHITE);
		layout14.add(noSectionsSaved, 0, 0, 2, 1);
		noSectionSavedOkay = new Button("Okay");
		noSectionSavedOkay.setStyle("-fx-base: #87ceeb;"); // sky blue
		noSectionSavedOkay.setAccessibleRoleDescription("Okay button");
		noSectionSavedOkay.setAccessibleText(
				"Scenario can only be saved after you have created and saved at least one section, press enter to go back to main window");
		layout14.add(noSectionSavedOkay, 2, 1);
		layout14.setBackground(
				new Background(new BackgroundFill(Color.gray(0.3, 0.8), CornerRadii.EMPTY, Insets.EMPTY)));

		// action event
		noSectionSavedOkay.setOnAction(e -> {
			noSectionsSavedWindow.close();
		});
		noSectionSavedOkay.setOnKeyPressed(e -> {
			if (e.getCode() == KeyCode.ENTER) {
				noSectionsSavedWindow.close();
			}
		});
	}

	/**
	 * story field empty error GUI
	 * 
	 * 
	 * 
	 * 
	 */

	private void storyEmptyErrorGUI() {

		emptyStoryWindow = new Stage();
		layout5 = new GridPane();
		layout5.setHgap(10);
		layout5.setVgap(10);
		layout5.setPadding(new Insets(5, 5, 5, 5));

		scene5 = new Scene(layout5);
		emptyStoryWindow.setScene(scene5);
		emptyStoryWindow.setTitle("Story field is empty");
		emptyStoryText = new Text("Section can not be saved if the story field is empty");
		emptyStoryText.setFill(Color.WHITE);
		layout5.add(emptyStoryText, 0, 0, 2, 1);
		emptyStoryOkay = new Button("Okay");
		emptyStoryOkay.setStyle("-fx-base: #87ceeb;"); // sky blue
		emptyStoryOkay.setAccessibleRoleDescription("Okay button");
		emptyStoryOkay.setAccessibleText(
				"Section can not be saved if the story field is empty, press enter to go back to main window");
		layout5.add(emptyStoryOkay, 2, 1);
		layout5.setBackground(
				new Background(new BackgroundFill(Color.gray(0.3, 0.8), CornerRadii.EMPTY, Insets.EMPTY)));

		// action event
		emptyStoryOkay.setOnAction(e -> {
			emptyStoryWindow.close();
		});
		emptyStoryOkay.setOnKeyPressed(e -> {
			if (e.getCode() == KeyCode.ENTER) {
				emptyStoryWindow.close();
			}
		});
	}

	/**
	 * buttons used error GUI
	 * 
	 * 
	 * 
	 */

	private void buttonsUsedErrorGUI() {

		buttonsUsedWindow = new Stage();
		layout8 = new GridPane();
		layout8.setHgap(10);
		layout8.setVgap(10);
		layout8.setPadding(new Insets(0, 5, 5, 5));

		scene8 = new Scene(layout8);
		buttonsUsedWindow.setScene(scene8);
		buttonsUsedWindow.setTitle("Warning");
		buttonsUsedError = new Text("The answer buttons used field and answer field can not have a higher number"
				+ "than the number of buttons available");
		buttonsUsedError.setFill(Color.WHITE);
		layout8.add(buttonsUsedError, 0, 0);
		layout8.setBackground(
				new Background(new BackgroundFill(Color.gray(0.3, 0.8), CornerRadii.EMPTY, Insets.EMPTY)));

		buttonsUsedWindowOkay = new Button("Okay");
		buttonsUsedWindowOkay.setStyle("-fx-base: #87ceeb;"); // sky blue
		layout8.add(buttonsUsedWindowOkay, 1, 1);
		buttonsUsedWindowOkay
				.setAccessibleText("The answer buttons used field and answer field can not have a higher number"
						+ "than the number of buttons available, press enter to return to main window");

		// action events
		buttonsUsedWindowOkay.setOnAction(e1 -> {
			buttonsUsedWindow.close();
		});
		buttonsUsedWindowOkay.setOnKeyPressed(e2 -> {
			if (e2.getCode() == KeyCode.ENTER) {
				buttonsUsedWindow.close();
			}
		});
	}

	/**
	 * section name empty GUI
	 * 
	 * 
	 * 
	 */

	private void sectionEmptyErrorGUI() {

		emptyNameWindow = new Stage();
		layout6 = new GridPane();
		layout6.setHgap(10);
		layout6.setVgap(10);
		layout6.setPadding(new Insets(0, 5, 5, 5));

		scene6 = new Scene(layout6);
		emptyNameWindow.setScene(scene6);
		emptyNameWindow.setTitle("Section name field is empty");
		emptyName = new Text("Section can not be saved unless it has a name");
		emptyName.setFill(Color.WHITE);
		layout6.add(emptyName, 0, 0);
		emptyNameButton = new Button("Okay");
		emptyNameButton.setStyle("-fx-base: #87ceeb;"); // sky blue
		layout6.add(emptyNameButton, 1, 1);
		layout6.setBackground(
				new Background(new BackgroundFill(Color.gray(0.3, 0.8), CornerRadii.EMPTY, Insets.EMPTY)));
		emptyNameButton.setAccessibleRoleDescription("Okay button");
		emptyNameButton.setAccessibleText(
				"Section can not be saved unless it has a name, press enter to go back to main window");

		// action event
		emptyNameButton.setOnAction(e1 -> {
			emptyNameWindow.close();
		});
		emptyNameButton.setOnKeyPressed(e2 -> {
			if (e2.getCode() == KeyCode.ENTER) {
				emptyNameWindow.close();
			}
		});
	}

	/**
	 * Braille field error GUI
	 * 
	 * 
	 * braille field contains a word longer than the number of braille cells
	 * available
	 * 
	 * 
	 */

	private void brailleFieldErrorGUI() {

		brailleWindow = new Stage();
		layout4 = new GridPane();
		layout4.setHgap(10);
		layout4.setVgap(10);
		layout4.setPadding(new Insets(5, 5, 5, 5));

		scene4 = new Scene(layout4);
		brailleWindow.setScene(scene4);
		brailleWindow.setTitle("Error");
		brailleEntry = new Text(
				"The braille field can only contain letters, and can not be empty or have a word longer than the number of braille cells available");
		brailleEntry.setFill(Color.WHITE);
		layout4.add(brailleEntry, 0, 0, 2, 1);
		brailleOkay = new Button("Okay");
		brailleOkay.setStyle("-fx-base: #87ceeb;"); // sky blue
		brailleOkay.setAccessibleRoleDescription("Okay button");
		brailleOkay.setAccessibleText(
				"The braille field can only contain letters, and can not be empty or have a word longer than the number of braille cells available, press enter to return to main window");
		layout4.add(brailleOkay, 2, 1);
		layout4.setBackground(
				new Background(new BackgroundFill(Color.gray(0.3, 0.8), CornerRadii.EMPTY, Insets.EMPTY)));

		// action events
		brailleOkay.setOnAction(e1 -> {
			brailleWindow.close();
		});
		brailleOkay.setOnKeyPressed(e -> {
			if (e.getCode() == KeyCode.ENTER) {
				brailleWindow.close();
			}
		});
	}

	private void soundErrorWindowGUI() {
		soundErrorWindow = new Stage();
		layout16 = new GridPane();
		layout16.setHgap(10);
		layout16.setVgap(10);
		layout16.setPadding(new Insets(5, 5, 5, 5));
		layout16.setBackground(
				new Background(new BackgroundFill(Color.gray(0.3, 0.8), CornerRadii.EMPTY, Insets.EMPTY)));

		scene16 = new Scene(layout16);
		soundErrorWindow.setScene(scene16);
		soundErrorWindow.setTitle("Sound import error");
		soundErrorText = new Text("The imported sound file needs to be in .wav format");
		soundErrorText.setFill(Color.WHITE);
		layout16.add(soundErrorText, 0, 0, 2, 1);
		soundErrorButton = new Button("Okay");
		soundErrorButton.setStyle("-fx-base: #87ceeb;"); // sky blue
		soundErrorButton.setAccessibleRoleDescription("Okay button");
		soundErrorButton.setAccessibleText(
				"The imported sound file needs to be in .wav format, press enter to return to previous window");
		layout16.add(soundErrorButton, 2, 1);

		// action button for answer okay
		soundErrorButton.setOnAction(e -> {
			soundErrorWindow.close();
			copySoundFile();
		});
		soundErrorButton.setOnKeyPressed(e -> {
			if (e.getCode() == KeyCode.ENTER) {
				soundErrorWindow.close();
				copySoundFile();
			}
		});
	}

	/**
	 * section name empty GUI
	 * 
	 * 
	 * 
	 */

	private void nameSoundErrorGUI() {

		nameSoundErrorWindow = new Stage();
		layout18 = new GridPane();
		layout18.setHgap(10);
		layout18.setVgap(10);
		layout18.setPadding(new Insets(0, 5, 5, 5));

		scene18 = new Scene(layout18);
		nameSoundErrorWindow.setScene(scene18);
		nameSoundErrorWindow.setTitle("Error Sound file name empty");
		soundNameErrorText = new Text("Sound file can not be saved unless it has a name");
		soundNameErrorText.setFill(Color.WHITE);
		layout18.add(soundNameErrorText, 0, 0);
		nameSoundErrorButton = new Button("Okay");
		nameSoundErrorButton.setStyle("-fx-base: #87ceeb;"); // sky blue
		layout18.add(nameSoundErrorButton, 1, 1);
		layout18.setBackground(
				new Background(new BackgroundFill(Color.gray(0.3, 0.8), CornerRadii.EMPTY, Insets.EMPTY)));
		nameSoundErrorButton.setAccessibleRoleDescription("Okay button");
		nameSoundErrorButton.setAccessibleText(
				"Sound file can not be saved unless it has a name, press enter to return to previous window");

		// action event
		nameSoundErrorButton.setOnAction(e1 -> {
			nameSoundErrorWindow.close();
			nameSoundFileWindow.show();
		});
		nameSoundErrorButton.setOnKeyPressed(e2 -> {
			if (e2.getCode() == KeyCode.ENTER) {
				nameSoundErrorWindow.close();
				nameSoundFileWindow.show();
			}
		});
	}

	/**
	 * answer field is not a number GUI
	 *
	 *
	 */
	private void notANumberWindowGUI() {
		notANumberWindow = new Stage();
		layout3 = new GridPane();
		layout3.setHgap(10);
		layout3.setVgap(10);
		layout3.setPadding(new Insets(5, 5, 5, 5));
		layout3.setBackground(
				new Background(new BackgroundFill(Color.gray(0.3, 0.8), CornerRadii.EMPTY, Insets.EMPTY)));

		scene3 = new Scene(layout3);
		notANumberWindow.setScene(scene3);
		notANumberWindow.setTitle("Error");
		answerIsNumber = new Text("The answer field and answer buttons used field needs to contain a number");
		answerIsNumber.setFill(Color.WHITE);
		layout3.add(answerIsNumber, 0, 0, 2, 1);
		answerOkay = new Button("Okay");
		answerOkay.setStyle("-fx-base: #87ceeb;"); // sky blue
		answerOkay.setAccessibleRoleDescription("Okay button");
		answerOkay.setAccessibleText(
				"The answer field and answer buttons used field needs to contain a number, press enter to return to main window");
		layout3.add(answerOkay, 2, 1);

		// action button for answer okay
		answerOkay.setOnAction(e -> {
			notANumberWindow.close();
		});
		answerOkay.setOnKeyPressed(e -> {
			if (e.getCode() == KeyCode.ENTER) {
				notANumberWindow.close();
			}
		});
	}

	/**
	 * Error GUI - for brailleCellsUsedWindow errors: no scenario name braille field
	 * empty answer field empty
	 * 
	 * 
	 */

	private void nameOrBrailleFieldEmptyWindow() {

		errorWindow = new Stage();
		layout12 = new GridPane();
		layout12.setHgap(10);
		layout12.setVgap(10);
		layout12.setPadding(new Insets(5, 5, 5, 5));
		layout12.setBackground(
				new Background(new BackgroundFill(Color.gray(0.4, 0.8), CornerRadii.EMPTY, Insets.EMPTY)));

		scene12 = new Scene(layout12);
		errorWindow.setScene(scene12);
		errorWindow.setTitle("Error");
		errorMessage = new Text(
				"You need to have a scenario name, at least one braille cell and one answer button to start creating a scenario");
		errorMessage.setFill(Color.WHITE);
		layout12.add(errorMessage, 0, 0, 2, 1);
		errorMessageButton = new Button("Okay");
		errorMessageButton.setStyle("-fx-base: #87ceeb;"); // sky blue
		errorMessageButton.setAccessibleRoleDescription("Okay button");
		errorMessageButton.setAccessibleText(
				"Make sure you have given your scenario a name, that the braille cell field contains a number higher than one "
						+ "and that the answer buttons available field contains a number higher than one press enter to return to previous window");
		layout12.add(errorMessageButton, 2, 1);

		// action event
		errorMessageButton.setOnAction(e1 -> {
			errorWindow.close();
		});
		errorMessageButton.setOnKeyPressed(e -> {
			if (e.getCode() == KeyCode.ENTER) {
				errorWindow.close();
			}
		});
	}

	/**
	 * sound button
	 * 
	 * record / import / exit
	 * 
	 * 
	 */

	private void soundGUISetup() {

		soundWindow = new Stage();
		soundWindow.setTitle("Sound Menu");
		layout2 = new GridPane();
		layout2.setHgap(10);
		layout2.setVgap(10);
		layout2.setPadding(new Insets(20, 20, 20, 20));
		layout2.setBackground(
				new Background(new BackgroundFill(Color.gray(0.5, 0.8), CornerRadii.EMPTY, Insets.EMPTY)));

		scene2 = new Scene(layout2);
		soundWindow.setScene(scene2);
		soundRecord = new Button("Record sound");
		soundRecord.setMinSize(150, 70);
		soundRecord.setStyle("-fx-base: #87ceeb;"); // sky blue
		soundRecord.setAccessibleRoleDescription("Record sound button");
		soundRecord.setAccessibleText("Press enter to record your own sound");
		layout2.add(soundRecord, 0, 1);
		soundImport = new Button("Import sound");
		soundImport.setMinSize(150, 70);
		soundImport.setStyle("-fx-base: #87ceeb;"); // sky blue
		soundImport.setAccessibleRoleDescription("Import sound button");
		soundImport.setAccessibleText("Press enter to import a sound file");
		layout2.add(soundImport, 3, 1);
		soundExit = new Button("Exit");
		soundExit.setStyle("-fx-base: #ffffff"); // sky blue
		soundExit.setAccessibleRoleDescription("Exit sound window button");
		soundExit.setAccessibleText("Press enter to exit sound window");
		layout2.add(soundExit, 6, 1);

		// action button for answer okay
		soundExit.setOnAction(e -> {
			soundWindow.close();
		});
		soundExit.setOnKeyPressed(e -> {
			if (e.getCode() == KeyCode.ENTER) {
				soundWindow.close();
			}
		});

		// Action Listener for soundImport
		soundImport.setOnAction(e -> {
			copySoundFile();
			soundWindow.close();
		});

		soundImport.setOnKeyReleased(e -> {
			if (e.getCode() == KeyCode.ENTER) {
				copySoundFile();
				soundWindow.close();
			}
		});

		soundRecord.setOnAction(e -> {
			soundWindow.close();
			nameSoundFileWindow.show();
		});

		soundRecord.setOnKeyReleased(e -> {
			if (e.getCode() == KeyCode.ENTER) {
				soundWindow.close();
				nameSoundFileWindow.show();
			}

		});

	}

	private void soundImportedGUI() {

		soundImportedWindow = new Stage();
		layout19 = new GridPane();
		layout19.setHgap(10);
		layout19.setVgap(10);
		layout19.setPadding(new Insets(0, 5, 5, 5));

		scene19 = new Scene(layout19);
		soundImportedWindow.setScene(scene19);
		soundImportedWindow.setTitle("Sound File Imported");
		soundImportedText = new Text(
				"Sound file was successfully imported\n\nTo add the sound file into your story, type the name of the sound file including the .wav extension, surrounded by angle brackets. For example: <sound.wav>");
		soundImportedText.setFill(Color.WHITE);
		layout19.add(soundImportedText, 0, 0);

		soundImportedOkay = new Button("Okay");
		soundImportedOkay.setStyle("-fx-base: #87ceeb;"); // sky blue
		soundImportedOkay.setAccessibleRoleDescription("Okay Button");
		soundImportedOkay.setAccessibleText(
				"Sound file was successfully imported, to add the sound file into your story, type the name of the sound file including the .wav extension, surrounded by angle brackets. For example, to add a file named sound .wav, type less than symbol sound period w a v greater than symbol");
		layout19.add(soundImportedOkay, 1, 1);

		layout19.setBackground(
				new Background(new BackgroundFill(Color.gray(0.3, 0.8), CornerRadii.EMPTY, Insets.EMPTY)));

		// action events
		soundImportedOkay.setOnAction(e1 -> {
			soundImportedWindow.close();
		});

		soundImportedOkay.setOnKeyPressed(e2 -> {
			if (e2.getCode() == KeyCode.ENTER) {
				soundImportedWindow.close();
			}
		});

	}

	/**
	 * name sound file
	 * 
	 * 
	 */

	private void nameSoundFileGUI() {

		nameSoundFileWindow = new Stage();
		nameSoundFileWindow.setTitle("Name Sound File");
		layout17 = new GridPane();
		layout17.setHgap(10);
		layout17.setVgap(10);
		layout17.setPadding(new Insets(20, 20, 20, 20));
		layout17.setBackground(
				new Background(new BackgroundFill(Color.gray(0.5, 0.8), CornerRadii.EMPTY, Insets.EMPTY)));
		scene17 = new Scene(layout17);
		nameSoundFileWindow.setScene(scene17);

		soundNameText = new Text("Name your sound file");
		soundNameText.setFont(Font.font("Arial", FontWeight.BOLD, 12));
		soundNameText.setFill(Color.GHOSTWHITE);
		layout17.add(soundNameText, 0, 0);

		soundNameField = new TextField();
		soundNameField.setAccessibleRole(AccessibleRole.TOOLTIP);
		soundNameField.setAccessibleRoleDescription("Name sound file text field, please name your sound file");
		soundNameField.setPrefWidth(70);
		layout17.add(soundNameField, 0, 1, 2, 1);

		soundTimeText = new Text("Length of recording in seconds");
		soundTimeText.setFont(Font.font("Arial", FontWeight.BOLD, 12));
		soundTimeText.setFill(Color.GHOSTWHITE);
		layout17.add(soundTimeText, 0, 2);

		soundTimeField = new TextField();
		soundTimeField.setAccessibleRole(AccessibleRole.TOOLTIP);
		soundTimeField.setAccessibleRoleDescription(
				"Enter the length of time you want to record in seconds, this field needs to be a number");
		soundTimeField.setPrefWidth(70);
		layout17.add(soundTimeField, 0, 3, 2, 1);

		soundNameOkay = new Button("Okay");
		soundNameOkay.setStyle("-fx-base: #87ceeb;"); // sky blue
		soundNameOkay.setAccessibleRoleDescription("Okay button");
		soundNameOkay.setAccessibleText("Press enter to confirm sound file name");
		layout17.add(soundNameOkay, 0, 4);

		soundNameCancel = new Button("Exit");
		soundNameCancel.setStyle("-fx-base: #ffffff"); // white
		soundNameCancel.setAccessibleRoleDescription("Exit button");
		soundNameCancel.setAccessibleText("Press enter to return to main window");
		layout17.add(soundNameCancel, 1, 4);

		// action events
		soundNameOkay.setOnAction(e -> {
			if (soundNameField.getText().length() == 0) {
				nameSoundErrorWindow.show();
			} else {
				recordWindow.show();
				nameSoundFileWindow.close();
			}
		});

		soundNameOkay.setOnKeyPressed(e -> {
			if (e.getCode() == KeyCode.ENTER) {
				if (soundNameField.getText().length() == 0) {
					nameSoundErrorWindow.show();
				} else {
					recordWindow.show();
					nameSoundFileWindow.close();
				}
			}
		});

		soundNameCancel.setOnAction(e -> {
			nameSoundFileWindow.close();
		});

		soundNameCancel.setOnKeyPressed(e -> {
			if (e.getCode() == KeyCode.ENTER) {
				nameSoundFileWindow.close();
			}
		});

	}

	/**
	 * record sound
	 * 
	 * 
	 */

	private void recordSoundGUI() {
		recordWindow = new Stage();
		recordWindow.setTitle("Record");
		recordLayout = new GridPane();
		recordLayout.setHgap(10);
		recordLayout.setVgap(10);
		recordLayout.setPadding(new Insets(20, 20, 20, 20));
		recordLayout.setBackground(
				new Background(new BackgroundFill(Color.gray(0.5, 0.8), CornerRadii.EMPTY, Insets.EMPTY)));
		recordScene = new Scene(recordLayout);
		recordWindow.setScene(recordScene);
		record = new Button("Start Recording");
		record.setMinSize(150, 70);
		record.setStyle("-fx-base: #87ceeb;"); // sky blue
		record.setAccessibleRoleDescription("Recording sound button");
		record.setAccessibleText("Press enter to start recording sound. Press enter again to stop recording");
		recordLayout.add(record, 0, 1);
		exitButton = new Button("Exit");
		exitButton.setStyle("-fx-base: #ffffff"); // white
		exitButton.setAccessibleRoleDescription("Exit sound window button");
		exitButton.setAccessibleText("Press enter to exit sound window");

		recordLayout.add(exitButton, 3, 1);
		exitButton.setOnAction(e -> {
			recordWindow.close();
		});

		exitButton.setOnKeyReleased(e -> {
			if (e.getCode() == KeyCode.ENTER) {
				recordWindow.close();
			}
		});

		// Name of file needs to go in this constructor
		String nameOfSoundFile = soundNameField.getText();
		SoundRecorder recorder;
		if (soundTimeField.getText().length() == 0 || soundTimeField.getText().matches("[0-9]+")) {
			recorder = new SoundRecorder(nameOfSoundFile);
		} else {
			recordingTime = Integer.parseInt(soundTimeField.getText());
			recorder = new SoundRecorder(nameOfSoundFile, recordingTime);
		}

		record.setOnAction(e -> {
			recorder.record();
		});

		record.setOnKeyReleased(e -> {
			if (e.getCode() == KeyCode.ENTER) {
				recorder.record();
			}
		});
	}

	/*
	 * ----------<[other GUIs
	 * 
	 * 
	 */

	/**
	 * add sound Credit for most of this method:
	 * 
	 * https://www.journaldev.com/861/java-copy-file
	 */
	private void copySoundFile() {
		soundWindow.close();
		FileChooser fileChooser = new FileChooser();
		fileChooser.setTitle("Import Sound File");
		File source = fileChooser.showOpenDialog(scenarioCreator);

		if (source.getName().contains(".wav")) {
			File dest = new File("./AudioFiles/" + source.getName());
			InputStream is = null;
			OutputStream os = null;
			try {
				is = new FileInputStream(source);
				os = new FileOutputStream(dest);
				byte[] buffer = new byte[1024];
				int length;
				while ((length = is.read(buffer)) > 0) {
					os.write(buffer, 0, length);
				}
			} catch (IOException e1) {
				e1.printStackTrace();
			} finally {
				try {
					is.close();
					os.close();
				} catch (IOException e1) {
				}

			}
			soundImportedWindow.show();

		}

		else {
			soundErrorWindow.show();
		}

	}

	/**
	 * Naming scenario GUI
	 * 
	 * 
	 * enter scenario name number of braille cells available number of answer
	 * buttons available
	 * 
	 * 
	 */
	private void setupScenarioGUI(Stage primaryStage) {

		brailleCellsUsedWindow = new Stage();
		layout11 = new GridPane();
		layout11.setHgap(10);
		layout11.setVgap(10);
		layout11.setPadding(new Insets(5, 5, 10, 5));

		scene11 = new Scene(layout11);
		brailleCellsUsedWindow.setScene(scene11);
		brailleCellsUsedWindow.setTitle("Scenario Setup");
		scenarioNameField = new TextField();
		scenarioNameField.setAccessibleRole(AccessibleRole.TOOLTIP);
		scenarioNameField.setAccessibleRoleDescription("Scenario name Field");
		scenarioNameText = new Text("Scenario Name");
		nameBrailleAnswer = new Text(
				"Enter the name of your scenario, the number of Braille Cells and Answer Buttons available");
		layout11.add(nameBrailleAnswer, 0, 0, 2, 1);
		brailleCellsField = new TextField();
		brailleCellsField.setAccessibleRole(AccessibleRole.TOOLTIP);
		brailleCellsField.setAccessibleRoleDescription("braille cells available Field, please enter a number");
		brailleCellsText = new Text("Braille Cells Available");
		answerButtonsField = new TextField();
		answerButtonsField.setAccessibleRole(AccessibleRole.TOOLTIP);
		answerButtonsField.setAccessibleRoleDescription("Answer buttons available field, please enter a number");
		answerButtonsText = new Text("Answer Buttons Available");
		layout11.add(scenarioNameField, 0, 1);
		layout11.add(scenarioNameText, 1, 1);
		layout11.add(brailleCellsField, 0, 2);
		layout11.add(brailleCellsText, 1, 2);
		layout11.add(answerButtonsField, 0, 3);
		layout11.add(answerButtonsText, 1, 3);
		layout11.setBackground(
				new Background(new BackgroundFill(Color.gray(0.5, 0.8), CornerRadii.EMPTY, Insets.EMPTY)));

		scenarioNameFieldLabel = new Label("Enter the \nname of \nyour scenario");
		scenarioNameFieldLabel.setLabelFor(scenarioNameField);
		scenarioNameFieldLabel.setVisible(false);
		layout11.add(scenarioNameFieldLabel, 0, 1);

		brailleCellsUsedLabel = new Label("Enter the \nnumber of \nBraille cells available");
		brailleCellsUsedLabel.setLabelFor(brailleCellsField);
		brailleCellsUsedLabel.setVisible(false);
		layout11.add(brailleCellsUsedLabel, 0, 2);

		answerButtonsUsedLabel = new Label("Enter the \nnumber of \nanswer buttons available");
		answerButtonsUsedLabel.setLabelFor(answerButtonsField);
		answerButtonsUsedLabel.setVisible(false);
		layout11.add(answerButtonsUsedLabel, 0, 3);

		okayStart = new Button("Create");
		okayStart.setStyle("-fx-base: #87ceeb;"); // sky blue
		okayStart.setAccessibleRoleDescription("Okay button");
		okayStart.setAccessibleText("Press enter to start creating a scenario");
		layout11.add(okayStart, 2, 3);

		// open scenario creator or display error message
		okayStart.setOnAction(e -> {

			nameNewScenario(scenarioCreator, errorWindow, brailleCellsUsedWindow, scenarioNameField, brailleCellsField,
					answerButtonsField);
		});

		okayStart.setOnKeyPressed(e -> {
			if (e.getCode() == KeyCode.ENTER) {

				nameNewScenario(scenarioCreator, errorWindow, brailleCellsUsedWindow, scenarioNameField,
						brailleCellsField, answerButtonsField);
			}
		});

		/*
		 * action event (starting window - create button)
		 * 
		 * 
		 * opens new window for naming scenario
		 * 
		 * 
		 */
		createButton.setOnAction(e1 -> {
			brailleCellsUsedWindow.show();
			primaryStage.close();
		});
		createButton.setOnKeyPressed(e -> {
			if (e.getCode() == KeyCode.ENTER) {
				brailleCellsUsedWindow.show();
				primaryStage.close();
			}
		});
	}

	private void scenarioMenuButton() {
		// manage scenario button
		scenarioMenuButton = new Button("Scenario Menu");
		scenarioMenuButton.setAccessibleRoleDescription("Test Scenario");
		scenarioMenuButton.setAccessibleText("Press enter to test current scenario");
		scenarioMenuButton.setStyle("-fx-base: #FFFFFF;"); // white
		layout.add(scenarioMenuButton, 7, 1);
	}

	/*
	 * .* comboBox
	 * 
	 * 
	 */

	private void createSectionComboBox() {
		// ComboBox (drop down menu)
		comboBoxList = FXCollections.observableArrayList();
		comboBox = new ComboBox<String>(comboBoxList);
		comboBox.setPrefWidth(200);
		comboBox.setPromptText("Select a section");
		comboBoxList.add(0, "New Section");
		layout.add(comboBox, 9, 0, 5, 1);
	}

	private void comboBoxOpen() {
		comboBox.setOnKeyPressed(e -> {
			if (e.getCode() == KeyCode.DOWN) {
				comboBox.show();
			}
		});
	}

	public static void main(String[] args) {

		// Inherited method from Application that lunches GUI
		launch(args);
	}

	@Override
	public void start(Stage primaryStage) throws Exception {

		createPrimaryStage();
		createScenarioMenu();
		createSectionMenu();
		createGotoMenu();
		createSoundMenu();
		createMenuBar();
		borderGlowSetup();
		sectionNameAndButtonsUsed();
		storyTextSetup();
		brailleInputSetup();
		answerSetup();
		soundButtonSetup();
		correctIncorrectSetup();
		saveSectionSetup();
		clearSectionSetup();
		blank();
		createSectionComboBox();
		scenarioMenuButton();
		comboBoxOpen();

		/*
		 * 
		 * 
		 *
		 * --------------------<{Other Scenes and Action Events
		 * 
		 * 
		 * 
		 * 
		 */

		setupScenarioGUI(primaryStage);
		soundGUISetup();
		nameSoundFileGUI();
		recordSoundGUI();
		nameSoundErrorGUI();
		soundImportedGUI();

		// Scene
		primaryStage.setTitle("Welcome");
		primaryStage.setScene(scene1);
		scene1.setFill(Color.TRANSPARENT);
		primaryStage.show();
		layout1.setBackground(
				new Background(new BackgroundFill(Color.gray(0.05, 0.6), CornerRadii.EMPTY, Insets.EMPTY)));

		// ----------------------------<<[warnings

		/**
		 * GUI for errors
		 * 
		 * 
		 * 
		 */
		nameOrBrailleFieldEmptyWindow();
		notANumberWindowGUI();
		brailleFieldErrorGUI();
		sectionEmptyErrorGUI();
		buttonsUsedErrorGUI();
		storyEmptyErrorGUI();
		noSectionErrorGUI();
		clearSectionWarningGUI();
		soundErrorWindowGUI();

		/////////////////////////////////////////////////////////////////////////////////////////

		savingScenarioGUI();
		newProjectWarningGUI();
		savingSectionGUI();

		// ---------<<[ other GUIs

		visualOrAudioPlayerGUI();

		/*
		 * -----<<[save section button
		 * 
		 * 
		 * 
		 */

		saveButton.setOnMouseClicked(e -> {

			saveSection(nameSectionField, answerButtonsUsedField, storyText, brailleText, answerText, correctText,
					incorrectText, comboBoxList, comboBox, brailleCellsField, answerButtonsField, notANumberWindow,
					brailleWindow, emptyNameWindow, buttonsUsedWindow, emptyStoryWindow, saveWindow);

		});

		saveButton.setOnKeyPressed(e -> {

			if (e.getCode() == KeyCode.ENTER) {

				saveSection(nameSectionField, answerButtonsUsedField, storyText, brailleText, answerText, correctText,
						incorrectText, comboBoxList, comboBox, brailleCellsField, answerButtonsField, notANumberWindow,
						brailleWindow, emptyNameWindow, buttonsUsedWindow, emptyStoryWindow, saveWindow);

			}
		});

		saveButton.setOnKeyPressed(e -> {
			new KeyCodeCombination(KeyCode.TAB, KeyCodeCombination.CONTROL_DOWN);
			clearSectionButton.requestFocus();
		});

		/**
		 * clear section button
		 * 
		 * 
		 * 
		 */
		clearSectionButton.setOnMouseClicked(e -> {
			clearSectionWarning.show();
		});

		clearSectionButton.setOnKeyPressed(e -> {

			if (e.getCode() == KeyCode.ENTER) {
				clearSectionWarning.show();
			}
		});

		clearSectionButton.setOnKeyPressed(e -> {
			new KeyCodeCombination(KeyCode.TAB, KeyCodeCombination.CONTROL_DOWN);
			comboBox.requestFocus();
		});

		/**
		 * return selected comboBox value
		 *
		 *
		 *
		 */
		comboBox.getSelectionModel().selectedIndexProperty().addListener(e -> {

			if (comboBox.getValue() == "New Section") {

				clearSectionWarning.show();

			} else {

				for (int j = 0; j < blockList.size(); j++) {
					if (comboBox.getValue() == blockList.get(j).name) {
						nameSectionField.setText((blockList.get(j).name));
						storyText.setText(blockList.get(j).story);
						correctText.setText(blockList.get(j).correctResponse);
						incorrectText.setText(blockList.get(j).wrongResponse);
						brailleText.setText(blockList.get(j).cells);
						answerText.setText(Integer.toString(blockList.get(j).answer));
						answerButtonsUsedField.setText(Integer.toString(blockList.get(j).buttonsUsed));

					}
				}

			}

		});

		comboBox.setOnKeyPressed(e -> {
			new KeyCodeCombination(KeyCode.TAB, KeyCodeCombination.CONTROL_DOWN);
			scenarioMenuButton.requestFocus();
		});

		/**
		 * scenario menu button
		 */
		scenarioMenuButton.setOnKeyPressed(e -> {
			new KeyCodeCombination(KeyCode.TAB, KeyCodeCombination.CONTROL_DOWN);
			nameSectionField.requestFocus();
		});

		/*
		 * ---------<{scenario menu action
		 * 
		 * 
		 */

		// File Menu Selection : new project
		newProject.setOnAction(e -> {
			warningWindow.show();
		});

		// hot key new project
		newProject.setAccelerator(
				new KeyCodeCombination(KeyCode.N, KeyCodeCombination.CONTROL_DOWN, KeyCodeCombination.ALT_DOWN));

		// warning window okay button pressed
		warningOkay.setOnAction(e -> {
			scenarioCreator.close();
			warningWindow.close();
			brailleCellsUsedWindow.show();
			scenarioNameField.clear();
			brailleCellsField.clear();
			answerButtonsField.clear();
		});

		warningOkay.setOnKeyPressed(e1 -> {
			if (e1.getCode() == KeyCode.ENTER) {
				scenarioCreator.close();
				warningWindow.close();
				brailleCellsUsedWindow.show();
				scenarioNameField.clear();
				brailleCellsField.clear();
				answerButtonsField.clear();
			}
		});

		warningCancel.setOnAction(e2 -> {
			warningWindow.close();
		});
		warningCancel.setOnKeyPressed(e3 -> {
			if (e3.getCode() == KeyCode.ENTER) {
				warningWindow.close();
			}

		});

		// File menu selection : save project
		saveProject.setOnAction(e -> {

			if (blockList.size() == 0) {
				noSectionsSavedWindow.show();
			} else {
				// send blocklist to printer == save txt file
				try {
					printer = new Printer(scenarioNameField.getText() + ".txt",
							Integer.parseInt(brailleCellsField.getText()),
							Integer.parseInt(answerButtonsField.getText()));
					printer.addBlockList(blockList);
					scenarioSavedWindow.show();
					printer.print();
				} catch (IOException e3) {
					e3.printStackTrace();
				} catch (OddSpecialCharacterException e3) {
					// TODO Auto-generated catch block
					e3.printStackTrace();
				} catch (InvalidBlockException e3) {

				}
			}
		});

		// hot key save project
		saveProject.setAccelerator(
				new KeyCodeCombination(KeyCode.S, KeyCodeCombination.CONTROL_DOWN, KeyCodeCombination.ALT_DOWN));

		loadProject.setOnAction(e -> {

		});

		/*
		 * ------<<[section menu actions
		 * 
		 * 
		 */

		// --
		saveSection.setOnAction(e -> {
			saveSection(nameSectionField, answerButtonsUsedField, storyText, brailleText, answerText, correctText,
					incorrectText, comboBoxList, comboBox, brailleCellsField, answerButtonsField, notANumberWindow,
					brailleWindow, emptyNameWindow, buttonsUsedWindow, emptyStoryWindow, saveWindow);
		});

		saveSection.setAccelerator(new KeyCodeCombination(KeyCode.S, KeyCombination.CONTROL_DOWN));

		//
		clearSection.setOnAction(e -> {
			clearSectionWarning.show();
		});

		// hot key clear
		clearSection.setAccelerator(new KeyCodeCombination(KeyCode.C, KeyCodeCombination.CONTROL_DOWN));

		/*
		 * ------<<[go to menu
		 * 
		 *
		 */

		goToSectionName.setOnAction(e -> {
			nameSectionField.requestFocus();
		});

		goToSectionName.setAccelerator(new KeyCodeCombination(KeyCode.DIGIT1, KeyCodeCombination.CONTROL_DOWN));

		// --
		goToAnswerButtonsUsed.setOnAction(e -> {
			answerButtonsUsedField.requestFocus();
		});

		goToAnswerButtonsUsed.setAccelerator(new KeyCodeCombination(KeyCode.DIGIT2, KeyCodeCombination.CONTROL_DOWN));

		// --
		goToStory.setOnAction(e -> {
			storyText.requestFocus();
		});
		goToStory.setAccelerator(new KeyCodeCombination(KeyCode.DIGIT3, KeyCodeCombination.CONTROL_DOWN));

		// --
		goToBraille.setOnAction(e -> {
			brailleText.requestFocus();
		});
		goToBraille.setAccelerator(new KeyCodeCombination(KeyCode.DIGIT4, KeyCodeCombination.CONTROL_DOWN));

		// --
		goToAnswer.setOnAction(e -> {
			answerText.requestFocus();
		});
		goToAnswer.setAccelerator(new KeyCodeCombination(KeyCode.DIGIT5, KeyCodeCombination.CONTROL_DOWN));

		// --
		goToCorrect.setOnAction(e -> {
			correctText.requestFocus();
		});

		goToCorrect.setAccelerator(new KeyCodeCombination(KeyCode.DIGIT6, KeyCodeCombination.CONTROL_DOWN));

		// --
		goToIncorrect.setOnAction(e -> {
			incorrectText.requestFocus();
		});
		goToIncorrect.setAccelerator(new KeyCodeCombination(KeyCode.DIGIT7, KeyCodeCombination.CONTROL_DOWN));

		/*
		 * ------<<[sound menu
		 * 
		 *
		 */

		addSound.setOnAction(e -> {
			soundWindow.show();
		});

		addSound.setAccelerator(
				new KeyCodeCombination(KeyCode.S, KeyCodeCombination.CONTROL_DOWN, KeyCodeCombination.SHIFT_DOWN));

		/*
		 * <<<starting window>>>
		 * 
		 */

		// starting window -> choose audio or visual player
		testButton.setOnAction(e1 -> {
			runTest(primaryStage, playerSelectionWindow, visualButton, audioButton);
		});

		testButton.setOnKeyPressed(e -> {
			if (e.getCode() == KeyCode.ENTER) {
				runTest(primaryStage, playerSelectionWindow, visualButton, audioButton);
			}
		});

		// Set true to help see how nodes are aligned
		layout.setGridLinesVisible(false);
	}

	// ------------------------<<<{methods}

	/**
	 * test scenario from opening window
	 * 
	 * 
	 */

	private void runTest(Stage primaryStage, Stage playerSelectionWindow, RadioButton visualButton,
			RadioButton audioButton) {
		primaryStage.close();
		playerSelectionWindow.show();

		visualButton.setOnAction(e2 -> {
			playerSelectionWindow.close();
			FileChooser fileChooser = new FileChooser();
			fileChooser.setTitle("Open Scenario File");
			File file = fileChooser.showOpenDialog(primaryStage);
			ScenarioParser s = new ScenarioParser(true);
			s.setScenarioFile(file.getAbsolutePath());
		});

		audioButton.setOnAction(e3 -> {
			playerSelectionWindow.close();
			FileChooser fileChooser = new FileChooser();
			fileChooser.setTitle("Open Scenario File");
			File file = fileChooser.showOpenDialog(primaryStage);
			ScenarioParser s = new ScenarioParser(false);
			s.setScenarioFile(file.getAbsolutePath());
		});
	}

	/**
	 * creating / naming new scenario
	 * 
	 * 
	 * 
	 */
	private void nameNewScenario(Stage scenarioCreator, Stage errorWindow, Stage brailleCellsUsedWindow,
			TextField scenarioNameField, TextField brailleCellsField, TextField answerButtonsField) {
		if (scenarioNameField.getText().isEmpty() || brailleCellsField.getText().isEmpty()
				|| answerButtonsField.getText().isEmpty() || !brailleCellsField.getText().matches("[0-9]+")
				|| !answerButtonsField.getText().matches("[0-9]+") || Integer.parseInt(brailleCellsField.getText()) == 0
				|| Integer.parseInt(answerButtonsField.getText()) == 0) {
			errorWindow.show();
		} else {
			try {
				scenarioCreator.show();
				brailleCellsUsedWindow.close();
			} catch (NumberFormatException e3) {
				errorWindow.show();
				e3.printStackTrace();
			}
		}
	}

	/**
	 * load fields from combobox
	 * 
	 * 
	 */

	private void fillFields(TextField nameSectionField, TextArea storyText, TextField brailleText, TextField answerText,
			TextArea correctText, TextArea incorrectText, TextField answerButtonsField) {
		blockList.get(blockList.indexOf((blockMap.get(nameSectionField.getText())))).story = storyText.getText();
		blockList.get(blockList.indexOf((blockMap.get(nameSectionField.getText())))).correctResponse = correctText
				.getText();
		blockList.get(blockList.indexOf((blockMap.get(nameSectionField.getText())))).wrongResponse = incorrectText
				.getText();
		blockList.get(blockList.indexOf((blockMap.get(nameSectionField.getText())))).cells = brailleText.getText();
		blockList.get(blockList.indexOf((blockMap.get(nameSectionField.getText())))).answer = Integer
				.parseInt(answerText.getText());
		blockList.get(blockList.indexOf((blockMap.get(nameSectionField.getText())))).buttonsUsed = Integer
				.parseInt(answerButtonsField.getText());
	}

	/**
	 * clear section
	 * 
	 * 
	 */

	private void clearSection() {
		nameSectionField.clear();
		storyText.clear();
		correctText.clear();
		incorrectText.clear();
		brailleText.clear();
		answerText.clear();
		answerButtonsUsedField.clear();
		clearSectionWarning.close();
		nameSectionField.requestFocus();
	}

	/**
	 * save section
	 * 
	 * 
	 * 
	 */

	private void saveSection(TextField nameSectionField, TextField answerButtonsUsedField, TextArea storyText,
			TextField brailleText, TextField answerText, TextArea correctText, TextArea incorrectText,
			ObservableList<String> comboBoxList, ComboBox<String> comboBox, TextField brailleCellsField,
			TextField answerButtonsField, Stage notANumberWindow, Stage brailleWindow, Stage emptyNameWindow,
			Stage buttonsUsedWindow, Stage emptyStoryWindow, Stage saveWindow) {

		// get name of section from user input
		if (nameSectionField.getText().equals("")) {
			emptyNameWindow.show();
		} else if (brailleText.getText().length() == 0
				|| brailleText.getText().length() > Integer.parseInt(brailleCellsField.getText())
				|| !brailleText.getText().matches("[A-z]+")) {
			brailleWindow.show();
		} else {

			String blockName = nameSectionField.getText();

			if (blockMap.containsKey(blockName)) {
				fillFields(nameSectionField, storyText, brailleText, answerText, correctText, incorrectText,
						answerButtonsField);
				saveWindow.show();
			} else {

				// save text to block
				Block blockText;

				try {
					blockText = new Block(blockName, storyText.getText(), correctText.getText(),
							incorrectText.getText(), Integer.parseInt(answerText.getText()), brailleText.getText(),
							Integer.parseInt(answerButtonsUsedField.getText()));
					blockList.add(blockText);
					blockMap.put(blockName, blockText);

					// save name to comboBox
					comboBoxList.add(blockName);
					comboBox.setItems(comboBoxList);

				} catch (NumberFormatException e2) {
					notANumberWindow.show();
					e2.printStackTrace();
				} catch (InvalidBlockException e2) {
					if (Integer.parseInt(answerText.getText()) < 1
							|| Integer.parseInt(answerText.getText()) > Integer
									.parseInt(answerButtonsUsedField.getText())
							|| Integer.parseInt(answerText.getText()) > Integer.parseInt(answerButtonsField.getText())
							|| Integer.parseInt(answerButtonsUsedField.getText()) > Integer
									.parseInt(answerButtonsField.getText())) {
						buttonsUsedWindow.show();
					} else if (storyText.getText().length() == 0) {
						emptyStoryWindow.show();
					}

				}
			}

		}

	}

}