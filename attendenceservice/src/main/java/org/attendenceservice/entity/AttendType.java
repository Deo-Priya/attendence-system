package org.attendenceservice.entity;

public enum AttendType {

	PRESENT("Greater than 8 hours"), ABSENT("Less than 4 hours"),
	HALF_DAY("Greater than 4 hours but less than 8 hours");

	private String value;

	AttendType(String value) {
		this.value=value;
	}

	public String getValue() {
		return value;
	}
}
