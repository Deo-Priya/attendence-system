package org.attendenceservice.service;

import java.time.LocalDate;
import java.util.List;

import org.attendenceservice.client.EmployeeClient;
import org.attendenceservice.dao.AttendenceDao;
import org.attendenceservice.dto.AttendenceDto;
import org.attendenceservice.dto.Employee;
import org.attendenceservice.entity.Attendence;
import org.attendenceservice.mapper.AttendenceMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

@Service
public class AttendenceService {

	@Autowired
	private AttendenceDao attendenceDao;

	@Autowired
	private EmployeeClient employeeClient;

	@Autowired
	private AttendenceMapper attendenceMapper;

	public List<AttendenceDto> getAttendence(LocalDate attendenceDate) {
		if (attendenceDate == null) {
			attendenceDate = LocalDate.now();
		}
		List<Attendence> listOfAttendences = attendenceDao.findBySwipeDate(attendenceDate);
		List<AttendenceDto> dtos = listOfAttendences.stream().map(attenence -> getAttendenceDto(attenence))
				.sorted((o1, o2) -> o1.getEmployee().getId() - o2.getEmployee().getId()).toList();
		return dtos;

	}

	public AttendenceDto getEmployeeAttendence(int empId, LocalDate attendenceDate) {
		if (attendenceDate == null) {
			attendenceDate = LocalDate.now();
		}
		Attendence attendence = attendenceDao.findByEmployeeIdAndSwipeDate(empId, attendenceDate);
		AttendenceDto attendenceDto = getAttendenceDto(attendence);
		return attendenceDto;
	}

	private AttendenceDto getAttendenceDto(Attendence attendence) {
		ResponseEntity<Employee> employee = employeeClient.getEmployeeById(attendence.getEmployeeId());
		AttendenceDto attendenceDto = attendenceMapper.attendenceToAttendenceDto(attendence);
		attendenceDto.setEmployee(employee.getBody());
		return attendenceDto;
	}

	public void save(Attendence attendence) {
		Attendence savedAttendence = attendenceDao.findByEmployeeIdAndSwipeDate(attendence.getEmployeeId(),
				attendence.getSwipeDate());
		if (savedAttendence != null) {
			savedAttendence.setTotalTimeInOffice(attendence.getTotalTimeInOffice());
			savedAttendence.setAttendence(attendence.getAttendence());
			attendenceDao.save(savedAttendence);
		} else {
			attendenceDao.save(attendence);
		}

	}
}
