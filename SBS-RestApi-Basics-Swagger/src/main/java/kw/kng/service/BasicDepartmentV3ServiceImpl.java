package kw.kng.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import kw.kng.entity.BasicDepartmentV3;
import kw.kng.repository.BasicDepartmentV3Repo;

@Service
public class BasicDepartmentV3ServiceImpl implements BasicDepartmentV3Service 
{
	
	//------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------
	
															//ONE TO ONE BIDIRECTIONAL MAPPING

//------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------

	
	
	@Autowired
	private BasicDepartmentV3Repo bdv3r;


	@Override
	public List<BasicDepartmentV3> findAllDepartments() 
	{
		List<BasicDepartmentV3> depts= bdv3r.findAll();
		return depts;
	}


	@Override
	public BasicDepartmentV3 saveBasicDepartmentV3(BasicDepartmentV3 bv3) {
		// TODO Auto-generated method stub
		return bdv3r.save(bv3);
	}
	

}
