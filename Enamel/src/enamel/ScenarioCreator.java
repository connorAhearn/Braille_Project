package enamel;

import java.io.IOException;
import java.util.ArrayList;
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
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.effect.DropShadow;
import javafx.scene.input.KeyCode;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.layout.CornerRadii;
import javafx.scene.layout.GridPane;
import javafx.scene.AccessibleRole;
import javafx.scene.Node;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;
import javafx.stage.Stage;

public class ScenarioCreator extends Application {

	Printer printer;
	ArrayList<Block> blockList = new ArrayList<>();

	@Override
	public void start(Stage primaryStage) throws Exception {

		// Adding components to GUI (component, column, row)
		GridPane layout = new GridPane();
		layout.setHgap(10);
		layout.setVgap(5);
		layout.setPadding(new Insets(0, 5, 5, 5));

		// File menu
		Menu fileMenu = new Menu("File");

		// Menu Items
		MenuItem newProject = new MenuItem("New Project");
		MenuItem loadProject = new MenuItem("Load Project");
		MenuItem saveProject = new MenuItem("Save Project");

		fileMenu.getItems().add(newProject);
		fileMenu.getItems().add(loadProject);
		fileMenu.getItems().add(saveProject);

		// Main menu bar
		MenuBar menuBar = new MenuBar();
		menuBar.getMenus().addAll(fileMenu);
		menuBar.setOpacity(0.7);
		layout.add(menuBar, 0, 0, 8, 1);

		// border glow
		int depth = 40;
		DropShadow borderGlow = new DropShadow();
		borderGlow.setColor(Color.LIGHTBLUE);
		borderGlow.setWidth(depth);
		borderGlow.setHeight(depth);
		borderGlow.setOffsetX(0f);
		borderGlow.setOffsetY(0f);

		// Story text area
		Text story = new Text(" Story");
		story.setFont(Font.font("Arial", FontWeight.BOLD, 13));
		story.setFill(Color.WHITE);
		layout.add(story, 0, 1, 6, 1);

		TextArea storyText = new TextArea();
		storyText.setPrefHeight(250);
		storyText.setPrefWidth(600);
		storyText.setOpacity(0.9);
		storyText.setWrapText(true);
		storyText.setEffect(borderGlow);
		layout.add(storyText, 0, 2, 8, 4);

		storyText.setAccessibleRoleDescription("this be the story text area");
		Label storyLabel = new Label("Enter your story here");
		storyLabel.setLabelFor(storyText);
		storyLabel.setVisible(false);
		layout.add(storyLabel, 0, 2, 8, 4);

		// Braille text field
		Text braille = new Text("Braille");
		braille.setFont(Font.font("Arial", FontWeight.BOLD, 11.5));
		braille.setFill(Color.GHOSTWHITE);
		layout.add(braille, 2, 6);

		TextField brailleText = new TextField();
		brailleText.setPrefWidth(40);
		layout.add(brailleText, 0, 6, 2, 1);

		// accessibility for braille text field
		Label brailleLabel = new Label("Enter the letter \n you want displayed \n on the braille cell");
		brailleLabel.setLabelFor(brailleText);
		brailleLabel.setVisible(false);
		layout.add(brailleLabel, 2, 6);

		// answer text field
		Text answer = new Text("Answer");
		answer.setFont(Font.font("Arial", FontWeight.BOLD, 11.5));
		answer.setFill(Color.WHITE);
		layout.add(answer, 5, 6);

		TextField answerText = new TextField();
		answerText.setPrefWidth(50);
		layout.add(answerText, 4, 6);

		// accessibility for answer field
		Label answerLabel = new Label("Enter the button\nnumber users should\npress for the\ncorrect response");
		answerLabel.setLabelFor(answerText);
		answerLabel.setVisible(false);
		layout.add(answerLabel, 5, 6);

		// sound button
		Button sound = new Button("Sound");
		sound.setAccessibleRoleDescription("Sound button");
		sound.setAccessibleText("Sound option is currently not available in this version");
		layout.add(sound, 7, 6);

		// Correct text area
		Text correct = new Text(" Correct");
		correct.setFont(Font.font("Arial", FontWeight.BOLD, 13));
		correct.setFill(Color.WHITE);
		layout.add(correct, 0, 7);

		TextArea correctText = new TextArea();
		correctText.setPrefHeight(100);
		correctText.setPrefWidth(600);
		correctText.setOpacity(0.95);
		correctText.setWrapText(true);
		correctText.setEffect(borderGlow);
		layout.add(correctText, 0, 8, 8, 4);

		// accessibility for correct text area
		Label correctLabel = new Label("Enter what happens \n when the right \n answer is triggered");
		correctLabel.setLabelFor(correctText);
		correctLabel.setVisible(false);
		layout.add(correctLabel, 0, 8, 8, 4);

		// Incorrect text area
		Text incorrect = new Text(" Incorrect");
		incorrect.setFont(Font.font("Arial", FontWeight.BOLD, 13));
		incorrect.setFill(Color.WHITE);
		layout.add(incorrect, 0, 13);

		TextArea incorrectText = new TextArea();
		incorrectText.setPrefHeight(100);
		incorrectText.setPrefWidth(600);
		incorrectText.setOpacity(0.95);
		incorrectText.setWrapText(true);
		incorrectText.setEffect(borderGlow);
		layout.add(incorrectText, 0, 14, 8, 3);

		// accessibility for incorrect text area
		Label incorrectLabel = new Label("Enter what happens \n when the wrong\n answer is triggered");
		incorrectLabel.setLabelFor(incorrectText);
		incorrectLabel.setVisible(false);
		layout.add(incorrectLabel, 0, 8, 8, 4);

		// blank text field for spacing
		Text blank1 = new Text(
				"                                  " + "                                               ");
		blank1.setFont(Font.font("Arial", FontWeight.SEMI_BOLD, 10));
		layout.add(blank1, 6, 6);

		// publish button
		Button publish = new Button("Publish");
		publish.setAccessibleRoleDescription("Publish button");
		publish.setAccessibleText("Press enter to save block");
		layout.add(publish, 0, 18);

		// ComboBox (drop down menu)
		ObservableList<String> comboBoxList = FXCollections.observableArrayList();
		ComboBox<String> comboBox = new ComboBox<String>(comboBoxList);
		comboBox.setPrefWidth(200);
		comboBox.setEditable(true);
		comboBox.setPromptText("Select a block");
		comboBoxList.add(0, "New Block");
		layout.add(comboBox, 9, 0, 5, 1);

		// return selected comboBox value
		comboBox.getSelectionModel().selectedIndexProperty().addListener(e -> {

			if (comboBox.getValue() == "New Block") {
				storyText.clear();
				correctText.clear();
				incorrectText.clear();
				brailleText.clear();
				answerText.clear();
			} else {
				for (int i = 0; i < blockList.size(); i++) {
					if (comboBox.getValue() == blockList.get(i).name) {
						storyText.setText(blockList.get(i).premise);
						correctText.setText(blockList.get(i).correctResponse);
						incorrectText.setText(blockList.get(i).wrongResponse);
						brailleText.setText(Character.toString(blockList.get(i).letter));
						answerText.setText(Integer.toString(blockList.get(i).answer));
					}
				}
			}
		});

		//////////////// GUIs and Action Events ////////////////////////////

		// GUI for Sound button
		Stage soundWindow = new Stage();
		soundWindow.setTitle("Sound Menu");
		GridPane layout2 = new GridPane();
		layout2.setHgap(10);
		layout2.setVgap(10);
		layout2.setPadding(new Insets(0, 5, 5, 5));

		Scene scene2 = new Scene(layout2);
		soundWindow.setScene(scene2);
		Text soundMessage = new Text("Sorry, the sound option is currently\n" + "not available for this version");
		layout2.add(soundMessage, 0, 0, 2, 1);
		Button soundOkay = new Button("Okay");
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
			}
		});
		soundOkay.setOnKeyPressed(e -> {
			if (e.getCode() == KeyCode.ENTER) {
				soundWindow.close();
			}
		});

		// GUI for answer field not containing a number
		Stage notANumber = new Stage();
		GridPane layout3 = new GridPane();
		layout3.setHgap(10);
		layout3.setVgap(10);
		layout3.setPadding(new Insets(5, 5, 5, 5));

		Scene scene3 = new Scene(layout3);
		notANumber.setScene(scene3);
		notANumber.setTitle("Incorrect field");
		Text answerIsNumber = new Text("Answer field needs to contain a number " + "between 1 and 4 inclusive");
		layout3.add(answerIsNumber, 0, 0, 2, 1);
		Button answerOkay = new Button("Okay");
		answerOkay.setAccessibleRoleDescription("Okay button");
		answerOkay.setAccessibleText(
				"The answer field needs to contain a number between 1 and 4 inclusive, press enter to return to main window");
		layout3.add(answerOkay, 2, 1);

		answerOkay.setOnAction(e -> {
			notANumber.close();
		});
		answerOkay.setOnKeyPressed(e -> {
			if (e.getCode() == KeyCode.ENTER) {
				notANumber.close();
			}
		});

		// GUI for incorrect Braille cell entry
		Stage brailleWindow = new Stage();
		GridPane layout4 = new GridPane();
		layout4.setHgap(10);
		layout4.setVgap(10);
		layout4.setPadding(new Insets(5, 5, 5, 5));

		Scene scene4 = new Scene(layout4);
		brailleWindow.setScene(scene4);
		brailleWindow.setTitle("Incorrect field");
		Text brailleEntry = new Text("Braille field must contain one letter");
		layout4.add(brailleEntry, 0, 0, 2, 1);
		Button brailleOkay = new Button("Okay");
		brailleOkay.setAccessibleRoleDescription("Okay button");
		brailleOkay
				.setAccessibleText("Braille field must contain one letter only, press enter to return to main window");
		layout4.add(brailleOkay, 2, 1);

		brailleOkay.setOnAction(e1 -> {
			brailleWindow.close();
		});
		brailleOkay.setOnKeyPressed(e -> {
			if (e.getCode() == KeyCode.ENTER) {
				brailleWindow.close();
			}
		});

		// GUI for naming block
		Stage nameBlockWindow = new Stage();
		GridPane layout1 = new GridPane();
		layout1.setHgap(10);
		layout1.setVgap(10);
		layout1.setPadding(new Insets(0, 5, 5, 5));

		Scene scene1 = new Scene(layout1);
		nameBlockWindow.setScene(scene1);
		nameBlockWindow.setTitle("Name Block");
		Text nameBlock = new Text("Enter name for the block");
		layout1.add(nameBlock, 0, 0);
		TextField nameField = new TextField();
		layout1.add(nameField, 0, 1);

		// accessibility for nameField
		Label nameLabel = new Label("Enter name for the block");
		nameLabel.setLabelFor(nameField);
		nameLabel.setVisible(false);
		layout.add(nameLabel, 0, 1);

		Button save = new Button("Save");
		save.setAccessibleRoleDescription("Save button");
		save.setAccessibleText("Press enter to save block");
		layout1.add(save, 0, 2);

		// save button action [pop up dialog box]
		save.setOnMouseClicked(e -> {

			// get name of file from user input
			String blockName = nameField.getText();

			// save name to comboBox
			comboBoxList.add(blockName);
			comboBox.setItems(comboBoxList);

			// save text to block

			int buttonsUsed = 4;
			if (blockName.equals("") || storyText.equals("") || Integer.parseInt(answerText.getText()) > buttonsUsed) {
				try {
					throw new InvalidBlockException();
				} catch (InvalidBlockException e2) {
					// TODO Auto-generated catch block
					e2.printStackTrace();
				}
			} else {
				Block blockText;
				try {
					blockText = new Block(blockName, storyText.getText(), correctText.getText(),
							incorrectText.getText(), Integer.parseInt(answerText.getText()),
							brailleText.getText().charAt(0));
					blockList.add(blockText);
				} catch (NumberFormatException e2) {
					// TODO Auto-generated catch block
					e2.printStackTrace();
				} catch (InvalidBlockException e2) {
					// TODO Auto-generated catch block
					e2.printStackTrace();
				}

			}

			// send block to printer
			try {
				printer = new Printer(blockName + ".txt");
				try {
					printer.addBlockList(blockList);
				} catch (InvalidCellException e2) {
					e2.printStackTrace();
				}
				printer.print();
			} catch (IOException e1) {
				e1.printStackTrace();
			}

			nameBlockWindow.close();

		});

		save.setOnKeyPressed(e -> {

			if (e.getCode() == KeyCode.ENTER) {

				// get name of file from user input
				String blockName = nameField.getText();

				// save name to comboBox
				comboBoxList.add(blockName);
				comboBox.setItems(comboBoxList);

				// save text to block
				Block blockText = null;
				try {
					blockText = new Block(blockName, storyText.getText(), correctText.getText(),
							incorrectText.getText(), Integer.parseInt(answerText.getText()),
							brailleText.getText().charAt(0));
					blockList.add(blockText);
				} catch (NumberFormatException e2) {
					// TODO Auto-generated catch block
					e2.printStackTrace();
				} catch (InvalidBlockException e2) {
					// TODO Auto-generated catch block
					e2.printStackTrace();
				}

				// send block to printer
				try {
					printer = new Printer(blockName + ".txt");
					try {
						printer.addBlock(blockText);
					} catch (InvalidCellException e2) {
						// TODO Auto-generated catch block
						e2.printStackTrace();
					}
					printer.print();
				} catch (IOException e1) {
					e1.printStackTrace();
				}
				nameField.clear();
				nameBlockWindow.close();
			}
		});

		// publish button
		// check answer field contains number
		// check braille field contains a blank or one char

		publish.setOnMouseClicked(e -> {

			try {
				int x = Integer.parseInt(answerText.getText());
				if (x >= 1 && x <= 4) {
					if (brailleText.getText().length() == 1) {
						if (brailleText.getText().matches("[A-z]"))
							nameBlockWindow.show();
						else {
							brailleWindow.show();
						}
					} else {
						brailleWindow.show();
					}

				} else {
					notANumber.show();
				}

			} catch (NumberFormatException e2) {
				notANumber.show();
			}

		});

		publish.setOnKeyPressed(e -> {

			if (e.getCode() == KeyCode.ENTER) {

				try {
					int x = Integer.parseInt(answerText.getText());
					if (x >= 1 && x <= 4) {
						if (brailleText.getText().length() == 1) {
							if (brailleText.getText().matches("[A-z]"))
								nameBlockWindow.show();
							else {
								brailleWindow.show();
							}
						} else {
							brailleWindow.show();
						}

					} else {
						notANumber.show();
					}

				} catch (NumberFormatException e2) {
					notANumber.show();
				}
			}
		});

		// Scene
		Scene scene = new Scene(layout, 1000, 655);
		primaryStage.setTitle("Scenario Creator");
		primaryStage.setScene(scene);
		scene.setFill(Color.TRANSPARENT);
		primaryStage.show();
		layout.setBackground(
				new Background(new BackgroundFill(Color.gray(0.05, 0.8), CornerRadii.EMPTY, Insets.EMPTY)));

		// Set true to help see how nodes are aligned
		layout.setGridLinesVisible(false);

	}

	public static void main(String[] args) {

		// Inherited method from Application that lunches GUI
		launch(args);
	}

}
