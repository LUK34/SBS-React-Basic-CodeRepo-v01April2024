package kw.kng.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import kw.kng.entity.BasicDepartmentV1;

@Repository
public interface BasicDepartmentV1Repo extends JpaRepository<BasicDepartmentV1, Long> 
{

}
