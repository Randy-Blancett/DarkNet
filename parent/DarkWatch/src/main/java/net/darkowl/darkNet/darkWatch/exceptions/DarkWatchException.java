/**
 * 
 */
package net.darkowl.darkNet.darkWatch.exceptions;

/**
 * Class to handle DarkWatch specific Excepitions
 * 
 * @author Randy Blancett
 * @since Nov 4, 2015
 * 
 */
public class DarkWatchException extends Exception {

	/**
	 * 
	 */
	private static final long serialVersionUID = -4888889069964803124L;

	/**
	 * @since Nov 4, 2015
	 */
	public DarkWatchException() {
		super();
	}

	/**
	 * @since Nov 4, 2015
	 * @param message
	 *            Message to be displayed
	 */
	public DarkWatchException(String message) {
		super(message);
	}

	/**
	 * @since Nov 5, 2015
	 * @param message
	 *            message to attatch
	 * @param cause
	 *            Error that cased this to happen
	 */
	public DarkWatchException(String message, Throwable cause) {
		super(message, cause);
		// TODO Auto-generated constructor stub
	}

}
