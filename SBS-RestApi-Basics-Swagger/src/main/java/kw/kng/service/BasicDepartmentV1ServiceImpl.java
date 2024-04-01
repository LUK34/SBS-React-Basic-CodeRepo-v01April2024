package kw.kng.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import kw.kng.entity.BasicDepartmentV1;
import kw.kng.repository.BasicDepartmentV1Repo;

@Service
public class BasicDepartmentV1ServiceImpl implements BasicDepartmentV1Service 
{
	
	//------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------
	
															//ONE TO MANY UNIDIRECTIONAL MAPPING

//------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------

	
	
	@Autowired
	private BasicDepartmentV1Repo bdv1r;

	@Override
	public BasicDepartmentV1 saveBasicDepartmentV1(BasicDepartmentV1 bv1) 
	{
		return bdv1r.save(bv1);
	}
	

}
