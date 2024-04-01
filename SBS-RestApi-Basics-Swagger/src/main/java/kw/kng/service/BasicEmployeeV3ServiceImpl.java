package kw.kng.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import kw.kng.entity.BasicEmployeeV3;
import kw.kng.repository.BasicEmployeeV3Repo;

@Service
public class BasicEmployeeV3ServiceImpl implements BasicEmployeeV3Service
{

	@Autowired
	private BasicEmployeeV3Repo  bev3r;
	
	@Override
	public BasicEmployeeV3 saveBasicEmployeeV3(BasicEmployeeV3 bv3) {
		// TODO Auto-generated method stub
		return bev3r.save(bv3);
	}

	@Override
	public List<BasicEmployeeV3> getEmployeeByDeptName(String name) 
	{
		return bev3r.getEmployeeByDeptName(name);
	}

	@Override
	public List<BasicEmployeeV3> findAllEmployees()
	{
		  Iterable<BasicEmployeeV3> iterableEmps = bev3r.findAll();
		    List<BasicEmployeeV3> emps = new ArrayList<>();
		    iterableEmps.forEach(emps::add);
		    return emps;
	}

}

/*

This occurs because the findAll() method does not return a List<BasicEmployeeV3> directly, but rather an Iterable<BasicEmployeeV3>. 
The Iterable interface is a more general type than List, meaning you cannot directly assign an Iterable to a List variable without explicit conversion.
Here's how you can resolve this issue by converting the Iterable<BasicEmployeeV3> to a List<BasicEmployeeV3>:

In this solution, we create a new ArrayList<BasicEmployeeV3> and then use the forEach method of the Iterable to add each element to the list.
 This way, you're effectively converting the Iterable to a List.

If you're using Spring Data JPA or a similar framework, it's common to encounter this scenario, as repository methods might return Iterable types for methods like findAll().
 Always ensure you convert to the appropriate collection type based on your method's return type requirements

 */


