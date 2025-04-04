package com.ems.EMS.service;

import java.util.Map;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.ems.EMS.model.Employee;
import com.ems.EMS.model.HR;

@Service
public class HRService {
	@Autowired
	public HR hr;
	public void displayAllEmployees() {
        Map<Integer, Employee> empRecords = hr.getEmployeeRecords();
        empRecords.forEach((id, employee) -> System.out.println("Employee ID: " + id + ", Details: " + employee));
    }
	public Employee getEmpById(int id) {
		Map<Integer, Employee> empRecords = hr.getEmployeeRecords();
		return empRecords.getOrDefault(id, null);
	}
}





















//@Service
//public class HRService {
//	@Autowired
//	public HR hr;
//	//dispaly all employees
//	//fetch emp by id
//	public Employee getEmpById(int id) {
//		Map<Integer, Employee> empRecords = hr.getEmployeeRecords();
//		return empRecords.getOrDefault(id, null);
//	}
//	
//
//}
