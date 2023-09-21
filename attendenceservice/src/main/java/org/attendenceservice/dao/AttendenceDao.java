package org.attendenceservice.dao;

import java.time.LocalDate;
import java.util.List;

import org.attendenceservice.entity.Attendence;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AttendenceDao extends JpaRepository<Attendence, Long>{

	List<Attendence> findBySwipeDate(LocalDate swipeDate);
	
	Attendence findByEmployeeIdAndSwipeDate(int employeeId, LocalDate swipeDate);
	
}
