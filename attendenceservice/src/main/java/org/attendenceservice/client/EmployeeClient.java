package org.attendenceservice.client;

import org.attendenceservice.dto.Employee;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@FeignClient(name = "${employee.feign.name}", url = "${employee.feign.url}")
public interface EmployeeClient {

	@GetMapping("/api/v1/employees/{id}")
	ResponseEntity<Employee> getEmployeeById(@PathVariable("id") int id);
}
