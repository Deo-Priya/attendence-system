package org.swipeinoutservice.entity;

import java.time.Duration;
import java.time.LocalDate;

public class EmployeeAttendence {

	private Long employeeId;
	private Duration totalTimeInOffice;
	private LocalDate swipeDate;
	
	public EmployeeAttendence() {}
	
	public EmployeeAttendence(Long empId, LocalDate swipeDate, Duration totalTimeInOffice) {
		super();
		this.employeeId = empId;
		this.swipeDate = swipeDate;
		this.totalTimeInOffice = totalTimeInOffice;
	}
	public Long getEmployeeId() {
		return employeeId;
	}
	public void setEmployeeId(Long employeeId) {
		this.employeeId = employeeId;
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
		return "EmployeeAttendence [employeeId=" + employeeId + ", totalTimeInOffice="
				+ totalTimeInOffice + ", swipeDate=" + swipeDate + "]";
	}

	
}
