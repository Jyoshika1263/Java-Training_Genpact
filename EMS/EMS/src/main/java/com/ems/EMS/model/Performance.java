package com.ems.EMS.model;

import java.util.List;

public class Performance {
	private int employeeId;
	private double rating;
	private String feedback;
	private List<String> projectsHandled;
	private boolean eligibleForPromotion;
	public Performance() {
    }
	@Override
	public String toString() {
		return "Performance [employeeId=" + employeeId + ", rating=" + rating + ", feedback=" + feedback
				+ ", projectsHandled=" + projectsHandled + ", eligibleForPromotion=" + eligibleForPromotion + "]";
	}
	public Performance(int employeeId, double rating, String feedback, List<String> projectsHandled,
			boolean eligibleForPromotion) {
		super();
		this.employeeId = employeeId;
		this.rating = rating;
		this.feedback = feedback;
		this.projectsHandled = projectsHandled;
		this.eligibleForPromotion = eligibleForPromotion;
	}
	public int getEmployeeId() {
		return employeeId;
	}
	public void setEmployeeId(int employeeId) {
		this.employeeId = employeeId;
	}
	public double getRating() {
		return rating;
	}
	public void setRating(double rating) {
		this.rating = rating;
	}
	public String getFeedback() {
		return feedback;
	}
	public void setFeedback(String feedback) {
		this.feedback = feedback;
	}
	public List<String> getProjectsHandled() {
		return projectsHandled;
	}
	public void setProjectsHandled(List<String> projectsHandled) {
		this.projectsHandled = projectsHandled;
	}
	public boolean isEligibleForPromotion() {
		return eligibleForPromotion;
	}
	public void setEligibleForPromotion(boolean eligibleForPromotion) {
		this.eligibleForPromotion = eligibleForPromotion;
	}

}
