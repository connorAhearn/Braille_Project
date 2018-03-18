package src.enamel;


/**
 * Exception thrown in the event that user input has an uneven amount
 * of special characters
 * 
 * @author Connor
 *
 */
@SuppressWarnings("serial")
public class OddSpecialCharacterException extends Exception{

	public OddSpecialCharacterException() {
		
	}
	
	public OddSpecialCharacterException(String msg) {
		super(msg);		
	}
}
