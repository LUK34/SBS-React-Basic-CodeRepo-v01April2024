package kw.kng.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import kw.kng.entity.BasicDepartmentV2;
import kw.kng.repository.BasicDepartmentV2Repo;

@Service
public class BasicDepartmentV2ServiceImpl  implements BasicDepartmentV2Service
{

	@Autowired
	BasicDepartmentV2Repo bdrepo;

	@Override
	public BasicDepartmentV2 saveBasicDepartmentV2(BasicDepartmentV2 bv2)
	{
		return bdrepo.save(bv2);
	}
	
	
	
	
	
}
