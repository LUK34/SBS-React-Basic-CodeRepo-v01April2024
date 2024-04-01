package kw.kng.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import kw.kng.dao.TableEmployeeDaoImpl;
import kw.kng.entity.TableEmployee;
import kw.kng.repository.TableEmployeeRepo;

@Service
public class TableEmployeeServiceImpl implements TableEmployeeService 
{

	@Autowired
	private TableEmployeeRepo tr;
	
	@Autowired
	private TableEmployeeDaoImpl td;
	
	@Override
	public TableEmployee saveEmployee(TableEmployee employee) 
	{
		return tr.save(employee);
		
	}
	
	@Override
	public List<TableEmployee> getTableEmployeeDetails()
	{
		return tr.getTableEmployeeDetails();
		//	return td.getAllTableEmployeeRecords(); //This is another way to do. But better do the above as there is less code.
	
	}

}
