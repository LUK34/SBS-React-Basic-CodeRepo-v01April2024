package kw.kng.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import kw.kng.entity.BasicEmployeeV2;

@Repository
public interface BasicEmployeeV2Repo extends JpaRepository<BasicEmployeeV2, Long> 
{

}
