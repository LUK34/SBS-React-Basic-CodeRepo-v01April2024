package kw.kng.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import kw.kng.entity.BasicEmployeeV2;
import kw.kng.repository.BasicEmployeeV2Repo;

@Service
public class BasicEmployeeV2ServiceImpl implements BasicEmployeeV2Service 
{
	
	@Autowired
	private BasicEmployeeV2Repo  bev2r;

	@Override
	public BasicEmployeeV2 saveBasicEmployeeV2(BasicEmployeeV2 bv2) 
	{
		return bev2r.save(bv2);
	}

}
