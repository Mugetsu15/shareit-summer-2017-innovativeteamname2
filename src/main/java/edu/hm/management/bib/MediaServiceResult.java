package edu.hm.management.bib;

import javax.ws.rs.core.Response;

public enum MediaServiceResult {
	
	OKAY(Response.Status.OK.getStatusCode(), "Okay."),
	FORBIDDEN(Response.Status.FORBIDDEN.getStatusCode(), "Forbidden."),
	NOTFOUND(Response.Status.NOT_FOUND.getStatusCode(), "Not Found"),
	INTERNALSERVERERROR(Response.Status.INTERNAL_SERVER_ERROR.getStatusCode(), "Internal Server Error"),
	BADREQUEST(Response.Status.BAD_REQUEST.getStatusCode(), "Bad Request");
	
	private final int errorCode;
	private final String errorNote;
	
	/**
	 * Constructor for ErrorCode Handling.
	 * @param code
	 */
	MediaServiceResult(int code, String note)  {
		errorCode = code;
		errorNote = note;
	}
	
	/**
	 * Returns the Error Code for a Response.
	 * @return
	 */
	public int getCode()  {
		return errorCode;
	}
	
	public String getNote()  {
		return errorNote;
	}
	
	String getStatus()  {
		return String.format("Status-Code: %d - %s", errorCode, errorNote);
	}
	
}
