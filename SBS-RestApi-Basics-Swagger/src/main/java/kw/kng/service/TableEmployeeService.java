package kw.kng.service;

import java.util.List;

import org.springframework.web.bind.annotation.RequestBody;

import kw.kng.entity.TableEmployee;

public interface TableEmployeeService
{

	public TableEmployee saveEmployee(@RequestBody TableEmployee employee);
	
	public List<TableEmployee> getTableEmployeeDetails();
	
	
}
