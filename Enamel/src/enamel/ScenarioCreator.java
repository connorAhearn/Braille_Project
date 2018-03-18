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

public class ScenarioCreator extends Application {

	Printer printer;
	ArrayList<Block> blockList = new ArrayList<>();
	HashMap<String, Block> blockMap = new HashMap<String, Block>();

	@Override
	public void start(Stage primaryStage) throws Exception {

		/*
		 * GUI for start Window / primary stage
		 *
		 *
		 * Adding components to GUI (comp, col, row, col span, row span)
		 *
		 *
		 */

		GridPane layout1 = new GridPane();
		layout1.setHgap(10);
		layout1.setVgap(10);
		layout1.setPadding(new Insets(5, 5, 10, 5));
		Scene scene1 = new Scene(layout1, 550, 200);

		Text startWindowText = new Text("                       Welcome to Scenario Creator");
		startWindowText.setFont(Font.font("Arial", FontWeight.BOLD, 20));
		startWindowText.setFill(Color.WHITE);
		layout1.add(startWindowText, 0, 2, 3, 1);
		Button createButton = new Button("Create New Scenario");
		createButton.setMinSize(150, 60);
		createButton.setStyle("-fx-base: #87ceeb;"); // sky blue
		createButton.setAccessibleRoleDescription("Create new scenario button");
		createButton.setAccessibleText("Welcome to scenario creator, To create a new scenario press enter");
		layout1.add(createButton, 0, 6);
		Button testButton = new Button("Test Scenario");
		testButton.setMinSize(150, 60);
		testButton.setStyle("-fx-base: #87ceeb;"); // sky blue
		testButton.setAccessibleRoleDescription("Test Scenario button");
		testButton.setAccessibleText("To test a scenario press enter");
		layout1.add(testButton, 3, 6);

		// GUI for scenario Creator
		Stage scenarioCreator = new Stage();
		GridPane layout = new GridPane();
		layout.setHgap(10);
		layout.setVgap(5);
		layout.setPadding(new Insets(0, 5, 5, 5));

		Scene scene = new Scene(layout, 1000, 655);
		scenarioCreator.setScene(scene);
		scenarioCreator.setTitle("Scenario Creator");
		scene.setFill(Color.TRANSPARENT);
		layout.setBackground(
				new Background(new BackgroundFill(Color.gray(0.05, 0.8), CornerRadii.EMPTY, Insets.EMPTY)));

		
		/*_________ Scenario menu
		 * 
		 * 
		 * create new scenario
		 * load scenario
		 * save scenario
		 * 
		 * 
		 */
		
		Menu scenarioMenu = new Menu("Scenario");

		MenuItem newProject = new MenuItem("New Project");
		MenuItem loadProject = new MenuItem("Load Project");
		MenuItem saveProject = new MenuItem("Save Project");
		MenuItem testProject = new MenuItem("Test Project");


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
		
		/* Go to 
		 * 
		 * 
		 * Goes to text field or area
		 * 
		 * 
		 */
		Menu goToMenu = new Menu("Go To");

		MenuItem goToSectionName = new MenuItem("Section Name");
		MenuItem goToAnswerButtonsUsed = new MenuItem("Answer Buttons Used");
		MenuItem goToStory = new MenuItem("Story");
		MenuItem goToBraille = new MenuItem("Braille");
		MenuItem goToAnswer = new MenuItem("Answer");
		MenuItem goToCorrect = new MenuItem("Correct");
		MenuItem goToIncorrect = new MenuItem("Incorrect");
		MenuItem goToComboBox = new MenuItem("Drop Down");

		goToMenu.getItems().add(goToSectionName);
		goToMenu.getItems().add(goToAnswerButtonsUsed);
		goToMenu.getItems().add(goToStory);
		goToMenu.getItems().add(goToBraille);
		goToMenu.getItems().add(goToAnswer);
		goToMenu.getItems().add(goToCorrect);
		goToMenu.getItems().add(goToIncorrect);
		goToMenu.getItems().add(goToComboBox);
		
		
		/* Sound menu
		 * 
		 * 
		 * add sound
		 * 
		 * 
		 */
		Menu soundMenu = new Menu("Sound");

		MenuItem addSound = new MenuItem("Add Sound");

		soundMenu.getItems().add(addSound);

		/* menu bar
		 * 
		 * Scenario
		 * Section
		 * Go to
		 * 
		 * 
		 */
		
		MenuBar menuBar = new MenuBar();
		menuBar.getMenus().addAll(scenarioMenu, sectionMenu, goToMenu, soundMenu);
		menuBar.setOpacity(0.7);
		layout.add(menuBar, 0, 0, 8, 1);

		// border glow to make things look fancy
		int depth = 40;
		DropShadow borderGlow = new DropShadow();
		borderGlow.setColor(Color.LIGHTBLUE);
		borderGlow.setWidth(depth);
		borderGlow.setHeight(depth);
		borderGlow.setOffsetX(0f);
		borderGlow.setOffsetY(0f);

		// Block title
		TextField nameSectionField = new TextField();
		nameSectionField.setPrefWidth(30);
		layout.add(nameSectionField, 0, 1);

		Text sectionName = new Text("Section Name");
		sectionName.setFont(Font.font("Arial", FontWeight.BOLD, 11.5));
		sectionName.setFill(Color.GHOSTWHITE);
		layout.add(sectionName, 1, 1, 2, 1);

		// accessibility for braille text field
		Label nameSectionLabel = new Label("Enter the name\n for this section \n of your story");
		nameSectionLabel.setLabelFor(nameSectionField);
		nameSectionLabel.setVisible(false);
		layout.add(nameSectionLabel, 0, 1);

		// number of answer buttons used
		TextField answerButtonsUsedField = new TextField();
		answerButtonsUsedField.setPrefWidth(30);
		layout.add(answerButtonsUsedField, 3, 1);

		Text answerButtonsUsedText = new Text("Answer Buttons Used");
		answerButtonsUsedText.setFont(Font.font("Arial", FontWeight.BOLD, 11.5));
		answerButtonsUsedText.setFill(Color.GHOSTWHITE);
		layout.add(answerButtonsUsedText, 4, 1);

		// accessibility for answer buttons used field
		Label answerButtonsUsedFieldLabel = new Label("Enter the number\n of answer buttons \n you want to use");
		answerButtonsUsedFieldLabel.setLabelFor(answerButtonsUsedField);
		answerButtonsUsedFieldLabel.setVisible(false);
		layout.add(answerButtonsUsedFieldLabel, 3, 1);

		// Story text area
		Text story = new Text(" Story");
		story.setFont(Font.font("Arial", FontWeight.BOLD, 13));
		story.setFill(Color.WHITE);
		layout.add(story, 0, 2, 6, 1);

		TextArea storyText = new TextArea();
		storyText.setPrefHeight(250);
		storyText.setPrefWidth(500);
		storyText.setOpacity(0.9);
		storyText.setWrapText(true);
		storyText.setEffect(borderGlow);
		layout.add(storyText, 0, 3, 8, 4);

		storyText.setAccessibleRoleDescription("this be the story text area");
		Label storyLabel = new Label("Enter your story here");
		storyLabel.setLabelFor(storyText);
		storyLabel.setVisible(false);
		layout.add(storyLabel, 0, 3, 8, 4);

		// Braille text field
		Text braille = new Text("Braille");
		braille.setFont(Font.font("Arial", FontWeight.BOLD, 11.5));
		braille.setFill(Color.GHOSTWHITE);
		layout.add(braille, 2, 7);

		TextField brailleText = new TextField();
		brailleText.setPrefWidth(40);
		layout.add(brailleText, 0, 7, 2, 1);

		// accessibility for braille text field
		Label brailleLabel = new Label("Enter the word \n you want displayed \n on the braille cell");
		brailleLabel.setLabelFor(brailleText);
		brailleLabel.setVisible(false);
		layout.add(brailleLabel, 2, 7);

		// answer text field
		Text answer = new Text("Answer");
		answer.setFont(Font.font("Arial", FontWeight.BOLD, 11.5));
		answer.setFill(Color.WHITE);
		layout.add(answer, 4, 7);

		TextField answerText = new TextField();
		answerText.setPrefWidth(50);
		layout.add(answerText, 3, 7);

		// accessibility for answer field
		Label answerLabel = new Label("Enter the button\nnumber users should\npress for the\ncorrect response");
		answerLabel.setLabelFor(answerText);
		answerLabel.setVisible(false);
		layout.add(answerLabel, 3, 7);

		// sound button
		Button sound = new Button("  Add Sound   ");
		sound.setAccessibleRoleDescription("Sound button");
		sound.setAccessibleText("Sound option is currently not available in this version");
		sound.setStyle("-fx-base: #87ceeb;"); // sky blue
		layout.add(sound, 7, 7);

		
		// Clear Section button
		Button clearSectionButton = new Button("Clear Section");
		clearSectionButton.setAccessibleRoleDescription("Clear button");
		clearSectionButton.setAccessibleText("Press enter to clear all fields");
		clearSectionButton.setStyle("-fx-base: #87ceeb;"); // sky blue
		layout.add(clearSectionButton, 7, 19);
		
		// Correct text area
		Text correct = new Text(" Correct");
		correct.setFont(Font.font("Arial", FontWeight.BOLD, 13));
		correct.setFill(Color.WHITE);
		layout.add(correct, 0, 8);

		TextArea correctText = new TextArea();
		correctText.setPrefHeight(100);
		correctText.setPrefWidth(500);
		correctText.setOpacity(0.95);
		correctText.setWrapText(true);
		correctText.setEffect(borderGlow);
		layout.add(correctText, 0, 9, 8, 4);

		// accessibility for correct text area
		Label correctLabel = new Label("Enter what happens \n when the right \n answer is triggered");
		correctLabel.setLabelFor(correctText);
		correctLabel.setVisible(false);
		layout.add(correctLabel, 0, 9, 8, 4);

		// Incorrect text area
		Text incorrect = new Text(" Incorrect");
		incorrect.setFont(Font.font("Arial", FontWeight.BOLD, 13));
		incorrect.setFill(Color.WHITE);
		layout.add(incorrect, 0, 14);

		TextArea incorrectText = new TextArea();
		incorrectText.setPrefHeight(100);
		incorrectText.setPrefWidth(500);
		incorrectText.setOpacity(0.95);
		incorrectText.setWrapText(true);
		incorrectText.setEffect(borderGlow);
		layout.add(incorrectText, 0, 15, 8, 3);

		// accessibility for incorrect text area
		Label incorrectLabel = new Label("Enter what happens \n when the wrong\n answer is triggered");
		incorrectLabel.setLabelFor(incorrectText);
		incorrectLabel.setVisible(false);
		layout.add(incorrectLabel, 0, 8, 8, 4);

		// blank text field for spacing
		Text blank1 = new Text("                                         ");
		blank1.setFont(Font.font("Arial", FontWeight.SEMI_BOLD, 10));
		layout.add(blank1, 6, 7);

		// save section button
		Button saveButton = new Button("Save Section");
		saveButton.setAccessibleRoleDescription("Save button");
		saveButton.setAccessibleText("Press enter to save section");
		saveButton.setStyle("-fx-base: #87ceeb;"); // sky blue
		layout.add(saveButton, 0, 19);

		// ComboBox (drop down menu)
		ObservableList<String> comboBoxList = FXCollections.observableArrayList();
		ComboBox<String> comboBox = new ComboBox<String>(comboBoxList);
		comboBox.setPrefWidth(200);
		comboBox.setEditable(true);
		comboBox.setPromptText("Select a section");
		comboBoxList.add(0, "New Section");
		layout.add(comboBox, 9, 0, 5, 1);
		
		// save section button
		Button testScenario = new Button("Test Scenario");
		testScenario.setAccessibleRoleDescription("Test Scenario");
		testScenario.setAccessibleText("Press enter to test current scenario");
		testScenario.setStyle("-fx-base: #FFFFFF;"); // sky blue
		layout.add(testScenario, 7, 1);

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

		// combo box open with enter
		comboBox.setOnKeyPressed(e -> {
			if (e.getCode() == KeyCode.ENTER) {
				comboBox.show();
			}
		});

		/*
		 * Error GUI - for brailleCellsUsedWindow
		 * 
		 * 
		 * errors: no scenario name braille field empty answer field empty
		 * 
		 * 
		 */

		Stage errorWindow = new Stage();
		GridPane layout12 = new GridPane();
		layout12.setHgap(10);
		layout12.setVgap(10);
		layout12.setPadding(new Insets(5, 5, 5, 5));
		layout12.setBackground(
				new Background(new BackgroundFill(Color.gray(0.4, 0.8), CornerRadii.EMPTY, Insets.EMPTY)));

		Scene scene12 = new Scene(layout12);
		errorWindow.setScene(scene12);
		errorWindow.setTitle("Error");
		Text errorMessage = new Text(
				"You need to have a scenario name, at least one braille cell and one answer button to start creating a scenario");
		errorMessage.setFill(Color.WHITE);
		layout12.add(errorMessage, 0, 0, 2, 1);
		Button errorMessageButton = new Button("Okay");
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

		/*
		 * Naming scenario GUI
		 * 
		 * 
		 * enter scenario name number of braille cells available number of answer
		 * buttons available
		 * 
		 * 
		 */

		Stage brailleCellsUsedWindow = new Stage();
		GridPane layout11 = new GridPane();
		layout11.setHgap(10);
		layout11.setVgap(10);
		layout11.setPadding(new Insets(5, 5, 10, 5));

		Scene scene11 = new Scene(layout11);
		brailleCellsUsedWindow.setScene(scene11);
		brailleCellsUsedWindow.setTitle("Scenario Setup");
		TextField scenarioNameField = new TextField();
		Text scenarioNameText = new Text("Scenario Name");
		Text nameBrailleAnswer = new Text(
				"Enter the name of your scenario, the number of Braille Cells " + "and Answer Buttons available");
		layout11.add(nameBrailleAnswer, 0, 0, 2, 1);
		TextField brailleCellsField = new TextField();
		Text brailleCellsText = new Text("Braille Cells Available");
		TextField answerButtonsField = new TextField();

		Text answerButtonsText = new Text("Answer Buttons Available");
		layout11.add(scenarioNameField, 0, 1);
		layout11.add(scenarioNameText, 1, 1);
		layout11.add(brailleCellsField, 0, 2);
		layout11.add(brailleCellsText, 1, 2);
		layout11.add(answerButtonsField, 0, 3);
		layout11.add(answerButtonsText, 1, 3);
		layout11.setBackground(
				new Background(new BackgroundFill(Color.gray(0.5, 0.8), CornerRadii.EMPTY, Insets.EMPTY)));

		Label scenarioNameFieldLabel = new Label("Enter the \nname of \nyour scenario");
		scenarioNameFieldLabel.setLabelFor(scenarioNameField);
		scenarioNameFieldLabel.setVisible(false);
		layout11.add(scenarioNameFieldLabel, 0, 1);

		Label brailleCellsUsedLabel = new Label("Enter the \nnumber of \nBraille cells available");
		brailleCellsUsedLabel.setLabelFor(brailleCellsField);
		brailleCellsUsedLabel.setVisible(false);
		layout11.add(brailleCellsUsedLabel, 0, 2);

		Label answerButtonsUsedLabel = new Label("Enter the \nnumber of \nanswer buttons available");
		answerButtonsUsedLabel.setLabelFor(answerButtonsField);
		answerButtonsUsedLabel.setVisible(false);
		layout11.add(answerButtonsUsedLabel, 0, 3);

		Button okayStart = new Button("Okay");
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

		/*
		 * Temp sound GUI
		 * 
		 * 
		 * 
		 * 
		 */

		Stage soundWindow = new Stage();
		soundWindow.setTitle("Sound Menu");
		GridPane layout2 = new GridPane();
		layout2.setHgap(10);
		layout2.setVgap(10);
		layout2.setPadding(new Insets(0, 5, 5, 5));
		layout2.setBackground(
				new Background(new BackgroundFill(Color.gray(0.5, 0.8), CornerRadii.EMPTY, Insets.EMPTY)));

		Scene scene2 = new Scene(layout2);
		soundWindow.setScene(scene2);
		Text soundMessage = new Text("Sorry, the sound option is currently\n" + "not available for this version");
		soundMessage.setFill(Color.WHITE);
		layout2.add(soundMessage, 0, 0, 2, 1);
		Button soundOkay = new Button("Okay");
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

		Stage notANumberWindow = new Stage();
		GridPane layout3 = new GridPane();
		layout3.setHgap(10);
		layout3.setVgap(10);
		layout3.setPadding(new Insets(5, 5, 5, 5));
		layout3.setBackground(
				new Background(new BackgroundFill(Color.gray(0.3, 0.8), CornerRadii.EMPTY, Insets.EMPTY)));

		Scene scene3 = new Scene(layout3);
		notANumberWindow.setScene(scene3);
		notANumberWindow.setTitle("Error");
		Text answerIsNumber = new Text("The answer field and answer buttons used field needs to contain a number");
		answerIsNumber.setFill(Color.WHITE);
		layout3.add(answerIsNumber, 0, 0, 2, 1);
		Button answerOkay = new Button("Okay");
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

		/*
		 * Braille field error GUI
		 * 
		 * 
		 * braille field contains a word longer than the number of braille cells
		 * available
		 * 
		 * 
		 */

		Stage brailleWindow = new Stage();
		GridPane layout4 = new GridPane();
		layout4.setHgap(10);
		layout4.setVgap(10);
		layout4.setPadding(new Insets(5, 5, 5, 5));

		Scene scene4 = new Scene(layout4);
		brailleWindow.setScene(scene4);
		brailleWindow.setTitle("Error");
		Text brailleEntry = new Text(
				"The braille field can not be empty or contain a word longer than the number of braille cells available");
		brailleEntry.setFill(Color.WHITE);
		layout4.add(brailleEntry, 0, 0, 2, 1);
		Button brailleOkay = new Button("Okay");
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

		/*
		 * section name empty GUI
		 * 
		 * 
		 * 
		 */

		Stage emptyNameWindow = new Stage();
		GridPane layout6 = new GridPane();
		layout6.setHgap(10);
		layout6.setVgap(10);
		layout6.setPadding(new Insets(0, 5, 5, 5));

		Scene scene6 = new Scene(layout6);
		emptyNameWindow.setScene(scene6);
		emptyNameWindow.setTitle("Name is empty");
		Text emptyName = new Text("Section can not be saved unless it has a name");
		emptyName.setFill(Color.WHITE);
		layout6.add(emptyName, 0, 0);
		Button emptyNameButton = new Button("Okay");
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

		/*
		 * buttons used error Window
		 * 
		 * 
		 * 
		 */
		Stage buttonsUsedWindow = new Stage();
		GridPane layout8 = new GridPane();
		layout8.setHgap(10);
		layout8.setVgap(10);
		layout8.setPadding(new Insets(0, 5, 5, 5));

		Scene scene8 = new Scene(layout8);
		buttonsUsedWindow.setScene(scene8);
		buttonsUsedWindow.setTitle("Warning");
		Text buttonsUsedError = new Text("The answer buttons used field and answer field can not have a higher number"
				+ "than the number of buttons available");
		buttonsUsedError.setFill(Color.WHITE);
		layout8.add(buttonsUsedError, 0, 0);
		layout8.setBackground(
				new Background(new BackgroundFill(Color.gray(0.3, 0.8), CornerRadii.EMPTY, Insets.EMPTY)));

		Button buttonsUsedWindowOkay = new Button("Okay");
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

		/*
		 * story field empty error GUI
		 * 
		 * 
		 * 
		 * 
		 */

		Stage emptyStoryWindow = new Stage();
		GridPane layout5 = new GridPane();
		layout5.setHgap(10);
		layout5.setVgap(10);
		layout5.setPadding(new Insets(5, 5, 5, 5));

		Scene scene5 = new Scene(layout5);
		emptyStoryWindow.setScene(scene5);
		emptyStoryWindow.setTitle("Story field is empty");
		Text emptyStoryText = new Text("Section can not be saved\nif the story field is empty");
		emptyStoryText.setFill(Color.WHITE);
		layout5.add(emptyStoryText, 0, 0, 2, 1);
		Button emptyStoryOkay = new Button("Okay");
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

		/*
		 * warning window : scenario can not be saved without one completed section
		 * 
		 * 
		 * 
		 * 
		 */

		Stage noSectionsSavedWindow = new Stage();
		GridPane layout14 = new GridPane();
		layout14.setHgap(10);
		layout14.setVgap(10);
		layout14.setPadding(new Insets(5, 5, 5, 5));

		Scene scene14 = new Scene(layout14);
		noSectionsSavedWindow.setScene(scene14);
		noSectionsSavedWindow.setTitle("Error");
		Text noSectionsSaved = new Text(
				"Scenario can only be saved after you have created and saved at least one section");
		noSectionsSaved.setFill(Color.WHITE);
		layout14.add(noSectionsSaved, 0, 0, 2, 1);
		Button noSectionSavedOkay = new Button("Okay");
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

		/////////////////////////////////////////////////////////////////////////////////////////

		/*
		 * confirm save GUI
		 * 
		 * 
		 * 
		 */

		Stage saveWindow = new Stage();
		GridPane layout7 = new GridPane();
		layout7.setHgap(10);
		layout7.setVgap(10);
		layout7.setPadding(new Insets(0, 5, 5, 5));

		Scene scene7 = new Scene(layout7);
		saveWindow.setScene(scene7);
		saveWindow.setTitle("Saved");
		Text saveConfirmed = new Text("This section has been saved");
		saveConfirmed.setFill(Color.WHITE);
		layout7.add(saveConfirmed, 0, 0);
		Button saveOkayButton = new Button("Okay");
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

		Stage scenarioSavedWindow = new Stage();
		GridPane layout13 = new GridPane();
		layout13.setHgap(10);
		layout13.setVgap(10);
		layout13.setPadding(new Insets(0, 5, 5, 5));

		Scene scene13 = new Scene(layout13);
		scenarioSavedWindow.setScene(scene13);
		scenarioSavedWindow.setTitle("Saved");
		Text projectSavedConfirmed = new Text("This scenario has been saved");
		projectSavedConfirmed.setFill(Color.WHITE);
		layout13.add(projectSavedConfirmed, 0, 0);
		Button scenarioSavedOkay = new Button("Okay");
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

		/*
		 * new project warning window GUI
		 * 
		 * 
		 * warning that every field will be cleared
		 * 
		 * 
		 */

		Stage warningWindow = new Stage();
		GridPane layout10 = new GridPane();
		layout10.setHgap(10);
		layout10.setVgap(10);
		layout10.setPadding(new Insets(5, 5, 5, 5));

		Scene scene10 = new Scene(layout10);
		warningWindow.setScene(scene10);
		warningWindow.setTitle("Warning");
		Text warningText = new Text(
				"	       Are you sure you want to start a new project?\n			any unsaved projects will be lost");
		warningText.setFill(Color.WHITE);
		layout10.add(warningText, 0, 0, 2, 2);
		Button warningOkay = new Button("Okay");
		warningOkay.setStyle("-fx-base: #87ceeb;"); // sky blue
		warningOkay.setAccessibleRoleDescription("Okay button");
		warningOkay.setAccessibleText(
				"Are you sure you want to start a new project? any unsaved projects will be lost, press enter to continue");
		layout10.add(warningOkay, 2, 4);
		Button warningCancel = new Button("Cancel");
		warningCancel.setStyle("-fx-base: #87ceeb;"); // sky blue
		warningCancel.setAccessibleRoleDescription("Cancel button");
		warningCancel.setAccessibleText("Press enter to return to main window");
		layout10.add(warningCancel, 0, 4);
		layout10.setBackground(
				new Background(new BackgroundFill(Color.gray(0.3, 0.8), CornerRadii.EMPTY, Insets.EMPTY)));

		// ************************* other GUIs *********************

		/*
		 * 
		 * 
		 * Pop up window : Choose between audio / visual player for testing
		 * 
		 * 
		 */

		Stage playerSelectionWindow = new Stage();
		GridPane layout9 = new GridPane();
		layout9.setHgap(10);
		layout9.setVgap(10);
		layout9.setPadding(new Insets(0, 5, 5, 5));
		layout9.setBackground(
				new Background(new BackgroundFill(Color.gray(0.6, 0.8), CornerRadii.EMPTY, Insets.EMPTY)));

		Scene scene9 = new Scene(layout9);
		playerSelectionWindow.setScene(scene9);
		playerSelectionWindow.setTitle("Player selection");
		Text playerSelectionText = new Text("       Would you like to test with\n     visual player or audio player?");
		layout9.add(playerSelectionText, 0, 1, 3, 1);

		Label playerLabel = new Label("Would you like to test with visual player or audio player?");
		playerLabel.setLabelFor(playerSelectionText);
		playerLabel.setVisible(false);
		layout9.add(playerLabel, 0, 1);

		final ToggleGroup group = new ToggleGroup();
		RadioButton visualButton = new RadioButton("Visual Player");
		visualButton.setToggleGroup(group);
		visualButton.setAccessibleText("Press enter to select visual player");
		RadioButton audioButton = new RadioButton("Audio Player");
		audioButton.setToggleGroup(group);
		audioButton.setAccessibleText("Press enter to select audio player");
		layout9.add(visualButton, 0, 2);
		layout9.add(audioButton, 1, 2);

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

									saveWindow.show();
									// get name of file from user input
									String blockName = nameSectionField.getText();

									if (blockMap.containsKey(blockName)) {
										blockList.get(blockList.indexOf(
												(blockMap.get(nameSectionField.getText())))).story = storyText
														.getText();
										blockList.get(blockList.indexOf((blockMap
												.get(nameSectionField.getText())))).correctResponse = correctText
														.getText();
										blockList.get(blockList.indexOf((blockMap
												.get(nameSectionField.getText())))).wrongResponse = incorrectText
														.getText();
										blockList.get(blockList.indexOf(
												(blockMap.get(nameSectionField.getText())))).letter = brailleText
														.getText().charAt(0);
										blockList.get(blockList
												.indexOf((blockMap.get(nameSectionField.getText())))).answer = Integer
														.parseInt(answerText.getText());
									} else {

										// save name to comboBox
										comboBoxList.add(blockName);
										comboBox.setItems(comboBoxList);

										// save text to block

										int buttonsUsed = 4;
										if (blockName.equals("") || storyText.equals("")
												|| Integer.parseInt(answerText.getText()) > buttonsUsed) {
											try {
												throw new InvalidBlockException();
											} catch (InvalidBlockException e2) {
												// TODO Auto-generated catch block
												e2.printStackTrace();
											}
										} else {
											Block blockText;
											try {
												blockText = new Block(blockName, storyText.getText(),
														correctText.getText(), incorrectText.getText(),
														Integer.parseInt(answerText.getText()),
														brailleText.getText().charAt(0));
												blockList.add(blockText);
												blockMap.put(blockName, blockText);
											} catch (NumberFormatException e2) {
												// TODO Auto-generated catch block
												e2.printStackTrace();
											} catch (InvalidBlockException e2) {
												// TODO Auto-generated catch block
												e2.printStackTrace();
											}

										}

									}
								}
							} else {
								emptyStoryWindow.show();
							}
						} else {
							brailleWindow.show();
						}
					} else {
						brailleWindow.show();
					}
				});

		saveButton.setOnKeyPressed(e -> {

			if (e.getCode() == KeyCode.ENTER) {

				try {
					int x = Integer.parseInt(answerText.getText());
					if (x >= 1 && x <= 4) {
						if (brailleText.getText().length() == 1) {
							if (brailleText.getText().matches("[A-z]"))
								if (storyText.getText().length() != 0) {
									if (nameSectionField.getText().length() == 0) {
										emptyNameWindow.show();
									} else {

										saveWindow.show();
										// get name of file from user input
										String blockName = nameSectionField.getText();

										if (blockMap.containsKey(blockName)) {
											blockList.get(blockList.indexOf(
													(blockMap.get(nameSectionField.getText())))).story = storyText
															.getText();
											blockList.get(blockList.indexOf((blockMap
													.get(nameSectionField.getText())))).correctResponse = correctText
															.getText();
											blockList.get(blockList.indexOf((blockMap
													.get(nameSectionField.getText())))).wrongResponse = incorrectText
															.getText();
											blockList.get(blockList.indexOf(
													(blockMap.get(nameSectionField.getText())))).letter = brailleText
															.getText().charAt(0);
											blockList.get(blockList.indexOf(
													(blockMap.get(nameSectionField.getText())))).answer = Integer
															.parseInt(answerText.getText());
										} else {

											// save name to comboBox
											comboBoxList.add(blockName);
											comboBox.setItems(comboBoxList);

											// save text to block

											int buttonsUsed = 4;
											if (blockName.equals("") || storyText.equals("")
													|| Integer.parseInt(answerText.getText()) > buttonsUsed) {
												try {
													throw new InvalidBlockException();
												} catch (InvalidBlockException e2) {
													// TODO Auto-generated catch block
													e2.printStackTrace();
												}
											} else {
												Block blockText;
												try {
													blockText = new Block(blockName, storyText.getText(),
															correctText.getText(), incorrectText.getText(),
															Integer.parseInt(answerText.getText()),
															brailleText.getText().charAt(0));
													blockList.add(blockText);
													blockMap.put(blockName, blockText);
												} catch (NumberFormatException e2) {
													// TODO Auto-generated catch block
													e2.printStackTrace();
												} catch (InvalidBlockException e2) {
													// TODO Auto-generated catch block
													e2.printStackTrace();
												}

											}

											// send blocklist to printer
											try {
												printer = new Printer(blockName + ".txt");
												try {
													printer.addBlockList(blockList);
												} catch (OddSpecialCharacterException e3) {
													// TODO Auto-generated catch block
													e3.printStackTrace();
												} catch (InvalidBlockException e3) {
													// TODO Auto-generated catch block
													e3.printStackTrace();
												}
												printer.print();
											} catch (IOException e1) {
												e1.printStackTrace();
											} catch (OddSpecialCharacterException e4) {
												// TODO Auto-generated catch block
												e4.printStackTrace();
											} catch (InvalidBlockException e4) {
												// TODO Auto-generated catch block
												e4.printStackTrace();
											}
										}
									}
								} else {
									emptyStoryWindow.show();
								}
							else {
								brailleWindow.show();
							}
						} else {
							brailleWindow.show();
						}

					} else {
						notANumberWindow.show();
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
	}			if (blockList.size() == 0) {
				noSectionsSavedWindow.show();
		// hot key save project
		saveProject.setAccelerator(
				new KeyCodeCombination(KeyCode.S, KeyCodeCombination.CONTROL_DOWN, KeyCodeCombination.ALT_DOWN));
		loadProject.setOnAction(e -> {
			runTest(primaryStage, playerSelectionWindow, visualButton, audioButton);
				runTest(primaryStage, playerSelectionWindow, visualButton, audioButton);
			}
		});

		// Set true to help see how nodes are aligned
		layout.setGridLinesVisible(false);
	}

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

	public static void main(String[] args) {

		// Inherited method from Application that lunches GUI
		launch(args);
	}

}
