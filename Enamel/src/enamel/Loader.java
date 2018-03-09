package enamel;

import java.io.File;

public class Loader {
	
	char a = "a".charAt(0);
	
	Block holdOn;
	
	
	

	public Loader(File read) {
	try {
		 holdOn = new Block(" ", " ", " ", " ", 1, a, 0);
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
	
	}

	private void setWrong(String w) {
	
	}

	private void setAnswer(String a) {
	
	}

	private void setLetter(String l) {
	
	}

	private void setButtonsUsed(String b) {
	
	}

}
