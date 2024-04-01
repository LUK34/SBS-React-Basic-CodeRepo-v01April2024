package kw.kng.controller;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import kw.kng.entity.BasicDepartmentV1;
import kw.kng.entity.BasicEmployeeV1;
import kw.kng.entity.BasicEmployeeV2;
import kw.kng.request.EmployeeRequest;
import kw.kng.request.EmployeeRequestV2;
import kw.kng.service.BasicDepartmentV1Service;
import kw.kng.service.BasicEmployeeV1Service;
import kw.kng.service.BasicEmployeeV2Service;

@RestController
public class BasicEmployeeV1Controller
{

	@Autowired
	private BasicEmployeeV1Service bv1;
	
	
	@Autowired
	private BasicDepartmentV1Service bd1;
	
	
	
	//------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------
	
																			//ONE TO ONE MAPPING
	
	//------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------
	
	
	
	//TYPE: POST, URL: localhost:8080/employeev1
	@PostMapping("/employeev1")
	public ResponseEntity<BasicEmployeeV1> saveEmployeeV1Record(@Valid @RequestBody EmployeeRequest er)
	{
		// BasicDepartmentV1 (Parent) -> BasicEmployeeV1 (Child)
		BasicDepartmentV1 basicDepartment= new BasicDepartmentV1();
		basicDepartment.setName(er.getDepartment()); //we must first save the department object because department is the parent class.
		basicDepartment=bd1.saveBasicDepartmentV1(basicDepartment);
				// bdr.save(basicDepartment);
		
		
		BasicEmployeeV1 basicEmployee = new BasicEmployeeV1(er);
		basicEmployee.setBasicdepartmentv1(basicDepartment); //After saving the department object we will get the department id which will be saved to employee object.
		basicEmployee=bv1.saveBasicEmployeeV1(basicEmployee);
				//ber.save(basicEmployee);
		return new ResponseEntity<BasicEmployeeV1>(basicEmployee, HttpStatus.CREATED);
	}

	@GetMapping("/employeev1/filter/{name}")
	public ResponseEntity<List<BasicEmployeeV1>> getEmployeeByDepartment( @PathVariable String name)
	{
		//return null;	
		return new ResponseEntity<List<BasicEmployeeV1>>(bv1.getEmployeeByDeptName(name), HttpStatus.OK);
		
	}
	
	
	

	
	
	
	
}
