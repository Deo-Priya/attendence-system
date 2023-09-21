package org.attendenceservice.dto;

import java.time.Duration;
import java.time.LocalDate;

import org.attendenceservice.entity.AttendType;

public class AttendenceDto {

	private Employee employee;
	private LocalDate swipeDate;
	private Duration totalTimeInOffice;
	private AttendType attendence;

	public Employee getEmployee() {
		return employee;
	}

	public void setEmployee(Employee employee) {
		this.employee = employee;
	}

	public LocalDate getSwipeDate() {
		return swipeDate;
	}

	public void setSwipeDate(LocalDate swipeDate) {
		this.swipeDate = swipeDate;
	}

	public Duration getTotalTimeInOffice() {
		return totalTimeInOffice;
	}

	public void setTotalTimeInOffice(Duration totalTimeInOffice) {
		this.totalTimeInOffice = totalTimeInOffice;
	}

	public AttendType getAttendence() {
		return attendence;
	}

	public void setAttendence(AttendType attendence) {
		this.attendence = attendence;
	}

	@Override
	public String toString() {
		return "AttendenceDto [employee=" + employee + ", swipeDate=" + swipeDate + ", totalTimeInOffice="
				+ totalTimeInOffice + ", attendence=" + attendence + "]";
	}

}
