package org.attendenceservice.entity;

import java.time.Duration;
import java.time.LocalDate;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

@Entity
public class Attendence {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private int id;
	
	@Column
	private int employeeId;
	
	@Column
	private LocalDate swipeDate;
	
	@Enumerated(EnumType.STRING)
	private AttendType attendence;
	
	@Column
	private Duration totalTimeInOffice;

	public Attendence() {}
	
	public Attendence(int employeeId, LocalDate swipeDate, Duration totalTimeInOffice) {
		super();
		this.employeeId = employeeId;
		this.swipeDate = swipeDate;
		this.totalTimeInOffice = totalTimeInOffice;
		long minutesTime = totalTimeInOffice.toMinutes();
		if(minutesTime < 240) {
			attendence = AttendType.ABSENT;
		} else if(minutesTime > 240 && minutesTime< 480) {
			attendence = AttendType.HALF_DAY;
		} else if(minutesTime > 480) {
			attendence = AttendType.PRESENT;
		}
	}

	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	
	public int getEmployeeId() {
		return employeeId;
	}
	public void setEmployeeId(int employeeId) {
		this.employeeId = employeeId;
	}
	
	public LocalDate getSwipeDate() {
		return swipeDate;
	}
	public void setSwipeDate(LocalDate swipeDate) {
		this.swipeDate = swipeDate;
	}
	
	public AttendType getAttendence() {
		return attendence;
	}

	public void setAttendence(AttendType attendence) {
		this.attendence = attendence;
	}

	public Duration getTotalTimeInOffice() {
		return totalTimeInOffice;
	}
	public void setTotalTimeInOffice(Duration totalTimeInOffice) {
		this.totalTimeInOffice = totalTimeInOffice;
	}
	
}
