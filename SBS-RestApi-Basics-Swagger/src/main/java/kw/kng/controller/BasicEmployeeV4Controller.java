package kw.kng.controller;

import java.util.ArrayList;
import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import kw.kng.entity.BasicDepartmentV4;
import kw.kng.entity.BasicEmployeeV4;
import kw.kng.request.EmployeeRequestV2;
import kw.kng.response.EmployeeResponse;
import kw.kng.response.EmployeeResponseV4;
import kw.kng.service.BasicDepartmentV4Service;
import kw.kng.service.BasicEmployeeV4Service;

@RestController
public class BasicEmployeeV4Controller 
{
	@Autowired
	private BasicEmployeeV4Service be4;
	
	@Autowired
	private BasicDepartmentV4Service bd4;
	
	//------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------
	
																			//ONE TO MANY BI DIRECTIONAL MAPPING
	
	//------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------
	
	
	//TYPE: POST, URL: localhost:8080/employeev2
	@PostMapping("/employeev4")
	public ResponseEntity<String> saveEmployeeV2Record(@Valid @RequestBody EmployeeRequestV2 er4)
	{
		BasicEmployeeV4 basicEmployeeV4= new BasicEmployeeV4(er4);
		basicEmployeeV4=be4.saveBasicEmployeeV4(basicEmployeeV4); //One Employee many departments. Save the employee details first.
		
		//First we have to save employee details then departments details
		
		for(String s :  er4.getDepartment())
		{
			BasicDepartmentV4 basicDepartmentV4= new BasicDepartmentV4();
			basicDepartmentV4.setName(s);
			basicDepartmentV4.setEmployee(basicEmployeeV4);
			
			bd4.saveBasicDepartmentV4(basicDepartmentV4);
			
		}
		return new ResponseEntity<String> ("Record saved sucessfully", HttpStatus.CREATED);
	}
	
	//TYPE: GET, URL: localhost:8080/employeev2
	@GetMapping("/employeev4")
	public ResponseEntity<List<EmployeeResponseV4>> getEmployee()
	{
			List<BasicEmployeeV4> list= be4.findByBasicEmployeeV4();
			List<EmployeeResponseV4> responseList= new ArrayList<>();
			list.forEach(e->
			{
				EmployeeResponseV4 eResponse = new EmployeeResponseV4();
				eResponse.setId(e.getId());
				eResponse.setEmployeeName(e.getName());
				List<String> depts = new ArrayList<>();
				for(BasicDepartmentV4 b : e.getDepartments())
				{
					depts.add(b.getName());
				}
				eResponse.setDepartment(depts);
				responseList.add(eResponse);
			});
			return new ResponseEntity<List<EmployeeResponseV4>>(responseList, HttpStatus.OK);
	}
	
	
}
