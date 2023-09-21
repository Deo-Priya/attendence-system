package org.swipeinoutservice.controller;

import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.swipeinoutservice.service.SwipeService;

@WebMvcTest(controllers = SwipeContoller.class)
@ExtendWith(SpringExtension.class)
public class SwipeControllerTest {
	
	//@Autowired
	//private MockMvc mockMvc;
	
	@MockBean
	private SwipeService swipeService;
	
	//@Test
	void getAllEmployeeTest() throws Exception {
		/*
		 * Employee emp = new Employee(1, "Test A", "A@gmail.com"); List<Employee> emps
		 * = new ArrayList<Employee>(); emps.add(emp);
		 * BDDMockito.given(employeeService.getEmployees()).willReturn(emps);
		 * mockMvc.perform(get("/api/v1/employees")).andExpect(status().is2xxSuccessful(
		 * )) .andExpect(jsonPath("$").isArray());
		 */
	}

}
