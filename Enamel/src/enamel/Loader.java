package enamel;

import java.io.EOFException;
import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Scanner;

public class Loader {
	
	// basic char used to inicailize a blank block
	char a = "a".charAt(0);
	
	Block holdOn;
	ArrayList<Block> blocklist = new ArrayList<Block>();
	
	
	// the file scanners we shall use.
	private Scanner fileScanner;
	private Scanner nextLineCheck; 
	

	public Loader() {
	try {
		 holdOn = new Block(" ", " ", " ", " ", 1, a, 1);
		}
		catch (InvalidBlockException e) {
			e.printStackTrace(); 
			System.out.println(e.getMessage());
		}
	
	
	}
	
	public void start(File read) throws EOFException {
		String fileLine, lineAfter ="";
		try {
			fileScanner = new Scanner(read);
			nextLineCheck = new Scanner(read);
		}
		catch(FileNotFoundException e){
			 e.printStackTrace(); 
			 System.out.println(e.getMessage());
		}
		
		// there are a few lines of text that need to be skipped on every text file
		if (fileScanner.hasNextLine()&&nextLineCheck.hasNextLine()) {
			
			// this keeps nextLineCheck, one line ahead of fileScanner
			nextLineCheck.nextLine();
			for (int i=0; i<3; i++) {
				if (fileScanner.hasNextLine()&&nextLineCheck.hasNextLine()) {
					nextLineCheck.nextLine();
					fileScanner.nextLine();
				}
				else {
					throw new EOFException();
				}
			}
			
				
		}
		else {
			throw new EOFException();
		}
		
		
		
		while (fileScanner.hasNextLine()) {
			fileLine = fileScanner.nextLine();
			if (nextLineCheck.hasNextLine()) {
				lineAfter = nextLineCheck.nextLine();
			}
		
			interpretLine(fileLine, lineAfter);
		}
		
		
		
	}
	
	private void interpretLine(String line, String nextLine) {
	
		if(line.equals("\\n")){
			// gonna need a copy constructor for block
			
		}
		else if(line.length() >= 8 && line.substring(0, 8).equals("/~NEXTT-")) {
			
		}
		
		else if(line.length() >= 14 && line.substring(0, 14).equals("/~Skip-button:")) {
			
		}
		// this one may need to change
		else if(line.length() >= 1 && (line.substring(0, 0).equals("*") || line.substring(0, 0).equals("^"))) {
			
		}
		else if (line.length() >= 14 && line.substring(0, 14).equals("/~disp-string:")) {
			
		}
		else if(line.length() >= 8 && line.substring(0, 8).equals("/~button")) {
			
		}

		else if(line.length() >= 7 && line.substring(0, 7).equals("/~sound")) {
			
		}
		else {
			
		}
		
	}
	
	
	private void setName(String n) {
		holdOn.name = n;
	}
	private void setStory(String s) {
		holdOn.story = s;
	}
	
	private void setCorrect(String c) {
		holdOn.correctResponse = c;
	}

	private void setWrong(String w) {
		holdOn.wrongResponse = w;
	}

	private void setAnswer(String a) {
		holdOn.answer = Integer.valueOf(a);
	}

	private void setLetter(String l) {
		holdOn.letter = l.charAt(0);
	}

	private void setButtonsUsed(String b) {
		
		holdOn.buttonsUsed = Integer.valueOf(b);
	}

}
