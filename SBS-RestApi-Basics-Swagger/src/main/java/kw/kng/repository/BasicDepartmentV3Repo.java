package kw.kng.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import kw.kng.entity.BasicDepartmentV3;

@Repository
public interface BasicDepartmentV3Repo extends JpaRepository<BasicDepartmentV3, Long> 
{

}
