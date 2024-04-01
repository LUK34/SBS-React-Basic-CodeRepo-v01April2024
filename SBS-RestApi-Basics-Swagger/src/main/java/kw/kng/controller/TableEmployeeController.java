package kw.kng.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import kw.kng.entity.TableEmployee;
import kw.kng.service.TableEmployeeService;

@RestController
public class TableEmployeeController 
{
	@Autowired
	private TableEmployeeService ts;
	
	@PostMapping("/tbemployees")
	public TableEmployee saveEmployeeDetails(@RequestBody TableEmployee employee)
	{
		return ts.saveEmployee(employee);
	}
	
	@GetMapping("/tbemployees")
	public List<TableEmployee> getEmployeeDetails()
	{
		return ts.getTableEmployeeDetails();
	}
	
	
	
}
