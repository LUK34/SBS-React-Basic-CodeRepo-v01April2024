package kw.kng.request;

import java.util.List;

import lombok.Data;

@Data
public class EmployeeRequestV2 
{
	
	private String name;
	
	private List<String> department;
	
}


/*
 
 
  //Binding Class used for MANY to ONE MAPPING.
 
 */