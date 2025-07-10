package com.example.test;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;

@Service
public class EmployeeProcessor {
	
	public List<Employee> loadEmployee()  {
		
		List<Employee> list = new ArrayList<>();
		try {
		
		FileReader fr = new FileReader ("C:\\Users\\aanvi\\Downloads\\test\\test\\employeeDetails.xt");        
	       BufferedReader br = new BufferedReader (fr);     
	       String line = br.readLine ();
	  
	       while (line != null) {
	          String []columns = line.split(",");
	          Integer empId = Integer.parseInt(columns[0]);
	          String fistName = columns[1];
	          String lastName = columns[2];
	          Integer salary = Integer.parseInt(columns[3]);
	          Integer mgrId =null;
	         if(columns.length==4) {
	           mgrId =  null;
	         }else {
	        	  mgrId = Integer.parseInt(columns[4]);
	         }
	          Employee emp = new Employee(empId,fistName,lastName,salary,mgrId);
	          list.add(emp);
	           line = br.readLine();
	       }     
		}catch (Exception e) {
			// TODO: handle exception
		}
	       return list;
		}
	
	public static Employee getEmpDetails(Integer empId ,List<Employee> list) {

		return list.stream().filter(x->x.getEmpId().equals(empId)).findFirst().get();
	}
	
	public Map<Integer, List<Employee>> groupByMgarId(List<Employee> emplist){
		return emplist.stream()
		   .filter(emp->emp.getMgrId()!= null)
		   .collect(Collectors.groupingBy(Employee::getMgrId));
	}
	
	public Map<Integer, Double> buildAvgSalaryMap(Map<Integer, List<Employee>> groupByMgrId){
		 Map<Integer, Double> avgMap = new HashMap<Integer, Double>();
	       groupByMgrId.entrySet().stream().forEach(x->{
	    	   avgMap.put(x.getKey(), x.getValue().stream().mapToDouble(Employee::getSalary).average().getAsDouble());
	       });
	       return avgMap;
	}
	
	public void getExceedingReportingLine(List<Employee> empList) {
		 Map<Integer, Integer> empMgrMap = new LinkedHashMap<Integer, Integer>();
	       
	       for(Employee e:empList) {
	    	   empMgrMap.put(e.getEmpId(), e.getMgrId());
	    	  // System.out.println(e.getFirstName()+" "+e.getMgrId());
	       }
	       int count=0;
	       System.out.println("map size"+empMgrMap.size());
	       for(Map.Entry<Integer,Integer> entry:empMgrMap.entrySet()) {
	    	  
	    	   Integer emp = entry.getKey();
	    	   Integer mgr = entry.getValue();
	    	   while(mgr!=null) {
	    		  	count++;
	    		   mgr = empMgrMap.get(mgr);
	    	   }
	    	   if(count > 5) { //excluding himself and CEO
	    	     System.out.println("Emp id "+emp+ " have more than 4 managers : "+(count-2));
	    	   }
	    	   count=0;
	       }
	}
	
	public void printReportAvgMinSalary( Map<Integer, Double> avgMap,List<Employee> list) {
		  // min avg salary
	       avgMap.entrySet().forEach(p1->{
	    	  Employee employee = getEmpDetails(p1.getKey(), list);
	    	  double minAvgSalary =  p1.getValue()+ (p1.getValue() * 0.20);
	    	  if(employee.getSalary()<minAvgSalary)
	    	   System.out.println("Employee who has less avg salary Empid : "+p1.getKey()+" by less than amount-"
	    	  +(minAvgSalary-employee.getSalary()));
	       });
	      
	}
	
	public void printReportAvgMaxSalary( Map<Integer, Double> avgMap,List<Employee> list) {
		
	       //max avg salary
	       avgMap.entrySet().forEach(p1->{
		    	  Employee employee = getEmpDetails(p1.getKey(), list);
		    	  double maxAvgSalary =  p1.getValue()+ (p1.getValue() * 0.50);
		    	  if(employee.getSalary()>maxAvgSalary)
		    	   System.out.println("Employee who has max avg salary Emp id "+p1.getKey()+
		    			   " by amount "+(employee.getSalary()-maxAvgSalary));
		    });
	}

}
