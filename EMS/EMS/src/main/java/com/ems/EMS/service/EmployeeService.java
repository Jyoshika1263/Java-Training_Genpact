package com.ems.EMS.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ems.EMS.model.Employee;

@Service
public class EmployeeService {
	@Autowired
	private Employee employee;
	//print employee details
	public void printEmpDetails() {
		System.out.println("Employee Details : "+employee);
	}
	//cal annual salary
	public double calAnnualSalary() {
		return employee.getSalary()*12;
	}
	//fetch employee skills
	public List<String> getEmployeeskills(){
		return employee.getSkills();
	}
	//eligible for promotion or not
	public boolean isEligiblePromotion() {
		return employee.getDesignation().equalsIgnoreCase("senior developer");
	}
	
    
}
