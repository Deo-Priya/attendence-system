package org.swipeinoutservice.service;

import java.time.Duration;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.swipeinoutservice.dao.SwipeDao;
import org.swipeinoutservice.entity.EmployeeAttendence;
import org.swipeinoutservice.entity.SwipeInOutEventDetails;
import org.swipeinoutservice.entity.SwipeType;
import org.swipeinoutservice.exception.InvalidSwipeException;
import org.swipeinoutservice.producer.KafkaProducer;

@Service
public class SwipeService {

	private static final Logger LOG = LoggerFactory.getLogger(SwipeService.class);

	@Autowired
	private SwipeDao swipeDao;

	@Autowired
	private KafkaProducer kafkaProducer;

	public SwipeInOutEventDetails markEmployeeIn(long employeeId) {
		return saveEventDetails(employeeId, SwipeType.IN);
	}

	public SwipeInOutEventDetails markEmployeeOut(long empId) {
		return saveEventDetails(empId, SwipeType.OUT);
	}

	private SwipeInOutEventDetails saveEventDetails(long employeeId, SwipeType swipeType) {
		List<SwipeInOutEventDetails> eventDetailList = swipeDao.findByEmployeeIdAndSwipeDate(employeeId,
				LocalDate.now());
		int nextSequence = validateEvent(eventDetailList, swipeType);
		SwipeInOutEventDetails eventDetails = new SwipeInOutEventDetails();
		eventDetails.setEmployeeId(employeeId);
		eventDetails.setInOutSequence(nextSequence);
		eventDetails.setSwipeDate(LocalDate.now());
		eventDetails.setSwipeTime(LocalTime.now());
		eventDetails.setSwipeType(swipeType);

		return swipeDao.save(eventDetails);
	}

	private int validateEvent(List<SwipeInOutEventDetails> eventDetailList, SwipeType swipeType) {
		int nextSequence = 1;
		if (eventDetailList.size() > 0) {
			Collections.sort(eventDetailList);
			LOG.info("Sorted EventList:: {}", eventDetailList);
			SwipeType lastSwipe = eventDetailList.get(eventDetailList.size() - 1).getSwipeType();
			if (swipeType == lastSwipe) {
				throw new InvalidSwipeException(
						String.format("Last Swipe action- [%s] was same as this Swipe Action- [%s]",
								lastSwipe.getValue(), swipeType.getValue()));
			}
			nextSequence = eventDetailList.size() + 1;
		}
		return nextSequence;
	}

	public List<EmployeeAttendence> getAttendence(LocalDate date) {
		if(date == null) {
			date = LocalDate.now();
		}
		List<SwipeInOutEventDetails> eventDetailList = swipeDao.findBySwipeDate(date);
		Collections.sort(eventDetailList);
		Map<Long, List<SwipeInOutEventDetails>> empAttendenceMap = eventDetailList.stream()
				.collect(Collectors.groupingBy(SwipeInOutEventDetails::getEmployeeId));

		List<EmployeeAttendence> employeeAttendences = empAttendenceMap.entrySet().stream().map(e -> {
			Long empId = e.getKey();
			List<SwipeInOutEventDetails> eventDetailsForEmployee = e.getValue();
			int totalSwipeEvents = eventDetailsForEmployee.size();
			LocalTime firstInTime = eventDetailsForEmployee.get(0).getSwipeTime();
			LocalTime lastOutTime = eventDetailsForEmployee.get(totalSwipeEvents - 1).getSwipeTime();
			Duration totalTimePresent = Duration.between(firstInTime, lastOutTime);
			return new EmployeeAttendence(empId, eventDetailsForEmployee.get(0).getSwipeDate(), totalTimePresent);
		}).peek(kafkaProducer::sendMessage).collect(Collectors.toList());
		return employeeAttendences;
	}

	public List<SwipeInOutEventDetails> getTodaySwipeDetails() {
		List<SwipeInOutEventDetails> eventDetailList = swipeDao.findBySwipeDate(LocalDate.now());
		Collections.sort(eventDetailList);
		return eventDetailList;
	}

}
