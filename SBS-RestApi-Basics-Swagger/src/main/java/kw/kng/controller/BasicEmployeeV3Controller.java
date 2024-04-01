package kw.kng.controller;

import java.util.ArrayList;
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

import kw.kng.entity.BasicDepartmentV3;
import kw.kng.entity.BasicEmployeeV3;
import kw.kng.request.EmployeeRequest;
import kw.kng.response.DepartmentResponse;
import kw.kng.response.EmployeeResponse;
import kw.kng.service.BasicDepartmentV3Service;
import kw.kng.service.BasicEmployeeV3Service;

@RestController
public class BasicEmployeeV3Controller
{

	@Autowired
	private BasicEmployeeV3Service bv3;
	
	
	@Autowired
	private BasicDepartmentV3Service bd3;
	
	
	
	//------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------
	
																			//ONE TO ONE -BI DIRECTIONAL MAPPING
	
	//------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------
	
	
	
	//TYPE: POST, URL: localhost:8080/employeev1
	@PostMapping("/employeev3")
	public ResponseEntity<BasicEmployeeV3> saveEmployeeV1Record(@Valid @RequestBody EmployeeRequest er)
	{
		// BasicDepartmentV1 (Parent) -> BasicEmployeeV1 (Child)
		BasicDepartmentV3 basicDepartment= new BasicDepartmentV3();
		basicDepartment.setName(er.getDepartment()); //we must first save the department object because department is the parent class.
		basicDepartment=bd3.saveBasicDepartmentV3(basicDepartment);
				// bdr.save(basicDepartment);
		
		
		BasicEmployeeV3 basicEmployee = new BasicEmployeeV3(er);
		basicEmployee.setBasicdepartmentv3(basicDepartment); //After saving the department object we will get the department id which will be saved to employee object.
		basicEmployee=bv3.saveBasicEmployeeV3(basicEmployee);
				//ber.save(basicEmployee);
		return new ResponseEntity<BasicEmployeeV3>(basicEmployee, HttpStatus.CREATED);
	}

	@GetMapping("/employeev3/filter/{name}")
	public ResponseEntity<List<BasicEmployeeV3>> getEmployeeByDepartment( @PathVariable String name)
	{
		//return null;	
		return new ResponseEntity<List<BasicEmployeeV3>>(bv3.getEmployeeByDeptName(name), HttpStatus.OK);
		
	}
	
	@GetMapping("/employeev3")
	public List<EmployeeResponse> getEmployeev3()	
	{ 
		List<BasicEmployeeV3> depts=bv3.findAllEmployees();				//brepo.findAll();
		List<EmployeeResponse> listy= new ArrayList<>();
		depts.forEach(d->
		{
			EmployeeResponse er=new EmployeeResponse();
			er.setEmployeeName(d.getName());
			er.setDepartmentName(d.getBasicdepartmentv3().getName());
			er.setId(d.getId());
			listy.add(er);
		});
		return listy;
	}
	

	
	
	
	
}
