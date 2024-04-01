package kw.kng.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import kw.kng.entity.BasicEmployeeV4;
import kw.kng.repository.BasicEmployeeV4Repo;

@Service
public class BasicEmployeeV4ServiceImpl implements BasicEmployeeV4Service 
{
	
	@Autowired
	private BasicEmployeeV4Repo  bev2r;

	@Override
	public BasicEmployeeV4 saveBasicEmployeeV4(BasicEmployeeV4 bv4) 
	{
		return bev2r.save(bv4);
	}
	
	@Override
	public List<BasicEmployeeV4> findByBasicEmployeeV4()
	{
		return bev2r.findAll();
	}

}
