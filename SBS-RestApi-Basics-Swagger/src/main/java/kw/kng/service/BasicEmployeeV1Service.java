package kw.kng.service;

import java.util.List;

import kw.kng.entity.BasicEmployeeV1;
import kw.kng.entity.BasicHelloWorld;

public interface BasicEmployeeV1Service 
{
	BasicEmployeeV1 saveBasicEmployeeV1(BasicEmployeeV1 bv1);
	List<BasicEmployeeV1>getEmployeeByDeptName(String name);

}
