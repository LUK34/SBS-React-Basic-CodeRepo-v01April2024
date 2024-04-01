package kw.kng.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import kw.kng.entity.TableEmployee;

@Repository
public interface TableEmployeeRepo extends JpaRepository<TableEmployee, Long> 
{
	@Query(value="select * from helloworld.tb_employee", nativeQuery=true)
	List<TableEmployee> getTableEmployeeDetails();

	//List<TableEmployee> getAllTableEmployeeRecords();
}
