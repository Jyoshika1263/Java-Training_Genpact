package com.ems.EMS.model;

public class Department {
	private int deptId;
	private String depName;
	public Department() {
    }
	
	@Override
	public String toString() {
		return "Department [deptId=" + deptId + ", depName=" + depName + "]";
	}
	public Department(int deptId, String depName) {
		super();
		this.deptId = deptId;
		this.depName = depName;
	}
	public int getDeptId() {
		return deptId;
	}
	public void setDeptId(int deptId) {
		this.deptId = deptId;
	}
	public String getDepName() {
		return depName;
	}
	public void setDepName(String depName) {
		this.depName = depName;
	}	
}
