package org.attendenceservice.controller;

import java.time.LocalDate;
import java.util.List;

import org.attendenceservice.dto.AttendenceDto;
import org.attendenceservice.service.AttendenceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1")
public class AttendenceController {

	@Autowired
	private AttendenceService attendenceService;

	@GetMapping("/attendence")
	public ResponseEntity<List<AttendenceDto>> getAttendence(@RequestParam(required=false, name= "date") LocalDate attendenceDate) {
		List<AttendenceDto> todaysAttendence = attendenceService.getAttendence(attendenceDate);
		return ResponseEntity.ok(todaysAttendence);
	}

	@GetMapping("/attendence/{employeeId}")
	public ResponseEntity<AttendenceDto> getEmployeeAttendence(@PathVariable("employeeId") int empId,
			@RequestParam(required=false, name="date") LocalDate attendenceDate) {
		AttendenceDto employeeAttendence = attendenceService.getEmployeeAttendence(empId, attendenceDate);
		return ResponseEntity.ok(employeeAttendence);
	}

}
