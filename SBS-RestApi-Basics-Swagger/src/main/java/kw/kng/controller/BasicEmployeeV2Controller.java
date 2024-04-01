package kw.kng.controller;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import kw.kng.entity.BasicDepartmentV2;
import kw.kng.entity.BasicEmployeeV2;
import kw.kng.request.EmployeeRequestV2;
import kw.kng.service.BasicDepartmentV2Service;
import kw.kng.service.BasicEmployeeV2Service;

@RestController
public class BasicEmployeeV2Controller 
{
	@Autowired
	private BasicEmployeeV2Service be2;
	
	@Autowired
	private BasicDepartmentV2Service bd2;
	
	//------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------
	
																			//ONE TO MANY UNIDIRECTIONAL MAPPING
	
	//------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------
	
	
	//TYPE: POST, URL: localhost:8080/employeev2
	@PostMapping("/employeev2")
	public ResponseEntity<String> saveEmployeeV2Record(@Valid @RequestBody EmployeeRequestV2 er2)
	{
		BasicEmployeeV2 basicEmployeeV2= new BasicEmployeeV2(er2);
		basicEmployeeV2=be2.saveBasicEmployeeV2(basicEmployeeV2); //One Employee many departments. Save the employee details first.
		
		//First we have to save employee details then departments details
		
		for(String s :  er2.getDepartment())
		{
			BasicDepartmentV2 basicDepartmentV2= new BasicDepartmentV2();
			basicDepartmentV2.setName(s);
			basicDepartmentV2.setEmployee(basicEmployeeV2);
			
			bd2.saveBasicDepartmentV2(basicDepartmentV2);
			
		}
		return new ResponseEntity<String> ("Record saved sucessfully", HttpStatus.CREATED);
	}
	
	
	
}
