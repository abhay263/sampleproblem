package com.example.test;

import java.io.IOException;
import java.util.List;
import java.util.Map;


public class Test {
	
	public static void main(String[] args) throws IOException {

		   //load the employee details from test file
		   EmployeeProcessor processor = new EmployeeProcessor();
		   List<Employee> list = processor.loadEmployee();
		
	       //Group employees by magId
	       Map<Integer, List<Employee>> groupEmpByMgrId= processor.groupByMgarId(list);
	      
	       // calculate avg salary of subordinates of each manager 
	       Map<Integer, Double> avgSalaryOfSubordinates = processor.buildAvgSalaryMap(groupEmpByMgrId);
	       
	       //print AvgMinSalary of managers having less that 20% of avg salary
	       processor.printReportAvgMinSalary(avgSalaryOfSubordinates,list);
	       //print AvgMaxSalary of managers having more than 50% of avg salary
	       processor.printReportAvgMaxSalary(avgSalaryOfSubordinates,list);
	      
	       //print Reporting line
	       processor.getExceedingReportingLine(list);

	}

	}

