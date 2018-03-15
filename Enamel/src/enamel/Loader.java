package enamel;

import java.io.File;

public class Loader {
	
	// basic char used to inicailize a blank block
	char a = "a".charAt(0);
	Block holdOn;
	
	
	

	public Loader(File read) {
	try {
		 holdOn = new Block(" ", " ", " ", " ", 1, a, 1);
		}
		catch (InvalidBlockException e) {
			e.printStackTrace(); 
			System.out.println(e.getMessage());
		}
	
	
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
