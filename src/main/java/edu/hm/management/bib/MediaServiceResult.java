package edu.hm.management.bib;

public enum MediaServiceResult {
	
	OKAY(200), FORBIDDEN(403), NOTFOUND(404), INTERNALSERVERERROR(500);
	
	private final int errorCode;
	
	/**
	 * Constructor for ErrorCode Handling.
	 * @param code
	 */
	MediaServiceResult(int code)  {
		errorCode = code;
	}
	
	/**
	 * Returns the Error Code for a Response.
	 * @return
	 */
	int getCode()  {
		return errorCode;
	}
	
	String getStatus()  {
		return String.format("Status-Code: %d", errorCode);
	}
	
}
