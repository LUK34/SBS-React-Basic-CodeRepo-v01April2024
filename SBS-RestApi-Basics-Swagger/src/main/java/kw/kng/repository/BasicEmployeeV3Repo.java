package kw.kng.repository;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

import kw.kng.entity.BasicEmployeeV3;

@Repository
public interface BasicEmployeeV3Repo extends PagingAndSortingRepository<BasicEmployeeV3, Long> 
{

	
	 List<BasicEmployeeV3> findBybasicdepartmentv3Name(String name);
	 
	 @Query("FROM BasicEmployeeV3 WHERE basicdepartmentv3.name = :name")
	 List<BasicEmployeeV3>getEmployeeByDeptName(String name);

}

//This is for ONE TO ONE BI-DIRECTIONAL MAPPING
