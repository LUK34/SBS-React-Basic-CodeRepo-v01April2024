package kw.kng.service;

import java.util.List;

import kw.kng.entity.BasicDepartmentV1;
import kw.kng.entity.BasicDepartmentV3;

public interface BasicDepartmentV3Service 
{
	 List<BasicDepartmentV3> findAllDepartments();
	 BasicDepartmentV3 saveBasicDepartmentV3( BasicDepartmentV3 bv3);
}

//This is for ONE TO ONE BI-DIRECTIONAL MAPPING