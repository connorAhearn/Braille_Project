package enamel;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;

public class Printer {
	
	private File file;
	private FileOutputStream output;
	private ArrayList<String> lines = new ArrayList<>();
	private BrailleInterpreter interpreter = new BrailleInterpreter();
	private String[] buttonLabels = {"ONEE", "TWOO", "THREEE", "FOURR",
			"FIVEE", "SIXX", "SEVENN", "EIGHTT", "NINEE", "TENN", "ELEVENN",
			"TWELVEE"};
	private boolean first = true;
	private int cells;
	private int buttons;
	
	/**
	 * Full Constructor
	 * 
	 * @param fileName - Name of the file the scenario will be saved as
	 * @param cells - Number of cells the machine used has <b>available</b>
	 * @param buttonsAvailable - Number of buttons available on the machine
	 * @throws IOException - Required by Java
	 * @throws OddSpecialCharacterException 
	 */
	public Printer(String fileName, int cells, int buttonsAvailable) throws IOException, OddSpecialCharacterException {
		file = new File(fileName);
		output = new FileOutputStream(file, true);
		if(!file.exists()) file.createNewFile();
		initialBlock(cells, buttonsAvailable);
		this.cells = cells;
		this.buttons = buttonsAvailable;
	}
	
	/**
	 * Simplified constructor for 1 Cell, 4 Buttons
	 * 
	 * @param fileName - Name of the file the scenario will be saved as
	 * @throws IOException - Required by Java
	 * @throws OddSpecialCharacterException 
	 */
	public Printer(String fileName) throws IOException, OddSpecialCharacterException {
		this(fileName, 1, 4);
	}
	
	/**
	 * Method used to add blocks to the text file
	 * 
	 * @param block - Single block to be printed to the text file. 
	 * @throws InvalidCellException 
	 * @throws OddSpecialCharacterException 
	 */
	public void addBlock(Block block) throws InvalidCellException, OddSpecialCharacterException {
		if(first) first = false;
		else addNext();
		clearPins();
		setPins(block.letter);
		addSpoken(block.premise);
		addInputBlock(block.buttonsUsed);
		for(int i = 1; i < block.buttonsUsed; i++) {
			addResponse((block.answer == i) ? block.correctResponse : block.wrongResponse, i, (block.answer == i));
		}
		newLine();
	}
	
	public void addBlockList(ArrayList<Block> blocks) throws InvalidCellException, OddSpecialCharacterException {
		for(Block block : blocks) {
			addBlock(block);
		}
	}
	
	/**
	 * Prints the blocks that have been sent to the printer object to the file previously provided
	 * 
	 * @throws IOException - Required by Java
	 */
	public void print() throws IOException {
		addNext();
		clearPins();
		for(String line : lines) {
			byte[] temp = line.getBytes();
			output.write(temp);
		}
	}
	
	//Adds line starting with /~, string, ends with newline character
	private void addConfig(String line) {
		lines.add("/~" + line + "\n");
	}
	
	//Adds line with string, ends with newline character
	private void addSpoken(String line) throws OddSpecialCharacterException {
		
		if(!line.contains("*") && !line.contains("<") && !line.contains(">")) {
			lines.add(line + "\n");
		}
		
		else if(line.contains("<") || line.contains(">")) {
			arrowTags(line);
		}
		
		else {
			asteriskTags(line);
		}
		
	}
	
	private void arrowTags(String line) throws OddSpecialCharacterException {
		
		if(!line.contains("<") && line.contains(">")) throw new OddSpecialCharacterException();
		if(line.contains("<") && !line.contains(">")) throw new OddSpecialCharacterException();
		
		String[] split = line.split("<");
		
		addSpoken(split[0]);
		
		for(int i = 1; i < split.length; i++) {
			
			String[] superSplit = split[i].split(">");
			if(superSplit.length != 2) throw new OddSpecialCharacterException();
			
			addSound(superSplit[0].trim());
			addSpoken(superSplit[1]);
		}
		
	}
	
	private void asteriskTags(String line) throws OddSpecialCharacterException {
		
		String[] split = line.split("*");
		
		if(split.length % 2 == 0) throw new OddSpecialCharacterException();
		
		for(int i = 0; i < split.length; i++) {
			if(i % 2 == 1) addSpoken(split[i]);
			else displayString(split[i]);
		}
		
	}
	
	//Adds empty line (Like hitting enter)
	private void newLine() {
		lines.add("\n");
	}
	
	//Inserts initial block to file declaring cells and buttons on machine
	//buttonsAvailable refers to how many buttons are on the simulator / machine
	private void initialBlock(int cells, int buttonsAvailable) throws OddSpecialCharacterException {
		addSpoken("Cell " + cells);
		addSpoken("Button " + buttonsAvailable);
		newLine();
		addPause(1);
	}
	
	//Standard line used at the beginning of a block	
	private void clearPins() {
		addConfig("disp-clearAll");
	}
	
	//Sets pins for the requested character
	private void setPins(char letter) throws InvalidCellException {
		addConfig("disp-cell-pins:0 " + interpreter.getPins(letter));
	}
	
	//Adds sound for the response depending on if the response is correct
	private void addAnswerSound(boolean correct) {
		if(correct) addSound("correct.wav");
		else addSound("wrong.wav");
	}
	
	private void addSound(String fileName) {
		addConfig("sound:" + fileName);
	}
	
	//Skips to NEXTT, used if another block will follow the current block. used at the end
	private void addSkip() {
		addConfig("skip:NEXTT");
	}
	
	private void addPause(int length) {
		addConfig("pause:" + length);
	}
	
	private void addNext() {
		addConfig("NEXTT");
	}
	
	private void repeatButton(int button) {
		addConfig("repeat-button:" + button + " " + buttonLabels[button]);
	}
	
	private void addRepeat() {
		addConfig("repeat");
	}
	
	private void endRepeat() {
		addConfig("endrepeat");
	}
	
	private void resetButtons() {
		addConfig("reset-buttons");
	}
	
	private void displayChar(char c) {
		addConfig("disp-cell-char:" + c);
	}
	
	private void displayString(String in) {
		addConfig("disp-string:" + in);
	}
	
	//Input declaring portion of a block
	//NOTE: buttonsUsed refers to the buttons being used for the given scenario / block
	private void addInputBlock(int buttonsUsed) {
		for(int i = 0; i < buttonsUsed; i++) {
			addConfig("skip-button:" + i + " " + buttonLabels[i]);
		}
		addConfig("user-input");
	}
	
	//Spoken is the spoken response, button is the button that creates the response
	//NOTE: button refers to the number displayed on the box / simulation. 1 = 1
	private void addResponse(String spoken, int button, boolean correct) throws OddSpecialCharacterException {
		addConfig(buttonLabels[button-1]);
		addAnswerSound(correct);
		addSpoken(spoken);
		addSkip();
	}
	
	

}
