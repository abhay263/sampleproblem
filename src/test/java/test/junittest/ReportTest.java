package test.junittest;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import com.example.test.Employee;
import com.example.test.EmployeeProcessor;

@ExtendWith(MockitoExtension.class)
public class ReportTest {
	
	@Mock
	List<Employee> mockList;
	
	@BeforeEach
	public void setup() {
		mockList = new ArrayList<Employee>();
		mockList.add(new Employee(1,"abc","xyz",30000,2));
		mockList.add(new Employee(5,"pqr","xyz",30000,2));
		mockList.add(new Employee(5,"pqr","xyz",30000,3));
	}

	 
	  @InjectMocks
	  private EmployeeProcessor processor;
	  
	  
	  @Test 
	  void groupByMgarIdSuccess(){
		//  assertThat(2).isEqualTo(2);	  
		  assertTrue(processor.groupByMgarId(mockList).size()==2);
	    }

	
}