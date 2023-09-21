package org.swipeinoutservice.controller;

import java.time.LocalDate;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.oauth2.jwt.Jwt;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.swipeinoutservice.AppConstants;
import org.swipeinoutservice.entity.EmployeeAttendence;
import org.swipeinoutservice.entity.SwipeInOutEventDetails;
import org.swipeinoutservice.service.SwipeService;

@RestController
@RequestMapping("/api/v1/swipe")
public class SwipeContoller {
	
	private static final Logger LOG = LoggerFactory.getLogger(SwipeContoller.class);

	@Autowired
	private SwipeService swipeService;
	
	@PutMapping("/in")
	public ResponseEntity<Void> swipeIn(Authentication authentication) {
		LOG.debug("Inside SwipeIn");
		Jwt jwt = (Jwt) authentication.getPrincipal();
		long empId = jwt.getClaim(AppConstants.JWT_USER_ID);
		swipeService.markEmployeeIn(empId);
		return new ResponseEntity<Void>(HttpStatus.OK);
	}
	
	@PutMapping("/out")
	public ResponseEntity<Void> swipeOut(Authentication authentication) {
		LOG.debug("Inside SwipeOut");
		Jwt jwt = (Jwt) authentication.getPrincipal();
		long empId = jwt.getClaim(AppConstants.JWT_USER_ID);
		swipeService.markEmployeeOut(empId);
		return new ResponseEntity<Void>(HttpStatus.OK);
	}
	
	@GetMapping("/attendence")
	public ResponseEntity<List<EmployeeAttendence>> getAttendence(@RequestParam(required = false, name = "date") LocalDate date){
		LOG.debug("Inside getAttendence");
		List<EmployeeAttendence> empAttendence = swipeService.getAttendence(date);
		return new ResponseEntity<>(empAttendence, HttpStatus.OK);
	}
	
	@GetMapping("/detailsForToday")
	public ResponseEntity<List<SwipeInOutEventDetails>> getTodaySwipeDetails(){
		LOG.debug("Inside getTodaySwipeDetails");
		List<SwipeInOutEventDetails> empAttendence = swipeService.getTodaySwipeDetails();
		return new ResponseEntity<>(empAttendence, HttpStatus.OK);
	}
	
}
