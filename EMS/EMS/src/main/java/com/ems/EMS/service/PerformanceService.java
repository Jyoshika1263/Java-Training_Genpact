package com.ems.EMS.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
 
import com.ems.EMS.model.Performance;
 
@Service
public class PerformanceService {
 
    @Autowired
    private Performance performance;
 

    public boolean isEligibleForPromotion() {
        return performance.isEligibleForPromotion();
    }
 
    public void printPerformanceDetails() {
        System.out.println("Performance Details:");
        System.out.println("Employee ID: " + performance.getEmployeeId());
        System.out.println("Rating: " + performance.getRating());
        System.out.println("Feedback: " + performance.getFeedback());
        System.out.println("Projects Handled: " + performance.getProjectsHandled());
        System.out.println("Eligible for Promotion: " + performance.isEligibleForPromotion());
    }

    public String getFeedback(Performance performance) {
        return performance.getFeedback();
    }
//    
//    //print all projects
//    public void printAllProjects() {
//        System.out.println("Projects Handled:");
//        for (String project : performance.getProjectsHandled()) {
//            System.out.println("- " + project);
//        }
//    }
}




//CapStone:
//	1. Teams->2 or 3 members
//	2.start thinking on idea
//	3.web application
//	4.spring web, MySQL, React as Front End
//	5.SRS Document