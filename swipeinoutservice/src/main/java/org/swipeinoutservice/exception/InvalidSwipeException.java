package org.swipeinoutservice.exception;

public class InvalidSwipeException extends RuntimeException {

	private static final long serialVersionUID = 1L;
	
	public InvalidSwipeException() {
		super();
	}
	
	public InvalidSwipeException(String message) {
		super(message);
	}

}
