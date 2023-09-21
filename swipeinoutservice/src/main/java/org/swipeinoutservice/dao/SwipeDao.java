package org.swipeinoutservice.dao;

import java.time.LocalDate;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.swipeinoutservice.entity.SwipeInOutEventDetails;

public interface SwipeDao extends JpaRepository<SwipeInOutEventDetails, Long>{
	
	List<SwipeInOutEventDetails> findByEmployeeIdAndSwipeDate(long employeeId, LocalDate localDate);

	List<SwipeInOutEventDetails> findBySwipeDate(LocalDate now);

}
