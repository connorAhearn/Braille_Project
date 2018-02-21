package enamel;

/**
 * Exception thrown when invalid input is given to the Braille Interpreter. 
 * 
 * @author Connor
 *
 */
public class InvalidCellException extends Exception{

	public InvalidCellException() {
		
	}
	
	public InvalidCellException(String msg) {
		super(msg);
	}

}