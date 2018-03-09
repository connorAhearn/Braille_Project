package enamel;

import java.io.File;

public class Loader {
	
	char a = "a".charAt(0);
	
	Block holdOn;
	
	
	

	public Loader(File read) {
	try {
		 holdOn = new Block(" ", " ", " ", " ", 1, a, 1);
		}
		catch (InvalidBlockException e) {}
	}
	
	private void setPremise(String p) {
		holdOn.premise = p;
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
