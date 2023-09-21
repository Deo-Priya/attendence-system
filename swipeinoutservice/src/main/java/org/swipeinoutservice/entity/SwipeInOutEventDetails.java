package org.swipeinoutservice.entity;

import java.time.LocalDate;
import java.time.LocalTime;

import io.micrometer.common.lang.NonNull;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

@Entity
public class SwipeInOutEventDetails implements Comparable<SwipeInOutEventDetails>{

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private long id;
	
	@Column
	@NonNull
	private long employeeId;
	
	@Column
	private int inOutSequence;
	
	@Column
	private LocalDate swipeDate;
	
	@Enumerated(EnumType.STRING)
	@NonNull
	private SwipeType swipeType;
	
	@Column
	private LocalTime swipeTime;

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public long getEmployeeId() {
		return employeeId;
	}

	public void setEmployeeId(long employeeId) {
		this.employeeId = employeeId;
	}

	public int getInOutSequence() {
		return inOutSequence;
	}

	public void setInOutSequence(int inOutSequence) {
		this.inOutSequence = inOutSequence;
	}

	public LocalDate getSwipeDate() {
		return swipeDate;
	}

	public void setSwipeDate(LocalDate swipeDate) {
		this.swipeDate = swipeDate;
	}

	public SwipeType getSwipeType() {
		return swipeType;
	}

	public void setSwipeType(SwipeType swipeType) {
		this.swipeType = swipeType;
	}

	public LocalTime getSwipeTime() {
		return swipeTime;
	}

	public void setSwipeTime(LocalTime swipeTime) {
		this.swipeTime = swipeTime;
	}

	@Override
	public String toString() {
		return "SwipeInOutEventDetails [id=" + id + ", employeeId=" + employeeId + ", inOutSequence=" + inOutSequence
				+ ", swipeDate=" + swipeDate + ", swipeType=" + swipeType + ", swipeTime=" + swipeTime + "]";
	}

	@Override
	public int compareTo(SwipeInOutEventDetails o) {
		if(this.swipeDate.compareTo(o.swipeDate) == 0) {
			if(this.employeeId == o.employeeId) {
				return this.inOutSequence - o.inOutSequence;
			}
			return (int)(this.employeeId - o.employeeId);
		}
		return this.swipeDate.compareTo(o.swipeDate);
	}
	
	

}
