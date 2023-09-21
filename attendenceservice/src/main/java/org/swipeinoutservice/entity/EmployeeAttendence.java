package org.swipeinoutservice.entity;

import java.time.Duration;
import java.time.LocalDate;

public class EmployeeAttendence {

	private int employeeId;
	private boolean presentInOffice;
	private Duration totalTimeInOffice;
	private LocalDate swipeDate;
	
	public EmployeeAttendence() {}
	
	public int getEmployeeId() {
		return employeeId;
	}
	public void setEmployeeId(int employeeId) {
		this.employeeId = employeeId;
	}
	public boolean isPresentInOffice() {
		return presentInOffice;
	}
	public void setPresentInOffice(boolean presentInOffice) {
		this.presentInOffice = presentInOffice;
	}
	public Duration getTotalTimeInOffice() {
		return totalTimeInOffice;
	}
	public void setTotalTimeInOffice(Duration totalTimeInOffice) {
		this.totalTimeInOffice = totalTimeInOffice;
	}

	public LocalDate getSwipeDate() {
		return swipeDate;
	}

	public void setSwipeDate(LocalDate swipeDate) {
		this.swipeDate = swipeDate;
	}

	@Override
	public String toString() {
		return "EmployeeAttendence [employeeId=" + employeeId + ", presentInOffice=" + presentInOffice
				+ ", totalTimeInOffice=" + totalTimeInOffice + "]";
	}
	
}
