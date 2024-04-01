package kw.kng.service;

import java.util.List;

import kw.kng.entity.BasicEmployeeV3;

public interface BasicEmployeeV3Service 
{
	BasicEmployeeV3 saveBasicEmployeeV3(BasicEmployeeV3 bv3);
	List<BasicEmployeeV3>getEmployeeByDeptName(String name);
	
	List<BasicEmployeeV3> findAllEmployees();

}
