package kw.kng.request;

import lombok.Data;

@Data
public class EmployeeRequest 
{
	private String name;
	private String department;

}


/*
 
 
 //Binding Class used for ONE to ONE MAPPING
In the JSON payload we are going to send employee name and department. And in the controller layer we will seperate this. 

*/
