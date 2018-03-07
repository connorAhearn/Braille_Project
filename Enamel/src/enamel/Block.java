package enamel;

public class Block {
	
	public String name;
	public String story;
	public String correctResponse;
	public String wrongResponse;
	public int answer;
	public char letter;
	public String cells;
	public int buttonsUsed;
	
	/**
	 * Main Constructor
	 * 
	 * @param story - String containing the Question / Story
	 * @param correct - String containing the message stated for the correct answer
	 * @param wrong - String containing the message stated for the wrong answer
	 * @param answer - Integer for the correct button press
	 * @param letter - Character to show up on the braille cell
	 * @param buttonsUsed - Amount of buttons used for answers for block
	 * @throws InvalidBlockException 
	 */
	public Block(String name, String story, String correct, String wrong, int answer, char letter, int buttonsUsed) throws InvalidBlockException {
		
		//Throws an exception if any of the 3 conditions are met
		if(name.equals("") || story.equals("") || answer > buttonsUsed || answer < 0 || answer > buttonsUsed) throw new InvalidBlockException();
		
		this.name = name;
		this.story = story;
		correctResponse = correct;
		wrongResponse = wrong;
		this.answer = answer;
		this.cells = Character.toString(letter);
		this.buttonsUsed = buttonsUsed;
	}
	
	/**
	 * Simplified Constructor - Assumes buttons used to be 2.
	 * 
	 * @param story - String containing the Question / Story
	 * @param correct - String containing the message stated for the correct answer
	 * @param wrong - String containing the message stated for the wrong answer
	 * @param answer - Integer for the correct button press
	 * @param letter - Character to show up on the braille cell
	 * @throws InvalidBlockException 
	 */
	public Block(String name, String story, String correct, String wrong, int answer, char letter) throws InvalidBlockException {
		this(name, story, correct, wrong, answer, letter, 2);
	}
	
	public Block(String name, String story, String correct, String wrong, int answer, String cells, int buttonsUsed) throws InvalidBlockException {
		
		if(name.equals("") || story.equals("") || answer > buttonsUsed || answer < 0 || answer > buttonsUsed) throw new InvalidBlockException();
		
		this.name = name;
		this.story = story;
		correctResponse = correct;
		wrongResponse = wrong;
		this.answer = answer;
		this.cells = cells;
		this.buttonsUsed = buttonsUsed;
	}
}
