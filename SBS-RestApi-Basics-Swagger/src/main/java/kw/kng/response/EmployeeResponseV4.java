package kw.kng.response;

import java.util.List;

import lombok.Data;

@Data
public class EmployeeResponseV4 
{

	private Long id;
	
	private String employeeName;
	
	private List<String> department;
	
}
