package org.swipeinoutservice.entity;

public enum SwipeType {

	IN("swipeIn"),
	OUT("swipeOut");
	
	private String value;
	
	SwipeType(String value) {
		this.value=value;
	}
	
	public String getValue() {
		return value;
	}
}
