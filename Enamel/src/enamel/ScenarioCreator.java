package src.enamel;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import javafx.application.Application;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.geometry.Insets;
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
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.layout.CornerRadii;
import javafx.scene.layout.GridPane;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import src.enamel.Block;
import src.enamel.InvalidBlockException;
import src.enamel.OddSpecialCharacterException;
import src.enamel.Printer;
import src.enamel.ScenarioParser;

public class ScenarioCreator extends Application {

	Printer printer;
	ArrayList<Block> blockList = new ArrayList<>();
	HashMap<String, Block> blockMap = new HashMap<String, Block>();
	GridPane layout1, layout, layout12, layout11, layout2, layout3, layout4, layout6, layout8, layout5, layout14, layout7, layout13, layout10, layout9;
	Scene scene1, scene, scene12, scene11, scene2, scene3, scene4, scene6, scene8, scene5, scene14, scene7, scene13, scene10, scene9;
	Button createButton, testButton, sound, saveButton, testScenario, clearSectionButton, errorMessageButton, okayStart, warningOkay, warningCancel, 
			soundOkay, answerOkay, brailleOkay, emptyNameButton, buttonsUsedWindowOkay, emptyStoryOkay, noSectionSavedOkay, saveOkayButton, scenarioSavedOkay;
	Stage scenarioCreator, errorWindow, brailleCellsUsedWindow, soundWindow, notANumberWindow, brailleWindow,
			emptyNameWindow, buttonsUsedWindow, emptyStoryWindow, noSectionsSavedWindow, saveWindow, scenarioSavedWindow, warningWindow, playerSelectionWindow;
	Text startWindowText, sectionName, answerButtonsUsedText, correct, story, braille, answer, incorrect,
			scenarioNameText, nameBrailleAnswer, brailleCellsText, answerButtonsText, blank1, errorMessage, warningText,
			soundMessage, answerIsNumber, brailleEntry, emptyName, buttonsUsedError, emptyStoryText, noSectionsSaved, saveConfirmed, playerSelectionText, projectSavedConfirmed;
	Label nameSectionLabel, answerButtonsUsedFieldLabel, storyLabel, brailleLabel, answerLabel, correctLabel, playerLabel,
			incorrectLabel, scenarioNameFieldLabel, brailleCellsUsedLabel, answerButtonsUsedLabel;
	Menu scenarioMenu, sectionMenu, goToMenu, soundMenu;
	MenuItem newProject, loadProject, saveProject, testProject, saveSection, clearSection, goToSectionName,
			goToAnswerButtonsUsed, goToStory, goToBraille, goToAnswer, goToCorrect, goToIncorrect, goToComboBox,
			addSound;
	MenuBar menuBar;
	DropShadow borderGlow;
	TextField nameSectionField, answerButtonsUsedField, brailleText, answerText, scenarioNameField, brailleCellsField,
			answerButtonsField;
	TextArea storyText, correctText, incorrectText;
	ObservableList<String> comboBoxList;
	ComboBox<String> comboBox;
	RadioButton audioButton, visualButton;

	// ***************** methods ******************

	/*
	 * run test
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

	/*
	 * creating / naming new scenario
	 * 
	 * 
	 */
	private void nameNewScenario(Stage scenarioCreator, Stage errorWindow, Stage brailleCellsUsedWindow,
			TextField scenarioNameField, TextField brailleCellsField, TextField answerButtonsField) {
		try {
			scenarioCreator.show();
			brailleCellsUsedWindow.close();
		} catch (NumberFormatException e3) {
			errorWindow.show();
			e3.printStackTrace();
		}
	}

	/*
	 * load sections
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

		newProject = new MenuItem("New Project");
		loadProject = new MenuItem("Load Project");
		saveProject = new MenuItem("Save Project");
		testProject = new MenuItem("Test Project");

		scenarioMenu.getItems().add(newProject);
		scenarioMenu.getItems().add(loadProject);
		scenarioMenu.getItems().add(saveProject);
		scenarioMenu.getItems().add(testProject);
	}

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
		layout1.setPadding(new Insets(5, 5, 10, 5));
		scene1 = new Scene(layout1, 550, 200);

		startWindowText = new Text("                       Welcome to Scenario Creator");
		startWindowText.setFont(Font.font("Arial", FontWeight.BOLD, 20));
		startWindowText.setFill(Color.WHITE);
		layout1.add(startWindowText, 0, 2, 3, 1);
		createButton = new Button("Create New Scenario");
		createButton.setMinSize(150, 60);
		createButton.setStyle("-fx-base: #87ceeb;"); // sky blue
		createButton.setAccessibleRoleDescription("Create new scenario button");
		createButton.setAccessibleText("Welcome to scenario creator, To create a new scenario press enter");
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

		MenuItem newProject = new MenuItem("New Scenario");
		MenuItem loadProject = new MenuItem("Load Scenario");
		MenuItem saveProject = new MenuItem("Save Scenario");
		MenuItem testProject = new MenuItem("Test Scenario");


		scenarioMenu.getItems().add(newProject);
		scenarioMenu.getItems().add(loadProject);
		scenarioMenu.getItems().add(saveProject);
		scenarioMenu.getItems().add(testProject);

		
		/* Section menu
		 * 
		 * 
		 * save section
		 * clear section
		 * 
		 * 
		 */
		Menu sectionMenu = new Menu("Section");

		MenuItem saveSection = new MenuItem("Save Section");
		MenuItem clearSection = new MenuItem("Clear Section");
		sectionMenu.getItems().add(saveSection);
		sectionMenu.getItems().add(clearSection);
	}

	private void createGotoMenu() {
		/*
		 * Go to
		 * 
		 * 
		 * Goes to text field or area
		 * 
		 * 
		 */
		goToMenu = new Menu("Go To");

		goToSectionName = new MenuItem("Section Name");
		goToAnswerButtonsUsed = new MenuItem("Answer Buttons Used");
		goToStory = new MenuItem("Story");
		goToBraille = new MenuItem("Braille");
		goToAnswer = new MenuItem("Answer");
		goToCorrect = new MenuItem("Correct");
		goToIncorrect = new MenuItem("Incorrect");
		goToComboBox = new MenuItem("Drop Down");

		goToMenu.getItems().add(goToSectionName);
		goToMenu.getItems().add(goToAnswerButtonsUsed);
		goToMenu.getItems().add(goToStory);
		goToMenu.getItems().add(goToBraille);
		goToMenu.getItems().add(goToAnswer);
		goToMenu.getItems().add(goToCorrect);
		goToMenu.getItems().add(goToIncorrect);
		goToMenu.getItems().add(goToComboBox);
	}

	private void blankAndSaveSectionSetup() {
		// blank text field for spacing
		blank1 = new Text("                                         ");
		blank1.setFont(Font.font("Arial", FontWeight.SEMI_BOLD, 10));
		layout.add(blank1, 6, 7);

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
	}

	private void answerSetup() {
		// answer text field
		answer = new Text("Answer");
		answer.setFont(Font.font("Arial", FontWeight.BOLD, 11.5));
		answer.setFill(Color.WHITE);
		layout.add(answer, 4, 7);

		answerText = new TextField();
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
		brailleText.setPrefWidth(40);
		layout.add(brailleText, 0, 7, 2, 1);

		// accessibility for braille text field
		brailleLabel = new Label("Enter the word \n you want displayed \n on the braille cell");
		brailleLabel.setLabelFor(brailleText);
		brailleLabel.setVisible(false);
		layout.add(brailleLabel, 2, 7);
	}

	private void storyTextSetup() {
		// Story text area
		story = new Text(" Story");
		story.setFont(Font.font("Arial", FontWeight.BOLD, 13));
		story.setFill(Color.WHITE);
		layout.add(story, 0, 2, 6, 1);

		storyText = new TextArea();
		storyText.setPrefHeight(250);
		storyText.setPrefWidth(500);
		storyText.setOpacity(0.9);
		storyText.setWrapText(true);
		storyText.setEffect(borderGlow);
		layout.add(storyText, 0, 3, 8, 4);

		storyText.setAccessibleRoleDescription("this be the story text area");
		storyLabel = new Label("Enter your story here");
		storyLabel.setLabelFor(storyText);
		storyLabel.setVisible(false);
		layout.add(storyLabel, 0, 3, 8, 4);
	}

	private void sectionNameAndButtonsUsed() {
		// Block title
		nameSectionField = new TextField();
		nameSectionField.setPrefWidth(30);
		layout.add(nameSectionField, 0, 1);

		sectionName = new Text("Section Name");
		sectionName.setFont(Font.font("Arial", FontWeight.BOLD, 11.5));
		sectionName.setFill(Color.GHOSTWHITE);
		layout.add(sectionName, 1, 1, 2, 1);

		// accessibility for braille text field
		nameSectionLabel = new Label("Enter the name\n for this section \n of your story");
		nameSectionLabel.setLabelFor(nameSectionField);
		nameSectionLabel.setVisible(false);
		layout.add(nameSectionLabel, 0, 1);

		// number of answer buttons used
		answerButtonsUsedField = new TextField();
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

	private void createMenuBar() {
		/*
		 * menu bar
		 * 
		 * Scenario Section Go to
		 * 
		 * 
		 */

		menuBar = new MenuBar();
		menuBar.getMenus().addAll(scenarioMenu, sectionMenu, goToMenu, soundMenu);
		menuBar.setOpacity(0.7);
		layout.add(menuBar, 0, 0, 8, 1);
	}

	private void createSoundMenu() {
		/*
		 * Sound menu
		 * 
		 * 
		 * add sound
		 * 
		 * 
		 */
		soundMenu = new Menu("Sound");

		addSound = new MenuItem("Add Sound");

		soundMenu.getItems().add(addSound);
	}
	
	private void visualOrAudioPlayerGUI() {
		/*
		 * 
		 * 
		 * Pop up window : Choose between audio / visual player for testing
		 * 
		 * 
		 */

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

	private void newProjectWarningGUI() {
		/*
		 * new project warning window GUI
		 * 
		 * 
		 * warning that every field will be cleared
		 * 
		 * 
		 */

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
		layout10.add(warningOkay, 2, 4);
		warningCancel = new Button("Cancel");
		warningCancel.setStyle("-fx-base: #87ceeb;"); // sky blue
		warningCancel.setAccessibleRoleDescription("Cancel button");
		warningCancel.setAccessibleText("Press enter to return to main window");
		layout10.add(warningCancel, 0, 4);
		layout10.setBackground(
				new Background(new BackgroundFill(Color.gray(0.3, 0.8), CornerRadii.EMPTY, Insets.EMPTY)));
	}

	private void savingScenarioGUI() {
		/*
		 * confirm save GUI
		 * 
		 * 
		 * 
		 */

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

	private void noSectionErrorGUI() {
		/*
		 * warning window : scenario can not be saved without one completed section
		 * 
		 * 
		 * 
		 * 
		 */

		noSectionsSavedWindow = new Stage();
		layout14 = new GridPane();
		layout14.setHgap(10);
		layout14.setVgap(10);
		layout14.setPadding(new Insets(5, 5, 5, 5));

		scene14 = new Scene(layout14);
		noSectionsSavedWindow.setScene(scene14);
		noSectionsSavedWindow.setTitle("Error");
		noSectionsSaved = new Text(
				"Scenario can only be saved after you have created and saved at least one section");
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

	private void storyEmptyErrorGUI() {
		/*
		 * story field empty error GUI
		 * 
		 * 
		 * 
		 * 
		 */

		emptyStoryWindow = new Stage();
		layout5 = new GridPane();
		layout5.setHgap(10);
		layout5.setVgap(10);
		layout5.setPadding(new Insets(5, 5, 5, 5));

		scene5 = new Scene(layout5);
		emptyStoryWindow.setScene(scene5);
		emptyStoryWindow.setTitle("Story field is empty");
		emptyStoryText = new Text("Section can not be saved\nif the story field is empty");
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

	private void buttonsUsedErrorGUI() {
		/*
		 * buttons used error Window
		 * 
		 * 
		 * 
		 */
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

	private void sectionEmptyErrorGUI() {
		/*
		 * section name empty GUI
		 * 
		 * 
		 * 
		 */

		emptyNameWindow = new Stage();
		layout6 = new GridPane();
		layout6.setHgap(10);
		layout6.setVgap(10);
		layout6.setPadding(new Insets(0, 5, 5, 5));

		scene6 = new Scene(layout6);
		emptyNameWindow.setScene(scene6);
		emptyNameWindow.setTitle("Name is empty");
		emptyName = new Text("Section can not be saved unless it has a name");
		emptyName.setFill(Color.WHITE);
		layout6.add(emptyName, 0, 0);
		emptyNameButton = new Button("Okay");
		emptyNameButton.setStyle("-fx-base: #87ceeb;"); // sky blue
		layout6.add(emptyNameButton, 1, 1);
		layout6.setBackground(
				new Background(new BackgroundFill(Color.gray(0.3, 0.8), CornerRadii.EMPTY, Insets.EMPTY)));
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

	private void brailleFieldErrorGUI() {
		/*
		 * Braille field error GUI
		 * 
		 * 
		 * braille field contains a word longer than the number of braille cells
		 * available
		 * 
		 * 
		 */

		brailleWindow = new Stage();
		layout4 = new GridPane();
		layout4.setHgap(10);
		layout4.setVgap(10);
		layout4.setPadding(new Insets(5, 5, 5, 5));

		scene4 = new Scene(layout4);
		brailleWindow.setScene(scene4);
		brailleWindow.setTitle("Error");
		brailleEntry = new Text(
				"The braille field can not be empty or contain a word longer than the number of braille cells available");
		brailleEntry.setFill(Color.WHITE);
		layout4.add(brailleEntry, 0, 0, 2, 1);
		brailleOkay = new Button("Okay");
		brailleOkay.setStyle("-fx-base: #87ceeb;"); // sky blue
		brailleOkay.setAccessibleRoleDescription("Okay button");
		brailleOkay.setAccessibleText(
				"The braille field can not be empty or contain a word longer than the number of braille cells available, press enter to return to main window");
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

	private void notANumberWindowSetup() {
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

	private void soundGUISetup() {
		/*
		 * Temp sound GUI
		 * 
		 * 
		 * 
		 * 
		 */

		soundWindow = new Stage();
		soundWindow.setTitle("Sound Menu");
		layout2 = new GridPane();
		layout2.setHgap(10);
		layout2.setVgap(10);
		layout2.setPadding(new Insets(0, 5, 5, 5));
		layout2.setBackground(
				new Background(new BackgroundFill(Color.gray(0.5, 0.8), CornerRadii.EMPTY, Insets.EMPTY)));

		scene2 = new Scene(layout2);
		soundWindow.setScene(scene2);
		soundMessage = new Text("Sorry, the sound option is currently\n" + "not available for this version");
		soundMessage.setFill(Color.WHITE);
		layout2.add(soundMessage, 0, 0, 2, 1);
		soundOkay = new Button("Okay");
		soundOkay.setStyle("-fx-base: #87ceeb;"); // sky blue
		soundOkay.setAccessibleRoleDescription("okay button");
		soundOkay.setAccessibleText(
				"Sorry, the sound option is currently not available for this version, press enter to go back to main window");
		layout2.add(soundOkay, 2, 1);

		// sound button events
		sound.setOnMouseClicked(e -> {
			soundWindow.show();

		});
		soundOkay.setOnMouseClicked(e -> {
			soundWindow.close();
		});

		sound.setOnKeyPressed(e -> {
			if (e.getCode() == KeyCode.ENTER) {
				soundWindow.show();
			} else {
				if (e.getCode() == KeyCode.CONTROL) {
					sound.setOnKeyPressed(e1 -> {
						if (e1.getCode() == KeyCode.TAB) {
							correctText.requestFocus();
						}
					});
				}
			}
		});
		soundOkay.setOnKeyPressed(e -> {
			if (e.getCode() == KeyCode.ENTER) {
				soundWindow.close();
			}
		});
	}

	private void setupScenarioGUI(Stage primaryStage) {
		/*
		 * Naming scenario GUI
		 * 
		 * 
		 * enter scenario name number of braille cells available number of answer
		 * buttons available
		 * 
		 * 
		 */

		brailleCellsUsedWindow = new Stage();
		layout11 = new GridPane();
		layout11.setHgap(10);
		layout11.setVgap(10);
		layout11.setPadding(new Insets(5, 5, 10, 5));

		scene11 = new Scene(layout11);
		brailleCellsUsedWindow.setScene(scene11);
		brailleCellsUsedWindow.setTitle("Scenario Setup");
		scenarioNameField = new TextField();
		scenarioNameText = new Text("Scenario Name");
		nameBrailleAnswer = new Text(
				"Enter the name of your scenario, the number of Braille Cells " + "and Answer Buttons available");
		layout11.add(nameBrailleAnswer, 0, 0, 2, 1);
		brailleCellsField = new TextField();
		brailleCellsText = new Text("Braille Cells Available");
		answerButtonsField = new TextField();

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

		okayStart = new Button("Okay");
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

	private void nameOrBrailleFieldEmptyWindow() {
		/*
		 * Error GUI - for brailleCellsUsedWindow
		 * 
		 * 
		 * errors: no scenario name braille field empty answer field empty
		 * 
		 * 
		 */
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
				"You need to have at a scenario name, at least one braille cell and one answer button to create a scenario,"
						+ "press enter to return to previous window");
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

	private void comboBoxOpenWithEnter() {
		// combo box open with enter
		comboBox.setOnKeyPressed(e -> {
			if (e.getCode() == KeyCode.ENTER) {
				comboBox.show();
			}
		});
	}

	private void createSaveButton() {
		// save section button
		testScenario = new Button("Test Scenario");
		testScenario.setAccessibleRoleDescription("Test Scenario");
		testScenario.setAccessibleText("Press enter to test current scenario");
		testScenario.setStyle("-fx-base: #FFFFFF;"); // sky blue
		layout.add(testScenario, 7, 1);
	}

	private void createSectionComboBox() {
		// ComboBox (drop down menu)
		comboBoxList = FXCollections.observableArrayList();
		comboBox = new ComboBox<String>(comboBoxList);
		comboBox.setPrefWidth(200);
		comboBox.setEditable(true);
		comboBox.setPromptText("Select a section");
		comboBoxList.add(0, "New Section");
		layout.add(comboBox, 9, 0, 5, 1);
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
		// border glow to make things look fancy
		borderGlowSetup();
		sectionNameAndButtonsUsed();
		storyTextSetup();
		brailleInputSetup();
		answerSetup();
		soundButtonSetup();
		clearSectionSetup();
		correctIncorrectSetup();
		blankAndSaveSectionSetup();
		createSectionComboBox();
		createSaveButton();

		/*
		 * 
		 * 
		 *
		 ************ Other Scenes and Action Events *********************
		 * 
		 * 
		 * 
		 * 
		 */

		comboBoxOpenWithEnter();
		nameOrBrailleFieldEmptyWindow();
		setupScenarioGUI(primaryStage);
		soundGUISetup();

		// Scene
		primaryStage.setTitle("Welcome");
		primaryStage.setScene(scene1);
		scene1.setFill(Color.TRANSPARENT);
		primaryStage.show();
		layout1.setBackground(
				new Background(new BackgroundFill(Color.gray(0.05, 0.6), CornerRadii.EMPTY, Insets.EMPTY)));

		// ****************** warnings ********************************************

		/*
		 * answer field error GUI
		 * 
		 * 
		 * answer field does not contain a number or has a larger number than the number
		 * of buttons used
		 * 
		 * 
		 */

		notANumberWindowSetup();
		brailleFieldErrorGUI();
		sectionEmptyErrorGUI();
		buttonsUsedErrorGUI();
		storyEmptyErrorGUI();
		noSectionErrorGUI();

		/////////////////////////////////////////////////////////////////////////////////////////

		savingScenarioGUI();
		newProjectWarningGUI();

		// ************************* other GUIs *********************

		visualOrAudioPlayerGUI();

		/*
		 * save section button
		 * 
		 * exceptions\: name field is empty story field is empty answer field contains a
		 * number between 1 and number of answer buttons used braille field is empty or
		 * contains more letters than the number of braille cells available
		 * 
		 * 
		 */

		saveButton.setOnMouseClicked(e -> {

			saveSection(nameSectionField, answerButtonsUsedField, storyText, brailleText, answerText, correctText,
					incorrectText, comboBoxList, comboBox, brailleCellsField, answerButtonsField, notANumberWindow,
					brailleWindow, emptyNameWindow, buttonsUsedWindow, emptyStoryWindow, saveWindow);

		});

		// hot key save section button
		saveButton.getScene().getAccelerators().put(new KeyCodeCombination(KeyCode.S, KeyCombination.CONTROL_DOWN),
				new Runnable() {
					@Override
					public void run() {
						saveSection(nameSectionField, answerButtonsUsedField, storyText, brailleText, answerText,
								correctText, incorrectText, comboBoxList, comboBox, brailleCellsField,
								answerButtonsField, notANumberWindow, brailleWindow, emptyNameWindow, buttonsUsedWindow,
								emptyStoryWindow, saveWindow);
					}
				});

		saveButton.setOnKeyPressed(e -> {

			if (e.getCode() == KeyCode.CONTROL) {
				saveButton.setOnKeyPressed(e1 -> {
					if (e1.getCode() == KeyCode.TAB) {
						comboBox.requestFocus();
					}
				});
			} else {
				if (e.getCode() == KeyCode.ENTER) {

					saveSection(nameSectionField, answerButtonsUsedField, storyText, brailleText, answerText,
							correctText, incorrectText, comboBoxList, comboBox, brailleCellsField, answerButtonsField,
							notANumberWindow, brailleWindow, emptyNameWindow, buttonsUsedWindow, emptyStoryWindow,
							saveWindow);
				}
			}
		});

		// return selected comboBox value
		comboBox.getSelectionModel().selectedIndexProperty().addListener(e -> {

			if (comboBox.getValue() == "New Section") {

				nameSectionField.clear();
				storyText.clear();
				correctText.clear();
				incorrectText.clear();
				brailleText.clear();
				answerText.clear();
				answerButtonsUsedField.clear();

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
			scenarioCreator.close();
			warningWindow.close();
			brailleCellsUsedWindow.show();
			scenarioNameField.clear();
			brailleCellsField.clear();
			answerButtonsField.clear();
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

	// ***************** methods ******************

	/*
	 * save section
	 * 
	 * sorry this code looks kinda ugly :(
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
					}
					if (storyText.getText().length() == 0) {
						emptyStoryWindow.show();
					}

				}
			}

		}
	}

}