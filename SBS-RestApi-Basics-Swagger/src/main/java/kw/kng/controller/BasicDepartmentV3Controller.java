package kw.kng.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import kw.kng.entity.BasicDepartmentV3;
//import kw.kng.repository.BasicDepartmentV3Repo;
import kw.kng.response.DepartmentResponse;
import kw.kng.service.BasicDepartmentV3Service;

@RestController
public class BasicDepartmentV3Controller
{

	@Autowired
	private BasicDepartmentV3Service bdv3s;
	
	//private BasicDepartmentV3Repo brepo;
	
	
	//ONE TO ONE MAPPING BI-DIRECTIONAL
	@GetMapping("/departmentv3")
	public List<DepartmentResponse> getDepartments()	
	{ 
		List<BasicDepartmentV3> depts=bdv3s.findAllDepartments();				//brepo.findAll();
		List<DepartmentResponse> listy= new ArrayList<>();
		depts.forEach(d->
		{
			DepartmentResponse dr=new DepartmentResponse();
			dr.setDepartmentName(d.getName());
			dr.setId(d.getId());
			dr.setEmployeeName(d.getBasicemployeev3().getName());
			listy.add(dr);
		});
		return listy;
	}
}
