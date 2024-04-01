package kw.kng.repository;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

import kw.kng.entity.BasicEmployeeV1;

@Repository
public interface BasicEmployeeV1Repo extends PagingAndSortingRepository<BasicEmployeeV1, Long> 
{

	
	 List<BasicEmployeeV1> findBybasicdepartmentv1Name(String name);
	 
	 @Query("FROM BasicEmployeeV1 WHERE basicdepartmentv1.name = :name")
	 List<BasicEmployeeV1>getEmployeeByDeptName(String name);

}
