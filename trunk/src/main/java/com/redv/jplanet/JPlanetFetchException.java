/**
 * 
 */
package com.redv.jplanet;

/**
 * @author sutra
 * 
 */
public class JPlanetFetchException extends Exception {

	/**
	 * 
	 */
	private static final long serialVersionUID = -2702527778389402444L;

	/**
	 * 
	 */
	public JPlanetFetchException() {
	}

	/**
	 * @param message
	 */
	public JPlanetFetchException(String message) {
		super(message);
	}

	/**
	 * @param cause
	 */
	public JPlanetFetchException(Throwable cause) {
		super(cause);
	}

	/**
	 * @param message
	 * @param cause
	 */
	public JPlanetFetchException(String message, Throwable cause) {
		super(message, cause);
	}

}
