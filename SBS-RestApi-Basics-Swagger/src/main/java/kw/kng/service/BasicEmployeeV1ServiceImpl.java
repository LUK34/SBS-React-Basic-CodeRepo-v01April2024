package kw.kng.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import kw.kng.entity.BasicEmployeeV1;
import kw.kng.repository.BasicEmployeeV1Repo;

@Service
public class BasicEmployeeV1ServiceImpl implements BasicEmployeeV1Service
{

	@Autowired
	private BasicEmployeeV1Repo  bev1r;
	
	@Override
	public BasicEmployeeV1 saveBasicEmployeeV1(BasicEmployeeV1 bv1) {
		// TODO Auto-generated method stub
		return bev1r.save(bv1);
	}

	@Override
	public List<BasicEmployeeV1> getEmployeeByDeptName(String name) 
	{
		return bev1r.getEmployeeByDeptName(name);
	}

}
