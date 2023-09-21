package org.swipeinoutservice.service;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.Test;
import org.mockito.BDDMockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.swipeinoutservice.dao.SwipeDao;

public class SwipeServiceTest {

	@MockBean
	private	SwipeDao employeeDao;
	
	@Autowired
	private SwipeService employeeService;
	
	@Configuration
	public static class config {
		@Bean
		public SwipeService getEmployeeService() {
			return new SwipeService();
		}
	}
	
	//@Test
	void getEmployeesTest() {
		/*
		 * Employee emp = new Employee(1, "Test A", "A@gmail.com"); List<Employee> emps
		 * = new ArrayList<Employee>(); emps.add(emp);
		 * BDDMockito.given(employeeDao.findAll()).willReturn(emps); assertEquals(emps,
		 * employeeService.getEmployees());
		 */
	}
	
}
