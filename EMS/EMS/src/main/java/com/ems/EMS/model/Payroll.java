package com.ems.EMS.model;

public class Payroll {
	private int employeeId;
	private double baseSalary;
	private double bonuses;
	private double deduction;
	public Payroll() {
    }
	public Payroll(int employeeId, double baseSalary, double bonuses, double deduction) {
		super();
		this.employeeId = employeeId;
		this.baseSalary = baseSalary;
		this.bonuses = bonuses;
		this.deduction = deduction;
	}
	public int getEmployeeId() {
		return employeeId;
	}
	public void setEmployeeId(int employeeId) {
		this.employeeId = employeeId;
	}
	public double getBaseSalary() {
		return baseSalary;
	}
	public void setBaseSalary(double baseSalary) {
		this.baseSalary = baseSalary;
	}
	public double getBonuses() {
		return bonuses;
	}
	public void setBonuses(double bonuses) {
		this.bonuses = bonuses;
	}
	public double getDeduction() {
		return deduction;
	}
	public void setDeduction(double deduction) {
		this.deduction = deduction;
	}
    public double calculatesalary() {
    	return baseSalary + bonuses - deduction;
    }
}
