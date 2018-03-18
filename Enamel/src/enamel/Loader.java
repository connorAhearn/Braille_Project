package enamel;

import java.io.EOFException;
import java.io.File;
import java.io.FileNotFoundException;
import java.util.*;


public class Loader {
	
	// basic char used to inicailize a blank block
	char a = "a".charAt(0);
	
	Block holdOn;
	ArrayList<Block> blocklist = new ArrayList<Block>();
	String stringBasedBoolean = "story";
	Boolean inText = false;
	
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
			// blocklist.add(new Block(holdOn));
			stringBasedBoolean = "story";
			inText = false;
			
		}
		else if(line.length() >= 8 && line.substring(0, 8).equals("/~NEXTT-")) {
			setName(line.substring(8));
		}
		
		else if(line.length() >= 14 && line.substring(0, 14).equals("/~Skip-button:")) {
			String[] param;
			if (nextLine.length() >= 14 ) {
				// possible issues with this condition 
				if(!(nextLine.substring(0, 14).equals("/~Skip-button:"))) {
					param = line.substring(14).split("\\s");
					setButtonsUsed(param[0]);
				}
			}
			else {
				param = line.substring(14).split("\\s");
				setButtonsUsed(param[0]);
			}
			
			inText = false;
		}
		// this one may need to change
	//	else if(line.length() >= 1 && (line.substring(0, 1).equals("*") || line.substring(0, 0).equals("^"))) {
			
			
			
			
		//}
		else if (line.length() >= 14 && line.substring(0, 14).equals("/~disp-string:")) {
			if(inText) {
				String[] param = line.split("\\s");
				// need to double check which special characters are for which functions
				switchAdd("*"+param[1]+"*");
			}
			else {
				setCell(line.substring(14));
				inText = true;
			}
		}
		
		else if(line.length() >= 8 && line.substring(0, 8).equals("/~button")) {
			
		}

		else if(line.length() >= 7 && line.substring(0, 7).equals("/~sound")) {
		
			if(inText) {
				String[] param = line.split("\\s");
				// need to double check which special characters are for which functions
				switchAdd("^"+param[1]+"^");
			}
			else {
							// space my be a problem
				if (line.substring(7).equals(" correct.wav")) {
					stringBasedBoolean = "correct";
				
				}
				else if(line.substring(7).equals(" wrong.wav")) {
					stringBasedBoolean = "wrong";
				}
				inText = true;
			}		

		}
		// may need to change, this is so that the line which don't hold info on the blocks,
		// don't get added as text.
		else if(line.length() >= 2 && line.substring(0, 2).equals("/~")) {
			
		}
		else {
			switchAdd(line);
			
		}
		
	}
	
	
	private void setName(String n) {
		holdOn.name = n;
	}
	private void addStory(String s) {
		holdOn.story +=s;
	}
	
	private void addCorrect(String c) {
		holdOn.correctResponse += c;
	}

	private void addWrong(String w) {
		holdOn.wrongResponse += w;
	}

	private void setAnswer(String a) {
		holdOn.answer = Integer.valueOf(a);
	}

	private void setCell(String c) {
		holdOn.cells = c;
	}

	private void setButtonsUsed(String b) {
		
		holdOn.buttonsUsed = Integer.valueOf(b);
	}
	
	private void switchAdd(String param) {
		switch(stringBasedBoolean) {
		case "story":
			addStory(param);
			break;
		case "correct":
			addCorrect(param);
			break;
		case "wrong":
			addWrong(param);
			break;
		default:
			System.out.println("Error with stringBasedBoolean. Unexpected value: "+stringBasedBoolean);
			break;
		}
	}

}
