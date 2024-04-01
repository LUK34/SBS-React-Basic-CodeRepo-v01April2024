package kw.kng.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import kw.kng.entity.BasicDepartmentV4;
import kw.kng.repository.BasicDepartmentV4Repo;

@Service
public class BasicDepartmentV4ServiceImpl  implements BasicDepartmentV4Service
{

	@Autowired
	BasicDepartmentV4Repo bdrepo;

	@Override
	public BasicDepartmentV4 saveBasicDepartmentV4(BasicDepartmentV4 bv4)
	{
		return bdrepo.save(bv4);
	}
	
	
	
	
	
}
